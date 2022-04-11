package com.rogerr.custom.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.rogerr.custom.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findById(Long id) {
    	String hql = "FROM User u WHERE u.id = :id"; 
		Query query = sessionFactory.openSession().createQuery(hql);
		query.setParameter("id", id);
		return (User) query.uniqueResult();
    }
    
    @Override
    public User findByUsername(String username) {
        String hql = "FROM User p WHERE p.username = :username"; // HQL Query
        Query query = sessionFactory.openSession().createQuery(hql);
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    @Override
    public void saveUser(User user) {
    	sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
    	sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        Query query = sessionFactory.openSession().createSQLQuery("DELETE FROM User WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUsers() {
        Criteria criteria = sessionFactory.openSession().createCriteria(User.class); // Criteria Query
        return (List<User>) criteria.list();
    }

    @Override
    public List<User> deleteAllUsers() {
        sessionFactory.openSession().createQuery("DELETE FROM User").executeUpdate();
        return Collections.emptyList();
    }
    
    @Override
    public Boolean isUserExist(User user) {
        Query query = sessionFactory.openSession().createQuery("FROM User p WHERE p.username = :username");
    	query.setString("username", user.getUsername());
    	List<?> pList = query.list();
    	if (!pList.isEmpty()) {
    		return true;
    	}
    	return false;
    }

}

