/**
 * Class representing an edge between two vertices. Used for GraphAdjList.
 *
 * DO NOT EDIT THIS CLASS!!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public final class Edge implements Comparable<Edge> {

    private final int u;
    private final int v;
    private final int weight;
    private final boolean directed;

    /**
     * Creates an Edge between two given vertices (ints) with a given weight.
     * If the edge is specified to be a directed edge, then u is the starting
     * vertex and v is the destination vertex.
     *
     * @param u one of the vertices at the end of the Edge, is the start
     *        vertex if the Edge is specified to be directed
     * @param v one of the vertices at the end of the Edge, is the destination
     *        vertex if the Edge is specified to be directed
     * @param weight the weight of the Edge
     * @param directed whether or not the edge is directed
     */
    public Edge(int u, int v, int weight, boolean directed) {
        this.u = u;
        this.v = v;
        this.weight = weight;
        this.directed = directed;
    }

    @Override
    public int hashCode() {
        return u ^ v ^ weight ^ (directed ? 1 : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Edge) {
            Edge e = (Edge) o;
            if (directed ^ e.directed) {
                return false;
            }
            if (directed) {
                return weight == e.weight && u == e.u && v == e.v;
            }
            return weight == e.weight
                    && (u == e.u && v == e.v)
                    || (u == e.v && v == e.u);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Edge e) {
        return weight - e.getWeight();
    }

    /**
     * Gets the weight of this Edge.
     *
     * @return the weight of this Edge
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the u vertex of this Edge, which is the starting vertex if the
     * Edge is directed.
     *
     * @return the u Vertex of this Edge
     */
    public int getU() {
        return u;
    }

    /**
     * Gets the v vertex of this Edge, which is the destination vertex if the
     * Edge is directed.
     *
     * @return the v Vertex of this Edge
     */
    public int getV() {
        return v;
    }

    /**
     * Gets whether or not this Edge is directed.
     *
     * @return true if this Edge is directed, false otherwise
     */
    public boolean isDirected() {
        return directed;
    }

    @Override
    public String toString() {
        if (directed) {
            return "Edge from " + u + " to " + v + " with weight " + weight;
        }
        return "Edge between " + u + " and " + v + " with weight " + weight;
    }
}
