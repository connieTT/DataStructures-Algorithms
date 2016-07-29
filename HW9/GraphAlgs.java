import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Your implementation of 5 different graph algorithms.
 *
 * @author Tongtong Zhao
 * @version 1.0
 */
public class GraphAlgs {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * int {@code start} which represents the starting vertex. You will be
     * modifying the empty list passed in to contain the vertices (ints) in
     * visited order. The start vertex should be at the beginning of the list
     * and the last vertex visited should be at the end.  (You may assume the
     * list is empty in the beginning).
     *
     * This method should utilize the adjacency list represented graph.
     *
     * When deciding which neighbors to visit next from a vertex, visit in the
     * order that the adjacency list has the vertices ordered starting from
     * index 0. Failure to do so may cause you to lose points.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * You may assume that the graph that we passed in will either be directed
     * or undirected, but not both. This should not have much of an effect, if
     * at all on your algorithm, however.
     *
     * @throws IllegalArgumentException if start is less than 0 or if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param start the vertex to begin the bfs on
     * @param graph the graph in an adjacency list format to search
     * @param bfsList an empty list initially, this is the list that you should
     * be adding to as you conduct bfs with the vertices in order of traversal
     * @return true if the graph is connected (you were able to reach every
     * vertex and edge from {@code start}), false otherwise
     */
    public static boolean breadthFirstSearch(int start, GraphAdjList graph,
                                             List<Integer> bfsList) {
        if (start < 0 || graph == null || bfsList == null) {
            throw new IllegalArgumentException("Invalid Inputs.");
        }
        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        Map<Integer, List<VertexDistancePair>> adjList =
                graph.getAdjacencyList();
        bfsList.add(start);
        boolean[] visited = new boolean[adjList.size()];

        Queue<Integer> nodes = new LinkedList<>();
        nodes.add(start);
        while (!nodes.isEmpty()) {
            int searching = nodes.remove();
            visited[searching] = true;
            List<VertexDistancePair> adjVerts =
                    adjList.get(searching);
            for (int i = 0; i < adjVerts.size(); i++) {
                VertexDistancePair vd = adjVerts.get(i);
                int child = vd.getVertex();
                if (!bfsList.contains(child)) {
                    nodes.add(child);
                    bfsList.add(child);
                }
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * int {@code start} which represents the starting vertex.  You will be
     * modifying the empty list passed in to contain the vertices (ints) in
     * visited order. The start vertex should be at the beginning of the list
     * and the last vertex visited should be at the end.  (You may assume the
     * list is empty in the beginning).
     *
     * This method should utilize the adjacency matrix represented graph.
     *
     * When deciding which neighbors to visit next from a vertex, visit starting
     * with vertex at index 0 to vertex  at index v-1. Failure to do so may
     * cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * You may assume that the graph that we passed in will either be directed
     * or undirected, but not both. This should not have much of an effect, if
     * at all on your algorithm, however.
     *
     * @throws IllegalArgumentException if start is less than 0 or if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param start the vertex to begin the dfs on
     * @param graph the graph in an adjacency matrix format to search
     * @param dfsList the list of visited vertices in order. This list will be
     * empty initially. You will be adding to this list as you perform dfs.
     * @return true if the graph is connected (you were able to reach every
     * vertex and edge from {@code start}), false otherwise
     */
    public static boolean depthFirstSearch(int start, GraphAdjMatrix graph,
                                           List<Integer> dfsList) {
        if (start < 0 || graph == null || dfsList == null) {
            throw new IllegalArgumentException("Invalid Inputs.");
        }
        if (!graph.getVertexList().contains(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        int[][] adjMatrix = graph.getAdjMatrix();
        Set<Integer> vertexSet = new HashSet<>();
        dfs(adjMatrix, start, vertexSet, dfsList);

        return (vertexSet.size() == adjMatrix.length) ? true : false;
    }
    /**
     * Private helper method for Depth-First Search
     *
     * @param adjMatrix   Adjacency List
     * @param start     Vertex to start from
     * @param vertexSet Set of vertices visited
     * @param dfsList  Vertices visited
     * @param <T>       Type of elements stored
     */
    private static <T> void dfs(int[][] adjMatrix, int start,
                                Set<Integer> vertexSet,
                                List<Integer> dfsList) {
        if (!vertexSet.contains(start)) {
            vertexSet.add(start);
            dfsList.add(start);
            int[] indexList = adjMatrix[start];
            for (int i = 0; i < indexList.length; i++) {
                if (indexList[i] != 0) {
                    dfs(adjMatrix, i, vertexSet, dfsList);
                }
            }
        }
    }

    /**
     * Find the single source shortest distance between the start vertex and
     * all vertices given a weighted graph using Dijkstra's shortest path
     * algorithm.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * This method should utilize the adjacency list represented graph.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * You may assume that the graph that we passed in will either be directed
     * or undirected, but not both. This should not have much of an effect, if
     * at all on your algorithm, however.
     *
     * @throws IllegalArgumentException if start is less 0 or if any input
     *  is null, or if start doesn't exist in the graph.
     * @throws IllegalStateException if any of the edges are negative
     * @param start index representing which vertex (row) to start at (source)
     * @param graph the Graph we are searching using an adjacency List
     * @return a map of the shortest distances from start to every other node
     *         in the graph (first Integer is the vertex number in the adj
     *         list, second Integer is the shortest distance to that vertex)
     */
    public static Map<Integer, Integer> shortPathDijk(int start,
                                                      GraphAdjList graph) {
        if (start < 0 || graph == null) {
            throw new IllegalArgumentException("Invalid Inputs.");
        }
        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Start node does not exist.");
        }

        Map<Integer, List<VertexDistancePair>> adjList =
                graph.getAdjacencyList();
        Map<Integer, Integer> dijkstra = new HashMap<>();

        for (Integer vertex : adjList.keySet()) {
            dijkstra.put(vertex, Integer.MAX_VALUE);
        }

        dijkstra.put(start, 0);
        PriorityQueue<VertexDistancePair> pq = new PriorityQueue<>();
        pq.add(new VertexDistancePair(start, 0));

        while (!pq.isEmpty()) {
            VertexDistancePair curr = pq.remove();
            if (curr.getDistance() < 0) {
                throw new IllegalStateException("Edges cannot be negative.");
            }
            List<VertexDistancePair> vdpairs =
                    graph.getAdjacencyList().get(curr.getVertex());
            for (VertexDistancePair vd : vdpairs) {
                if (vd.getDistance() < 0) {
                    throw new IllegalStateException(
                            "Edges cannot be negative.");
                }
                int dist = curr.getDistance() + vd.getDistance();
                if (dist < dijkstra.get(vd.getVertex())) {
                    dijkstra.put(vd.getVertex(), dist);
                    pq.add(new VertexDistancePair(vd.getVertex(),
                            dist));
                }
            }
        }
        return dijkstra;
    }

    /**
     * Run Prim's algorithm on the given graph and return the MST/MSF
     * in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return a minimal spanning forest (MSF).
     *
     * This method should utilize the adjacency list represented graph.
     *
     * A minimal spanning forest (MSF) is just a generalized version of the MST
     * for disconnected graphs. After running the algorithm once, just check to
     * see if there are still some vertices that are not connected to the
     * MST/MSF. If all vertices have been visited, you are done. If not, run
     * the algorithm again on an unvisited vertex.
     *
     * You may assume that all of the edge weights are unique (THIS MEANS THAT
     * THE MST/MSF IS UNIQUE FOR THE GRAPH, REGARDLESS OF STARTING VERTEX!!)
     * Although, if your algorithm works correctly, it should work even if the
     * MST/MSF is not unique, this is just for testing purposes.
     *
     * You should not allow for any self-loops in the MST/MSF. Additionally,
     * you may assume that the graph is undirected.
     *
     * You may NOT use/import anything for this method that is not in the
     * standard java.lang package.
     *
     * @throws IllegalArgumentException if any input is null
     * @param graph the Graph we are searching using an adjacency list
     * @return the MST/MSF of the graph
     */
    public static Set<Edge> mstPrim(GraphAdjList graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Invalid input.");
        }
        Map<Integer, List<VertexDistancePair>> vertexList =
                graph.getAdjacencyList();

        int start = 0;
        Set<Integer> reached = new HashSet<>();
        Set<Edge> output = new HashSet<>();
        Queue<Edge> queue = new PriorityQueue<>();
        if (!graph.getAdjacencyList().containsKey(start)) {
            return output;
        }

        for (VertexDistancePair vp
                : vertexList.get(start)) {
            queue.add(new Edge(
                    start, vp.getVertex(), vp.getDistance(), false));
        }
        reached.add(start);

        while (!queue.isEmpty()) {
            Edge current = queue.remove();
            if (!(reached.contains(current.getV()))) {
                output.add(current);
                reached.add(current.getV());
                for (VertexDistancePair vp
                        : vertexList.get(current.getV())) {
                    queue.add(new Edge(
                            current.getV(), vp.getVertex(),
                            vp.getDistance(), false));
                }
            }
        }

        //if (reached.size() < vertexList.size() - 1) {
        //    return null;
        //}
        return output;
    }

    /**
     * Run Kruskal's algorithm on the given graph and return the MST/MSF
     * in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return a minimal spanning forest (MSF).
     *
     * This method should utilize the adjacency list represented graph.
     *
     * A minimal spanning forest (MSF) is just a generalized version of the MST
     * for disconnected graphs. Unlike Prim's algorithm, Kruskal's algorithm
     * will naturally return a MSF if the graph is disconnected.
     *
     * You may assume that all of the edge weights are unique (THIS MEANS THAT
     * THE MST/MSF IS UNIQUE FOR THE GRAPH.) Although, if your algorithm works
     * correctly, it should work even if the MST/MSF is not unique, this is
     * just for testing purposes.
     *
     * You should not allow for any self-loops in the MST/MSF. Additionally,
     * you may assume that the graph is undirected.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you.  A Disjoint Set will keep track of which verticies are
     * connected to each other by the edges you've chosen for your MST/MSF.
     * Without a Disjoint Set, it is possible for Kruskal's to omit edges that
     * should be in the final MST/MSF.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null
     * @param graph the Graph we are searching using an adjacency list
     * @return the MST/MSF of the graph
     */
    public static Set<Edge> mstKruskal(GraphAdjList graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Invalid input.");
        }

        Map<Integer, List<VertexDistancePair>> vertexList =
                graph.getAdjacencyList();
        Queue<Edge> queue = new PriorityQueue<>();
        Set<Edge> output = new HashSet<>();
        Set<Integer> reached = new HashSet<>();
        DisjointSet dSet = new DisjointSet<>(vertexList.keySet());

        for (int start : vertexList.keySet()) {
            for (VertexDistancePair vp
                    : vertexList.get(start)) {
                queue.add(new Edge(
                        start, vp.getVertex(), vp.getDistance(), false));
            }
        }

        while (!queue.isEmpty()) {
            Edge current = queue.remove();

            if (dSet.find(current.getU()) != dSet.find(current.getV())) {
                output.add(current);
                dSet.union(current.getU(), current.getV());

                for (VertexDistancePair vp
                        : vertexList.get(current.getV())) {
                    queue.add(new Edge(
                            current.getV(), vp.getVertex(),
                            vp.getDistance(), false));
                }
            }
        }
        return output;
    }
}