package com.nincolperez.Assignment5;
import java.util.Scanner;

public class TreeDriver {
    public static void main(String[] args) {
        Tree tree = new Tree();
        String word;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter word to insert in binary tree: ");
        word = input.next();

        //0.Inserting word characters into binary tree and displaying the binary tree
        for(int i = 0; i < word.length();i++) {
            tree.insert(word.charAt(i));
        }
        System.out.println("0.Displaying Binary Tree");
        tree.displayTree();

        //#1 Find Character's Ancestors
        System.out.println("");
        System.out.println("1.Displaying Character's Ancestors");
        System.out.println("Enter a character from this word to get its ancestors:");
        char findAncestor = input.next().charAt(0);
        Node c = new Node();
        c.cData = findAncestor;
        tree.findAncestorMain(c);   //Calling the findAncestor helper method
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");

        //#2 Display the leaves
        System.out.println("2.Displaying Leaves");
        tree.displayLeavesMain();
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");

        //#3 Count edges
        System.out.println("3.Displaying number of edges to get to a character");
        Node edge = new Node();
        System.out.println("Enter a character from this word to get number of edges to get to it:");
        edge.cData = input.next().charAt(0);
        System.out.println(tree.numOfEdgesMain(edge));
        System.out.println("-----------------------------------------------------------------------------------");

        //#4 Display nodes on the path to maximum the maximum value
        System.out.println("4.Displaying nodes on the path to the maximum value");
        tree.displayNodesToMaxMain();
        System.out.println("-----------------------------------------------------------------------------------");

        //#5 Display all the subtrees. A root with or without children : from left to right, through the entire tree
        //This includes leaves as one-node tree.
        System.out.println("5. Displaying subtrees");
        tree.displaySubtreesMain();
        System.out.println("-----------------------------------------------------------------------------------");

        ///tree.traverse(2);
    }


}
