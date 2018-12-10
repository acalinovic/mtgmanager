/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.utils;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import be.bbs.mtgmanager.model.entity.*;
public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
            	Configuration configuration = new Configuration();            	
            	
            	configuration
            	.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
            	.setProperty("hibernate.connection.password", "albkeelboz")
            	.setProperty("hibernate.connection.url", "jdbc:postgresql://192.168.0.90:5432/mtgmanagerv2")
            	.setProperty("hibernate.connection.username", "postgres")
            	.setProperty("hibernate.default_catalog", "pg_catalog")
            	.setProperty("hibernate.default_schema", "mtgmanagerv2")
            	.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            	/*
*/
            	configuration.configure();
            	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            	.applySettings(configuration.getProperties()).build();

            	sessionFactory = configuration
            	.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
