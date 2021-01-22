package edu.so;

public class Visitante implements Runnable {
	private int id;
	private Cola<Visitante> visitantes;
	private boolean termino = false;
	
	
	public Visitante(int id, Cola<Visitante> visitantes) {
		super();
		this.id = id;
		this.visitantes = visitantes;
	}

	@Override
	public void run() {
		System.out.println("El visitante "+this.id+" da vueltas");
		try {
			Thread.sleep(100 + (long)(1000*Math.random()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("El visitante "+this.id+" se va a encolar para el paseo");
		visitantes.agregar(this);
		this.esperarFinPaseo();
		System.out.println("El visitante "+this.id+" se fue");
	}
	
	private synchronized void esperarFinPaseo() {
		while(!this.termino) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void finPaseo() {
		this.termino = true;
		this.notify();
	}

	public int getId() {
		return this.id;
	}

}
