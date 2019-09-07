import java.lang.Math;

public class BSTNode<T extends Comparable<T>> {

	private T data;
	

	private BSTNode<T> left;		// a reference to the left child
	private BSTNode<T> right; 		// a reference to the right child
	
	public BSTNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	public void print() {
		T dataReturned = this.getData();
		System.out.println("Data: " + dataReturned);
	}
	
	public int getHeight() {
		int height;
		height = heightRec(this);
		return height;
	}
	
	
	
	private int heightRec(BSTNode<T> node) {
		
		if(node.getLeft() == null && node.getRight() == null)
			return 0;
		if(node.getLeft() != null && node.getRight() == null)
			return 1 + heightRec(node.getLeft());
		
		if(node.getLeft() == null && node.getRight() != null)
			return 1 + heightRec(node.getRight());
			
		return 1 + Math.max(heightRec(node.getLeft()), heightRec(node.getRight()));
	}

	

	// add a main method to test the code
	public static void main(String [] args) 
	{
		BSTNode<String> nodeOne = new BSTNode<String>("Node1");
		BSTNode<String> nodeTwo = new BSTNode<String>("Node2");
		BSTNode<String> nodeThree = new BSTNode<String>("Node3");
		BSTNode<String> node1 = new BSTNode<String>("Node1");
		BSTNode<String> node2 = new BSTNode<String>("Node2");
		BSTNode<String> node3 = new BSTNode<String>("Node3");
		
		nodeOne.print();
		nodeOne.setLeft(nodeTwo);
		nodeOne.setRight(nodeThree);
		
		nodeOne.getLeft().setLeft(node2);
	
		
		System.out.println(nodeOne.getHeight());
	}
}