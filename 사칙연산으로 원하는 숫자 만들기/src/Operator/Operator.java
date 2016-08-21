package Operator;

public abstract class Operator {
	public String name;
	
	public Operator(String name) {
		this.name = name;
	}
	
	public float operate(float a, float b) {
		return a+b;
	}
}
