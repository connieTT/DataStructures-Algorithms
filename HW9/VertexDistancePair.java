/**
 * Class to store a vertex in a graph and an integer associated with it
 * representing the distance to this vertex from the previous vertex in an
 * adjacency list. Used for GraphAdjList.
 *
 * DO NOT EDIT THIS CLASS!!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class VertexDistancePair implements Comparable<VertexDistancePair> {

    private final int vertex;
    private final int distance;

    /**
     * Creates a VertexDistancePair.
     *
     * @param vertex the vertex to be stored with as an int.
     * @param distance the integer representing the distance to this vertex
     *        from a previous vertex.
     */
    public VertexDistancePair(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    /**
     * Gets the vertex stored in this VertexDistancePair.
     *
     * @return the vertex stored in this VertexDistancePair.
     */
    public int getVertex() {
        return vertex;
    }

    /**
     * Gets the distance stored in this VertexDistancePair.
     *
     * @return the distance stored in this VertexDistancePair.
     */
    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(VertexDistancePair pair) {
        return this.getDistance() - pair.getDistance();
    }

    @Override
    public String toString() {
        return "Pair with vertex " + vertex + " and distance " + distance;
    }
}
