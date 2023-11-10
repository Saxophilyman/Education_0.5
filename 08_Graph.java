class Vertex
{
    public int Value;
    public Vertex(int val)
    {
        Value = val;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
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
        if (v1 < max_vertex && v2 < max_vertex && m_adjacency[v1][v2] == 1)
            return true;
        return false;
    }

    public void AddEdge(int v1, int v2) {
        if (v1 < max_vertex && v2 < max_vertex)
            m_adjacency[v1][v2] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        if (v1 < max_vertex && v2 < max_vertex)
            m_adjacency[v1][v2] = 0;
    }
}
