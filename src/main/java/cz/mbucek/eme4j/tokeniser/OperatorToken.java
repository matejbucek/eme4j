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
import cz.mbucek.eme4j.general.Pair;
import cz.mbucek.eme4j.operators.OperatorDescriptor;

public class OperatorToken extends Token {

	private Pair<OperatorDescriptor, Function> pair;
	
	public OperatorToken(Pair<OperatorDescriptor, Function> pair) {
		this.pair = pair;
	}
	
	public int getPrecedence() {
		return pair.key().precedence();
	}
	
	public boolean isRightAssociative() {
		return pair.key().rightAssociative();
	}
	
	public boolean isOneArg() {
		return pair.key().oneArg();
	}
	
	public double apply(double... args) {
		return pair.value().apply(args);
	}
	
	public boolean isModifier() {
		return pair.key().modifier();
	}
	
	public double modify(double n) {
		return Double.parseDouble(pair.key().signature()[0] + String.valueOf(n));
	}

	@Override
	public String toString() {
		return "OperatorToken [pair=" + pair + "]";
	}
}
