/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.rest;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import lt.bit.data.Person;

/**
 *
 * @author Bronius
 */
@Path("person")
public class PersonREST {

    @Context
    private HttpServletRequest request;

    @GET
    @Produces("application/json")
    public List<Person> getList(@QueryParam("fn") String firstName) {
        EntityManager em = (EntityManager) request.getAttribute("em");

        Query q;
        if (firstName == null) {
            q = em.createQuery("select p from Person p order by p.firstName");
        } else {
            //q = em.createQuery("select p from Person p where p.firstName like : fn");
            //q = em.createQuery("select p from Person p where p.firstName like : fn or p.lastName like: fn order by p.firstName");
            q = em.createQuery("select p from Person p where p.firstName like : fn or p.lastName like: fn order by p.firstName");
            q.setParameter("fn", "%" + firstName + "%");
        }
        return q.getResultList();

        /*
        public List<Person> getList() {
        EntityManager em = (EntityManager)request.getAttribute("em");
        Query q = em.createQuery("select p from Person p");
        return q.getResultList();
         */
        //List<Person> l = new ArrayList();
//        Person p = new Person("Jonas", "Mariaucius");
//        l.add(p);
//        p = new Person("Maryte", "Mauriseviciene");
//
//        l.add(p);
//        return l;
    }

    @POST
    @Consumes("application/json")
    public Person add(Person p) {
        p.setId(null);
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(p);
        } catch (RuntimeException ex) {
            tx.rollback();
            tx = null;
            throw ex;
        } finally {
            if (tx != null) {
                tx.commit();
            }
        }
        return p;
    }
    /*
    //@path nereikia
    @POST
    @Consumes("application/json")
    //@Produces("application/json")
    public Person add(Person p){
        p.setId(null); 
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            em.persist(p);
        } catch (RuntimeException ex) {
            tx.rollback();
            tx = null;
            throw ex;
        } finally {
            if (tx != null) {
                tx.commit();
            }
        }
        return p;
    }
    */
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Person remove(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Person p = em.find(Person.class, id);
        try {
            if (p != null) {
                em.remove(p);
            }
        } catch (RuntimeException ex) {
            tx.rollback();
            tx = null;
            throw ex;
        } finally {
            if (tx != null) {
                tx.commit();
            }
        }
        return p;

    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Person getOne(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        return em.find(Person.class, id);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
