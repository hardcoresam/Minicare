package com.minicare.filter;

import com.minicare.model.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
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
        if(member != null) {
            //request.setAttribute("success","You are already Logged in");  //Put this in Homepage in the starting.

            filterConfig.getServletContext().getRequestDispatcher("/jsp/"+ member.getType().toString().toLowerCase() +"/HomePage"+ member.getType().toString().toLowerCase() +".jsp").forward(request,response);
        }
        else {
            request.setAttribute("loginError1","Please Login");
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {}
}
