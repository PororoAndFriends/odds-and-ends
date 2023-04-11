package HW01;

import java.util.ArrayList;
import java.util.List;

public class AStarNode {
	
	public static void printGoal(AStarNode goal){
		int count = goal.getLevel();
		while(true) {
			System.out.println("=====" + count + "회=====");
			goal.display();
			
			if(goal.parent == null)	break;
			
			goal = goal.parent;
			count--;

		}
	}
	
	private int[][] state;
	private int Man_Heu;	// h(n), 맨해튼 거리를 이용한 휴리스틱
	private int level;	// g(n)
	private int ZeroX;	// 0의 x의 좌표, 제일 왼쪽이 0
	private int ZeroY;	// 0의 y의 좌표, 제일 위쪽이 0
	private AStarNode parent = null;
	private List<AStarNode> child = new ArrayList<>();
	private int fn;	// g(n) + h(n)
	
	public AStarNode(int[][] state) {
		this.state = new int[state.length][state[0].length];
		for(int i=0;i<state.length;i++) {
			for(int j=0;j<state[0].length;j++) {
				this.state[i][j] = state[i][j];
		}			}
		// 배열 복사
	}
	
	public AStarNode(int[][] state, int  y, int x) {
		// 시작 노드 생성자
		this.state = new int[state.length][state[0].length];
		for(int i=0;i<state.length; i++) {
			for(int j=0;j<state[0].length;j++) {
				this.state[i][j] = state[i][j];
			}		}
		this.ZeroX = x;
		this.ZeroY = y;
		this.level = 0;
		this.Man_Heu = this.heuristic();
		this.fn = Man_Heu + level;
	}
	
	
	public int heuristic() {
		int count = 0;
		
		for(int i=0; i<this.state.length; i++) {	// 행
			for(int j=0; j<this.state[0].length; j++) {	// 열
					
				if(this.state[i][j] == 1)	count += i + j;
				else if(this.state[i][j] == 2) count += i + Math.abs(j-1);
				else if(this.state[i][j] == 3) count += i + Math.abs(j-2);
				
				else if(this.state[i][j] == 8) count += Math.abs(i-1) + j;
				else if(this.state[i][j] == 4) count += Math.abs(i-1) + Math.abs(j-2);
				
				else if(this.state[i][j] == 7) count += Math.abs(i-2) + j;
				else if(this.state[i][j] == 6) count += Math.abs(i-2) + Math.abs(j-1);
				else if(this.state[i][j] == 5) count += Math.abs(i-2) + Math.abs(j-2);

			}		}
		return count;
	}
	
	public AStarNode moveUp() {
		AStarNode MU = new AStarNode(this.state);
		MU.ZeroX = this.ZeroX;
		MU.ZeroY = this.ZeroY - 1;
		MU.level = this.level + 1;
		int temp = MU.state[MU.ZeroY][MU.ZeroX];
		MU.state[MU.ZeroY][MU.ZeroX] = 0;
		MU.state[this.ZeroY][this.ZeroX] = temp;
		MU.Man_Heu = MU.heuristic();
		MU.fn = MU.level + MU.Man_Heu;
		MU.parent = this;
		
		return MU;
	}
	
	public AStarNode moveDown() {
		AStarNode MD = new AStarNode(this.state);
		MD.ZeroX = this.ZeroX;
		MD.ZeroY = this.ZeroY + 1;
		MD.level = this.level + 1;
		MD.state[this.ZeroY][this.ZeroX] = MD.state[MD.ZeroY][MD.ZeroX];
		MD.state[MD.ZeroY][MD.ZeroX] = 0;
		MD.Man_Heu = MD.heuristic();
		MD.fn = MD.level + MD.Man_Heu;
		MD.parent = this;
		return MD;
	}
	
	public AStarNode moveRight() {
		AStarNode MR = new AStarNode(this.state);
		MR.level = this.level + 1;
		MR.ZeroX = this.ZeroX + 1;
		MR.ZeroY = this.ZeroY;
		int temp = MR.state[MR.ZeroY][MR.ZeroX];
		MR.state[MR.ZeroY][MR.ZeroX] = 0;
		MR.state[this.ZeroY][this.ZeroX] = temp;
		MR.Man_Heu = MR.heuristic();
		MR.fn = MR.level + MR.Man_Heu;
		MR.parent = this;
		return MR;
	}
	
	public AStarNode moveLeft() {
		AStarNode ML = new AStarNode(this.state);
		ML.ZeroX = this.ZeroX - 1;
		ML.ZeroY = this.ZeroY;
		ML.level = this.level + 1;
		int temp = ML.state[ML.ZeroY][ML.ZeroX];
		ML.state[ML.ZeroY][ML.ZeroX] = 0;
		ML.state[this.ZeroY][this.ZeroX] = temp;
		ML.Man_Heu = ML.heuristic();
		ML.fn = ML.level + ML.Man_Heu;
		ML.parent = this;
		return ML;
	}
	
	public void extend() {
		if(this.ZeroY != 0) {
			this.child.add(this.moveUp());
		}
		if(this.ZeroY != this.state.length-1) {
			this.child.add(this.moveDown());
		}
		if(this.ZeroX != this.state[0].length-1) {
			this.child.add(this.moveRight());
		}
		if(this.ZeroX != 0) {
			this.child.add(this.moveLeft());
		}
	}
	
	public void display() {
		for(int i=0;i<this.state.length;i++) {
			for(int j=0;j<this.state[0].length; j++) {
				System.out.print(this.state[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("g(x) + h(x) = " + this.level + " + " + this.Man_Heu  + " = " + (this.level + this.Man_Heu));
		}

	public int getFn() {
	return this.fn;
}

	public List<AStarNode> getChild() {
	return child;
	}
	
	public AStarNode getParent() {
		return parent;
	}
	
	public int getLevel() {
		return level;
	}
	
}

