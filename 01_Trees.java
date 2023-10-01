import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; 
    public SimpleTreeNode<T> Parent; 
    public List<SimpleTreeNode<T>> Children; 
    int depth;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = new ArrayList<>();
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
        if (Root.Children.isEmpty()){
            List<SimpleTreeNode<T>> listNodes = new ArrayList<>();
            listNodes.add(Root);
            return listNodes;
        }
        return recursionForGetAllNodes(Root);
    }

    private List<SimpleTreeNode<T>> recursionForGetAllNodes(SimpleTreeNode<T> currentRoot) {
        List<SimpleTreeNode<T>> listNodes = new ArrayList<SimpleTreeNode<T>>();
        if (!currentRoot.Children.isEmpty()) {
            listNodes.add(currentRoot);
            for (SimpleTreeNode<T> child : currentRoot.Children) {
                recursionForGetAllNodes(child);
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
                recursionForFindNodesByValue(child, val);
            }
        }
        return listNodesOfSearchingValue;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(OriginalNode, NewParent);
    }
	public int Count() {
		
		if (Root == null) return 0; 
		
		return 1 + Count(Root.Children, 0);
		
	}
	
	private int Count(List<SimpleTreeNode<T>> simpleTreeNodes, int index) {
		
		if (simpleTreeNodes == null || index >= simpleTreeNodes.size()) return 0;
		
		return 1 + Count(simpleTreeNodes, index + 1) + Count(simpleTreeNodes.get(index).Children, 0);
		
	}

	public int LeafCount() {
		
		if (Root == null) return 0; 
		
		return LeafCount(Root.Children, 0);
		
	}
	
	private int LeafCount(List<SimpleTreeNode<T>> simpleTreeNodes, int index) {
		
		if (simpleTreeNodes == null || simpleTreeNodes.size() == 0) return 1;
		
		if (index >= simpleTreeNodes.size()) return 0;
		
		return LeafCount(simpleTreeNodes, index + 1) + LeafCount(simpleTreeNodes.get(index).Children, 0);

	}
}
