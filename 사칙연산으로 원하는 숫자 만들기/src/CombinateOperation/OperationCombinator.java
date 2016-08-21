package CombinateOperation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import NumberCombination.NumCombi;
import NumberCombination.NumberVector;
import Operator.Addition;
import Operator.Division;
import Operator.Multiplication;
import Operator.Operator;
import Operator.Subtraction;

public class OperationCombinator {
	// 숫자 n개를 넣으면, 숫자 위치를 바꾸지 않고 결과를 만들 수 있는 모든 경우를 리턴
	public static ArrayList<String> getAllOperCombi(float[] numbers) {
		// reduceDimToTwo에 넣기 위해 어레이를 만들어서 numbers로 만든 numberVector를 넣음
		ArrayList<NumberVector> input = new ArrayList<NumberVector>();
		input.add(new NumberVector(numbers));
		
		// 처음 받아온 숫자들로 가능한 모든 연산들을 진행하여 차원을 2까지 줄임
		ArrayList<NumberVector> last =
				MultipleNumbers.reduceDimToTwo(input, numbers.length);
		
		// 차원을 2까지 줄였으므로, 결과와 비교하는 메소드인 getResult를 실행
		ArrayList<String> results = new ArrayList<String>();
		for(int i=0; i<last.size(); i++) {
			String result = TwoNumbers.getResult(last.get(i));
			if(result!=null) { results.add(result); }
		}
		
		return results;
	}
	
	// 숫자를 n개를 순열로 넣으면, 숫자 위치를 바꾸지 않고 원하는 결과를 만들 수 있는 하나의 경우를 프린트
	public static boolean getOneOperCombi(float[] numbers) {
		ArrayList<String> results = getAllOperCombi(numbers);
		if(!results.isEmpty()) {
			System.out.println(results.get(0));
			return true;
		}
		return false;
	}
	
	// 숫자 n개 넣으면, 숫자 위치를 바꾸면서 결과를 만들 수 있는 경우를 모든 조합에 대해 1개씩 프린트
	public static void getAllOperCombiWithReorder(float[] numbers) {
		ArrayList<NumberVector> numVecs = NumCombi.getPermutation(numbers, numbers.length);
		for(int i=0; i<numVecs.size(); i++) { 
			getOneOperCombi(numVecs.get(i).numbers);
		}
	}
	
	// 숫자 n개 넣으면, 숫자 위치를 바꾸면서 결과를 만들 수 있는 하나 경우 프린트
	public static boolean getOneOperCombiWithReorder(float[] numbers) {
		ArrayList<NumberVector> numVecs = NumCombi.getPermutation(numbers, numbers.length);
		for(int i=0; i<numVecs.size(); i++) { 
			if(getOneOperCombi(numVecs.get(i).numbers)) { return true; } 
		}
		return false;
	}
}
