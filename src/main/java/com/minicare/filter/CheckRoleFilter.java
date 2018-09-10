package com.minicare.filter;

import com.minicare.model.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckRoleFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        boolean error = false;
        if(request.getRequestURI().contains("/seeker")) {
            if(member == null || member.getType() != Member.MemberType.SEEKER) {
                error = true;
            }
        }
        if(request.getRequestURI().contains("/sitter")) {
            if(member == null || member.getType() != Member.MemberType.SITTER) {
                error = true;
            }
        }
        if(request.getRequestURI().contains("/member")) {
            if(member == null) {
                error = true;
            }
        }

        if(error) {
            filterConfig.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request,response);
        }
        else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {}
}