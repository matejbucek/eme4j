package cz.mbucek.eme4j.tokeniser;

public class NumberToken extends Token {
	private double value;
	
	public NumberToken(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "NumberToken [value=" + value + "]";
	}
}
