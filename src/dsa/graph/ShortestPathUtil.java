package dsa.graph;


import dsa.Weighted;
import dsa.graph.Graph.Edge;
import dsa.graph.Graph.Vertex;
import dsa.map.Map;
import dsa.map.hashing.LinearProbingHashMap;
import dsa.priority_queue.AdaptablePriorityQueue;
import dsa.priority_queue.HeapAdaptablePriorityQueue;
import dsa.priority_queue.PriorityQueue.Entry;
import dsa.set.HashSet;
import dsa.set.Set;


/**
 * ShortestPathUtil
 * @author Viet Dinh
 *
 */
public class ShortestPathUtil {
    
    /**
     * dijkstra algorithm of shortest path
     * @param <V> vertex
     * @param <E> edge
     * @param g graph
     * @param src source vertex
     * @return shortest path costs from the starting vertex to every other vertex
     */
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> g, Vertex<V> src) {
        //NOTE: since Dijkstra's algorithm is very similar to Prim-Jarnik's algorithm,
        //     you should review the provided Prim-Jarnik implementation in the next
        //     section of the lab on Minimum Spanning Trees
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            pqEntries.put( v, q.insert(weights.get(v), v));
        }
        while(!q.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = q.deleteMin();
            Vertex<V> u = entry.getValue();
            weights.put(u, weights.get(u));
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight() + weights.get(u);
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return weights;
    }
    
    /**
     * shortestPathTree
     * @param <V> vertex
     * @param <E> edge
     * @param g graph
     * @param s starting vertex
     * @param distances map of the distances
     * @return map of the edges in the shortest path tree
     */
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> g, Vertex<V> s, Map<Vertex<V>, Integer> distances) {
        Map<Vertex<V>, Edge<E>> weights = new LinearProbingHashMap<>();
        for (Vertex<V> v : distances) {
        	   if (v != s) {
        	      for (Edge<E> e : g.incomingEdges(v)) {
        	         Vertex<V> u = g.opposite(v, e);
        	         if (distances.get(v) == distances.get(u) + e.getElement().getWeight())
        	            weights.put(v, e);
        	      }
        	   }
        }
        	return weights;

    }
}
