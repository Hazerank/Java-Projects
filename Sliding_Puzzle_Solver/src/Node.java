
public class Node {
	Node[] children;
	String set;
	int pos;
	static int size;
	Node parrent;
	char type;
	
	public Node(String set,int pos,int size) {
		children = new Node[4];
		this.set = set;
		this.pos = pos;
		Node.size = size;
		this.parrent = null;
	}
	
	public Node(String set,int pos, char type, Node parrent) {
		children = new Node[4];
		this.set = set;
		this.pos = pos;
		this.parrent = null;
		this.type = type;
		this.parrent = parrent;
	}
}
