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

import cz.mbucek.eme4j.functions.Function;
import cz.mbucek.eme4j.functions.FunctionDescriptor;
import cz.mbucek.eme4j.general.Pair;

public class FunctionToken extends Token {

	private Pair<FunctionDescriptor, Function> pair;
	
	public FunctionToken(Pair<FunctionDescriptor, Function> pair) {
		this.pair = pair;
	}
	
	public int args() {
		return pair.key().args();
	}
	
	public double apply(double... args) {
		if(pair.key().args() != args.length)
			throw new RuntimeException();
		return pair.value().apply(args);
	}

	@Override
	public String toString() {
		return "FunctionToken [pair=" + pair + "]";
	}
}
