import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
    int Level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = new ArrayList<SimpleTreeNode<T>>();
        Level = 0;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null


    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }


    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        NodeToDelete.Parent.Children.remove(NodeToDelete);
        NodeToDelete.Parent = null;
        NodeToDelete.Children = null;
    }


    public List<SimpleTreeNode<T>> GetAllNodes() {
        if (Root == null) {
            return new ArrayList<>();
        }
        return recursionForGetAllNodes(Root);
    }

    private List<SimpleTreeNode<T>> recursionForGetAllNodes(SimpleTreeNode<T> currentRoot) {
        List<SimpleTreeNode<T>> listNodes = new ArrayList<SimpleTreeNode<T>>();
        listNodes.add(currentRoot);
        if (!currentRoot.Children.isEmpty()) {
            for (SimpleTreeNode<T> child : currentRoot.Children) {
                listNodes.addAll(recursionForGetAllNodes(child));
            }
        }
        return listNodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        if (Root == null) {
            return new ArrayList<>();
        }
        return recursionForFindNodesByValue(Root, val);
    }

    private List<SimpleTreeNode<T>> recursionForFindNodesByValue(SimpleTreeNode<T> currentRoot, T val) {
        List<SimpleTreeNode<T>> listNodesOfSearchingValue = new ArrayList<>();
        if (currentRoot.NodeValue.equals(val)) {
            listNodesOfSearchingValue.add(currentRoot);
        }
        if (!currentRoot.Children.isEmpty()) {
            for (SimpleTreeNode<T> child : currentRoot.Children) {
                listNodesOfSearchingValue.addAll(recursionForFindNodesByValue(child, val));
            }
        }
        return listNodesOfSearchingValue;
    }


    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(OriginalNode, NewParent);
    }

    public int Count() {
        if (Root == null) {
            return 0;
        }
        return getCount(Root);
    }

    private int getCount(SimpleTreeNode<T> currentRoot) {
        int allCount = 0;
        if (currentRoot.Children.isEmpty()) {
            allCount++;
        }
        if (!currentRoot.Children.isEmpty()) {
            allCount++;
            for (SimpleTreeNode<T> child : currentRoot.Children) {
                allCount += getCount(child);
            }
        }
        return allCount;
    }

    public int LeafCount() {
        // количество листьев в дереве
        if (Root == null) {
            return 0;
        }
        if (Root.Children.isEmpty()) {
            return 1;
        }
        return getLeafCount(Root);
    }

    private int getLeafCount(SimpleTreeNode<T> currentRoot) {
        int allLeafCount = 0;
        if (currentRoot.Children.isEmpty()) {
            allLeafCount++;
        } else
            for (SimpleTreeNode<T> child : currentRoot.Children) {
                allLeafCount += getLeafCount(child);
            }
        return allLeafCount;
    }


    public static <T> void determinateLevels(SimpleTree<T> simpleTree) {

        if (simpleTree.Root == null) return;

        simpleTree.Root.Level = 0;

        determinateLevels(simpleTree.Root.Children, 0, 1);

    }

    private static <T> void determinateLevels(List<SimpleTreeNode<T>> simpleTreeNodes, int index, int level) {


        if (simpleTreeNodes == null) return;

        if (index < simpleTreeNodes.size()) {

            simpleTreeNodes.get(index).Level = level;

            determinateLevels(simpleTreeNodes.get(index).Children, 0, level + 1);
            determinateLevels(simpleTreeNodes, index + 1, level);

        }

    }

    public ArrayList<T> EvenTrees() {
        ArrayList <T> cutForCreateEvenTrees = new ArrayList<>();
        if (Count() < 4 || Count() % 2 != 0) {
            return cutForCreateEvenTrees;
        }
        for (SimpleTreeNode<T> child : Root.Children) {
            cutForCreateEvenTrees.addAll(recursionForEvenTrees(child));
        }
        return cutForCreateEvenTrees;
    }

    private ArrayList<T> recursionForEvenTrees(SimpleTreeNode<T> currentRoot){
        ArrayList <T> currentCutForCreateEvenTrees = new ArrayList<>();
        if (currentRoot.Children.isEmpty()){
            return currentCutForCreateEvenTrees;
        }
        for (SimpleTreeNode<T> child : currentRoot.Children) {
            if (getCount(currentRoot) != 0 && getCount(currentRoot) % 2 == 0){
            currentCutForCreateEvenTrees.add(currentRoot.Parent.NodeValue);
            currentCutForCreateEvenTrees.add(currentRoot.NodeValue);
            }
            currentCutForCreateEvenTrees.addAll(recursionForEvenTrees(child));
        }
        return currentCutForCreateEvenTrees;
    }
}
