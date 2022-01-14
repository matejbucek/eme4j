# Extensible Math Expressions for Java
Java library, that makes it easier to work with Mathematical Expressions.

## Examples
1. Simple expressions
```java
    public static void main(String[] args) {
      var e = new ExpressionBuilder("(3 * 2 - 5 / 9)^2").build();
      System.out.println(e.evaluate());
      // 29.641975308641978
    }
```
2. Functions
```java
    public static void main(String[] args) {
      var e = new ExpressionBuilder("sin(0.5)").build();
      System.out.println(e.evaluate());
      // 0.479425538604203
    }
```
3. Constants
```java
    public static void main(String[] args) {
      var e = new ExpressionBuilder("sin(π)").build();
      System.out.println(e.evaluate());
      // 1.2246467991473532E-16
    }
```
4. Variables
```java
    public static void main(String[] args) {
      var e = new ExpressionBuilder("sin(y)").setVariable("y", 50).build();
      System.out.println(e.evaluate());
      // -0.26237485370392877
    }
```

## Extensibility
1. Defining own Function
```java
    public static class SomeClass{
      @FunctionDescriptor(signature = "tan", args = 1)
      public static final Function TAN = (x) -> Math.tan(x[0]);
    }
    public static void main(String[] args) {
      var e = new ExpressionBuilder("tan(π)").addSource(SomeClass.class).build();
      System.out.println(e.evaluate());
      // -3.380515006246586
    }
```

2. Defining own Constant
```java
    public static class SomeClass{
      @ConstantDescriptor(signature = "GITHUB")
      public static final double = 2008;
    }
    public static void main(String[] args) {
      var e = new ExpressionBuilder("GITHUB").addSource(SomeClass.class).build();
      System.out.println(e.evaluate());
      // 2008.0
    }
```

3. Defining own Operator
```java
    public static class SomeClass{
      @OperatorDescriptor(signature = "->", precedence = 6)
      public static final Function ARROW = (x) -> Math.pow(x[0], x[1]) * Math.pow(x[1], x[0]); 
    }
    public static void main(String[] args) {
      var e = new ExpressionBuilder("5->6").addSource(SomeClass.class).build();
      System.out.println(e.evaluate());
      // 1.215E8
    }
```
## TODO
* Exceptions
* Sets
* Guides 
* Documentation (JavaDoc)

## Build

## Authors
* Matěj Bucek

