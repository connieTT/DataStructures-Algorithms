import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * A class representing a graph, with an edge list and adjacency list.
 * Related classes include Edge, Vertex, and VertexDistancePair.
 *
 * DO NOT EDIT THIS CLASS!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class GraphAdjList {

    private Set<Edge> edges;
    private Map<Integer, List<VertexDistancePair>> adjacencyList;
    private boolean directed;

    /**
     * Constructor to build a Graph from a set of edges.
     *
     * @param vertexList the vertices to build the graph from.
     * @param edges the edge list to build the graph from.
     */
    public GraphAdjList(List<Integer> vertexList, Set<Edge> edges) {
        this.adjacencyList = new HashMap<>();
        this.edges = edges;
        for (int v : vertexList) {
            adjacencyList.putIfAbsent(v,
                new ArrayList<>());
        }
        for (Edge e : edges) {
            adjacencyList.get(e.getU()).add(
                    new VertexDistancePair(e.getV(), e.getWeight()));
            if (!e.isDirected()) {
                adjacencyList.get(e.getV()).add(
                        new VertexDistancePair(e.getU(), e.getWeight()));
            } else {
                this.directed = true;
            }
        }
    }

    /**
     * Gets the edge set of this graph.
     *
     * @return the edge set of this graph
     */
    public Set<Edge> getEdgeSet() {
        return edges;
    }

    /**
     * Gets the adjacency list of this graph.
     *
     * @return the adjacency list of this graph
     */
    public Map<Integer, List<VertexDistancePair>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Gets whether or not the edges of this graph are directed.
     *
     * @return true if this graph is directed, false otherwise
     */
    public boolean isDirected() {
        return directed;
    }
}
