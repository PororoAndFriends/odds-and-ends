package HW01;

public class DFSNode {
	
	public static void printGoal(DFSNode goal){
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
	private int level;
	private int point = 0;	// 확장하는 노드(0 : 위, 1 : 아래, 2 : 오른쪽, 3 : 왼쪽, 4 : 해가 없음)
	private int ZeroX;	// 0의 x의 좌표, 제일 왼쪽이 0
	private int ZeroY;	// 0의 y의 좌표, 제일 위쪽이 0
	private DFSNode parent = null;
	private DFSNode child;
	int cantExtend = 5;	
	
	public DFSNode(int[][] state) {
		this.state = new int[state.length][state[0].length];
		for(int i=0;i<state.length;i++) {
			for(int j=0;j<state[0].length;j++) {
				this.state[i][j] = state[i][j];
		}			}
		// 배열 복사
	}
	
	public DFSNode(int[][] state, int  y, int x) {
		// 시작 노드 생성자
		this.state = new int[state.length][state[0].length];
		for(int i=0;i<state.length; i++) {
			for(int j=0;j<state[0].length;j++) {
				this.state[i][j] = state[i][j];
			}		}
		this.ZeroX = x;
		this.ZeroY = y;
		this.level = 0;
	}
	
	
	public boolean isGoal() {
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
		if(count == 0)	return true;
		else return false;
	}
	
	
	public DFSNode moveUp() {
		DFSNode MU = new DFSNode(this.state);
		MU.ZeroX = this.ZeroX;
		MU.ZeroY = this.ZeroY - 1;
		MU.level = this.level + 1;
		int temp = MU.state[MU.ZeroY][MU.ZeroX];
		MU.state[MU.ZeroY][MU.ZeroX] = 0;
		MU.state[this.ZeroY][this.ZeroX] = temp;
		MU.parent = this;
		MU.cantExtend = 1;
		
		return MU;
	}
	
	public DFSNode moveDown() {
		DFSNode MD = new DFSNode(this.state);
		MD.ZeroX = this.ZeroX;
		MD.ZeroY = this.ZeroY + 1;
		MD.level = this.level + 1;
		int temp = MD.state[MD.ZeroY][MD.ZeroX];
		MD.state[this.ZeroY][this.ZeroX] = temp;
		MD.state[MD.ZeroY][MD.ZeroX] = 0;
		MD.parent = this;
		MD.cantExtend = 0;
		
		return MD;
	}
	
	public DFSNode moveRight() {
		DFSNode MR = new DFSNode(this.state);
		MR.level = this.level + 1;
		MR.ZeroX = this.ZeroX + 1;
		MR.ZeroY = this.ZeroY;
		int temp = MR.state[MR.ZeroY][MR.ZeroX];
		MR.state[MR.ZeroY][MR.ZeroX] = 0;
		MR.state[this.ZeroY][this.ZeroX] = temp;
		MR.parent = this;
		MR.cantExtend = 3;
		
		return MR;
	}
	
	public DFSNode moveLeft() {
		DFSNode ML = new DFSNode(this.state);
		ML.ZeroX = this.ZeroX - 1;
		ML.ZeroY = this.ZeroY;
		ML.level = this.level + 1;
		int temp = ML.state[ML.ZeroY][ML.ZeroX];
		ML.state[ML.ZeroY][ML.ZeroX] = 0;
		ML.state[this.ZeroY][this.ZeroX] = temp;
		ML.parent = this;
		ML.cantExtend = 2;
		
		return ML;
	}
	
	public void extend() {
		
		if(this.point == 0) {
			if(this.ZeroY != 0 && cantExtend != point) {
				this.child = this.moveUp();
				this.point++;
			}else {
				this.point++;
				this.extend();
			}
		}else if(this.point == 1) {
			if(this.ZeroY != this.state.length-1 && cantExtend != point) {
				this.child = this.moveDown();
				this.point++;
			}else {
				this.point++;
				this.extend();
			}
		}else if(this.point == 2) {
			if(this.ZeroX != this.state[0].length-1 && cantExtend != point) {
				this.child = this.moveRight();
				this.point++;
			}else {
				this.point++;
				this.extend();
			}
		}else if(this.point == 3) {
			if(this.ZeroX != 0 && cantExtend != point) {
				this.child = this.moveLeft();
				this.point++;
			}else {
				this.point++;
			}
		}
	}
	
	
	
	public void display() {
		for(int i=0;i<this.state.length;i++) {
			for(int j=0;j<this.state[0].length; j++) {
				System.out.print(this.state[i][j] + " ");
			}
			System.out.println();
		}
		}

	public DFSNode getChild() {
	return child;
	}
	
	public DFSNode getParent() {
		return parent;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getPoint() {
		return point;
	}
	
}

