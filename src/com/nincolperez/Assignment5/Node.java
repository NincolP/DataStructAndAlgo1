package com.nincolperez.Assignment5;

public class Node {
    public char cData;           // character data item
    public Node leftChild;         // this node's left child
    public Node rightChild;        // this node's right child

    public void displayNode()    {  // display ourself
        System.out.print(cData + " ");
    }
}
