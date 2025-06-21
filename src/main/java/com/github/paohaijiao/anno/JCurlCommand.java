package com.github.paohaijiao.anno;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JCurlCommand {
    /**
     * CURL COMMAND
     * @return
     */
    String value();

    /**
     * CHECK THE COMMAND OR EXECUTE
     * @return
     */
    boolean execute() default true;

    /**
     * return http status
     * @return
     */
    int expectedStatus() default 200;

    String expectedBusinessStatus() default "200";

    /**
     * validate by path expression
     * @return
     */
    String validationScript() default "";

}
