package etu1985.framework;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Session {
    String[] value() default {}; // Ajoutez vos valeurs par défaut ici
}
