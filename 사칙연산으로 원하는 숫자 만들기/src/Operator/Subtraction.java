package Operator;

public class Subtraction extends Operator {
	public String name = "Subtraction";
	
	public Subtraction(String name) {
		super(name);
	}
	
	@Override
	public float operate(float a, float b) {
		return a-b;
	}
}
