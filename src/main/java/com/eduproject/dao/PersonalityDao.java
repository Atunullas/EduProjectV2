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

	public List<Personality> performFetchWithLimit(Long limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Personality.class);
		if (limit == 0) {
			criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		} else {
			criteria.add(Restrictions.sqlRestriction("1=1 order by rand() limit " + limit));
		}
		List<Personality> result = (List<Personality>) getHibernateTemplate().findByCriteria(criteria);
		return result;
	}

	public void performDelete(Long personalityId) {
		Personality personality = performFetchById(personalityId);
		if (personality != null) {
			getHibernateTemplate().delete(personality);
		}
	}

	public void performSave(Personality personality) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(personality);
	}

	public void performUpdate(Personality personality) {
		getHibernateTemplate().saveOrUpdate(personality);
	}

	public Personality performFetchById(Long personId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Personality.class);
		criteria.add(Restrictions.eq("personId", personId));
		List<Personality> result = (List<Personality>) getHibernateTemplate().findByCriteria(criteria);
		if (result.size() == 1) {
			return result.get(0);
		}
		return null;
	}

}
