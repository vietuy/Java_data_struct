package dsa.graph;

/**
 * Graph interface
 * @author Viet Dinh
 *
 * @param <V> vertices 
 * @param <E> edge
 */
public interface Graph<V, E> {
    /**
     * Returns true if the graph is a directed graph, otherwise returns false
     * @return true if the graph is a directed graph, otherwise returns false
     */
    boolean isDirected();
    /**
     * Returns the number of vertices in the graph
     * @return number of vertices in the graph
     */
    int numVertices();
    /**
     * Returns an iteration of all vertices in the graph
     * @return iteration of all vertices in the graph
     */
    Iterable<Vertex<V>> vertices();
    /**
     * Returns the number of edges in the graph
     * @return number of edges in the graph
     */
    int numEdges();
    /**
     * Returns an iteration of all edges in the graph
     * @return iteration of all edges in the graph
     */
    Iterable<Edge<E>> edges();
    /**
     * Returns the edge that connects vertex u and vertex v; if no edge connects the two vertices, return null.
     *  For undirected graphs, getEdge(u,v) = getEdge(v,u)
     * @param vertex1 vertices 1
     * @param vertex2 vertices 2
     * @return edge that connects two given vertices
     */
    Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);
    /**
     * Returns the two endpoint vertices of edge e. 
     * For a directed graph, the first vertex is the source vertex and the second is the destination vertex
     * @param edge edge 
     * @return two endpoint vertices of edge e
     */
    Vertex<V>[] endVertices(Edge<E> edge);
    /**
     * Returns the other vertex of the edge, given an edge e incident to vertex v
     * @param vertex opposite vertex
     * @param edge edge to the other vertex
     * @return other vertex of the edge
     */
    Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);
    /**
     * Returns the number of outgoing edges from given vertex
     * @param vertex vertex
     * @return number of outgoing edges from v
     */
    int outDegree(Vertex<V> vertex);
    /**
     * Returns the number of incoming edges to v. For undirected graphs, outDegree(v) = inDegree(v)
     * @param vertex vertex
     * @return number of incoming edges to the given vertex
     */
    int inDegree(Vertex<V> vertex);
    /**
     * Returns an iteration of all outgoing edges from given vertex
     * @param vertex vertex
     * @return iteration of all outgoing edges from given vertex
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);
    /**
     * Returns an iteration of all incoming edges to vertex v. For undirected graphs, ougoingEdges(v) = incomingEdges(v)
     * @param vertex vertex
     * @return iteration of all incoming edges to given vertex
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);
    /**
     * Creates and returns a new Vertex storing element x
     * @param vertexData element to create vertex
     * @return Vertex storing given element
     */
    Vertex<V> insertVertex(V vertexData);
    /**
     * Creates and returns a new Edge from two given vertices and stores edge data
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @param edgeData edge value
     * @return new Edge from two given vertices
     */
    Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);
    /**
     * Removes vertex v and all its incident edges from the graph
     * @param vertex vertex to be removed
     * @return removed vertex
     */
    Vertex<V> removeVertex(Vertex<V> vertex);
    /**
     * Removes edge e from the graph
     * @param edge edge to be remove
     * @return removed edge
     */
    Edge<E> removeEdge(Edge<E> edge);
    
    /**
     * Edge
     * @author Viet Dinh
     *
     * @param <E> edge
     */
    interface Edge<E> {
        /**
         * Return edge's element
         * @return edge's element
         */
        E getElement();
    }
    
    /**
     * Vertex
     * @author Viet Dinh
     *
     * @param <V> vertex
     */
    interface Vertex<V> {
        /**
         * Return vertex's element
         * @return vertex's element
         */
        V getElement();
    }
}

