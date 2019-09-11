
public class BSTTree<T extends Comparable<T>> implements BinarySearchTreeADT<T> {

	private BSTNode<T> root;
	private int size;

	public BSTTree() {
		root = null;
		size = 0;
	}

	public void insert(T element) {
		root = insert(root, element);
		size++;
	}

	private BSTNode<T> insert(BSTNode<T> current, T element) {
		if (current == null) {
			current = new BSTNode<T>(element);
		} else if (element.compareTo(current.getData()) < 0) {
			current.setLeft(insert(current.getLeft(), element));
		} else if (element.compareTo(current.getData()) > 0) {
			current.setRight(insert(current.getRight(), element));
		}

		return current;
	}

	@Override
	public void remove(T element) {

		BSTNode<T> par = null;
		BSTNode<T> cur = this.root;
		boolean isLeft = false;

		while (cur != null) {
			if (element.compareTo(cur.getData()) == 0) {// you found the node
				if (cur.getLeft() == null && cur.getRight() == null) { // if the current node has no kids
					if (cur.getData().compareTo(this.root.getData()) == 0) {// handle the case when current is the root
						this.root = null;
					} else {// if cur is a leaf node
						if (isLeft) {// if current is the left child
							par.setLeft(null);
						} else {
							par.setRight(null);
						}
					}
				}else if (cur.getLeft() != null && cur.getRight() == null) {// if current has one child and that child is the left child
					if (isLeft) {// if the current is the left child
						par.setLeft(cur.getLeft());
					} else {// if current is the right child
						par.setRight(cur.getLeft());
					}
				}else if (cur.getLeft() == null && cur.getRight() != null) {// or if current's has only one right child
						if (isLeft) {// if the current is the left child
							par.setLeft(cur.getLeft());
						} else {// if current is the right child
							par.setRight(cur.getLeft());
						}
					}else {
						
						BSTNode<T> suc = cur.getRight();
						while(suc.getLeft() != null) {
							suc = suc.getLeft();
						}
						
						if(isLeft) {
							par.setLeft(suc);
							this.remove(suc.getData());
						}else {
							par.setRight(suc);
							this.remove(suc.getData());
						}
					}
			} else if (element.compareTo(cur.getData()) < 0) {
				par = cur;
				cur = cur.getLeft();
				isLeft = true;
			} else {
				par = cur;
				cur = cur.getRight();
				isLeft = false;
			}

		}

	}

	@Override
	public boolean contains(T element) {
		BSTNode<T> cur = root;
		while (cur != null) {
			if (cur.getData().compareTo(element) == 0) {
				return true;
			} else if (cur.getData().compareTo(element) < 0) {
				cur = cur.getLeft();
			} else {
				cur = cur.getRight();
			}
		}

		return false;
	}

	@Override
	public int getHeight() {
		return this.root.getHeight();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String preOrderTraversal() {
		String result = "";
		return preOrderTraversal(result, this.root);
	}
	
	private String preOrderTraversal(String result, BSTNode<T> current) {
		if(current == null) {
			return result;
		}
		
		result = result + " " + current.getData();
		result = preOrderTraversal(result, current.getLeft());		
		result = preOrderTraversal(result, current.getRight());
		
		return result;
	}

	@Override
	public String inOrderTraversal() {
		String result = "";
		return inOrderTraversal(result, this.root);
	}
	
	private String inOrderTraversal(String result, BSTNode<T> current) {
		if(current == null) {
			return result;
		}
		
		result = inOrderTraversal(result, current.getLeft());
		result = result + current.getData();
		result = inOrderTraversal(result, current.getRight());
		
		return result;
	}

	public void postOrderTraversal() {
		postOrderTraversal(this.root);
	}

	private void postOrderTraversal(BSTNode<T> current) {
		if (current != null) {
			postOrderTraversal(current.getLeft());
			postOrderTraversal(current.getRight());
			System.out.println(current.getData());
		}
	}

	/*
	 * you can find this page at tinyurl.com/AK-02-20-19 prints a tree sideways on
	 * your screen this is meant to help you debug your program source: Building
	 * Java Programs, 4th Ed., by Reges and Stepp, Ch. 17
	 */
	public void printSideways() {
		System.out.println("------------------------------------------");
		printSideways(root, "");
		System.out.println("------------------------------------------");
	}

// private recursive helper method for printSideways above
	private void printSideways(BSTNode<T> current, String indent) {
		if (current != null) {
			printSideways(current.getRight(), indent + "    ");
			System.out.println(indent + current.getData());
			printSideways(current.getLeft(), indent + "    ");
		}
	}

	public static void main(String[] args) {
		BSTTree<Integer> tree = new BSTTree<Integer>();
		tree.insert(33);
		tree.insert(22);
		tree.insert(12);
		tree.insert(13);
		tree.insert(5);
		tree.insert(17);
		tree.insert(139);
		tree.printSideways();
		System.out.println("preOrder");
		System.out.println(tree.preOrderTraversal());
	
	}

}
