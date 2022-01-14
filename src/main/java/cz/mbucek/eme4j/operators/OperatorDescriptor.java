package cz.mbucek.eme4j.operators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface OperatorDescriptor {
	public String[] signature();
	public boolean rightAssociative() default false;
	public boolean oneArg() default false;
	public int precedence();
}
