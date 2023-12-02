import java.util.*;

class Vertex {
    public int Value;
    boolean hit;

    public Vertex(int val) {
        Value = val;
        hit = false;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    Stack<Integer> deepWayToVertexPoint;
    Queue<Integer> queueToFindShortWay;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
        deepWayToVertexPoint = new Stack<>();
        queueToFindShortWay = new LinkedList<>();
    }

    public void AddVertex(int value) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value);
                return;
            }
        }
    }

    public void RemoveVertex(int v) {
        vertex[v] = null;
        for (int i = 0; i < m_adjacency[v].length; i++) {
            m_adjacency[v][i] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) {
        return v1 < max_vertex && v2 < max_vertex && m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2) {
        if (v1 < max_vertex && v2 < max_vertex) {
            m_adjacency[v1][v2] = 1;
            m_adjacency[v2][v1] = 1;
        }
    }

    public void RemoveEdge(int v1, int v2) {
        if (v1 < max_vertex && v2 < max_vertex) {
            m_adjacency[v1][v2] = 0;
            m_adjacency[v2][v1] = 0;
        }
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        refreshOptionsStructureData();
        if (VFrom >= vertex.length || VTo >= vertex.length) {
            return new ArrayList<>();
        }
        vertex[VFrom].hit = true;
        deepWayToVertexPoint.push(VFrom);
        recursionDepthFirstSearch(VFrom, VTo);
        int stackSize = deepWayToVertexPoint.size();
        ArrayList<Vertex> allDeepWay = new ArrayList<>();
        for (int i = 0; i < stackSize; i++) {
            allDeepWay.add(0, vertex[deepWayToVertexPoint.pop()]);
        }
        return allDeepWay;
    }

    private void recursionDepthFirstSearch(int fromCurrentVertex, int toVertex) {
        if (IsEdge(fromCurrentVertex, toVertex)) {
            deepWayToVertexPoint.push(toVertex);
            return;
        }
        if (hasAdjacency(fromCurrentVertex, toVertex)) {
            return;
        }
        deepWayToVertexPoint.pop();
        if (deepWayToVertexPoint.empty()) {
            return;
        }
        recursionDepthFirstSearch(deepWayToVertexPoint.peek(), toVertex);
    }

    private boolean hasAdjacency(int from, int to) {
        for (int i = 0; i < m_adjacency[from].length; i++) {
            if (m_adjacency[from][i] == 1 && !vertex[i].hit) {
                vertex[i].hit = true;
                deepWayToVertexPoint.push(i);
                recursionDepthFirstSearch(i, to);
                return true;
            }
        }
        return false;
    }

    private void refreshOptionsStructureData() {
        for (Vertex value : vertex) {
            value.hit = false;
        }
        deepWayToVertexPoint.removeAllElements();
        queueToFindShortWay.clear();
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        refreshOptionsStructureData();
        if (VFrom >= vertex.length || VTo >= vertex.length) {
            return new ArrayList<>();
        }
        vertex[VFrom].hit = true;
        queueToFindShortWay.add(VFrom);
        ArrayList<Vertex> shortWayList = new ArrayList<>();
        int[] parent = new int[max_vertex];
        int currentIndex;
        boolean isWayFind = false;

        while (!queueToFindShortWay.isEmpty()) {
            currentIndex = queueToFindShortWay.poll();
            if (IsEdge(currentIndex, VTo)) {
                parent[VTo] = currentIndex;
                isWayFind = true;
                break;
            }

            for (int i = 0; i < m_adjacency[currentIndex].length; i++) {
                if (m_adjacency[currentIndex][i] == 1 && !vertex[i].hit) {
                    vertex[i].hit = true;
                    queueToFindShortWay.add(i);
                    parent[i] = currentIndex;
                }
            }
        }

        if (!isWayFind) {
            return shortWayList;
        }

        currentIndex = VTo;
        shortWayList.add(vertex[VTo]);
        while (currentIndex != VFrom) {
            currentIndex = parent[currentIndex];
            shortWayList.add(0, vertex[currentIndex]);
        }
        return shortWayList;
    }

    public ArrayList<Vertex> WeakVertices() {
        ArrayList<Vertex> listOfWeakVertices = new ArrayList<>();
        // возвращает список узлов вне треугольников
        for (int currentIndex = 0; currentIndex < vertex.length; currentIndex++) {
            ArrayList<Integer> listOfEdge = new ArrayList<>();
            for (int i = 0; i < m_adjacency[currentIndex].length; i++) {
                if (IsEdge(currentIndex, i)) {
                    listOfEdge.add(i);
                }
            }
            if (!isBelongTriangle(listOfEdge)){
                listOfWeakVertices.add(vertex[currentIndex]);
            }
        }
        return listOfWeakVertices;
    }

    private boolean isBelongTriangle(ArrayList<Integer> listOfEdges) {
        if (listOfEdges.size() < 2){
            return false;
        }
        for (int i = 0; i < listOfEdges.size(); i++) {
            for (int j = i+1; j < listOfEdges.size(); j++) {
                if (IsEdge(listOfEdges.get(i), listOfEdges.get(j))){
                    return true;
                }
            }
        }
        return false;
    }
}
