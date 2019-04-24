package NichtTiere;

import Tiere.*;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

public class TierWarteschlange extends AbstractSequentialList<Tier> {
	public class Node {
		Tier value;
		Node next;

		public Node(Tier tier) {
			value = tier;
		}

		public Node getTail() {
			return next;
		}

		public int size() {
			return getTail() == null ? 1 : 1 + getTail().size();
		}

	}

	Node head;

	public boolean add(Tier tier) {
		Node n = new Node(tier);
		if (isEmpty())
			head = n;
		else
			getLast().next = n;
		return true;
	}

	public Tier remove() {
		Tier h = head.value;
		head = head.next;
		return h;
	}

	public void remove(Node n) {
		if (isEmpty())
			return;
		if (head == n) {
			head = head.next;
			return;
		}
		Node current = head;
		while (current.next != n && current.next != null)
			current = current.next;
		current.next = current.next.next;

	}

	public void printWarteschlange() {
		Node current = head;
		while (current != null) {
			System.out.print(current.value.getTierart() + "(" + current.value.getGeschlecht() + ") | ");
			current = current.getTail();
		}
		System.out.println();
	}

	private Node getLast() {
		if (isEmpty())
			return null;
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}

	public Node node(int index) {
		if (isEmpty())
			return null;
		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public int size() {
		return head == null ? 0 : head.size();
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int sturm() {
		if (isEmpty())
			return 0;
		int counter = 0;
		while (head != null && head.value instanceof Vogel) {
			head = head.next;
			counter++;
		}
		if (isEmpty())
			return counter;
		Node current = head;

		while (current.next != null) {
			if (current.next.value instanceof Vogel) {
				current.next = current.next.next;
				counter++;
				continue;
			}
			current = current.next;
		}
		return counter;
	}

	public int grossesFressen() {
		if (isEmpty())
			return 0;
		Node current = head;
		int counter = 0;
		while (current.next != null) {
			if (current.next.value.getRaubtier() && !current.value.getRaubtier()
					&& (current.value instanceof Reptil ? !((Reptil) (current.value)).getGiftig() : true)) {
				remove(current);
				counter++;
			}
			current = current.next;
		}
		return counter;
	}

	@Override
	public ListIterator<Tier> listIterator(int index) {
		return null;
	}

}
