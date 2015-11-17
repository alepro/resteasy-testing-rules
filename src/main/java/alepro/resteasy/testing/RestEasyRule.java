package alepro.resteasy.testing;

import java.util.Collection;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.rules.ExternalResource;

/**
 *
 * @author alepro
 */
public class RestEasyRule extends ExternalResource {
    public final Dispatcher dispatcher;

    public RestEasyRule() {
        this.dispatcher = MockDispatcherFactory.createDispatcher();
    }
    
    public static RestEasyRule newRule() {
        return new RestEasyRule();
    }
    
    public RestEasyRule forResourses(Collection<Object> resourses) {
        resourses.forEach(t -> dispatcher.getRegistry().addSingletonResource(t));
        return this;
    } 
    
    public RestEasyRule withProviders(Collection<Class> providers) {
        final ResteasyProviderFactory providerFactory = dispatcher.getProviderFactory();
        providers.forEach(t -> providerFactory.registerProvider(t));
        return this;
    }
    
    public RestEasyRule havingCorsFilter(CorsFilter filter) {
        final ResteasyProviderFactory factory = dispatcher.getProviderFactory();
        factory.getContainerRequestFilterRegistry().registerSingleton(filter);
        factory.getContainerResponseFilterRegistry().registerSingleton(filter);
        return this;
    }    

}
