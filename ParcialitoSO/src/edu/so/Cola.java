package edu.so;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Cola<T> {
	private List<T> elements = new LinkedList<>();
	
	public synchronized int agregar(T elem) {
		this.elements.add(elem);
		this.notify();
		return this.elements.size() - 1;
	}
	
	public synchronized T siguiente() {
		while(this.elements.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.elements.remove(0);
	}
	
	public synchronized Optional<T> tope() {
		if (this.elements.isEmpty())
			return Optional.empty();//null
		return Optional.of(this.elements.get(0)); //this.elements.get(0)
	}

}
