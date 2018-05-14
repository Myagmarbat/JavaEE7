/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs.cs544.mg.day12.interceptor;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author mmmmm
 */
@Interceptor
public class MyInterceptor implements Serializable {
    private Logger logger = Logger.getLogger("edu.mum.cs.cs544.mg.day12");

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("before calling method :"
                + context.getMethod().getName());

        Object[] params = context.getParameters();
        for (Object param : params) {
            if( param != null)
                System.out.println("PARAM " + param.toString());
        }

        Object result = context.proceed();


        System.out.println("after calling method :"
                + context.getMethod().getName());

        return result;

    }
}
