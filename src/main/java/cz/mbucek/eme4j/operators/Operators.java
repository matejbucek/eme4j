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

package cz.mbucek.eme4j.operators;

import java.util.stream.LongStream;

import cz.mbucek.eme4j.functions.Function;

/**
 * All the operators defined by this library should
 * be declared and defined in this file.
 * 
 * @author Matěj Bucek
 *
 */
public class Operators {

	@OperatorDescriptor(signature = "+", precedence = 2)
	public static final Function ADD = (x) -> x[0] + x[1];
	
	@OperatorDescriptor(signature = "*", precedence = 3)
	public static final Function MULTIPLY = (x) -> x[0] * x[1];
	
	@OperatorDescriptor(signature = {"-", "–"}, precedence = 2)
	public static final Function SUBTRACT = (x) -> x[0] - x[1];
	
	@OperatorDescriptor(signature = "/", precedence = 4)
	public static final Function DIVIDE = (x) -> x[0] / x[1];
	
	@OperatorDescriptor(signature = "^", rightAssociative = true, precedence = 5)
	public static final Function POW = (x) -> Math.pow(x[0], x[1]);
	
	@OperatorDescriptor(signature = "!", rightAssociative = true, precedence = 6, oneArg = true)
	public static final Function FACTORIAL = (x) -> LongStream.rangeClosed(1, (long) x[0]).reduce(1, (long y, long z) -> y * z);
}
