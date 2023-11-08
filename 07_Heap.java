class Heap {
    public int[] HeapArray;
    public int HeapSize;

    public Heap() {
        HeapArray = null;
        HeapSize = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        HeapArray = new int[(int) (Math.pow(2, depth + 1) - 1)];
        for (int i = 0; i < a.length; i++) {
            Add(a[i]);

        }
    }

    public int GetMax() {
        if (HeapSize == 0) {
            return -1;
        }
        int getMax = HeapArray[0];
        HeapArray[0] = HeapArray[HeapSize-1];
        HeapArray[HeapSize-1] = 0;
        HeapSize--;
        siftingDown(0);
        return getMax;
    }

    private void siftingDown(int indexOfSiftingKey) {
        if (2 * indexOfSiftingKey + 2 >= HeapSize){
            return;
        }
        if (HeapArray[indexOfSiftingKey] < Math.max(HeapArray[2 * indexOfSiftingKey + 1], HeapArray[2 * indexOfSiftingKey + 2]) && indexOfSiftingKey <= HeapSize) {
            int SiftingKey = HeapArray[indexOfSiftingKey];
            int indexOfMaxKey;
            if (HeapArray[2 * indexOfSiftingKey + 1] > HeapArray[2 * indexOfSiftingKey + 2]) {
                indexOfMaxKey = 2 * indexOfSiftingKey + 1;
            } else indexOfMaxKey = 2 * indexOfSiftingKey + 2;
            HeapArray[indexOfSiftingKey] = HeapArray[indexOfMaxKey];
            HeapArray[indexOfMaxKey] = SiftingKey;
            siftingDown(indexOfMaxKey);
        }
    }

    public boolean Add(int key) {
        if (HeapSize + 1 <= HeapArray.length) {
            HeapArray[HeapSize] = key;
            HeapSize++;
            siftingUp(HeapSize-1);
            return true;
        }
        return false;
    }

    private void siftingUp(int indexOfSiftingKey) {
        int parentNode = (indexOfSiftingKey - 1) / 2;
        if (parentNode >= 0 && HeapArray[indexOfSiftingKey] > HeapArray[parentNode]) {
            int x = HeapArray[parentNode];
            HeapArray[parentNode] = HeapArray[indexOfSiftingKey];
            HeapArray[indexOfSiftingKey] = x;
            siftingUp(parentNode);
        }
    }
}
