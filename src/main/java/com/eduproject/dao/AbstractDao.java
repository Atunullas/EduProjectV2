package com.eduproject.dao;

public abstract class AbstractDao<T>  {
/*
	public List<T> performFetch(Class className) {
		DetachedCriteria criteria = DetachedCriteria.forClass(className);
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	public void performDelete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	public void performSave(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void performUpdate(Object entity) {
		getHibernateTemplate().update(entity);
	}
	*/
}