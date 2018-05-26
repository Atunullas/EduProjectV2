package com.eduproject.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.eduproject.dto.QuestionDTO;
import com.eduproject.model.Question;

@Repository("QuestAnsDao")
@SuppressWarnings("unchecked")
public class QuestAnsDao extends HibernateDaoSupport {

	@Autowired
	public void settingSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public List<Question> performFetchAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		List<Question> result = (List<Question>) getHibernateTemplate().findByCriteria(criteria);
		return result;
	}

	public QuestionDTO performFetchById(Integer questId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Question.class);
		criteria.add(Restrictions.eq("id", Integer.valueOf(questId)));
		List<QuestionDTO> result = (List<QuestionDTO>) getHibernateTemplate().findByCriteria(criteria);
		if (result.size() > 1) {
			return result.get(0);
		}
		return null;
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
