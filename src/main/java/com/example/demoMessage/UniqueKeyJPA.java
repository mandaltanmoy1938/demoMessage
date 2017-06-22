package com.example.demoMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Tanmoy Mandal on 1/1/2017.
 */
@Constraint(validatedBy={UniqueKeyValidatorJPA.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueKeyJPA {

    String[] columnNames();

    String message() default "{UniqueKey.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        UniqueKeyJPA[] value();
    }
}
