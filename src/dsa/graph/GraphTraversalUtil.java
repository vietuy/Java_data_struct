package dsa.graph;

import dsa.graph.Graph.Edge;
import dsa.graph.Graph.Vertex;
import dsa.map.Map;
import dsa.map.UnorderedArrayMap;
import dsa.queue.ArrayBasedQueue;
import dsa.queue.Queue;
import dsa.set.HashSet;
import dsa.set.Set;

/**
 * GraphTraversalUtil implementation
 * @author Viet Dinh
 *
 */
public class GraphTraversalUtil {
    
    /**
     * depthFirstSearch traversal
     * @param <V> vertex
     * @param <E> edge
     * @param graph graph to perform dfs
     * @param start starting vertex
     * @return collection of vertices reachable from v, with their discovery edges
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	Set<Vertex<V>> hs = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> map = new UnorderedArrayMap<Vertex<V>, Edge<E>>();
    	dfsHelper(graph, start, hs, map);
    	return map;
    }
    
    /**
     * Helper method for DFS
     * @param <V> vertex
     * @param <E> edge
     * @param graph graph
     * @param u vertex
     * @param known set of know vertex
     * @param forest the forest of dfs
     */
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> v, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
    	known.add(v);
    	for(Edge<E> e : graph.outgoingEdges(v) ) {
    		Vertex<V> u = graph.opposite(v, e);
    		if(!known.contains(u)) {
    			forest.put(u, e);
    			dfsHelper(graph, u, known, forest);
    		}
    	}
    }
    
    /**
     * breadthFirstSearch graph traversal
     * @param <V> vertex
     * @param <E> edge
     * @param graph graph to perform BFS
     * @param start starting vertex 
     * @return  collection of vertices reachable from v, with their discovery edges
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	Set<Vertex<V>> hs = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> map = new UnorderedArrayMap<Vertex<V>, Edge<E>>();
    	Queue<Vertex<V>> queue = new ArrayBasedQueue<Vertex<V>>();
    	hs.add(start);
    	queue.enqueue(start);
    	while(!queue.isEmpty()) {
    		Vertex<V> u = queue.dequeue();
    		for(Edge<E> e : graph.outgoingEdges(u)) {
    			Vertex<V> w = graph.opposite(u, e);
    			if(!hs.contains(w)) {
    				hs.add(w);
    				map.put(w, e);
    				queue.enqueue(w);
    			}
    		}
    	}
    	return map;
    }
}
