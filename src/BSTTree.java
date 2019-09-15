//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Binary Tree Simulations: Homework 1
// Files:           TreeSimulations.java, BSTTree,java, BSTNode.java
// Course:          (course number, term, and year)
//
// Author:          Sam Kruse
// Email:           sdkruse@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NA
// Partner Email:   NA
// Partner Lecturer's Name: NA
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NA
// Online Sources:  tinyurl.com/AK-02-20-19 Andrew gave this to us
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * The implementation of our binary search tree.
 * @author SKruse
 *
 * @param <T>
 */
public class BSTTree<T extends Comparable<T>> implements BinarySearchTreeADT<T> {

	private BSTNode<T> root;
	private int size;

	public BSTTree() {
		root = null;
		size = 0;
	}
/**
 * If the node is not a repeat, add it to the tree using recursion.
 */
	public void insert(T element) {
		if (!this.contains(element)) {
			root = insert(root, element);
			size++;
		}
	}
/**
 * The recursive insertion of a node.
 * @param current the current node.
 * @param element is the data to add.
 * @return
 */
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
	/**
	 * This removes an element from the tree.
	 */
	public void remove(T element) {
		Boolean removed = remove(element, this.root);
		if (removed) {
			size--;
		}
	}
/**
 * The most complicated algorithm in the assignment.
 * If the element to be removed is a leaf node, just set its parent's pointer
 *  to null. If it has on child, just set the parent's point to its child.
 *  If the node to be removed has two children, find its in order successor.
 * 
 * @param element
 * @param current
 * @return
 */
	public boolean remove(T element, BSTNode<T> current) {
		
		BSTNode<T> par = null;
		BSTNode<T> cur = current;
		boolean isLeft = false;
		boolean found = false;

		while (cur != null) {
			if (element.compareTo(cur.getData()) == 0) {// you found the node
				found = true;
				if (cur.getLeft() == null && cur.getRight() == null) { // if the current node has no kids
					if (cur.getData().compareTo(this.root.getData()) == 0) {
						// handle the case when current is the root
						this.root = null;
					} else {// if cur is a leaf node
						cur = null;
						if (isLeft) {// if current is the left child
							par.setLeft(null);
						} else {
							par.setRight(null);
						}
					}
				} else if (cur.getLeft() != null && cur.getRight() == null) {// if current has one child and that child
																				// is the left child

					if (isLeft) {// if the current is the left child
						par.setLeft(cur.getLeft());
						cur = null;
					} else {// if current is the right child
						par.setRight(cur.getLeft());
						cur = null;
					}
				} else if (cur.getLeft() == null && cur.getRight() != null) {// or if current's has only one right child

					if (isLeft) {// if the current is the left child
						par.setLeft(cur.getRight());
					} else {// if current is the right child
						par.setRight(cur.getRight());
					}
					cur = null;
				} else {
					//if cur has two children, find in order successor, replace current with successor
					BSTNode<T> suc = cur.getRight();
					while (suc.getLeft() != null) {
						suc = suc.getLeft();
					}

					if (isLeft) {
						par.setLeft(suc);
					} else {
						par.setRight(suc);
					}

					suc.setLeft(cur.getLeft());
					remove(suc.getData(), suc.getRight());

					cur = null;

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

		return found;
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
		if (current == null) {
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
		if (current == null) {
			return result;
		}
		result = inOrderTraversal(result, current.getLeft());
		result = result + " " + current.getData();
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
		/**
		 * System.out.println("preOrder"); System.out.println(tree.inOrderTraversal());
		 * tree.remove(139); System.out.println(tree.inOrderTraversal());
		 */
		System.out.println(tree.inOrderTraversal());
		System.out.println("height " + tree.getHeight());
		tree.remove(13);
		tree.printSideways();
		System.out.println(tree.inOrderTraversal());
		System.out.println("height " + tree.getHeight());
		System.out.println("size = " + tree.getSize());

	}

}
