package com.eduproject.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.eduproject.model.Personality;

@Repository("PersonalityDao")
@SuppressWarnings("unchecked")
public class PersonalityDao extends HibernateDaoSupport {

	@Autowired
	public void settingSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public List<Personality> performFetchAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Personality.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		List<Personality> result = (List<Personality>) getHibernateTemplate().findByCriteria(criteria);
		return result;
	}

	public List<Personality> performFetchWithLimit(Integer limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Personality.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand() limit " + limit));
		List<Personality> result = (List<Personality>) getHibernateTemplate().findByCriteria(criteria);
		return result;
	}

	public void performDelete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	public void performSave(Object entity) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(entity);
	}

	public void performUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public Personality performFetchById(Integer personId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Personality.class);
		criteria.add(Restrictions.eq("personId", personId));
		List<Personality> result = (List<Personality>) getHibernateTemplate().findByCriteria(criteria);
		if (result.size() == 0) {
			return result.get(0);
		}
		return null;
	}

}
