package com.flaviojmendes.zapperson.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.flaviojmendes.zapperson.service.CompanyService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class JWTAuthorizationFilter extends GenericFilterBean {

    public JWTAuthorizationFilter() {
        super();
    }

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_USER_STRING = "UserMail";



    private CompanyService companyService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader(HEADER_STRING);
        String userHeader = req.getHeader(HEADER_USER_STRING);

        if (header == null || userHeader == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        if(companyService==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            companyService = webApplicationContext.getBean(CompanyService.class);
        }

        boolean authorized = DigestUtils.md5DigestAsHex(userHeader.getBytes()).equals(header.replace(TOKEN_PREFIX, ""));

        if(!authorized) {
            chain.doFilter(req, res);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication( new UsernamePasswordAuthenticationToken(userHeader, null, new ArrayList<>()));
        chain.doFilter(req, res);

    }
}