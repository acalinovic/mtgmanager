/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.mtgmanager.repositoy;

import be.bbs.bbsfx.repository.GenericRepositoryInterface;
import be.bbs.bbsfx.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.query.Query;
import org.hibernate.query.spi.NamedQueryRepository;

import be.bbs.mtgmanager.model.entity.*;

/**
 *
 * @author boris
 * @param <T>
 */
@SuppressWarnings("unused")
public class GenericRepositoryImpl<T> implements GenericRepositoryInterface<T> {

	private static final Logger LOG = Logger.getLogger(GenericRepositoryImpl.class.getName());

	private void log(Level level, String message, Object object) {
		LOG.log(level, "{0} " + message + " :: {1}", new Object[] { object.getClass().getName(), object.toString() });

	}
	
	public GenericRepositoryImpl() {
	}

	public T execute(String operation, T entity) {
		Session session = DatabaseHelper.getSession();
		Transaction tx = session.beginTransaction();
		System.out.println(tx);
		try {
			switch (operation) {
			case "saveOrUpdate":
				session.saveOrUpdate(entity);
				tx.commit();
				session.close();
				break;
			case "delete":
				session.delete(entity);
				tx.commit();
				session.close();
				break;
			default:
				tx.rollback();
				break;
			}
			//log(Level.INFO, operation + " Successfuly proccessed", session);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			log(Level.SEVERE, operation + " failed, persistence context left as is", session);
			return entity;
		}
	}

	@Override
	public T save(T entity) {
		Session session = DatabaseHelper.getSession();

		try {
			session.getTransaction().begin();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();

			LOG.log(Level.INFO, "{0} successfully persisted :: {1}",
					new Object[] { entity.getClass().getName(), entity.toString() });
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "{0} PERSIST FAILED!!! :: {1}",
					new Object[] { entity.getClass().getName(), entity.toString() });
			e.printStackTrace();
		}

		return entity;
	}

	@Override
	public Boolean delete(T entity) {
		Session session = DatabaseHelper.getSession();

		try {
			Transaction tx = session.beginTransaction();
			session.delete(entity);
			session.close();
			tx.commit();
			LOG.log(Level.FINEST, "{0} successfully persisted :: {1}",
					new Object[] { entity.getClass().getName(), entity.toString() });
		} catch (HibernateException e) {
			LOG.log(Level.FINEST, "{0} failed to delete :: {1}",
					new Object[] { entity.getClass().getName(), entity.toString() });
			return false;
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> findBy(Class entityClass, String field, String value) {
		try {
			List<T> result;
			Session session = DatabaseHelper.getSession();
			String querry = "From " + entityClass.getSimpleName() + " WHERE" + field + " =  " + value ;
			result =  session.createQuery(querry).getResultList();
			session.close();

			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T findOneBy(Class entityClass, String field, Object value) {
		try {
			Session session = DatabaseHelper.getSession();
			String query = "From " + entityClass.getSimpleName() + " WHERE " + field + " =:value" ;
			Object result = session.createQuery(query)
					.setParameter("value", value)
					.getSingleResult();
			session.close();

			return (T) result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Class classname, int id) {
		try {
			Session session = DatabaseHelper.getSession();
			String querry = "From " + classname.getSimpleName() + " WHERE id = :id" ;
			Object entity = session.createQuery(querry).setParameter("id", id).getSingleResult();
			session.close();
			return (T) entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class entityClass) {
		try {
			Session session = DatabaseHelper.getSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(entityClass);
			Root<T> root = query.from(entityClass);
			query.select(root);
			Query<T> q=session.createQuery(query);
			List<T> entities=q.getResultList();
			for (T entity : entities) {
			   System.out.println( entity.toString());
			}
			return entities;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
