package com.eduproject.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.eduproject.model.QuestAns;

@Repository("QuestAnsDao")
public class QuestAnsDao extends HibernateDaoSupport {

	@Autowired
	public void settingSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<QuestAns> performFetch(Integer questId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(QuestAns.class);
		criteria.add(Restrictions.eq("id", Integer.valueOf(questId)));
		List<?> result = getHibernateTemplate().findByCriteria(criteria);
		System.out.println("------------------------------------->" + result.size());
		return (List<QuestAns>) result;
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

}
