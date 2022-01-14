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
}
