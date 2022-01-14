/*
 * Copyright 2022 Matěj Bucek
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cz.mbucek.eme4j.functions;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import cz.mbucek.eme4j.general.ExpressionContext;

/**
 * Used by {@link ExpressionContext} whilst scraping classes
 * and by other internal mechanisms.
 * 
 * @author Matěj Bucek
 *
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface FunctionDescriptor {
	/**
	 * An array of signatures that will be used whilst evaluating
	 * the expression.
	 * @return an array of signatures
	 */
	public String[] signature();
	/**
	 * The number of arguments that will the function use.
	 * @return the number of arguments
	 */
	public int args(); 
}
