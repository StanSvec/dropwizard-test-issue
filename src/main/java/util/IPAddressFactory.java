package util;

import com.google.common.base.Strings;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.glassfish.hk2.api.Factory;

/**
 * Return IP address as string value based on CloudFront HTTP Header.
 */
public class IPAddressFactory implements Factory<IPAddress> {

    private final ContainerRequestContext ctx;

    @Inject
    public IPAddressFactory(ContainerRequestContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public IPAddress provide() {
        return provide(ctx);
    }

    public static IPAddress provide(ContainerRequestContext ctx) {
        String ipAddress = ctx.getHeaderString("X-Forwarded-For"); // get IPv4 address from header
        if (Strings.isNullOrEmpty(ipAddress)) {
            return IPAddress.localhost();
        }

        return new IPAddress(ipAddress);
    }

    @Override
    public void dispose(IPAddress s) {}
}
