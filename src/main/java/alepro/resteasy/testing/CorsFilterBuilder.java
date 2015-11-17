package alepro.resteasy.testing;

import java.util.Collection;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

/**
 *
 * @author alepro
 */
public class CorsFilterBuilder {
    private final CorsFilter filter;

    public CorsFilterBuilder() {
        this.filter = new CorsFilter();
    }

    public CorsFilter build() {
        return filter;
    }

    public CorsFilterBuilder withAllowedOrigins(Collection<String> urls) {
        filter.getAllowedOrigins().addAll(urls);
        return this;
    }

    public CorsFilterBuilder withAllowedMethods(String methods) {
        filter.setAllowedMethods(methods);
        return this;
    }
}
