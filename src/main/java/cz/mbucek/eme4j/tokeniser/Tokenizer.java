package cz.mbucek.eme4j.tokeniser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import cz.mbucek.eme4j.exceptions.OperatorTokenizationException;
import cz.mbucek.eme4j.general.ExpressionContext;

public class Tokenizer {

	private ExpressionContext context;

	public Tokenizer(ExpressionContext context) {
		this.context = context;
	}

	public List<Token> tokenize(String expression) throws OperatorTokenizationException{
		var i = 0;
		var chars = expression.toCharArray();
		var tokens = new ArrayList<Token>();
		var builder = new StringBuilder();
		while(i < chars.length) {
			if(chars[i] == ' ') i++;
			if(i < chars.length && isNumeric(chars[i])) {
				if(i + 1 < chars.length && isPeriodOrNumeric(chars[i + 1])) {
					do {
						builder.append(chars[i++]);
					} while(i < chars.length && isPeriodOrNumeric(chars[i]));
					tokens.add(new NumberToken(Double.parseDouble(builder.toString())));
					builder.setLength(0);
				} else {
					tokens.add(new NumberToken(Double.parseDouble(String.valueOf(chars[i++]))));
				}
			} else {
				if(i < chars.length && isAlphabetic(chars[i])) {
					do {
						builder.append(chars[i++]);
					} while(i < chars.length && isAlphabetic(chars[i]));
					
					if(isFunction(builder.toString())) {
						tokens.add(new FunctionToken(context.getFunction(builder.toString())));
					} else if(isConstant(builder.toString())) {
						tokens.add(new ConstantToken(context.getConstant(builder.toString())));
					} else {
						tokens.add(new VariableToken(builder.toString()));
					}
					builder.setLength(0);
				} else if(chars[i] != ' ') {

					if(chars[i] == '(') {
						tokens.add(new OpenParenthesesToken());
						i++;
						continue;
					} else if(chars[i] == ')') {
						tokens.add(new CloseParenthesesToken());
						i++;
						continue;
					} else if(chars[i] == ',') {
						tokens.add(new ValueSeparatorToken());
						i++;
						continue;
					}

					do {
						builder.append(chars[i++]);
					} while(i < chars.length && (!isAlphabetic(chars[i]) && !isNumeric(chars[i]) && chars[i] != ' ' && chars[i] != '(' && chars[i] != ')' && chars[i] != ','));

					if(!isOperator(builder.toString())) {
						throw new OperatorTokenizationException();
					}

					tokens.add(new OperatorToken(context.getOperator(builder.toString())));
					builder.setLength(0);
				}
			}
		}
		return tokens;
	}


	private boolean isOperator(String value) {
		return context.hasOperator(value);
	}

	private boolean isFunction(String value) {
		return context.hasFunction(value);
	}

	private boolean isConstant(String value) {
		return context.hasConstant(value);
	}

	private boolean isPeriodOrNumeric(char value) {
		return Character.isDigit(value) | value == '.';
	}

	private boolean isNumeric(char value) {
		return Character.isDigit(value);
	}

	private boolean isAlphabetic(char value) {
		return Character.isLetter(value);
	}

	public List<Token> toRPN(List<Token> tokens){
		var orderedTokens = new ArrayList<Token>();
		var stack = new Stack<Token>();
		for(var token : tokens) {
			if(token instanceof OperatorToken o1) {
				while(!stack.isEmpty() && (stack.peek() instanceof OperatorToken o2) && isHigherPrecedence(o1, o2)) {
					orderedTokens.add(stack.pop());
				}
				stack.push(token);
			} else if (token instanceof OpenParenthesesToken) {
				stack.push(token);
			} else if (token instanceof CloseParenthesesToken) {
				while(!(stack.peek() instanceof OpenParenthesesToken)) {
					orderedTokens.add(stack.pop());
				}
				stack.pop();
				if(!stack.isEmpty() && stack.peek() instanceof FunctionToken) {
					orderedTokens.add(stack.pop());
				}
			} else if (token instanceof FunctionToken) {
				stack.push(token);
			} else if (token instanceof ValueSeparatorToken) {
				while(!stack.isEmpty() && !(stack.peek() instanceof OpenParenthesesToken)) {
					orderedTokens.add(stack.pop());
				}
			} else {
				orderedTokens.add(token);
			}
		}
		while(!stack.isEmpty()) {
			orderedTokens.add(stack.pop());
		}
		return orderedTokens;
	}

	private boolean isHigherPrecedence(OperatorToken o1, OperatorToken o2) {
		return (o1.rightAssociative())? o1.getPrecedence() < o2.getPrecedence() : o1.getPrecedence() <= o2.getPrecedence();
	}

	public double evalueate(List<Token> tokens) {
		List<Token> withValues = null;
		for(var variable : context.getVariables()) {
			withValues = tokens.stream().map(t -> (t instanceof VariableToken v && v.getSignature().equals(variable.key())? new NumberToken(variable.value()) : t)).toList();
		}
		
		if(withValues == null) withValues = tokens;
		
		var stack = new Stack<Token>();
		
		for(var token : withValues) {
			if(!(token instanceof OperatorToken || token instanceof FunctionToken || token instanceof ConstantToken)) {
				stack.push(token);
			} else {
				if(token instanceof FunctionToken f) {
					double[] values = new double[f.args()];
					for(int i = 0; i < f.args(); i++) {
						if(!stack.isEmpty() && stack.pop() instanceof NumberToken n) {
							values[f.args() - i - 1] = n.getValue();
						}
					}
					stack.push(new NumberToken(f.apply(values)));
				} else if(token instanceof OperatorToken o) {
					if(stack.pop() instanceof NumberToken n1 && stack.pop() instanceof NumberToken n0) {
						stack.push(new NumberToken(o.apply(n0.getValue(), n1.getValue())));
					} else {
						throw new RuntimeException();
					}
				} else if(token instanceof ConstantToken c) {
					stack.push(new NumberToken(c.getValue()));
				}
			}
		}
		if(stack.pop() instanceof NumberToken n)
			return n.getValue();
		else
			throw new RuntimeException();
	}

}
