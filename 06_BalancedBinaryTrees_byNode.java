import java.util.*;

class BSTNode
{
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок	
    public int     Level; // глубина узла
	
    public BSTNode(int key, BSTNode parent)
     {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
      }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);
        if (a.length == 0) {
            return;
        }
        Root = new BSTNode(0, null);
        Root.Level = 0;
        recursionForGenerateTree(Root, 0, a.length, a);
    }

    private void recursionForGenerateTree(BSTNode currentNode, int leftBound, int rightBound, int[] array) {
        if (currentNode.Parent != null) {
            currentNode.Level = currentNode.Parent.Level + 1;
        }
        int bounds = rightBound - leftBound;
        if (bounds > 3) {
            int median = (bounds - 1) / 2;
            currentNode.NodeKey = array[median];
            currentNode.LeftChild = new BSTNode(0, currentNode);
            currentNode.RightChild = new BSTNode(0, currentNode);
            recursionForGenerateTree(currentNode.LeftChild, leftBound, median, array);
            recursionForGenerateTree(currentNode.RightChild, median + 1, rightBound, array);
        }
        if (bounds == 3) {
            currentNode.NodeKey = array[rightBound - 2];
            currentNode.LeftChild = new BSTNode(array[leftBound], currentNode);
            currentNode.LeftChild.Level = currentNode.Level + 1;
            currentNode.RightChild = new BSTNode(array[rightBound - 1], currentNode);
            currentNode.RightChild.Level = currentNode.Level + 1;
        }
        if (bounds == 2) {
            currentNode.NodeKey = array[rightBound - 1];
            currentNode.LeftChild = new BSTNode(array[leftBound], currentNode);
            currentNode.LeftChild.Level = currentNode.Level + 1;
        }
        if (bounds == 1) {
            currentNode.NodeKey = array[leftBound];
        }
    }

    public boolean IsBalanced(BSTNode root_node) {
        return isBalancedPartOfTree(root_node) > 0; // сбалансировано ли дерево с корнем root_node
    }

    public static int isBalancedPartOfTree(BSTNode currentRoot) {
        if (currentRoot == null) {
            return 0;
        }
        int leftPartOfTree = isBalancedPartOfTree(currentRoot.LeftChild);
        if (leftPartOfTree == -1) {
            return -1;
        }
        int rightPartOfTree = isBalancedPartOfTree(currentRoot.RightChild);
        if (rightPartOfTree == -1) {
            return -1;
        }
        if (Math.abs(leftPartOfTree - rightPartOfTree) > 1) {
            return -1;
        } else
            return Math.max(leftPartOfTree, rightPartOfTree) + 1;
    }
}
