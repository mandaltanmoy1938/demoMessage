package com.example.demoMessage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by Tanmoy Mandal on 12/29/2016.
 */
@Transactional
//@Repository
public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Object> {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private String [] columnNames;
//    private String columnName;
//    private Class<?> entityClass;

    @Override
    public void initialize(UniqueKey constraintAnnotation) {
        this.columnNames = constraintAnnotation.columnNames();
        System.out.println(constraintAnnotation.message());
//        this.entityClass = constraintAnnotation.className();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
//        Class<?> entityClass = this.entityClass;
        Class<?> entityClass = value.getClass();
        System.out.println("session: " + getSession().toString());
        Criteria criteria = getSession().createCriteria(entityClass);
        try {
            for (int i = 0; i < columnNames.length; i++) {
                String propertyName = columnNames[i];
//                Method readMethod= new PropertyDescriptor(this.columnName, entityClass).getReadMethod();
                Method readMethod= new PropertyDescriptor(propertyName, entityClass).getReadMethod();
                Object propertyValue = readMethod.invoke(value);
//                criteria.add(Restrictions.eq(this.columnName, propertyValue));
                criteria.add(Restrictions.eq(propertyName, propertyValue));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int a=criteria.list().size();
        return a==0;

//        Criterion criterion= Restrictions.eq()
//        return false;
    }
}
