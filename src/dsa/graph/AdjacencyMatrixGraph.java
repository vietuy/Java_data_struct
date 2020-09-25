package dsa.graph;

import dsa.Position;
import dsa.list.List;
import dsa.list.SinglyLinkedList;
import dsa.list.positional.PositionalLinkedList;
import dsa.list.positional.PositionalList;
import dsa.map.Map;
import dsa.map.hashing.LinearProbingHashMap;

/**
 * AdjacencyMatrixGraph implementation
 * @author Viet Dinh
 *
 * @param <V> vertex
 * @param <E> edge
 */
public class AdjacencyMatrixGraph<V, E> extends AbstractGraph<V, E> {

    private GraphEdge[][] matrix;
    private PositionalList<Vertex<V>> vertexList;
    private PositionalList<Edge<E>> edgeList;
    private Map<Vertex<V>, Integer> vertexMap;

    /**
     * AdjacencyMatrixGraph constructor
     */
    public AdjacencyMatrixGraph() {
        this(false);
    }
    
    /**
     * AdjacencyMatrixGraph constructor with type boolean
     * @param directed boolean value of directed
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
        vertexMap = new LinearProbingHashMap<Vertex<V>, Integer>();
    }

    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        // For a directed graph, we consistently use the 
        // "upper half" of the matrix since the matrix is not symmetric
        // like it is with an undirected graph      
        GraphVertex v1 = validate(vertex1);
        GraphVertex v2 = validate(vertex2);
        if(!isDirected() && vertexMap.get(v2) < vertexMap.get(v1)) {
            return matrix[vertexMap.get(v2)][vertexMap.get(v1)];
        }
        return matrix[vertexMap.get(v1)][vertexMap.get(v2)];
    }

    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        GraphEdge e = validate(edge);
        return e.getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if (ends[0] == vertex) {
            return ends[1];
        }
        if (ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (GraphEdge e : matrix[vertexMap.get(v)]) {
            if (e != null) {
                list.addLast(e);
            }
        }
        if (!isDirected()) {
            for (int i = 0; i < matrix.length; i++) {
                GraphEdge e = matrix[i][vertexMap.get(v)];
                if (e != null) {
                    list.addLast(e);
                }
            }
        }
        return list;
    }

    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][vertexMap.get(v)];
            if (e != null) {
                list.addLast(e);
            }
        }
        if (!isDirected()) {
            for (GraphEdge e : matrix[vertexMap.get(v)]) {
                if (e != null) {
                    list.addLast(e);
                }
            }
        }
        return list;
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        GraphVertex v = new GraphVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        vertexMap.put(v, vertexMap.size());
        v.setPosition(pos);
        growArray();
        return v;
    }
    
    @SuppressWarnings("unchecked")
    private void growArray() {
        // We are using a poor implementation here since we will
        // grow the matrix each time a new vertex is added to the graph     
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++)
            {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        GraphVertex origin = validate(v1);
        GraphVertex destination = validate(v2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        if( !isDirected() && vertexMap.get(destination) < vertexMap.get(origin)) {
            matrix[vertexMap.get(destination)][vertexMap.get(origin)] = e;
        } else {
            matrix[vertexMap.get(origin)][vertexMap.get(destination)] = e;
        }
        return e;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        Vertex<V> temp = vertexList.remove(v.getPosition());
        int index = vertexMap.get(v);
		for (int i = 0; i < numVertices(); i++) {
			if(matrix[index][i] != null) {
				removeEdge(matrix[index][i]);
			}
			matrix[index][i] = null;
			if(matrix[i][index] != null) {
				removeEdge(matrix[i][index]);
			}
			matrix[i][index] = null;

		}
        return temp;
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Edge<E> temp = edgeList.remove(e.getPosition());
        int index = vertexMap.get(e.getEndpoints()[0]);
        int index2 = vertexMap.get(e.getEndpoints()[1]);
        matrix[index][index2] = null;
        return temp;
    }

    private GraphVertex validate(Vertex<V> v) {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid graph vertex.");
        }
        return (GraphVertex) v;
    }
}
