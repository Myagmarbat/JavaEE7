/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs.cs544.mg.day12.ejb;

import edu.mum.cs.cs544.mg.day12.entity.Product;
import edu.mum.cs.cs544.mg.day12.interceptor.MyInterceptor;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author mmmmm
 */
@Stateless
@LocalBean
public class ProductEJB {
    @PersistenceContext
    private EntityManager em;    
    
    public List<Product> findProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }

    
    public Product getProductById(Integer id) {
        return em.find(Product.class, id);
    }

    @Interceptors(MyInterceptor.class)
    public Integer doCreateAction(Product product) {
        em.persist(product);
        return product.getId();
    }

    @Interceptors(MyInterceptor.class)
    public void doUpdateAction(Integer id, Product p) {
        Product product = em.find(Product.class, id);
        product.setName(p.getName());
        product.setQuantity(p.getQuantity());
    }

    @Interceptors(MyInterceptor.class)
    public Integer doDeleteAction(Integer id) {
        Product product = em.find(Product.class, id);
        int removeId = product.getId();
        em.remove(product);
        return removeId;
    }

}
