package CombinateOperation;

import Main.*;
import NumberCombination.NumberVector;

public class TwoNumbers {
	
	public static boolean checkResult(float num1, float num2) {
		float[] numbers = {num1, num2};
		NumberVector numberVector = new NumberVector(numbers);
		return getResult(numberVector)!=null;
	}
	
	public static boolean checkResult(NumberVector numberVertor) {
		return getResult(numberVertor)!=null;
	}
	
	public static String getResult(NumberVector numberVertor) {
		for(int i=0; i<Main.operators.length; i++) {
			// 사칙연산으로 두 수에 대한 연산을 진행해봄
			float resultOfOperation 
				= Main.operators[i].operate(numberVertor.numbers[0], numberVertor.numbers[1]);
			// 연산결과 원하는결과가 나온다면, (숫자 + 연산이름 + 숫자 "=" 결과) 를 프린트
			if(resultOfOperation == Main.desiredResult) {
				String result = 
						numberVertor.process[0] + 
						Main.operators[i].name + 
						numberVertor.process[1] + "=";
			// 결과가 정수면 정수로 바꿔서 더함
				if(resultOfOperation%1==0) { result += (int) resultOfOperation; }
				else result += resultOfOperation;
			// 중복이 없으면 결과를 result에 추가함
				if(!Main.result.contains(result)) { Main.result.add(result); }
				return result;	// 어차피 숫자 2개로 할때는 가능한 경우 1개밖에 없음 (2+2=2*2 빼고)
			}
		}
		return null;
	}
}
