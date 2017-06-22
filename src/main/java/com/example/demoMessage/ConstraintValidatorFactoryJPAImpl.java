package com.example.demoMessage;

import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

/**
 * Created by Tanmoy Mandal on 1/1/2017.
 */
public class ConstraintValidatorFactoryJPAImpl implements ConstraintValidatorFactory {

    private EntityManagerFactory entityManagerFactory;

    public ConstraintValidatorFactoryJPAImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
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

        if(EntityManagerAwareValidator.class.isAssignableFrom(key)) {
            EntityManagerAwareValidator validator = (EntityManagerAwareValidator) instance;
            validator.setEntityManager(entityManagerFactory.createEntityManager());
        }

        return instance;
    }

    @Override
    public void releaseInstance(ConstraintValidator<?, ?> instance) {

    }
}
