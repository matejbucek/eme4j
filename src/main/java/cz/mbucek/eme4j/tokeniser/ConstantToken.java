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
