package org.example.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class ExpensesFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var context = servletRequest.getServletContext();
        context.log("[ExpensesFilter] doFilter");

        int freemoney = (int)context.getAttribute("freeMoney");
        for (var k : servletRequest.getParameterMap().keySet()) {
            freemoney -= Integer.parseInt(servletRequest.getParameter(k));
            if (freemoney < 0 ){
                servletResponse.getWriter().println("Not enough money");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
