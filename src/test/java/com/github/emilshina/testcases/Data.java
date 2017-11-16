package com.github.emilshina.testcases;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for test data.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Data {

    String source() default "";

    String dataType() default "";

    Class entity() default Object.class;

    Class entityArr() default Object[].class;
}
