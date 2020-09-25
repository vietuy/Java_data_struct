package dsa.disjoint_set;

import dsa.Position;
import dsa.map.Map;
import dsa.map.hashing.LinearProbingHashMap;

/**
 * UpTreeDisjointSetForest implementation
 * @author Viet Dinh
 *
 * @param <E> generic elements
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of up-trees
    // NOTE: The textbook implementation does not include this
    //       functionality; instead, the textbook implementation leaves
    //       the responsibility of tracking positions to the client in a
    //       separate map structure
    private Map<E, UpTreeNode<E>> map;
    
    /**
     * UpTreeDisjointSetForest constructor
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

	@Override
    public Position<E> makeSet(E value) {
		map.put(value, new UpTreeNode<E>(value));
    	return map.get(value);
    }

    @Override
    public Position<E> find(E value) {
    	UpTreeNode<E> n = map.get(value);
    	if(n.parent != n) n.parent = (UpTreeNode<E>) find(n.parent.element);
    	return n.parent;
        // NOTE: The textbook solution requires the client to keep
        //       track of the location of each position in the forest.
        //       Our implementation includes a Map to handle this for the 
        //       client, so we should allow the client to find the set
        //       that contains a node by specifying the element
    }
    
//    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
//        return map.get(current.getParent().getElement());
//    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        // Instead of trusting the client to give us the roots
        // of two up-trees, we will verify by finding the root 
        // of the up-tree that contains the element in positions s and t
        UpTreeNode<E> a = validate(find(s.getElement()));
        UpTreeNode<E> b = validate(find(t.getElement()));
        if(a != b)
    	   if(a.count > b.count) {
    		   b.parent = a;
    		   a.count += b.getCount();
    	   } else {
    		   a.parent = b;
    		   b.count += a.getCount();
    	   }
    }
    
    private UpTreeNode<E> validate(Position<E> p) {
        if(!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>)p;
    }

	/**
	 * UpTreeNode
	 * @author Viet Dinh
	 *
	 * @param <E> generic elements
	 */
    private static class UpTreeNode<E> implements Position<E> {
        
        private E element;
        private UpTreeNode<E> parent;
        private int count;
        
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }
        
        public void setElement(E element) {
            this.element = element;
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }
        
        public UpTreeNode<E> getParent() {
            return parent;
        }
        
        public void setCount(int count) {
            this.count = count;
        }
        
        public int getCount() {
            return count;
        }
    }
}
