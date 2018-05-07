package com.wolf.cloud.common.mvc;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * @author wolf
 */
@Component
public class MicRateLimitZuulFilter extends ZuulFilter {

    @Autowired
    SystemPublicMetrics systemPublicMetrics;

    private Map<String, RateLimiter> map = Maps.newConcurrentMap();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 这边的order一定要大于org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter的order
        // 也就是要大于5
        // 否则，RequestContext.getCurrentContext()里拿不到serviceId等数据。
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        Collection<Metric<?>> metrics = systemPublicMetrics.metrics();
        Optional<Metric<?>> freeMemoryMetric = metrics.stream().filter(t -> "mem.free".equals(t.getName())).findFirst();
        if (!freeMemoryMetric.isPresent()) {
            return true;
        }
        long freeMemory = freeMemoryMetric.get().getValue().longValue();
        return freeMemory < 1024000L;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletResponse response = context.getResponse();

            String key = null;
            // 对配置serviceId格式的路由
            String serviceId = (String) context.get(SERVICE_ID_KEY);
            System.out.println("serviceId : " + serviceId);
            if (StringUtils.isNotBlank(serviceId)) {
                key = serviceId;
                map.putIfAbsent(serviceId, RateLimiter.create(1));
            } else {
                URL routeHost = context.getRouteHost();
                if (routeHost != null) {
                    String url = routeHost.toString();
                    System.out.println("url : " + url);
                    key = url;
                    map.putIfAbsent(url, RateLimiter.create(2));
                }
            }
            RateLimiter rateLimiter = map.get(key);
            if (!rateLimiter.tryAcquire()) {
                HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.setStatus(httpStatus.value());
                response.getWriter().append(httpStatus.getReasonPhrase());

                context.setSendZuulResponse(false);

                throw new ZuulException(httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
            }
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }

        return null;
    }
}
