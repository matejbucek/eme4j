package cz.mbucek.eme4j.general;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.mbucek.eme4j.constants.ConstantDescriptor;
import cz.mbucek.eme4j.constants.Constants;
import cz.mbucek.eme4j.functions.Function;
import cz.mbucek.eme4j.functions.FunctionDescriptor;
import cz.mbucek.eme4j.functions.Functions;
import cz.mbucek.eme4j.operators.OperatorDescriptor;
import cz.mbucek.eme4j.operators.Operators;
public class ExpressionContext{
	private List<Class<?>> sources;
	private Map<String, Pair<OperatorDescriptor, Function>> operators;
	private Map<String, Pair<FunctionDescriptor, Function>> functions;
	private Map<String, Pair<ConstantDescriptor, Double>> constants;
	private List<Pair<String, Double>> variables;

	public ExpressionContext() {
		sources = new ArrayList<>();

		sources.add(Operators.class);
		sources.add(Functions.class);
		sources.add(Constants.class);

		operators = new HashMap<>();
		functions = new HashMap<>();
		constants = new HashMap<>();
		variables = new ArrayList<>();
	}

	public void addSource(Class<?> source) {
		sources.add(source);
	}

	public void scrapeSources() {
		for(var source : sources) {
			try {
				scrape(source, OperatorDescriptor.class);
				scrape(source, FunctionDescriptor.class);
				scrape(source, ConstantDescriptor.class);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private <K extends Annotation> void scrape(Class<?> source, Class<K> descriptor) throws IllegalArgumentException, IllegalAccessException{
		var fields = source.getFields();
		for(var field : fields) {
			if(field.isAnnotationPresent(descriptor)) {
				var ann = field.getAnnotation(descriptor);
				if(ann instanceof OperatorDescriptor o) {
					for(var sign : o.signature()) {
						operators.put(sign, new Pair<OperatorDescriptor, Function>(o, (Function) field.get(null)));
					}
				} else if (ann instanceof FunctionDescriptor f) {
					for(var sign : f.signature()) {
						functions.put(sign, new Pair<FunctionDescriptor, Function>(f, (Function) field.get(null)));
					}
				} else if (ann instanceof ConstantDescriptor c) {
					for(var sign : c.signature()) {
						constants.put(sign, new Pair<ConstantDescriptor, Double>(c, field.getDouble(null)));
					}
				}
			}
		}
	}

	public List<Pair<String, Double>> getVariables(){
		return Collections.unmodifiableList(variables);
	}
	
	public List<Class<?>> getSources(){
		return sources;
	}

	public boolean hasOperator(String signature) {
		return operators.containsKey(signature);
	}

	public boolean hasFunction(String signature) {
		return functions.containsKey(signature);	
	}

	public boolean hasConstant(String signature) {
		return constants.containsKey(signature);
	}
	
	public Pair<OperatorDescriptor, Function> getOperator(String operator){
		return (hasOperator(operator))? operators.get(operator) : null;
	}
	
	public Pair<FunctionDescriptor, Function> getFunction(String function){
		return (hasFunction(function))? functions.get(function) : null;
	}
	
	public Pair<ConstantDescriptor, Double> getConstant(String constant){
		return (hasConstant(constant))? constants.get(constant) : null;
	}

	public void setVariable(String name, double value) {
		variables.add(new Pair<String, Double>(name, value));
	}
}
