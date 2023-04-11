package HW01;

public class DFS_Main {

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		int[][] initial = {{1, 0, 2}, {8, 4, 3}, {7, 6, 5}};
		
		DFSNode Node = new DFSNode(initial, 0, 1);
		// 탐색할 노드
		
		int count = 0;
		
		while(true) {
			if(Node.isGoal()) {
				DFSNode.printGoal(Node);
				System.out.println("탐색 횟수 : " + count);
				long end = System.currentTimeMillis();
				System.out.println("걸린 시간(밀리초) : " + (end - start));
				break;
			}else if(Node.getLevel() > 10) {
				Node = Node.getParent();
			}
			// 종료 조건 명시, 무한루프에 빠지지 않게 level 제한
			// level을 100으로 제한하면 95번째 실행에 해를 구하는 알고리즘이 됨
			// -> 깊이 우선 탐색은 최단경로를 찾아주지 않음
			
			Node.extend();
			
			if(Node.getPoint() != 4) {	// Node가 모두 확장되지 않았을 때
			Node = Node.getChild();
			}else {
				while(Node.getPoint()==4) {
					Node = Node.getParent();
				}
				Node.extend();
				Node = Node.getChild();
			}
			
			count++;
			
		}	// while문 종료

	}

}
