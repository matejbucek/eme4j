package cz.mbucek.eme4j.tokeniser;

public class VariableToken extends Token {
	private String signature;
	
	public VariableToken(String signature) {
		this.signature = signature;
	}
	
	public String getSignature() {
		return signature;
	}

	@Override
	public String toString() {
		return "VariableToken [signature=" + signature + "]";
	}
}
