package edu.isistan;

import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

public class Cola<T> {

	private List<T> elems = new LinkedList<>();
	
	public synchronized boolean isEmpty(){
		return elems.isEmpty();
	}
	
	public synchronized T siguiente(){
		//Espero hasta que haya elementos
		while (elems.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return elems.remove(0);
	}
	
	public synchronized void add(T e){
		elems.add(e);
		//Notifico que hay elementos
		this.notify();
	}
	
	public synchronized void addAll(Collection<? extends T> c){
		elems.addAll(c);
		//Notifico que hay elementos
		this.notifyAll();
	}
}
