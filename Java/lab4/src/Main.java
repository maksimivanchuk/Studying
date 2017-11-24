import java.io.*;
import java.util.*;

class Node<T extends Comparable>
{

    public T iData;
    public double dData;

    public Node<T> leftChild;
    public Node<T> rightChild;

    public Node() {
        this.iData = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(T id) {
        this.iData = id;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void displayNode()
    {
        System.out.print('{');
        System.out.print(iData);
        System.out.print("} ");
    }
}

class Tree <T extends Comparable> implements Cloneable {
    private Node<T> root;
    public Tree()
    {
        root = null;
    }

    @Override
    public Tree clone() {
        try {
            return (Tree)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }

    public Node<T> find(T key)
    {
        Node<T> current = root;
        while (current.iData != key)
        {
            if (key.compareTo(current.iData)< 0)
                current = current.leftChild;
            else
                current = current.rightChild;
            if (current == null)
                return null;
        }
        return current;
    }

    public void insert(T id) {
        Node<T> newNode = new Node(id);
        if (root == null)
            root = newNode;
        else
        {
            insertTo(newNode,root);
        }
    }

    private void insertTo(Node<T> newNode, Node current) {
        if (newNode.iData.compareTo(current.iData) < 0)
        {
            if (current.leftChild == null) {
                current.leftChild = newNode;
            }
            else {
                insertTo(newNode,current.leftChild);
            }
        }
        if (newNode.iData.compareTo(current.iData) > 0)
        {
            if (current.rightChild== null){
                current.rightChild= newNode;
            }
            else {
                insertTo(newNode,current.rightChild);
            }
        }
    }

    public boolean delete(T key)
    {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.iData != key)
        {
            parent = current;
            if (key.compareTo(current.iData) < 0)
            {
                isLeftChild = true;
                current = current.leftChild;
            }
            else
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }

        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }
        else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        else if
                (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        else
        {
            Node successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if
                    (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
        }
        return true;
    }


    private Node getSuccessor(Node delNode)
    {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while(current != null)
        {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if(successor != delNode.rightChild)
        {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        else
            successor.leftChild= delNode.leftChild;
        return successor;
    }

    public void traverse(int traverseType)
    {
        switch(traverseType)
        {
            case 1: preOrder(root);
                break;
            case 2: inOrder(root);
                break;
            case 3: postOrder(root);
                break;
        }
        System.out.println();
    }

    private void preOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }

    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks =30;
        boolean isRowEmpty = false;
        System.out.println();
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
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null || temp.rightChild != null)
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
            }
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println();
    }
}

public class Main{
    public static void main(String[] args) throws IOException {
        Tree<Student> theTree = new Tree<>();
        Student student1 = new Student("Valik",3,4);
        Student student2 = new Student("Vadim",1,4);
        Student student3 = new Student("Vlad",1,10);
        Student student4 = new Student("Ivan",3,7);
        Student student5 = new Student("Max",2,5);
        Student student6 = new Student("Anton",1,6);

        theTree.insert(student1);
        theTree.insert(student2);
        theTree.insert(student3);
        theTree.insert(student4);
        theTree.insert(student5);
        theTree.insert(student6);
        theTree.displayTree();

        System.out.print("Delete student2: ");
        theTree.delete(student2);
        theTree.displayTree();

        System.out.print("Root-left-right: ");
        theTree.traverse(1);

        System.out.print("Left- right - root: ");
        theTree.traverse(3);

        System.out.print("Left -root-right: ");
        theTree.traverse(2);

        System.out.print("Student :");
        theTree.find(student3).displayNode();

    }
}