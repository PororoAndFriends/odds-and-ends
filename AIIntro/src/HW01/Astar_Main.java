package HW01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Astar_Main {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		int count = 0;
		// 100회 이상 반복하고 해를 못구하면 해가 없는 것으로 결정
		
//		int[][] initial = {{2, 0, 3}, {1, 8, 4}, {7, 6, 5}};
		int[][] initial = {{8, 1, 3}, {0, 2, 4}, {7, 6, 5}};
		
		AStarNode initialNode = new AStarNode(initial, 1, 0);
		// 초기 노드 생성
		
		AStarNode testNode = initialNode;
		
		List<AStarNode> endNode = new ArrayList<>();
		endNode.add(initialNode);
		// 현재 가장 말단에 있는 노드들의 리스트
		
		
		while(true) {
			if(testNode.heuristic() == 0) {
				AStarNode.printGoal(testNode);
				long end = System.currentTimeMillis();
				System.out.println("걸린 시간(밀리초) : " + (end-start));
				break;
			}else if(count > 100) {
				System.out.println("해가 없습니다");
				break;
			}
			// 종료 조건 명시
			
			testNode.extend();
			for(AStarNode i : testNode.getChild()) {
				endNode.add(i);
			}	endNode.remove(testNode);
			// 설정된 노드 확장, 확장하는 노드는 더이상 말단 노드가 아니기에 삭제
			
			List<Integer> temp = new ArrayList<>();
			// f(n)이 가장 작은 노드 비교를 위한 리스트

			for(AStarNode i : endNode) {
				temp.add(i.getFn());
			}	// 말단 노드들의 f(n) 값들을 모두 temp 리스트에 담음
			
			int min = Collections.min(temp);
			int tempIndex = temp.indexOf(min);
			// temp 리스트에 있는 값들 중 가장 작은 값의 인덱스를 반환
			
			testNode = endNode.get(tempIndex);
			count ++;
			
		}

	}

}
