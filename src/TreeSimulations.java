
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
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * This class simulates the creation of many trees. Its main method will show
 * some summary statistics on our simulations.
 * 
 * @author SKruse
 *
 */
public class TreeSimulations {
	/**
	 * This class creates and iterates the through the number of trees defined by
	 * the user.
	 * 
	 * @param randSeed  is the seed for the number generator
	 * @param treeSize  is the number of nodes.
	 * @param randRange the greatest number that can be added.
	 * @param numTrees  is the number of trees to create.
	 */
	public static void testManyTrees(int randSeed, int treeSize, int randRange, int numTrees) {
		// initial setup
		BSTTree<Integer> tree;
		Random rnd = new Random(randSeed); // Random with seed

		int minHeight = 0;
		int maxHeight = 0;
		double avgHeight = 0.00;
		int curHeight = 0;

		// iterate numTrees times

		for (int i = 0; i < numTrees; i++) {

			// instantiate a new tree each time
			tree = new BSTTree<Integer>();

			// continue while the size of this tree is less than treeSize

			while (tree.getSize() < treeSize) {
				// use this code to insert a random Integer into a tree
				tree.insert(rnd.nextInt(randRange));
			}

			// after filling, if the tree tree's height equals its size, print out the tree
			int height = tree.getHeight();
			if (height == tree.getSize()) {
				tree.printSideways();
			}
			curHeight = tree.getHeight();
			// initialize heights
			if (i == 0) {
				maxHeight = curHeight;
				minHeight = curHeight;
				double avg = curHeight;
				avgHeight = avg;
			}

			// update data on max, min, and average height
			if (maxHeight < curHeight) {
				maxHeight = curHeight;
			}

			if (minHeight > curHeight) {
				minHeight = curHeight;
			}
			// calculate a moving average.
			if (i > 0) {
				double j = i;
				avgHeight = (curHeight + j * avgHeight) / (j + 1);
			}

		}
		DecimalFormat avgFormat = new DecimalFormat("####.###");
		// System.out.println(df.format(PI));
		///// after all simulated trees made, output statistics
		System.out.println("min height was : " + minHeight);
		System.out.println("max height was : " + maxHeight);
		System.out.println("avg height was : " + avgFormat.format(avgHeight));
	} // testManyTrees

	/**
	 * This will get the inputs from the user for the simulation.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the BST Simulator.");
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter the Random seed number: ");
		int randSeed = scnr.nextInt();
		System.out.println("Enter the number of Integers to be placed in each tree: ");
		int treeSize = scnr.nextInt();
		System.out.println("Enter the maximum random integer to be generated: ");
		int randRange = scnr.nextInt();
		System.out.println("Enter the number of trees to be simulated: ");
		int numTrees = scnr.nextInt();
		testManyTrees(randSeed, treeSize, randRange, numTrees);
		scnr.close();
	} // main
} // class
// see sample run below
