/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.bbsfx.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author boris
 * @param <T>
 */
 public interface GenericRepositoryInterface<T> {
	 public static final String SAVE_OR_UPDATE = "saveOrUpdate";

    public T save(T entity);
    public Boolean delete(T entity);
    public T findById(Class entityClass, int id);
    public List<T> findBy(Class entityClass, String field, String value);
    public T findOneBy(Class entityClass, String field, String value);
    public List<T> findAll(Class entityClass);
}
