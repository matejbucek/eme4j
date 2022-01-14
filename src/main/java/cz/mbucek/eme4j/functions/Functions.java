package cz.mbucek.eme4j.functions;

public class Functions {

	@FunctionDescriptor(signature = "sin", args = 1)
	public static final Function SIN = (x) -> Math.sin(x[0]);
	
	@FunctionDescriptor(signature = "cos", args = 1)
	public static final Function COS = (x) -> Math.cos(x[0]);
}
