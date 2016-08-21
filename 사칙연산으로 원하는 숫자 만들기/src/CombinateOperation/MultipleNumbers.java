package CombinateOperation;

import java.util.ArrayList;

import Main.Main;
import NumberCombination.NumberVector;

public class MultipleNumbers {

	// NumberVector 하나를 받아서, 특정위치에서 연산을 진행하고, 변수를 하나 줄여주는 메소드
	public static NumberVector reduceDimension
		(NumberVector numVector, int operationNum, int operationPosition) {
		// return할 numberVector를 만들기 위한 초기화
		int returnVectorDimension = numVector.dimension-1;
		float returnNumbers[] = new float[returnVectorDimension];
		String returnProcess[] = new String[returnVectorDimension];
		
		// 연산하는 위치 앞부분은 숫자가 다 그대로
		for(int i=0; i<operationPosition; i++) {
			returnNumbers[i] = numVector.numbers[i];
			returnProcess[i] = numVector.process[i];
		}
		
		// 연산하는 자리에서 연산해서 변수를 하나 줄임
		returnNumbers[operationPosition] = 
			Main.operators[operationNum].operate
				(numVector.numbers[operationPosition], numVector.numbers[operationPosition+1]);
		// 연산과정을 String으로 출력
		returnProcess[operationPosition] = "(" +
				numVector.process[operationPosition] + 
				Main.operators[operationNum].name +
				numVector.process[operationPosition+1] + ")";
		
		// 연산하는 위치 뒷부분은 숫자가 다 그대로
		for(int i=operationPosition+1; i<returnVectorDimension; i++) {
			returnNumbers[i] = numVector.numbers[i+1];
			returnProcess[i] = numVector.process[i+1];
		}
		
		// 연산결과를 numberVector로 출력
		NumberVector returnNumberVector = 
				new NumberVector(returnNumbers, returnProcess, returnVectorDimension);
		return returnNumberVector;
	}

	// NumberVector 어레이로 받아서, 가능한 모든 위치에서 가능한 모든 연산을 진행하고, 변수를 하나 줄여주는 메소드
	// 들어온 vector의 차원이 n이고 연산개수가 4개이면, (n-1)^4배로 어레이의 크기가 늘어난다
	public static ArrayList<NumberVector> reduceDimension
		(ArrayList<NumberVector> inputNumVecs, int inputDimension) {
		ArrayList<NumberVector> outputNumVecs = new ArrayList<NumberVector>();
		
		for(int i=0; i<inputNumVecs.size(); i++) {
		for(int operPos=0; operPos<inputDimension-1; operPos++) {
		for(int operNum=0; operNum<Main.operators.length; operNum++) {
			NumberVector outputNumVec 
				= reduceDimension(inputNumVecs.get(i), operNum, operPos);
			outputNumVecs.add(outputNumVec);
		}
		}
		}
		return outputNumVecs;
	}

	// NumberVector 어레이로 받아서, 차원을 하나씩 줄여가면서 최종적으로 2까지 줄임
	public static ArrayList<NumberVector> reduceDimToTwo
						(ArrayList<NumberVector> input, int intiDimension) {
		ArrayList<NumberVector> output = new ArrayList<NumberVector>();
		for(int i=0; i<(intiDimension-2); i++) {
			output = MultipleNumbers.reduceDimension(input, intiDimension-i);
			input = output;
		}
		return output;
	}
}
