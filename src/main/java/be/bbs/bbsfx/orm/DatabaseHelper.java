/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.orm;

import be.bbs.bbsfx.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author boris
 */
public class DatabaseHelper {

    public final static SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    
    public static Session getSession() {
        Session session = SESSION_FACTORY.openSession();
        return session;
    }
    public static void disconnect() {
        try {
            SESSION_FACTORY.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
