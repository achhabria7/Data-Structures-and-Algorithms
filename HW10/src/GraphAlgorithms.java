import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementations of various graph algorithms.
 *
 * @author Anmol Chhabria
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
            Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Start node "
                    + "does not exist in graph.");
        }

        List<Vertex<T>> ret = new ArrayList<>();
        bfsHelper(start, graph, ret);

        return ret;
    }

    /**
     * Helper method for breadth first search. It is iterative
     * and returns the final list with vertices in the bfs algorithm
     * order.
     *
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param ret the list to be returned, to which the nodes are added
     * @param <T> the data type representing the vertices in the graph.
     * @return a  List of vertices in the order they were visited
     */

    public static <T> List<Vertex<T>> bfsHelper(Vertex<T> start,
                Graph<T> graph,  List<Vertex<T>> ret) {
        if (!ret.contains(start)) {

            ret.add(start);

            Queue<Vertex<T>> vertexQueue = new LinkedList<>();
            vertexQueue.add(start);

            while (!vertexQueue.isEmpty()) {
                Vertex<T> current = vertexQueue.remove();
                if (!ret.contains(current)) {
                    ret.add(current);
                }
                List<VertexDistancePair<T>> aList
                        = graph.getAdjacencyList().get(current);
                for (int i = 0; i < aList.size(); i++) {
                    Vertex<T> adjNode = aList.get(i).getVertex();
                    if (!vertexQueue.contains(adjNode)
                            && !ret.contains(adjNode)) {
                        vertexQueue.add(adjNode);
                    }
                }
            }

        }

        return ret;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
            Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Start node "
                    + "does not exist in graph.");
        }

        List<Vertex<T>> ret = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        dfsHelper(start, graph, ret, visited);
        return ret;
    }

    /**
     * Helper method for depth first search. It adds to a set of visited
     * nodes and the return list every time it is called recursively.
     *
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param ret the list to be returned, to which the nodes are added
     * @param visited a list of visited nodes, to which visited nodes are added
     * @param <T> the data type representing the vertices in the graph.
     * @return a  List of vertices in the order they were visited
     */
    public static <T> List<Vertex<T>> dfsHelper(
            Vertex<T> start, Graph<T> graph,
            List<Vertex<T>> ret, Set<Vertex<T>> visited) {
        if (!ret.contains(start)) {
            ret.add(start);
            List<VertexDistancePair<T>> aList
                    = graph.getAdjacencyList().get(start);
            for (int i = 0; i < aList.size(); i++) {
                dfsHelper(aList.get(i).getVertex(), graph, ret, visited);
            }
        }
        return ret;
    }

    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
            Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException(
                    "Start node does not exist in graph.");
        }

        Map<Vertex<T>, Integer> distances = new HashMap<>();
        Set<Vertex<T>> vertices = graph.getAdjacencyList().keySet();
        for (Vertex<T> v : vertices) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        //Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<VertexDistancePair<T>> vpq = new PriorityQueue<>();
        vpq.add(new VertexDistancePair<T>(start, new Integer(0)));

        while (!vpq.isEmpty()) {
            VertexDistancePair<T> curr = vpq.remove();
            List<VertexDistancePair<T>> adjNodes
                    = graph.getAdjacencyList().get(curr.getVertex());
            for (VertexDistancePair<T> v : adjNodes) {
                if ((distances.get(curr.getVertex()) + v.getDistance()) < distances.get(v.getVertex())) {
                    distances.put(v.getVertex(), distances.get(curr.getVertex()) + v.getDistance());
                    vpq.add(new VertexDistancePair<T>(v.getVertex(), distances.get(curr.getVertex()) + v.getDistance()));
                }
            }
        }

        return distances;
    }

    /**
     * Run Kruskal's algorithm on the given graph and return the minimum
     * spanning tree in the form of a set of Edges. If the graph is
     * disconnected, and therefore there is no valid MST, return null.
     *
     * You may assume that there will only be one valid MST that can be formed.
     * In addition, only an undirected graph will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, {@code java.util.Map} and any class from java.util
     * that implements the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {

        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }

        PriorityQueue<Edge<T>> vpq = new PriorityQueue<>();
        for (Edge<T> edge : graph.getEdgeList()) {
            vpq.add(edge);
        }

        Set<Edge<T>> ret = new HashSet<>();

        Map<Vertex<T>, DisjointSet> clusters = new HashMap<>();
        Set<Vertex<T>> adjList = graph.getAdjacencyList().keySet();
        for (Vertex<T> v : adjList) {
            DisjointSet ds = new DisjointSet();
            clusters.put(v, ds);
        }

        while (!vpq.isEmpty()) {
            Edge<T> uv = vpq.remove();

            if (clusters.get(uv.getU()).find()
                    != clusters.get(uv.getV()).find()) {
                ret.add(uv);
                clusters.get(uv.getU()).union(clusters.get(uv.getV()));
            }
        }

        if (ret.size() != adjList.size() - 1) {
            return null;
        }

        return ret;
    }
}
