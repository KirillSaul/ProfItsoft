package ua.fourthAssignment.spring.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Objects.nonNull;

public class Security extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getRequestURI().equals("/login") || nonNull(req.getSession().getAttribute("username"))) {
            super.doFilter(req, res, chain);
        } else {
            res.sendRedirect("/login");
        }
    }
}
