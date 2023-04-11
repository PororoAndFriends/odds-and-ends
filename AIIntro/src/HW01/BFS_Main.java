package HW01;

import java.util.LinkedList;
import java.util.Queue;

public class BFS_Main {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		int[][] initial = {{8, 1, 3}, {0, 2, 4}, {7, 6, 5}};
		
		BFSNode Node = new BFSNode(initial, 1, 0);
		// 시작노드
		
		Queue<BFSNode> levelN = new LinkedList<>();
		// BFS는 FIFO이기 떄문에 Queue 생성
		
		while(true) {
			
			if(Node.getLevel() > 100) {
				System.out.println("해가 없습니다");
				break;
			}else if(Node.isGoal()) {
				BFSNode.printGoal(Node);
				long end = System.currentTimeMillis();
				System.out.println("걸린 시간(밀리초) : " + (end - start));
				break;
			}
			// 종료 조건 명시
			
			
		Node.extend();
		
		for(BFSNode i :	Node.getChild()) {
			levelN.add(i);
		}
		
		Node = levelN.poll();
		
		}
	}
}
