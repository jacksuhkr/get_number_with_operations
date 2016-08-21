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
	// ���� n���� ������, ���� ��ġ�� �ٲ��� �ʰ� ����� ���� �� �ִ� ��� ��츦 ����
	public static ArrayList<String> getAllOperCombi(float[] numbers) {
		// reduceDimToTwo�� �ֱ� ���� ��̸� ���� numbers�� ���� numberVector�� ����
		ArrayList<NumberVector> input = new ArrayList<NumberVector>();
		input.add(new NumberVector(numbers));
		
		// ó�� �޾ƿ� ���ڵ�� ������ ��� ������� �����Ͽ� ������ 2���� ����
		ArrayList<NumberVector> last =
				MultipleNumbers.reduceDimToTwo(input, numbers.length);
		
		// ������ 2���� �ٿ����Ƿ�, ����� ���ϴ� �޼ҵ��� getResult�� ����
		ArrayList<String> results = new ArrayList<String>();
		for(int i=0; i<last.size(); i++) {
			String result = TwoNumbers.getResult(last.get(i));
			if(result!=null) { results.add(result); }
		}
		
		return results;
	}
	
	// ���ڸ� n���� ������ ������, ���� ��ġ�� �ٲ��� �ʰ� ���ϴ� ����� ���� �� �ִ� �ϳ��� ��츦 ����Ʈ
	public static boolean getOneOperCombi(float[] numbers) {
		ArrayList<String> results = getAllOperCombi(numbers);
		if(!results.isEmpty()) {
			System.out.println(results.get(0));
			return true;
		}
		return false;
	}
	
	// ���� n�� ������, ���� ��ġ�� �ٲٸ鼭 ����� ���� �� �ִ� ��츦 ��� ���տ� ���� 1���� ����Ʈ
	public static void getAllOperCombiWithReorder(float[] numbers) {
		ArrayList<NumberVector> numVecs = NumCombi.getPermutation(numbers, numbers.length);
		for(int i=0; i<numVecs.size(); i++) { 
			getOneOperCombi(numVecs.get(i).numbers);
		}
	}
	
	// ���� n�� ������, ���� ��ġ�� �ٲٸ鼭 ����� ���� �� �ִ� �ϳ� ��� ����Ʈ
	public static boolean getOneOperCombiWithReorder(float[] numbers) {
		ArrayList<NumberVector> numVecs = NumCombi.getPermutation(numbers, numbers.length);
		for(int i=0; i<numVecs.size(); i++) { 
			if(getOneOperCombi(numVecs.get(i).numbers)) { return true; } 
		}
		return false;
	}
}
