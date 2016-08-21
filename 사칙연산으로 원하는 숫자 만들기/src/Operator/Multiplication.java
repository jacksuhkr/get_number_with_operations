package Operator;

public class Multiplication extends Operator {	
	public Multiplication(String name) {
		super(name);
	}
	
	@Override
	public float operate(float a, float b) {
		return a*b;
	}
}
