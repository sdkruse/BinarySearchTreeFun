
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
import java.lang.Math;

/**
 * 
 * @author SKruse A class that implements a binary tree node structure.
 * @param <T> is the type of data contained in the node.
 */
public class BSTNode<T extends Comparable<T>> {

	private T data;

	private BSTNode<T> left; // a reference to the left child
	private BSTNode<T> right; // a reference to the right child

	public BSTNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	/**
	 * Get the data at the node.
	 * 
	 * @return
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data at the node.
	 * 
	 * @return
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Return the left child of the node.
	 * 
	 * @return
	 */
	public BSTNode<T> getLeft() {
		return left;
	}

	/**
	 * Set the left child on the node.
	 * 
	 * @return
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * Return the right child of the current node.
	 * 
	 * @return
	 */
	public BSTNode<T> getRight() {
		return right;
	}

	/**
	 * Set the right child of the current node.
	 * 
	 * @return
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}

	/**
	 * This returns the height of a node relative to it's children from a recursive
	 * algorithm.
	 * 
	 * @return an integer that is the height of this node.
	 */
	public int getHeight() {
		int height;
		height = heightRec(this);
		return height;
	}

	/**
	 * Returns the height of a node using recursion.
	 * 
	 * @param node the node to find the height of.
	 * @return an integer which is the height of the current node.
	 */
	private int heightRec(BSTNode<T> node) {

		if (node.getLeft() == null && node.getRight() == null)
			return 1;
		if (node.getLeft() != null && node.getRight() == null)
			return 1 + heightRec(node.getLeft());

		if (node.getLeft() == null && node.getRight() != null)
			return 1 + heightRec(node.getRight());

		return 1 + Math.max(heightRec(node.getLeft()), heightRec(node.getRight()));
	}

	// add a main method to test the code
	/**
	 * THis just contains some tests to show the above methods work.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// test height methods
		BSTNode<String> nodeOne = new BSTNode<String>("Node1");
		BSTNode<String> nodeTwo = new BSTNode<String>("Node2");
		BSTNode<String> nodeThree = new BSTNode<String>("Node3");
		BSTNode<String> node1 = new BSTNode<String>("Node1");
		BSTNode<String> node2 = new BSTNode<String>("Node2");
		BSTNode<String> node3 = new BSTNode<String>("Node3");

		nodeOne.setLeft(nodeTwo);

		nodeOne.setRight(nodeThree);

		nodeThree.setLeft(node1);

		node1.setRight(node2);

		node2.setRight(node3);

		System.out.println(nodeOne.getHeight());
	}
}