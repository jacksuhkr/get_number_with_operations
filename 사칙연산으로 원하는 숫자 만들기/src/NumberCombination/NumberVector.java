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
 			if(numbers[i]%1==0) // 정수이면 .0을 삭제함
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
	
	// 넘버벡터 순서대로 정렬하기
	public int compareTo(NumberVector o) {
		// 차원이 작은게 작은거
		if(dimension>o.dimension) return -1;
		else if(dimension<o.dimension) return 1;
		
		// 두 벡터의 숫자들로 이루어진 TreeSet 만들기, 숫자를 정렬하기 위함
		TreeSet<Float> numSet = new TreeSet<Float>();
		TreeSet<Float> onumSet = new TreeSet<Float>();
		for(int i=0; i<dimension; i++) {
			numSet.add(numbers[i]);
			onumSet.add(o.numbers[i]);
		}
		
		// 원소개수가 같으면 원소별 크기 비교해서 대소비교
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
			if(numbers[i]%1==0) { numsForPrint += (int) numbers[i] + " "; }	//정수면 .0빼고 프린트
			else numsForPrint += numbers[i] + " ";
		}
		numsForPrint += "]";
		return numsForPrint;
	}
}
