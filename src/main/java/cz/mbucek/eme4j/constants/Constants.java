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

package cz.mbucek.eme4j.constants;

/**
 * All the constants defined by this library should
 * be declared and defined in this file.
 * 
 * @author Matěj Bucek
 *
 */
public class Constants {

	@ConstantDescriptor(signature = {"PI", "π"})
	public static final double PI = Math.PI;
	
	@ConstantDescriptor(signature = "φ")
	public static final double GR = 1.6180339887498948482;
	
	@ConstantDescriptor(signature = "e")
	public static final double E = Math.E;
}
