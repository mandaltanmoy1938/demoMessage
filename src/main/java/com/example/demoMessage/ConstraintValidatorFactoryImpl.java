package com.example.demoMessage;

import org.hibernate.SessionFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

/**
 * Created by Tanmoy Mandal on 12/29/2016.
 */
public class ConstraintValidatorFactoryImpl implements ConstraintValidatorFactory {
    private SessionFactory sessionFactory;

    public ConstraintValidatorFactoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
        T instance = null;

        try {
            instance = key.newInstance();
        } catch (Exception e) {
            // could not instantiate class
            e.printStackTrace();
        }
        if (SessionManagerAwareValidator.class.isAssignableFrom(key)) {
            SessionManagerAwareValidator sessionManagerAwareValidator = (SessionManagerAwareValidator) instance;
            sessionManagerAwareValidator.setSessionManager(sessionFactory.getCurrentSession());
        }
        return instance;
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {

    }
}
