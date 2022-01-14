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
	
	public boolean rightAssociative() {
		return pair.key().rightAssociative();
	}
	
	public double apply(double... args) {
		return pair.value().apply(args);
	}

	@Override
	public String toString() {
		return "OperatorToken [pair=" + pair + "]";
	}
}
