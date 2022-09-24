package SpringSecurityUdemy.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


//These custom filters can also extend GenericFilterBean to have access to generic properties and config from web.xml
// OncePerRequestFilter can also be extended
public class AuthoritiesLoggingAtFilter implements Filter {
    /*
    This should not be used as the order of execution is not guaranteed, this doesnt replace current filters.
    This is only shown for demonstration purposes and only logs a message into console.
     */
    private final Logger LOG =
            Logger.getLogger(AuthoritiesLoggingAtFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.info("Authentication Validation is in progress");
        chain.doFilter(request, response);
    }

}