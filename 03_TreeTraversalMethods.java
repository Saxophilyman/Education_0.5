import java.io.*;
import java.util.*;

class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}


class BSTFind<T> {
    public BSTNode<T> Node;
    public boolean NodeHasKey;
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }

    public BSTFind(BSTNode<T> Node, boolean NodeHasKey) {
        this.Node = Node;
        this.NodeHasKey = NodeHasKey;
    }

    public BSTFind(BSTNode<T> Node, boolean NodeHasKey, boolean ToLeft) {
        this.Node = Node;
        this.NodeHasKey = NodeHasKey;
        this.ToLeft = ToLeft;
    }
}

class BST<T> {
    BSTNode<T> Root; 
    private int countOfNode;

    public BST(BSTNode<T> node) {
        Root = node;
        countOfNode = 1;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        if (Root == null) {
            return new BSTFind<>();
        }
        BSTFind<T> find;
        find = recursionForBSTFind(Root, key);
        return find;
    }

    private BSTFind<T> recursionForBSTFind(BSTNode<T> node, int key) {
        if (node.NodeKey == key) {
            return new BSTFind<T>(node, true);
        }
        BSTFind<T> find = null;
        if (node.NodeKey > key) {
            if (node.LeftChild == null) {
                return new BSTFind<T>(node, false, true);
            }
            find = recursionForBSTFind(node.LeftChild, key);
        }
        if (node.NodeKey < key) {
            if (node.RightChild == null) {
                return new BSTFind<T>(node, false, false);
            }
            find = recursionForBSTFind(node.RightChild, key);
        }
        return find;
    }

    public boolean AddKeyValue(int key, T val) {
        BSTFind<T> find = FindNodeByKey(key);
        if (find.Node == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }
        // если ключ уже есть
        if (find.NodeHasKey) {
            return false;
        }
        if (find.ToLeft) {
            find.Node.LeftChild = new BSTNode<>(key, val, find.Node);
            countOfNode++;
            return true;
        } else
            find.Node.RightChild = new BSTNode<>(key, val, find.Node);
        countOfNode++;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        if (FromNode == null || !FindNodeByKey(FromNode.NodeKey).NodeHasKey) {
            return null;
        }
        BSTFind<T> findFromNode = FindNodeByKey(FromNode.NodeKey);
        if (FindMax) {
            return findMax(findFromNode.Node);
        }
        return findMin(findFromNode.Node);
    }

    private BSTNode<T> findMax(BSTNode<T> fromNode) {
        BSTNode<T> max = fromNode;
        if (fromNode.RightChild != null) {
            max = findMax(fromNode.RightChild);
        }
        return max;
    }

    private BSTNode<T> findMin(BSTNode<T> fromNode) {
        BSTNode<T> min = fromNode;
        if (fromNode.LeftChild != null) {
            min = findMin(fromNode.LeftChild);
        }
        return min;
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> findNodeForDelete = FindNodeByKey(key);
        if (findNodeForDelete.Node == null || !findNodeForDelete.NodeHasKey) {
            return false;
        }
        if (findNodeForDelete.Node == Root && findNodeForDelete.Node.LeftChild == null && findNodeForDelete.Node.RightChild == null) {
            Root = null;
            return true;
        }
        if (findNodeForDelete.Node == Root && (findNodeForDelete.Node.LeftChild == null || findNodeForDelete.Node.RightChild == null)) {
            if (findNodeForDelete.Node.LeftChild == null) {
                Root = findNodeForDelete.Node.RightChild;
                Root.Parent = null;
                countOfNode--;
                return true;
            }
            Root = findNodeForDelete.Node.LeftChild;
            Root.Parent = null;
            countOfNode--;
            return true;
        }

        if (findNodeForDelete.Node == Root && findNodeForDelete.Node.LeftChild != null && findNodeForDelete.Node.RightChild != null) {
            BSTNode<T> minNodeForRoot = FinMinMax(findNodeForDelete.Node.RightChild, false);
            if (minNodeForRoot != Root.RightChild) {
                minNodeForRoot.RightChild = Root.RightChild;
            }
            minNodeForRoot.Parent.LeftChild = null;
            minNodeForRoot.LeftChild = Root.LeftChild;
            Root = minNodeForRoot;
            Root.Parent = null;
            countOfNode--;
            return true;
        }

        if (findNodeForDelete.Node.LeftChild == null && findNodeForDelete.Node.RightChild == null) {
            if (findNodeForDelete.Node.Parent.LeftChild == findNodeForDelete.Node) {
                findNodeForDelete.Node.Parent.LeftChild = null;
                countOfNode--;
                return true;
            }
            findNodeForDelete.Node.Parent.RightChild = null;
            countOfNode--;
            return true;
        }

        if (findNodeForDelete.Node.LeftChild == null) {
            findNodeForDelete.Node.RightChild.Parent = findNodeForDelete.Node.Parent;
            if (findNodeForDelete.Node.Parent.LeftChild == findNodeForDelete.Node) {
                findNodeForDelete.Node.Parent.LeftChild = findNodeForDelete.Node.RightChild;
                countOfNode--;
                return true;
            }
            findNodeForDelete.Node.Parent.RightChild = findNodeForDelete.Node.RightChild;
            countOfNode--;
            return true;
        }

        if (findNodeForDelete.Node.RightChild == null) {
            findNodeForDelete.Node.LeftChild.Parent = findNodeForDelete.Node.Parent;
            if (findNodeForDelete.Node.Parent.LeftChild == findNodeForDelete.Node) {
                findNodeForDelete.Node.Parent.LeftChild = findNodeForDelete.Node.LeftChild;
                countOfNode--;
                return true;
            }
            findNodeForDelete.Node.Parent.RightChild = findNodeForDelete.Node.LeftChild;
            countOfNode--;
            return true;
        }

        BSTNode<T> minNode = FinMinMax(findNodeForDelete.Node.RightChild, false);
        minNode.Parent.LeftChild = null;
        minNode.LeftChild = findNodeForDelete.Node.LeftChild;
        minNode.Parent = findNodeForDelete.Node.Parent;
        if (minNode != findNodeForDelete.Node.RightChild) {
            minNode.RightChild = findNodeForDelete.Node.RightChild;
        }
        if (findNodeForDelete.Node.Parent.LeftChild == findNodeForDelete.Node) {
            findNodeForDelete.Node.Parent.LeftChild = minNode;
            countOfNode--;
            return true;
        }

        findNodeForDelete.Node.Parent.RightChild = minNode;

        countOfNode--;
        return true;
    }

    public int Count() {
        if (Root == null) {
            return 0;
        }
        return countOfNode;
    }


public ArrayList<BSTNode> WideAllNodes() {
        if (Root == null) {
            return new ArrayList<>();
        }

        ArrayList<BSTNode> allNodes = new ArrayList<>();
        Queue<BSTNode> nodeQueue = new LinkedList<>();

        nodeQueue.add(Root);

        while (nodeQueue.peek() != null) {
            if (nodeQueue.peek().LeftChild != null) {
                nodeQueue.add(nodeQueue.peek().LeftChild);
            }
            if (nodeQueue.peek().RightChild != null) {
                nodeQueue.add(nodeQueue.peek().RightChild);
            }
            allNodes.add(nodeQueue.remove());
        }
        return allNodes;
    }


    public ArrayList<BSTNode> DeepAllNodes(int numberAlgorithmOfSearch) {
        if (numberAlgorithmOfSearch == 0) {
            return inOrderAlgorithm(Root);
        }
        if (numberAlgorithmOfSearch == 1) {
            return postOrderAlgorithm(Root);
        }
        if (numberAlgorithmOfSearch == 2) {
            return preOrderAlgorithm(Root);
        }
        return new ArrayList<>();
    }

    private ArrayList<BSTNode> inOrderAlgorithm(BSTNode currentRoot) {
        ArrayList<BSTNode> allNodes = new ArrayList<>();
        if (currentRoot != null) {
            allNodes.addAll(inOrderAlgorithm(currentRoot.LeftChild));
            allNodes.add(currentRoot);
            allNodes.addAll(inOrderAlgorithm(currentRoot.RightChild));
        }
        return allNodes;
    }

    private ArrayList<BSTNode> postOrderAlgorithm(BSTNode currentRoot) {
        ArrayList<BSTNode> allNodes = new ArrayList<>();
        if (currentRoot != null) {
            allNodes.addAll(postOrderAlgorithm(currentRoot.LeftChild));
            allNodes.addAll(postOrderAlgorithm(currentRoot.RightChild));
            allNodes.add(currentRoot);
        }
        return allNodes;
    }

    private ArrayList<BSTNode> preOrderAlgorithm(BSTNode currentRoot) {
        ArrayList<BSTNode> allNodes = new ArrayList<>();
        if (currentRoot != null) {
            allNodes.add(currentRoot);
            allNodes.addAll(preOrderAlgorithm(currentRoot.LeftChild));
            allNodes.addAll(preOrderAlgorithm(currentRoot.RightChild));
        }
        return allNodes;
    }
}










