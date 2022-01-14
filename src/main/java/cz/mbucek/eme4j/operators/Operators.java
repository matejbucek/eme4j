package cz.mbucek.eme4j.operators;

import cz.mbucek.eme4j.functions.Function;

public class Operators {

	@OperatorDescriptor(signature = "+", precedence = 2)
	public static final Function ADD = (x) -> x[0] + x[1];
	
	@OperatorDescriptor(signature = "*", precedence = 3)
	public static final Function MULTIPLY = (x) -> x[0] * x[1];
	
	@OperatorDescriptor(signature = {"-", "–"}, precedence = 2)
	public static final Function SUBTRACT = (x) -> x[0] - x[1];
	
	@OperatorDescriptor(signature = "/", precedence = 4)
	public static final Function DIVIDE = (x) -> x[0] / x[1];
	
	@OperatorDescriptor(signature = "^", rightAssociative = true, precedence = 5)
	public static final Function POW = (x) -> Math.pow(x[0], x[1]);
}
