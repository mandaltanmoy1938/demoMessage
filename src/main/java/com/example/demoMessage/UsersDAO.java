package com.example.demoMessage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tanmoy Mandal on 12/29/2016.
 */
@Repository
@Transactional
public class UsersDAO {

    @Autowired
    private SessionFactory _sessionFactory;


    public Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public String save(UserEntity userEntity) {
        try {
            getSession().saveOrUpdate(userEntity);
            return "Saved Successfully";
        } catch (Exception ex) {
            return ex.getClass().toString();
        }
    }

    public List<UserEntity> getEntityList(){
        return getSession().createCriteria(UserEntity.class).list();
    }

    public List<UserEntity> findByCode(String code) {
        return getSession().createCriteria(UserEntity.class).add(Restrictions.eq("code",code)).list();
    }


}
