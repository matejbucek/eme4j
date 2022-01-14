package cz.mbucek.eme4j.general;

import java.util.Collections;
import java.util.List;

import cz.mbucek.eme4j.exceptions.OperatorTokenizationException;
import cz.mbucek.eme4j.tokeniser.Token;
import cz.mbucek.eme4j.tokeniser.Tokenizer;

public class Expression {

	private ExpressionContext context;
	private Tokenizer tokenizer;
	private String expression;
	private List<Token> tokens;

	private Expression(String expression, ExpressionContext context) {
		this.expression = expression;
		this.context = context;
		this.tokenizer = new Tokenizer(context);
	}

	public List<Token> parse() throws OperatorTokenizationException {
		parseInside();
		return Collections.unmodifiableList(tokens);
	}

	private void parseInside() throws OperatorTokenizationException {
		context.scrapeSources();
		tokens = tokenizer.toRPN(tokenizer.tokenize(expression));
	}

	public double evaluate() throws OperatorTokenizationException {
		if(tokens == null)
			parseInside();
		return tokenizer.evalueate(tokens);
	}
	
	public void setVariable(String name, double value) {
		context.setVariable(name, value);
	}

	@Override
	public String toString() {
		return "Expression [Tokens="+tokens+"]";
	}

	public static class ExpressionBuilder {

		private String expression;
		private ExpressionContext context;

		public ExpressionBuilder(String expression) {
			this.expression = expression;
			this.context = new ExpressionContext();
		}

		public ExpressionBuilder addSource(Class<?> source) {
			context.addSource(source);
			return this;
		}
		
		public ExpressionBuilder setVariable(String name, double value) {
			context.setVariable(name, value);
			return this;
		}

		public Expression build() {
			return new Expression(expression, context);
		}
	}
}
