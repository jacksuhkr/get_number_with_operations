package NumberCombination;

import java.util.TreeSet;

public class NumberVector implements Comparable<NumberVector>{
	public float numbers[];
	public String process[];
	public int dimension;
	
	public NumberVector(float numbers[]) {
		this.dimension = numbers.length;
		this.numbers = new float[dimension];
		this.process = new String[dimension];
		for(int i=0; i<dimension; i++) {
			this.numbers[i] = numbers[i];
 			this.process[i] = Float.toString(numbers[i]);
 			if(numbers[i]%1==0) // �����̸� .0�� ������
 			{ this.process[i] = Integer.toString((int) numbers[i]); }
		}
	}
	
	public NumberVector(float numbers[], String process[], int dimension) {
		this.dimension = dimension;
		this.numbers = new float[dimension];
		this.process = new String[dimension];
		for(int i=0; i<dimension; i++) {
			this.numbers[i] = numbers[i];
			this.process[i] = process[i];
		}
	}
	
	// �ѹ����� ������� �����ϱ�
	public int compareTo(NumberVector o) {
		// ������ ������ ������
		if(dimension>o.dimension) return -1;
		else if(dimension<o.dimension) return 1;
		
		// �� ������ ���ڵ�� �̷���� TreeSet �����, ���ڸ� �����ϱ� ����
		TreeSet<Float> numSet = new TreeSet<Float>();
		TreeSet<Float> onumSet = new TreeSet<Float>();
		for(int i=0; i<dimension; i++) {
			numSet.add(numbers[i]);
			onumSet.add(o.numbers[i]);
		}
		
		// ���Ұ����� ������ ���Һ� ũ�� ���ؼ� ��Һ�
		Float[] forCompare = numSet.toArray(new Float[dimension]);
		Float[] oforCompare = onumSet.toArray(new Float[dimension]);
		for(int i=0; i<dimension; i++) {
			if(forCompare[i]>oforCompare[i]) return 1;
			else if(forCompare[i]<oforCompare[i]) return -1;
		}
		return 0;
	}
	
    @Override
    public boolean equals(Object o) {
    	if (super.equals(o)) {
			return true;
		}
		if (o instanceof NumberVector) {
			
			if (this.compareTo(((NumberVector) o))==0) {
				return true;
			}
		}
		return false;
    }
	
	public String getNumsForPrint() {
		String numsForPrint;
		numsForPrint = "[ ";
		for(int i=0; i<dimension; i++) {
			if(numbers[i]%1==0) { numsForPrint += (int) numbers[i] + " "; }	//������ .0���� ����Ʈ
			else numsForPrint += numbers[i] + " ";
		}
		numsForPrint += "]";
		return numsForPrint;
	}
}
