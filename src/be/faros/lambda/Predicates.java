package be.faros.lambda;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;

/*
 * In addition to the stream API, Java 8 also introduced some nice new API methods that make certain things much 
 * simpler, ex. removeIf function 
 * 
 * The actual implementation of removeIf in the Collection interface, it actually uses an Iterator under the 
 * covers
 * 
 * https://dzone.com/articles/towards-more-functional-java-using-lambdas-as-pred
 */
public class Predicates {
	
	private ConcurrentMap<String, Object> dataCache;

	/*
	 * Remove all map entries for a given set of keys
	 */
	public void removeMapEntries(Set<String> deleteKeys) {
	    Iterator<Map.Entry<String, Object>> iterator = dataCache.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<String, Object> entry = iterator.next();
	        if (deleteKeys.contains(entry.getKey())) {
	            iterator.remove();
	        }
	    }
	}

	/*
	 * Same functionality but using for loop
	 */
	public void removeMapEntriesWithForLoop(Set<String> deleteKeys) {
	    for (String deleteKey : deleteKeys) {
	        dataCache.remove(deleteKey);
	    }
	}

	/*
	 * Java 8 introduced the removeIf method as a default method, not in Map but instead in the Collection interface. 
	 * This new method "removes all of the elements of this collection that satisfy the given predicate.
	 * 
	 * Since Predicate is annotated with @FunctionalInterface, it can act as a lambda expression, a method reference, 
	 * or a constructor reference.
	 */
	public void removeMapEntriesUsingPredicate(Set<String> deleteKeys) {
	    dataCache.entrySet().removeIf(new Predicate<Map.Entry<String, Object>>() {
	        @Override
	        public boolean test(Map.Entry<String, Object> entry) {
	            return deleteKeys.contains(entry.getKey());
	        }
	    });
	}

	/*
	 * We've replaced the anonymous class with a lambda expression that takes a single Map.Entry argument
	 */
	public void removeMapEntriesUsingLambda(Set<String> deleteKeys) {
	    dataCache.entrySet().removeIf((Map.Entry<String, Object> entry) -> 
	    	deleteKeys.contains(entry.getKey()));
	}
	
	/*
	 * Java 8 can infer the argument types of lambda expressions, so we can remove the explicit (and a bit noisy) 
	 * type declarations
	 */
	public void removeMapEntriesUsingLambda_2(Set<String> deleteKeys) {
	    dataCache.entrySet().removeIf(entry -> deleteKeys.contains(entry.getKey()));
	}
	
	
	public static void main(String[] args) {

	}

}
