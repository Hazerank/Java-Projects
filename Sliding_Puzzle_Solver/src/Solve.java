import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solve {
	Node root;
	Set<String> history;
	
	public Solve(String input,int[] tempArray){
		this.root = new Node(input,tempArray[0],tempArray[1]);
		history = new HashSet<String>();
	}
	
	
	public String solve() {
		return BFS();
	}
	
	
	private String BFS() {
		Queue<Node> que = new ArrayDeque<Node>();
		que.offer(this.root);
		Node temp = null;
		while(!que.isEmpty()) {
			temp = que.poll();
			if(isDone(temp)) return finish(temp);
			addChildren(temp);
			for(int i = 0; i < 4 ; i++) {
				if(temp.children[i] != null) que.offer(temp.children[i]);
			}
		}
		return "N";
	}

	
	private void addChildren(Node node) {
		history.add(node.set);
		Node newNode;
		int pos = node.pos;  int size = Node.size;
		String newSet;       String set = node.set;
		char type = node.type;
		if(pos % size != 0 && type != 'R') {
			newSet = arrange(set, pos-1);
			if(!history.contains(newSet) ) {
				newNode =  new Node(newSet,pos-1,'L',node); 
				node.children[0] = newNode;
			}
		}
		
		if(pos % size != size - 1 && type != 'L') {
			newSet = arrange(set, pos + 1);
			if(!history.contains(newSet)) {
				newNode =  new Node(newSet,pos+1,'R',node);
				node.children[1] = newNode;
			}
		}
		if(pos / size != 0 && type != 'D') {
			newSet = arrange(set, pos - size);
			if(!history.contains(newSet)) {
				newNode =  new Node(newSet,pos-size,'U',node);
				node.children[2] = newNode;
			}
		}
		
		if(pos / size != size - 1 && type != 'U' ) {
			newSet = arrange(set, pos + size);
			if(!history.contains(newSet) ) {
				newNode =  new Node(newSet,pos+size,'D',node);
				node.children[3] = newNode;
			}
		}
	}
	private String finish(Node node) {
		if(node == root) return "";
		return finish(node.parrent) + node.type;
	}
	
	private boolean isDone(Node node) {
		
		if(!node.set.endsWith("0")) return false;

		for(int i = 0 ; i < node.set.length()-2;i++) {
			if(node.set.charAt(i) + 1 != node.set.charAt(i+1))	return false;
		}

		return true;
	}
	
	private String arrange(String s,int index) {
		
		char temp = s.charAt(index);
		return s.replace('0','!').replace(temp,'0').replace('!',temp);
	}
	
	
}
