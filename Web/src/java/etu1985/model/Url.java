package etu1985.model;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Url {
    String url() default "";
}
