/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.filter;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Bronius
 */
@WebFilter(filterName = "EMFilter", urlPatterns = {"/*"})
public class EMFilter implements Filter {

    private EntityManagerFactory emf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        emf = Persistence.createEntityManagerFactory("WebServisJSONPU");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager em = emf.createEntityManager();
        request.setAttribute("em", em);

        try {
            chain.doFilter(request, response);
        } finally {
            em.close();
            request.removeAttribute("em");
        }
    }

    @Override
    public void destroy() {
        emf.close();
    }

}
