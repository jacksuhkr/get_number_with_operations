package NumberCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NumCombi {
	// 주어준 숫자들numbers에서 주어진 개수dimension만큼 숫자조합을 뽑아서 리턴
	public static ArrayList<NumberVector> getCombination(float[] numbers, int dimension) {
		ArrayList<NumberVector> permutations = getPermutation(numbers, dimension);
		
		/*Set<TreeSet<Float>> removeSame = new HashSet<TreeSet<Float>>();
		for(int i=0; i<permutations.size(); i++) {
			TreeSet<Float> numSet = new TreeSet<Float>();
			for(int l=0; l<permutations.get(i).dimension; l++) {
				numSet.add(permutations.get(i).numbers[l]);
			}
			removeSame.add(numSet);
		}
		
		ArrayList<NumberVector> numberVectors = new ArrayList<NumberVector>();
		Iterator<TreeSet<Float>> iterator = removeSame.iterator();
		while(iterator.hasNext()) {
			Iterator<Float> floatIterator = iterator.next().iterator();
			float numsForVector[] = new float[dimension];
			int i=0;
			while(floatIterator.hasNext()) {
				numsForVector[i] = floatIterator.next();
				i++;
			}
			numberVectors.add(new NumberVector(numsForVector));
		}*/
		
		// permutations의 벡터를 TreeSet으로 받아오면 정렬되고 중복은 사라짐
		TreeSet<NumberVector> removeSame = new TreeSet<NumberVector>();
		removeSame.addAll(permutations);
		
		// 출력할 numberVector에 옮김
		ArrayList<NumberVector> numberVectors = new ArrayList<NumberVector>();
		Iterator<NumberVector> iterator = removeSame.iterator();
		while(iterator.hasNext()) {
			numberVectors.add(iterator.next());
		}
		
		return numberVectors;
	}
	
	// 주어진 숫자들에서 n개 뽑아서 순열 만들기, 중복인정 x
	// 이렇게 말고, for문 사이사이에 중복여부를 검사해서 if로 for문을 안 들어가게 할 수도 있을듯
	public static ArrayList<NumberVector> getPermutation(float[] numbers, int dimension) {
		ArrayList<NumberVector> permutations = new ArrayList<NumberVector>();
	
		ArrayList<ArrayList<Float>> repeatedPermu = getRepeatedPermutation(numbers, dimension);
		
		// set을 이용하여 중복을 제거함. 가령 9,9,2,6 이런 경우 제거
		for(int i=0; i<repeatedPermu.size(); i++) {
			Set<Float> checkSame = new HashSet<Float>();
			checkSame.addAll(repeatedPermu.get(i));
			if(checkSame.size()==dimension) { 
				float numCombi[] = new float[dimension];
				for(int l=0; l<dimension; l++) {
					numCombi[l] = repeatedPermu.get(i).get(l);
				}
				NumberVector numberVector = new NumberVector(numCombi);
				permutations.add(numberVector);
			}
		}
		
		return permutations;
	}

	private static ArrayList<ArrayList<Float>> getRepeatedPermutation(float[] numbers, int dimension) {
		// 첫 시작 조합들 모음 Array시작. 길이 1짜리 어레이
		ArrayList<ArrayList<Float>> repeatedPermu = new ArrayList<ArrayList<Float>>();
		for(int i=0; i<numbers.length; i++) {
			ArrayList<Float> firstNode = new ArrayList<Float>();
			firstNode.add(numbers[i]);
			repeatedPermu.add(firstNode);
		}
		
		// 원하는 개수까지 조합을 만듦. 예를 들어 1~9에서 숫자 4개를 뽑으면 9^4개가 됨
		for(int i=1; i<dimension; i++) {
			repeatedPermu = pickOneMoreNum(repeatedPermu, numbers);
		}
		return repeatedPermu;
	}
	
	// 숫자순열을 만들 때, 숫자 하나를 추가
	public static ArrayList<ArrayList<Float>> pickOneMoreNum
								(ArrayList<ArrayList<Float>> input, float[] numbers) {
		ArrayList<ArrayList<Float>> output = new ArrayList<ArrayList<Float>>();
		for(int i=0; i<input.size(); i++) {
			for(int l=0; l<numbers.length; l++) {
				ArrayList<Float> node = new ArrayList<Float>();
				node.addAll(input.get(i));
				node.add(numbers[l]);
				output.add(node);
			}
		}
		return output;
	}
}
