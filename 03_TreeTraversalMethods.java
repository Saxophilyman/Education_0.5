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
