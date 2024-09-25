package org.example.odc.web.filter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.odc.web.filter.wrapper.ResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseFormattingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        if (isSwaggerPath(requestURI)) {
            chain.doFilter(request, response);
        } else {
            ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);

            chain.doFilter(httpRequest, responseWrapper);

            String originalResponseBody = responseWrapper.getCaptureAsString();

            String formattedResponseBody = formatResponse(originalResponseBody);

            httpResponse.setContentType("application/json");
            PrintWriter out = httpResponse.getWriter();
            out.write(formattedResponseBody);
            out.flush();
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }

    private boolean isSwaggerPath(String requestURI) {
        return requestURI.startsWith("/api/v1/swagger-ui/") ||
                requestURI.startsWith("/api/v1/v3/api-docs") ||
                requestURI.startsWith("/api/v1/swagger-ui.html") ||
                requestURI.startsWith("/api/v1/swagger-resources/");
    }

    private String formatResponse(String originalResponseBody) {
        return String.format("{ \"status\": \"success\", \"data\": %s }", originalResponseBody);
    }
}
