package Operator;

public class Addition extends Operator {
	public Addition(String name) {
		super(name);
	}
	
	@Override
	public float operate(float a, float b) {
		return a+b;
	}
}
