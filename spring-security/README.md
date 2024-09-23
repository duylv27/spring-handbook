# Spring Security

## Introduction
- Explore basic components that we need to be aware when working with **Spring Security**.
- Explore components used for custom **Spring Security** for your application.
- Visualize how your request go through **Spring Security** components.
- Best practices for securing your Spring applications.

## Basic Components

### Filter
In Spring Security, the **Filter** is similar to the concept used in traditional Java web applications 
(e.g., JSP Servlet). Filters act as a layer to process requests before they reach the controller, performing tasks such as:
- Security checks.
- Request header validation.
- Input validation.

Example for simple filter to check if request header is included `Authorization` or not, if not return `401`:
```java
public class AuthRequestFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request, 
            ServletResponse response, 
            FilterChain chain
    ) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String authHeader = httpRequest.getHeader("Authorization");
            if (authHeader == null || authHeader.isEmpty()) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 status code
                httpResponse.getWriter().write("Unauthorized: Missing Authorization header");
                return;
            }
            
            // here, get and validate JWT as your business
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
```
For simple requirement, implementing a filter as above is enough.

### Filter Chain
Now, what if you want to add more filter, you need to be aware of concept called **Filter Chain**.

The **Filter Chain** is _**a sequence of filters**_ that a request must pass through. 
Each filter in the chain performs a specific security task. 
The chain structure allows Spring Security to apply multiple filters (e.g., authentication, authorization, CSRF protection) 
in a predefined order.

Here is another filter which check if header include `X-Tenant-Id` or not. If yes check current user 
(expected to retrieve from `AuthRequestFilter` after processing JWT Token) with correspond `X-Tenant-Id`
```java
public class TenantFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest, 
            ServletResponse servletResponse, 
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String tenantId = request.getHeader("X-Tenant-Id"); 
        boolean hasAccess = isUserAllowed(tenantId); 
        if (hasAccess) {
            filterChain.doFilter(request, response); 
            return;
        }
        throw new AccessDeniedException("Access denied"); 
    }

}
```
[Reference: Adding a Custom Filter to the Filter Chain](https://docs.spring.io/spring-security/reference/servlet/architecture.html#adding-custom-filter)

So, we have 2 filters `AuthRequestFilter`, and `TenantFilter`.

How do make sure it run in correct order? 

In traditional ways
1. Configure it on `web.xml`:
```text
<filter>
    <filter-name>authRequestFilter</filter-name>
    <filter-class>com.filter.AuthRequestFilter</filter-class>
</filter>

<filter>
    <filter-name>tenantFilter</filter-name>
    <filter-class>com.filter.TenantFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>authRequestFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>

<filter-mapping>
    <filter-name>tenantFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
2. Programmatically configuration:
```java
@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Register AuthRequestFilter first
        Dynamic authFilter = context.addFilter("authRequestFilter", new AuthRequestFilter());
        authFilter.addMappingForUrlPatterns(null, false, "/*");

        // Register TenantFilter second
        Dynamic tenantFilter = context.addFilter("tenantFilter", new TenantFilter());
        tenantFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
```

The more filters you need, the more configurations and management are required. 
Then Spring Security comes in, providing additional functionalities and support to streamline this process.

### DelegatingFilterProxy
### SecurityFilterChain