package NumberCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class NumCombi {
	// �־��� ���ڵ�numbers���� �־��� ����dimension��ŭ ���������� �̾Ƽ� ����
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
		
		// permutations�� ���͸� TreeSet���� �޾ƿ��� ���ĵǰ� �ߺ��� �����
		TreeSet<NumberVector> removeSame = new TreeSet<NumberVector>();
		removeSame.addAll(permutations);
		
		// ����� numberVector�� �ű�
		ArrayList<NumberVector> numberVectors = new ArrayList<NumberVector>();
		Iterator<NumberVector> iterator = removeSame.iterator();
		while(iterator.hasNext()) {
			numberVectors.add(iterator.next());
		}
		
		return numberVectors;
	}
	
	// �־��� ���ڵ鿡�� n�� �̾Ƽ� ���� �����, �ߺ����� x
	// �̷��� ����, for�� ���̻��̿� �ߺ����θ� �˻��ؼ� if�� for���� �� ���� �� ���� ������
	public static ArrayList<NumberVector> getPermutation(float[] numbers, int dimension) {
		ArrayList<NumberVector> permutations = new ArrayList<NumberVector>();
	
		ArrayList<ArrayList<Float>> repeatedPermu = getRepeatedPermutation(numbers, dimension);
		
		// set�� �̿��Ͽ� �ߺ��� ������. ���� 9,9,2,6 �̷� ��� ����
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
		// ù ���� ���յ� ���� Array����. ���� 1¥�� ���
		ArrayList<ArrayList<Float>> repeatedPermu = new ArrayList<ArrayList<Float>>();
		for(int i=0; i<numbers.length; i++) {
			ArrayList<Float> firstNode = new ArrayList<Float>();
			firstNode.add(numbers[i]);
			repeatedPermu.add(firstNode);
		}
		
		// ���ϴ� �������� ������ ����. ���� ��� 1~9���� ���� 4���� ������ 9^4���� ��
		for(int i=1; i<dimension; i++) {
			repeatedPermu = pickOneMoreNum(repeatedPermu, numbers);
		}
		return repeatedPermu;
	}
	
	// ���ڼ����� ���� ��, ���� �ϳ��� �߰�
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
