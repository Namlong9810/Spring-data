package com.example.QueryDataExample.annotation;

import java.lang.annotation.*;

/**
 * Annotation to mark a field to be exported/imported via Excel
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

    /**
     * Column name in Excel
     */
    String name();

    /**
     * Column order (lower value = earlier column)
     */
    int order() default 0;

    /**
     * Column width. If non-positive, auto-sizing will be applied.
     */
    int width() default -1;

    /**
     * Format for date fields (e.g., "yyyy-MM-dd")
     */
    String format() default "";
}

