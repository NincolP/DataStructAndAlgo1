package com.nincolperez.Assignment5;

import java.util.Stack;

public class Tree {
    private Node root;             // first node of tree
    // -------------------------------------------------------------
    public Tree()                  // constructor
    { root = null; }            // no nodes in tree yet
    // -------------------------------------------------------------
    public Node find(char key)      // find node with given key
    {                           // (assumes non-empty tree)
        Node current = root;               // start at root
        while(current.cData
                != key)        // while no match,
        {
            if(key < current.cData)         // go left?
                current = current.leftChild;
            else                            // or go right?
                current = current.rightChild;
            if(current == null)             // if no child,
                return null;                 // didn't find it
        }
        return current;                    // found it
    }  // end find()
    // -------------------------------------------------------------
    public void insert(char character)
    {
        Node newNode = new Node();    // make new node
        //newNode.iData = id;           // insert data
        newNode.cData = character;
        if(root==null)                // no node in root
            root = newNode;
        else                          // root occupied
        {
            Node current = root;       // start at root
            Node parent;
            while(true)                // (exits internally)
            {
                parent = current;
                if(character < current.cData)  // go left?
                {
                    current = current.leftChild;
                    if(current == null)  // if end of the line,
                    {                 // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                }  // end if go left
                else                    // or go right?
                {
                    current = current.rightChild;
                    if(current == null)  // if end of the line
                    {                 // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                }  // end else go right
            }  // end while
        }  // end else not root
    }  // end insert()
    // -------------------------------------------------------------
    public boolean delete(char ch) // delete node with given key
    {                           // (assumes non-empty list)
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while(current.cData != ch)        // search for node
        {
            parent = current;
            if(ch < current.cData)         // go left?
            {
                isLeftChild = true;
                current = current.leftChild;
            }
            else                            // or go right?
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null)             // end of the line,
                return false;                // didn't find it
        }  // end while
        // found node to delete

        // if no children, simply delete it
        if(current.leftChild==null &&
                current.rightChild==null)
        {
            if(current == root)             // if root,
                root = null;                 // tree is empty
            else if(isLeftChild)
                parent.leftChild = null;     // disconnect
            else                            // from parent
                parent.rightChild = null;
        }

        // if no right child, replace with left subtree
        else if(current.rightChild==null)
            if(current == root)
                root = current.leftChild;
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

            // if no left child, replace with right subtree
        else if(current.leftChild==null)
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        else  // two children, so replace with inorder successor
        {
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);

            // connect parent of current to successor instead
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            // connect successor to current's left child
            successor.leftChild = current.leftChild;
        }  // end else two children
        // (successor cannot have a left child)
        return true;                                // success
    }  // end delete()
    // -------------------------------------------------------------
    // returns node with next-highest value after delNode
    // goes to right child, then right child's left descendents
    private Node getSuccessor(Node delNode)
    {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;   // go to right child
        while(current != null)               // until no more
        {                                 // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild;      // go to left child
        }
        // if successor not
        if(successor != delNode.rightChild)  // right child,
        {                                 // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    // -------------------------------------------------------------
    public void traverse(int traverseType)
    {
        switch(traverseType)
        {
            case 1: System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2: System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3: System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }
    // -------------------------------------------------------------
    private void preOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.cData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
    // -------------------------------------------------------------
    private void inOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.cData + " ");
            inOrder(localRoot.rightChild);
        }
    }
    // -------------------------------------------------------------
    private void postOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.cData + " ");
        }
    }
    // -------------------------------------------------------------
    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while(isRowEmpty==false)
        {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');

            while(globalStack.isEmpty()==false)
            {
                Node temp = (Node)globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.cData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if(temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }  // end displayTree()

    //*******ASSIGNMENT METHODS*****************------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
    //#1 FIND ALL ANCESTORS
    //Helper method so user only have to enter the character
    public void findAncestorMain(Node find) {
        //Node current = root;
        findAncestor(root, find);
    }
    //Recursive method
    public void findAncestor(Node current, Node c) {
        if(c.cData == current.cData ) {
            return;//Base case. When character is found stop
        }
         else {
            if (c.cData < current.cData) {              //find the character node and visit(print)
                findAncestor(current.leftChild, c);     //each current node. Current node will change to
            }
            else {
                findAncestor(current.rightChild, c);
            }
            System.out.print(current.cData + " ");  //next node based on the search
        }
    }
//--------------------------------------------------------------------------------------------------------------------
    //#2 DISPLAYS LEAVES LEFT TO RIGHT
    //Display leaves Helper
    public void displayLeavesMain() {
        displayLeaves(root);
    }
    //Recursive method
    public void displayLeaves(Node r) {
        if(r != null) {
            displayLeaves(r.leftChild);
                if(r.leftChild == null && r.rightChild == null)
                System.out.print(r.cData + " ");
            displayLeaves(r.rightChild);
        }
    }
//---------------------------------------------------------------------------------------------------------------
    //#3 NUMBER OF EDGES
    //Helper method
    public int numOfEdgesMain(Node n) {

      if(find(n.cData) == null) {
          return 0;
      }
      else {
          Node current = root;
          return numOfEdges(current, n);
      }
    }
    //Counter to store number of edges.
    int edgeSum = 0;
    //Recursive method
    public int numOfEdges(Node current, Node c) {
        if (c.cData != current.cData) {
            if (c.cData < current.cData) {
                numOfEdges(current.leftChild, c);
            }
            else {
                numOfEdges(current.rightChild, c);
            }
            edgeSum++;
        }
        return edgeSum;
    }
    //-----------------------------------------------------------------------------------------------------------
    //#4 Displays ALL NODES ON THE PATH TO THE MAXIMUM VALUE
    //Helper Method
    public void displayNodesToMaxMain() {
        //Node c = root;
        displayNodesToMax(root);
        System.out.println();
    }
    //Recursive method
    public void displayNodesToMax(Node current) {
        if(current.rightChild == null) {
            System.out.print(current.cData + " ");
            return;
        }
        System.out.print(current.cData + " ");
        displayNodesToMax(current.rightChild);
    }
    //------------------------------------------------------------------------------------------------------------------
    //#5 Display all the subtrees
    //Helper method
    public void displaySubtreesMain() {
        Node current = root;
        displaySubtrees(current);
    }
    public void displaySubtrees(Node current) {
        if(current.rightChild == null && current.leftChild == null) {
            System.out.println(current.cData + " ");
        }
        else if(current.rightChild == null) {
            System.out.println(current.cData + " " + current.leftChild.cData);
            displaySubtrees(current.leftChild);
        }
        else if(current.leftChild == null) {
            System.out.println(current.cData + " " + current.rightChild.cData);
            displaySubtrees(current.rightChild);
        }
        else {
            System.out.println(current.cData + " " + current.leftChild.cData + " " + current.rightChild.cData);
            displaySubtrees(current.leftChild);
            displaySubtrees(current.rightChild);
        }
    }
}
