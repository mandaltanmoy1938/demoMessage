package com.example.demoMessage;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by Tanmoy Mandal on 12/29/2016.
 */
@Target({ElementType.TYPE})
@Constraint(validatedBy = UniqueKeyValidator.class)
//@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

@Documented
public @interface UniqueKey {
    String [] columnNames();
//    String columnName();

//    Class<?> className();

    String message() default "{UniqueKey.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        UniqueKey[] value();
    }
}
