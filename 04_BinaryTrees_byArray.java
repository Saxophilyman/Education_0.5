import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = (int) Math.pow(2, depth) - 1;
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key) {
        if (Tree[0] == null) {
            return 0;
        }
        // ищем в массиве индекс ключа
        return recursionForFindKeyIndex(0, key); // не найден
    }


    private Integer recursionForFindKeyIndex(int arrayIndex, int key) {
        if (Tree[arrayIndex] == null) {
            return -arrayIndex;
        }
        if (Tree[arrayIndex] == key) {
            return arrayIndex;
        }
        if (key < Tree[arrayIndex]) {
            return recursionForFindKeyIndex(2 * arrayIndex + 1, key);
        }
        if (key > Tree[arrayIndex]) {
            return recursionForFindKeyIndex(2 * arrayIndex + 2, key);
        }
        return null;
    }

    public int AddKey(int key) {
        if (Tree[0] == null) {
            Tree[0] = key;
            return 0;
        }
        
        // добавляем ключ в массив
        return recursionForAddKey(0, key);
        // индекс добавленного/существующего ключа или -1 если не удалось
    }

    private Integer recursionForAddKey(int arrayIndex, int key) {
        if (Tree[arrayIndex] == null) {
            Tree[arrayIndex] = key;
            return arrayIndex;
        }
        if (Tree[arrayIndex] == key) {
            return arrayIndex;
        }
        if (key < Tree[arrayIndex]) {
            return recursionForFindKeyIndex(2 * arrayIndex + 1, key);
        }
        if (key > Tree[arrayIndex]) {
            return recursionForFindKeyIndex(2 * arrayIndex + 2, key);
        }
        return -1;
    }
}
