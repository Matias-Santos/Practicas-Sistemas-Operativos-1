package edu.isistan;

public class Paquete {
	private int id;
	private int tipo;
	private boolean entregado;
	
	public Paquete(int id, int tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.entregado = false;
	}
	public int getId() {
		return id;
	}
	public int getTipo() {
		return tipo;
	}
	
	public synchronized void entregar(){
		this.entregado = true;
		this.notify();
	}
	
	public synchronized void esperarEntregado(){
		while(!this.entregado)
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public String toString() {
		return "Paquete [id=" + id + ", tipo=" + tipo + "]";
	}
	
}
