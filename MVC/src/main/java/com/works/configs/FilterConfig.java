package com.works.configs;

import com.works.entities.Admin;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String userAgent = req.getHeader("user-agent");
        String ip = req.getRemoteAddr();
        System.out.println(url + " - " + userAgent + " - " + ip);


        String[] freeUrls = {"/", "/login"};
        boolean status = true;
        for (String item : freeUrls) {
            if (item.equals(url)) {
                status = false;
            }
        }

        if (status) {
            // session control
            boolean statusLogin = req.getSession().getAttribute("admin") == null;
            if(statusLogin) {
                // session empty!
                res.sendRedirect("/");
            }else {
                Admin adminObj = (Admin) req.getSession().getAttribute("admin");
                req.setAttribute("admin", adminObj);
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }


    }

}
