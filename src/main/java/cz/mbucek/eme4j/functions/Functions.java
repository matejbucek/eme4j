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

import java.util.Random;

/**
 * All the functions defined by this library should
 * be declared and defined in this file.
 * 
 * @author Matěj Bucek
 *
 */
public class Functions {

	@FunctionDescriptor(signature = "sin", args = 1)
	public static final Function SIN = (x) -> Math.sin(x[0]);
	
	@FunctionDescriptor(signature = "cos", args = 1)
	public static final Function COS = (x) -> Math.cos(x[0]);
	
	@FunctionDescriptor(signature = "tan", args = 1)
	public static final Function TAN = (x) -> Math.tan(x[0]);
	
	@FunctionDescriptor(signature = "cotg", args = 1)
	public static final Function COTG = (x) -> 1 / Math.tan(x[0]);
	
	@FunctionDescriptor(signature = "rad", args = 1)
	public static final Function RAD = (x) -> Math.toRadians(x[0]);
	
	@FunctionDescriptor(signature = "deg", args = 1)
	public static final Function DEG = (x) -> Math.toDegrees(x[0]);
	
	/**
	 * Calculates a logarithm of base x[0] from number x[1];
	 * 
	 * <code>log(b, n)</code> is a <code>log<sub>b</sub>(n)</code>
	 */
	@FunctionDescriptor(signature = "log", args = 2)
	public static final Function LOG = (x) -> Math.log(x[1]) / Math.log(x[0]);
	
	@FunctionDescriptor(signature = "abs", args = 1)
	public static final Function ABS = (x) -> Math.abs(x[0]);
	
	@FunctionDescriptor(signature = "random", args = 2)
	public static final Function RANDOM = (x) -> {var r = new Random(); return r.nextDouble(x[0], x[1]);};
	
	@FunctionDescriptor(signature = "round", args = 1)
	public static final Function ROUND = (x) -> Math.round(x[0]);
}
