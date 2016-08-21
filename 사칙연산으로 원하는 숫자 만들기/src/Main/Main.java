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
	public static float[] numbers = new float[9];		// 1~9인 어레이
	public static ArrayList<Float> getNumbers = new ArrayList<Float>();
	
	public static ArrayList<NumberVector> numberVectors = new ArrayList<NumberVector>();
	public static ArrayList<String> result = new ArrayList<String>();
	
	/* 메인 시작 */
	public static void main(String[] args) {
		setNumbers();
		setOperators();
		
		System.out.println("연산 결과 어떤 숫자가 나오길 원하시나요?");
		desiredResult = scanner.nextFloat();	scanner.nextLine(); 
		
		System.out.println();
		System.out.println("사용하실 숫자들을 입력해주세요. 숫자를 하나 입력하실때마다 엔터를 눌러주세요.");
		System.out.println("			(완료되었으면 q를 입력하고 엔터를 누르세요.)");
		String checkExit="";
		while(true) {
			checkExit = scanner.nextLine();
			if(checkExit.equals("q")) { break; }
			
			try {
				getNumbers.add(Float.parseFloat(checkExit));
			} catch(Exception e) {
				System.out.println("숫자를 입력해주세요!");
			}
		}
		float[] usingNumbers = new float[getNumbers.size()];
		for(int i=0; i<getNumbers.size(); i++) {
			usingNumbers[i] = getNumbers.get(i);
		}
		
		System.out.println();
		System.out.println("숫자를 몇개 뽑으실 건가요?");
		int dimension = scanner.nextInt();
		
		numberVectors= NumCombi.getCombination(usingNumbers, dimension);
		for(int i=0; i<numberVectors.size(); i++) {
			System.out.println(numberVectors.get(i).getNumsForPrint());
			if(!OperationCombinator.getOneOperCombiWithReorder(numberVectors.get(i).numbers)) {
				System.out.println("가능한 조합이 없음");
				result.add("가능한 조합이 없음");
			}
			System.out.println();
		}
		
		/*
		numberVectors = NumCombi.getCombination(numbers, 3);
		for(int i=0; i<numberVectors.size(); i++) {
			System.out.println(numberVectors.get(i).getNumsForPrint());
			if(!OperationCombinator.getOneOperCombiWithReorder(numberVectors.get(i).numbers)) {
				System.out.println("가능한 조합이 없음");
				result.add("가능한 조합이 없음");
			}
			System.out.println();
		}
		*/

//		float[] newNum = { 6, 13, 4, 5, 7 };
//		OperationCombinator.getOneOperCombiWithReorder(newNum);
		
		writeResult(result);
	}
	/* 메인 종료 */
	
	
	
	// 사용할 숫자들 선택, 여기서는 1,2,3,4,5,6,7,8,9
	private static void setNumbers() {
		for(int i=0; i<9; i++) {
			numbers[i] = i+1; 
		}
	}

	// 0,1,2,3 순서대로 덧셈,뺄셈,곱셈,나눗셈
	private static void setOperators() {
		operators[0] = new Addition("+");
		operators[1] = new Subtraction("-");
		operators[2] = new Multiplication("*");
		operators[3] = new Division("/");
	}
	
	// 결과 출력
	private static void writeResult(ArrayList<String> result) {
	    try {
	    	BufferedWriter out = new BufferedWriter(new FileWriter("ResultText/result.txt"));
	    	
	    	for (int i=0; i<result.size(); i++) {
	    		if(result.get(i).contains("[")) { out.newLine(); } 	// 한줄 띄기 위한 용
		    	out.write(result.get(i)); 
			    out.newLine();
		    }
	    	out.close();
	    } catch(Exception e) {}
	}
}
