package NichtTiere;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import Tiere.Tier;

@SuppressWarnings("serial")
public class Stall<T extends Tier> extends TreeSet<T> {
	
	@Override
	public boolean add(T e) {
		return super.add(e);
	}
	
	/**
	 * Adds all objects from this Stall to the collection
	 * @param collection
	 * @param raubtier
	 */
	public void raubtiertrennung(Collection<T> c, boolean raubtier) {
		Iterator<T> itr = this.iterator();
		while(itr.hasNext()) {
	         T element = itr.next();
	         if(element.getRaubtier() == raubtier) c.add(element);
		}
	}
	
	/**
	 * Returns a string representation of this collection.
	 */
	@Override
	public String toString() {
		String result = "";
		Iterator<T> itr = this.iterator();
		while(itr.hasNext()) {
	         Tier element = itr.next();
	         if(element instanceof Tier)
	        	 result += (element.getTierart() + "(" + element.getGeschlecht() + ") | ");
	         else
	        	 result += element.toString()+" | ";
		}
		return result;
	}
}
