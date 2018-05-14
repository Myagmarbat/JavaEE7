/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs.cs544.mg.day12.mb;

import edu.mum.cs.cs544.mg.day12.ejb.ProductEJB;
import edu.mum.cs.cs544.mg.day12.entity.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;

/**
 *
 * @author mmmmm
 */
@Named(value = "indexMB")
@RequestScoped

public class IndexMB {    
    private Integer id;
    private String name;
    private Integer quantity;
    
    @EJB
    private ProductEJB productEJB;
    
    public IndexMB() {
        
    }
    
    @Transactional
    public String doCreateAction(){        
        Product product = new Product(name, quantity);        
        Integer id = productEJB.doCreateAction(product);
        return "index";
    }
    
    @Transactional
    public void doReadAction(int id){
        Product product = productEJB.getProductById(id);
        this.setProductToMB(product);
    }
    
    public String goUpdatePage(int id){                
        Product product = productEJB.getProductById(id);
        this.setProductToMB(product);
        return "update";
    }
    
    @Transactional
    public String doUpdateAction(){              
        productEJB.doUpdateAction(id, this.getProductFromMB());
        return "index";
    }
   
    public String goDeletePage(int id){
        Product product = productEJB.getProductById(id);
        this.setProductToMB(product);        
        return "delete";
    }
    
//    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String doDeleteAction(){
        Integer removedId = productEJB.doDeleteAction(id);  
        return "index";
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public void setProductToMB(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
    }
    
    public Product getProductFromMB(){
        Product product = new Product(name, quantity);        
        return product;
    }
    
    
    public List<Product> findAll(){                
        return productEJB.findProducts();
    }
      
    
}
