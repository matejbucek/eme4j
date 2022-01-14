package cz.mbucek.eme4j.functions;

public class Functions {

	@FunctionDescriptor(signature = "sin", args = 1)
	public static final Function SIN = (x) -> Math.sin(x[0]);
	
	@FunctionDescriptor(signature = "cos", args = 1)
	public static final Function COS = (x) -> Math.cos(x[0]);
	
	@FunctionDescriptor(signature = "tan", args = 1)
	public static final Function TAN = (x) -> Math.tan(x[0]);
	
	@FunctionDescriptor(signature = "cotg", args = 1)
	public static final Function COTG = (x) -> 1 / Math.tan(x[0]);
}
