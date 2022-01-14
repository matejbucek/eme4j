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

package cz.mbucek.eme4j.tokeniser;

import cz.mbucek.eme4j.constants.ConstantDescriptor;
import cz.mbucek.eme4j.general.Pair;

public class ConstantToken extends Token {

	private Pair<ConstantDescriptor, Double> pair;
	
	public ConstantToken(Pair<ConstantDescriptor, Double> pair) {
		this.pair = pair;
	}
	
	public double getValue() {
		return pair.value();
	}

	@Override
	public String toString() {
		return "ConstantToken [pair=" + pair + "]";
	}
}
