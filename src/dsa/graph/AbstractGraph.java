package dsa.graph;

import dsa.Position;
import dsa.graph.Graph;
import dsa.graph.Graph.Edge;
import dsa.graph.Graph.Vertex;

/**
 * AbstractGraph 
 * @author Viet Dinh
 *
 * @param <V> vertex
 * @param <E> edge
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    
    private boolean isDirected;
    
    /**
     * AbstractGraph constructor
     * @param directed boolean value of the type of graph
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * Return boolean value of the type of graph
     *@return boolean value of the type of graph
     */
    public boolean isDirected() {
        return isDirected;
    }
    
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if(ends[0] == vertex) {
            return ends[1];
        }
        if(ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * GraphVertex
     * @author Viet Dinh
     *
     */
    protected class GraphVertex implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        
        /**
         * GraphVertex's constructor
         * @param element element
         */
        public GraphVertex(V element) {
            setElement(element);
        }
        
        /**
         * Set the vertex's element
         * @param element element
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         *Returns the vertex's element
         *@return vertex's element
         */
        public V getElement() {
            return element;
        }
        
        /**
         * Return position of the vertex
         * @return position of the vertex
         */
        public Position<Vertex<V>> getPosition(){
            return position;
        }
        
        /**
         * Set the vertex to the given position
         * @param pos position to be set vertex
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }
    
    /**
     * Graph Edge
     * @author Viet Dinh
     *
     */
    protected class GraphEdge implements Edge<E> {
        private E element;
        private Vertex<V>[] endpoints;
        private Position<Edge<E>> position;
        
        /**
         * GraphEdge constructor
         * @param element edge's element
         * @param v1 vertex 1
         * @param v2 vertex 2
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[])new Vertex[]{v1, v2};
        }
        
        /**
         * Set the edge's element
         * @param element edge's element
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         *Returns the edge's element
         *@return edge's element
         */
        public E getElement() {
            return element;
        }
        
        /**
         * Return the edge's endpoints
         * @return edge's endpoints
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        /**
         * Return position of the edge
         * @return position of the edge
         */
        public Position<Edge<E>> getPosition(){
            return position;
        }
        /**
         * Set the edge to the given position
         * @param pos position to be set edge
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }
    
    /**
     * Valid the given edge to GraphEdge
     * @param e Edge
     * @return Valid the given edge to GraphEdge
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}
