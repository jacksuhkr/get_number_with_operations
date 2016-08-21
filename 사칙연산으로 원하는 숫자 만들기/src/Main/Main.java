package Main;

import Operator.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import CombinateOperation.OperationCombinator;
import NumberCombination.NumCombi;
import NumberCombination.NumberVector;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
	public static final Operator[] operators = new Operator[4];
	public static float desiredResult = 10;
	public static float[] numbers = new float[9];		// 1~9�� ���
	public static ArrayList<Float> getNumbers = new ArrayList<Float>();
	
	public static ArrayList<NumberVector> numberVectors = new ArrayList<NumberVector>();
	public static ArrayList<String> result = new ArrayList<String>();
	
	/* ���� ���� */
	public static void main(String[] args) {
		setNumbers();
		setOperators();
		
		System.out.println("���� ��� � ���ڰ� ������ ���Ͻó���?");
		desiredResult = scanner.nextFloat();	scanner.nextLine(); 
		
		System.out.println();
		System.out.println("����Ͻ� ���ڵ��� �Է����ּ���. ���ڸ� �ϳ� �Է��ϽǶ����� ���͸� �����ּ���.");
		System.out.println("			(�Ϸ�Ǿ����� q�� �Է��ϰ� ���͸� ��������.)");
		String checkExit="";
		while(true) {
			checkExit = scanner.nextLine();
			if(checkExit.equals("q")) { break; }
			
			try {
				getNumbers.add(Float.parseFloat(checkExit));
			} catch(Exception e) {
				System.out.println("���ڸ� �Է����ּ���!");
			}
		}
		float[] usingNumbers = new float[getNumbers.size()];
		for(int i=0; i<getNumbers.size(); i++) {
			usingNumbers[i] = getNumbers.get(i);
		}
		
		System.out.println();
		System.out.println("���ڸ� � ������ �ǰ���?");
		int dimension = scanner.nextInt();
		
		numberVectors= NumCombi.getCombination(usingNumbers, dimension);
		for(int i=0; i<numberVectors.size(); i++) {
			System.out.println(numberVectors.get(i).getNumsForPrint());
			if(!OperationCombinator.getOneOperCombiWithReorder(numberVectors.get(i).numbers)) {
				System.out.println("������ ������ ����");
				result.add("������ ������ ����");
			}
			System.out.println();
		}
		
		/*
		numberVectors = NumCombi.getCombination(numbers, 3);
		for(int i=0; i<numberVectors.size(); i++) {
			System.out.println(numberVectors.get(i).getNumsForPrint());
			if(!OperationCombinator.getOneOperCombiWithReorder(numberVectors.get(i).numbers)) {
				System.out.println("������ ������ ����");
				result.add("������ ������ ����");
			}
			System.out.println();
		}
		*/

//		float[] newNum = { 6, 13, 4, 5, 7 };
//		OperationCombinator.getOneOperCombiWithReorder(newNum);
		
		writeResult(result);
	}
	/* ���� ���� */
	
	
	
	// ����� ���ڵ� ����, ���⼭�� 1,2,3,4,5,6,7,8,9
	private static void setNumbers() {
		for(int i=0; i<9; i++) {
			numbers[i] = i+1; 
		}
	}

	// 0,1,2,3 ������� ����,����,����,������
	private static void setOperators() {
		operators[0] = new Addition("+");
		operators[1] = new Subtraction("-");
		operators[2] = new Multiplication("*");
		operators[3] = new Division("/");
	}
	
	// ��� ���
	private static void writeResult(ArrayList<String> result) {
	    try {
	    	BufferedWriter out = new BufferedWriter(new FileWriter("ResultText/result.txt"));
	    	
	    	for (int i=0; i<result.size(); i++) {
	    		if(result.get(i).contains("[")) { out.newLine(); } 	// ���� ��� ���� ��
		    	out.write(result.get(i)); 
			    out.newLine();
		    }
	    	out.close();
	    } catch(Exception e) {}
	}
}
