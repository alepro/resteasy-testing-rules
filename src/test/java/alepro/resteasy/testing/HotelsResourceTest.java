package alepro.resteasy.testing;

import static com.google.common.collect.Lists.newArrayList;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Test;
import org.junit.Rule;

/**
 *
 * @author alepro
 */
public class HotelsResourceTest {

    @Rule
    public RestEasyRule rest = RestEasyRule.newRule()
            .forResourses(newArrayList(new HotelsResource()))
            .havingCorsFilter(new CorsFilterBuilder()
                    .withAllowedMethods("GET, POST")
                    .withAllowedOrigins(newArrayList("http://host1.com", "http://host2.com"))
                    .build());
    
    
    @Test
    public void shouldBeOK() throws URISyntaxException {
        MockHttpResponse response = new MockHttpResponse();
        final MockHttpRequest req = MockHttpRequest.get("/hotels");
        req.header("Origin", "http://host1.com");
        rest.dispatcher.invoke(req, response);
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    public void shouldForbidRequest() throws URISyntaxException {
        MockHttpResponse response = new MockHttpResponse();
        final MockHttpRequest req = MockHttpRequest.get("/hotels");
        req.header("Origin", "http://example.com");
        rest.dispatcher.invoke(req, response);
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_FORBIDDEN);
    }
    
    @Test
    public void shouldForbidDeleteRequest() throws URISyntaxException {
        MockHttpResponse response = new MockHttpResponse();
        final MockHttpRequest req = MockHttpRequest.delete("/hotels");
        req.header("Origin", "http://host1.com");
        rest.dispatcher.invoke(req, response);
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

}
