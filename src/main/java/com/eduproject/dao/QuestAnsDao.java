package com.eduproject.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.eduproject.model.Question;
import com.eduproject.model.Subject;

@Repository("QuestAnsDao")
@SuppressWarnings("unchecked")
public class QuestAnsDao extends HibernateDaoSupport {

	@Autowired
	public void settingSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public List<Question> performFetchAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		List<Question> result = (List<Question>) getHibernateTemplate().findByCriteria(criteria);
		return result;
	}

	public Question performFetchById(Long questId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		criteria.add(Restrictions.eq("questionId", questId));
		List<Question> result = (List<Question>) getHibernateTemplate().findByCriteria(criteria);
		if (result.size() == 1) {
			return result.get(0);
		}
		return null;
	}

	public List<Question> performFetchWithLimit(Integer limit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand() LIMIT " + limit));
		List<Question> result = (List<Question>) getHibernateTemplate().findByCriteria(criteria);
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

	public List<Subject> performFetchAllSubjects() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subject.class);
		criteria.setProjection(Projections.distinct(Projections.property("subjectName")));
		List<Subject> result = (List<Subject>) getHibernateTemplate().findByCriteria(criteria);
		return result;
	}

}
