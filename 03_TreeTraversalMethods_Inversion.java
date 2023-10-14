    public void inversionTree() {
        if (Root != null) {
            recursionForInversionTree(Root);
        }
    }

    private void recursionForInversionTree(BSTNode currentRoot) {
        if (currentRoot != null) {
            BSTNode containerNode = currentRoot.LeftChild;
            currentRoot.LeftChild = currentRoot.RightChild;
            currentRoot.RightChild = containerNode;
            recursionForInversionTree(currentRoot.LeftChild);
            recursionForInversionTree(currentRoot.RightChild);
        }
    }
