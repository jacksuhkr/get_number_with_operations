package Operator;

public class Division extends Operator {
	public Division(String name) {
		super(name);
	}
	
	@Override
	public float operate(float a, float b) {
		return a/b;
	}
}
