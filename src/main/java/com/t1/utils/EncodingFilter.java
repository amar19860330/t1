/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t1.utils;

/**
 *
 * @author amar
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class EncodingFilter extends HttpServlet implements Filter {

    /**
     *
     */
    private static final long serialVersionUID = 2895948233409664165L;
    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            String encoding = this.encoding;
            if (encoding != null) {
                request.setCharacterEncoding(encoding);
                response.setCharacterEncoding(encoding);
            }
        }
        chain.doFilter(request, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");

    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }
}
