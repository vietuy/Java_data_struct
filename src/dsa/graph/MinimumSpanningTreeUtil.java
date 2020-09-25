package dsa.graph;

import dsa.Position;
import dsa.Weighted;
import dsa.disjoint_set.DisjointSetForest;
import dsa.disjoint_set.UpTreeDisjointSetForest;
import dsa.graph.Graph.Edge;
import dsa.graph.Graph.Vertex;
import dsa.list.positional.PositionalLinkedList;
import dsa.list.positional.PositionalList;
import dsa.map.Map;
import dsa.map.hashing.LinearProbingHashMap;
import dsa.priority_queue.AdaptablePriorityQueue;
import dsa.priority_queue.HeapAdaptablePriorityQueue;
import dsa.priority_queue.HeapPriorityQueue;
import dsa.priority_queue.PriorityQueue;
import dsa.priority_queue.PriorityQueue.Entry;
import dsa.set.HashSet;
import dsa.set.Set;

/**
 * MinimumSpanningTreeUtil implementation
 * @author Viet Dinh
 *
 */
public class MinimumSpanningTreeUtil {
    

    /**
     * Return a collection of the edges in the minimum spanning tree
     * @param <V> vertex
     * @param <E> edge
     * @param g graph
     * @return collection of the edges in the minimum spanning tree
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
        PriorityQueue<Integer, Edge<E>> q = new HeapPriorityQueue<>();
        DisjointSetForest<Vertex<V>> distances = new UpTreeDisjointSetForest<>();
        for (Edge<E> e : g.edges()) q.insert(e.getElement().getWeight(), e);
        	// Initialize each vertex into its own disjoint set
        for (Vertex<V> v : g.vertices()) distances.makeSet(v);
        int components = g.numVertices();

        	// Main loop   
        while (components > 1) {  
        	Entry<Integer, Edge<E>> entry  = q.deleteMin();
        	Edge<E> e = entry.getValue();
        	Position<Vertex<V>> u = distances.find(g.endVertices(e)[0]);
        	Position<Vertex<V>> v = distances.find(g.endVertices(e)[1]);
           if (u != v) {
              distances.union(u, v);
              tree.addLast(e);
              components--;
           }
        }
        return tree;
    }
    
    /**
     * Return a collection of edges in the minimum spanning tree
     * @param <V> vertex
     * @param <E> edge
     * @param g graph
     * @return collection of edges in the minimum spanning tree
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();
        
        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
        
        Vertex<V> src = g.vertices().iterator().next();
        
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
            if(connectingEdges.get(u) != null) {
                tree.addLast(connectingEdges.get(u));
            }
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    connectingEdges.put(z, e);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return tree;
    }

}
