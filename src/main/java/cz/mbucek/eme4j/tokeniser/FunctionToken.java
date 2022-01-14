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
