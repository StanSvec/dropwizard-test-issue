package resource;


import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import jakarta.ws.rs.core.Response;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import resources.TestResource;
import util.IPAddress;
import util.IPAddressFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ResourceTest {

    private static final ResourceExtension EXT = ResourceExtension.builder()
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .addProvider(new AbstractBinder() {
                @Override
                protected void configure() {
                    bindFactory(IPAddressFactory.class).to(IPAddress.class).in(RequestScoped.class);
                }
            })
            .addResource(new TestResource())
            .build();

    @Test
    void getPersonNotFound() {
        Response response = EXT.target("/api").request().get();
        assertEquals(response.getStatusInfo().getStatusCode(), Response.Status.OK.getStatusCode());
    }
}
