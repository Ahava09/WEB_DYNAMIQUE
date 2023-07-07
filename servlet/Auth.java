package etu1985.framework;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Auth {
    String admin() default "";
}