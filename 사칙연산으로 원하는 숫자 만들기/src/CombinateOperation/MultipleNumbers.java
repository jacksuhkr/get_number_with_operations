package CombinateOperation;

import java.util.ArrayList;

import Main.Main;
import NumberCombination.NumberVector;

public class MultipleNumbers {

	// NumberVector �ϳ��� �޾Ƽ�, Ư����ġ���� ������ �����ϰ�, ������ �ϳ� �ٿ��ִ� �޼ҵ�
	public static NumberVector reduceDimension
		(NumberVector numVector, int operationNum, int operationPosition) {
		// return�� numberVector�� ����� ���� �ʱ�ȭ
		int returnVectorDimension = numVector.dimension-1;
		float returnNumbers[] = new float[returnVectorDimension];
		String returnProcess[] = new String[returnVectorDimension];
		
		// �����ϴ� ��ġ �պκ��� ���ڰ� �� �״��
		for(int i=0; i<operationPosition; i++) {
			returnNumbers[i] = numVector.numbers[i];
			returnProcess[i] = numVector.process[i];
		}
		
		// �����ϴ� �ڸ����� �����ؼ� ������ �ϳ� ����
		returnNumbers[operationPosition] = 
			Main.operators[operationNum].operate
				(numVector.numbers[operationPosition], numVector.numbers[operationPosition+1]);
		// ��������� String���� ���
		returnProcess[operationPosition] = "(" +
				numVector.process[operationPosition] + 
				Main.operators[operationNum].name +
				numVector.process[operationPosition+1] + ")";
		
		// �����ϴ� ��ġ �޺κ��� ���ڰ� �� �״��
		for(int i=operationPosition+1; i<returnVectorDimension; i++) {
			returnNumbers[i] = numVector.numbers[i+1];
			returnProcess[i] = numVector.process[i+1];
		}
		
		// �������� numberVector�� ���
		NumberVector returnNumberVector = 
				new NumberVector(returnNumbers, returnProcess, returnVectorDimension);
		return returnNumberVector;
	}

	// NumberVector ��̷� �޾Ƽ�, ������ ��� ��ġ���� ������ ��� ������ �����ϰ�, ������ �ϳ� �ٿ��ִ� �޼ҵ�
	// ���� vector�� ������ n�̰� ���갳���� 4���̸�, (n-1)^4��� ����� ũ�Ⱑ �þ��
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

	// NumberVector ��̷� �޾Ƽ�, ������ �ϳ��� �ٿ����鼭 ���������� 2���� ����
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
