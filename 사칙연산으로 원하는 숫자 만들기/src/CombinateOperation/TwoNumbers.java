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
			// ��Ģ�������� �� ���� ���� ������ �����غ�
			float resultOfOperation 
				= Main.operators[i].operate(numberVertor.numbers[0], numberVertor.numbers[1]);
			// ������ ���ϴ°���� ���´ٸ�, (���� + �����̸� + ���� "=" ���) �� ����Ʈ
			if(resultOfOperation == Main.desiredResult) {
				String result = 
						numberVertor.process[0] + 
						Main.operators[i].name + 
						numberVertor.process[1] + "=";
			// ����� ������ ������ �ٲ㼭 ����
				if(resultOfOperation%1==0) { result += (int) resultOfOperation; }
				else result += resultOfOperation;
			// �ߺ��� ������ ����� result�� �߰���
				if(!Main.result.contains(result)) { Main.result.add(result); }
				return result;	// ������ ���� 2���� �Ҷ��� ������ ��� 1���ۿ� ���� (2+2=2*2 ����)
			}
		}
		return null;
	}
}
