package NichtTiere;

import Tiere.*;

import java.util.AbstractSequentialList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A Queue for Objects of type "Tier"
 * @author Halvar Kelm and Gaston Magnin
 */
public class TierWarteschlange extends AbstractSequentialList<Tier> {
	
	/**
	 * Nodes for the list this queue is based on
	 * @author Halvar Kelm and Gaston Magnin
	 */
	public class Node {
		protected Tier value;
		protected Node next;

		/**
		 * Basic constructor to create a new node with an animal
		 * @param tier
		 */
		public Node(Tier tier) {
			value = tier;
		}

		/**
		 * Access to the part of the list behind the current node
		 * @return the next node
		 */
		public Node getTail() {
			return next;
		}
		
		/**
		 * Access to the value of the node
		 * @return the value of the node
		 */
		public Tier getValue() {
			return value;
		}
		
		/**
		 * The size of the list connected to the node
		 * @return
		 */
		public int size() {
			return getTail() == null ? 1 : 1 + getTail().size();
		}

	}

	//pointer to the start node of the queue
	Node head;
	
	/**
	 * Adds an node at the end of the list
	 * @param the Tier to add
	 */
	public boolean add(Tier tier) {
		Node n = new Node(tier);
		if (isEmpty())
			head = n;
		else
			getLast().next = n;
		return true;
	}

	/**
	 * Remove the first node from the list
	 * @return the Tier at the first node
	 */
	public Tier remove() {
		if(isEmpty()) return null;
		Tier h = head.getValue();
		head = head.getTail();
		return h;
	}

	/**
	 * Remove the specific node from the list
	 * @param the node to remove
	 * @return 
	 */
	public Tier remove(Node n) {
		if (isEmpty()) return null;
		if (head == n) {
			head = head.getTail();
			return null;
		}
		Node current = head;
		while (current.getTail() != n && current.getTail() != null) current = current.getTail();
		Tier t = current.getTail().getValue();
		current.next = current.getTail().getTail();
		return t;
	}

	/**
	 * Get the last node of the list without removing it
	 * @return the last node of the list
	 */
	private Node getLast() {
		if (isEmpty()) return null;
		
		Node current = head;
		while (current.getTail() != null) {
			current = current.getTail();
		}
		return current;
	}

	/**
	 * Get the node at an index
	 * @param index
	 * @return the node at that index
	 */
	public Node node(int index) {
		if (isEmpty()) return null;
		
		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.getTail();
		}
		return current;
	}
	
	/**
	 * The size of the queue
	 * @return the size
	 */
	@Override
	public int size() {
		return head == null ? 0 : head.size();
	}

	/**
	 * prints the queue
	 */
	@Override
	public String toString() {
		String result = "";
		Node current = head;
		while (current != null) {
			result += (current.getValue().getTierart() + "(" + current.getValue().getGeschlecht() + ") | ");
			current = current.getTail();
		}
		return result;
	}
	
	/**
	 * prints the queue as well 
	 */
	@Deprecated
	public String printWarteschlange() {
		return toString();
	}
	
	/**
	 * Whether the queue is empty
	 * @return if the queue is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Release a storm on the queue - removes all Vogel-Objects
	 * @return the number of removed Vogel-Objects
	 */
	public int sturm() {
		TierItr tItr = new TierItr(0);
		int counter = 0;
		while(tItr.hasNext()) {
			Tier t = tItr.next();
			if(t instanceof Vogel) {
				counter++;
				tItr.remove();
			}
		}
		return counter;
	}

	/**
	 * Releases the Predators on the queue
	 * Every Predator-Tier eats the Tier on it's left
	 * if that Tier is not venomous and not a Predator itself
	 * @return the number of eaten Tier
	 */
	public int grossesFressen() {
		if (isEmpty()) return 0;
		
		Node current = head;
		int counter = 0;
		while (current.getTail() != null) {
			if (current.getTail().getValue().getRaubtier() && !current.getValue().getRaubtier()
					&& (current.getValue() instanceof Reptil ? !((Reptil) (current.getValue())).getGiftig() : true)) {
				remove(current);
				counter++;
			}
			current = current.getTail();
		}
		return counter;
	}
	
	public ListIterator<Tier> listIterator(int index) {
        return new TierItr(index);

    }

    /**
     * An Iterator to traverse the TierWarteschlange
     * @author Halvar Kelm and Gaston Magnin
     */
	private class TierItr implements ListIterator<Tier> {
        private Node lastReturned = null;
        private Node next;
        private int nextIndex;
        private int expectedModCount = modCount;

        TierItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size()) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size();
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public int nextIndex() {
            return nextIndex;
        }


        public int previousIndex() {
            return nextIndex - 1;
        }


        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();
            Node lastNext = lastReturned.next;
            TierWarteschlange.this.remove(lastReturned);
            modCount++;
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super Tier> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size()) {
                action.accept(next.value);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }


        public Tier next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }

        @Deprecated //we don't do that here
        public Tier previous() {/*
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();
            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;*/
            return lastReturned.value;
        }

        public void set(Tier e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.value = e;
        }

        @Deprecated //we don't do that here
        public void add(Tier e) {
        	/*
            checkForComodification();
            lastReturned = null;
            if (next == null) {
              //  linkLast(e);
            }else {
              //  linkBefore(e, next);
            }
            nextIndex++;
            expectedModCount++;
        	*/
        }

    }

}
