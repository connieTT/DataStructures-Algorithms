import java.util.List;
/**
 * Class that represents a graph using an adjacency matrix.
 *
 * DO NOT EDIT THIS CLASS!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class GraphAdjMatrix {

    private List<Integer> vertexList;
    private int[][] adjMatrix;

    /**
     * Constructor to build a Graph.
     *
     * @param vertexList the data held in each of the vertices. The
     * data at index 0 should correlate to the vertex of row/col 0 in
     * the adjMatrix, the data at index 1 should correlate to the
     * vertex of row/col 1, etc.
     * @param adjMatrix matrix of adjacencies. If the graph is directed,
     * the row should be the starting vertex, the columns should be the ending
     * vertex. If the graph has no weights, then a 1 will signify an
     * edge, a 0 means no edge. Otherwise, the entry should be the weight,
     * and if there is no edge, the entry will be 0. Diagonal
     * entries will be 0.
     */
    public GraphAdjMatrix(List<Integer> vertexList, int[][] adjMatrix) {
        this.vertexList = vertexList;
        this.adjMatrix = adjMatrix;
    }

    /**
     * Gets the vertex/data list of the graph.
     *
     * @return the vertex/data list of the graph
     */
    public List<Integer> getVertexList() {
        return this.vertexList;
    }

    /**
     * Gets teh adjacency matrix of the graph.
     *
     * @return the adjacency matrix of the graph
     */
    public int[][] getAdjMatrix() {
        return this.adjMatrix;
    }
}