package com.rogerr.custom.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.rogerr.custom.model.Subscriber;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;
	

//    @Override
//    public User findById(Long id) {
//    	String hql = "FROM Subscriber u WHERE u.id = :id"; 
//		Query query = sessionFactory.openSession().createQuery(hql);
//		query.setParameter("id", id);
//		return (User) query.uniqueResult();
//    }
    
    @Override
	public Subscriber findById(Long id) {
		Subscriber user = null;
		String hql = "FROM Subscriber where id = :id";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		user = (Subscriber) query.getSingleResult();
		
		return user;
	}
    
    @Override
    public Subscriber findByUsername(String username) {
        String hql = "FROM Subscriber p WHERE p.username = :username"; // HQL Query
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setParameter("username", username);
        return (Subscriber) query.getSingleResult();
    }

    @Override
    public void saveUser(Subscriber user) {
    	sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(Subscriber user) {
    	sessionFactory.getCurrentSession().save(user);
    }

    @Override
	public void deleteUserById(Long id) {
		Session currentSession = getSession();
		Subscriber user = currentSession.byId(Subscriber.class).load(id);
		currentSession.delete(user);
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Subscriber> findAllUsers() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Subscriber.class); // Criteria Query
        return (List<Subscriber>) criteria.list();
    }

    @Override
    public List<Subscriber> deleteAllUsers() {
        sessionFactory.openSession().createQuery("DELETE FROM Subscriber").executeUpdate();
        return Collections.emptyList();
    }
    
    @Override
	public Boolean isUserExist(Subscriber user) {
        Query query = sessionFactory.openSession().createQuery("FROM Subscriber p WHERE p.username = :username");
    	query.setParameter("username", user.getUsername());
    	List<?> pList = query.getResultList();
    	if (!pList.isEmpty()) {
    		return true;
    	}
    	return false;
    }
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}

