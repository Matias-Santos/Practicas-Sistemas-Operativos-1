package edu.so;

public class Coche implements Runnable {
	private int id;
	private Cola<Visitante> visitantes;
	private Cola<Coche> reparar;
	private Cola<Coche> parking;
	private boolean turno = false;
	private boolean roto = false;
	
	
	public Coche(int id, Cola<Visitante> visitantes, Cola<Coche> reparar, Cola<Coche> parking) {
		super();
		this.id = id;
		this.visitantes = visitantes;
		this.reparar = reparar;
		this.parking = parking;
	}

	@Override
	public void run() {
		Visitante[] pasajeros = new Visitante[4];
		while(true) {
			System.out.println("El coche " + this.id + " se encola en espera");
			int pos = this.parking.agregar(this);
			if (pos != 0) {
				this.esperarTurno();
			}
			System.out.println("El coche " + this.id + " carga pasajeros");
			for(int i=0; i<4; i++) {
				pasajeros[i] = this.visitantes.siguiente();
				System.out.println("El coche " + this.id + " cargo al pasajero "+ pasajeros[i].getId());
			}
			synchronized (this.parking) {
				this.parking.siguiente();
				this.parking.tope().ifPresent(c -> c.notificarSiguiente());
			}
			System.out.println("El coche " + this.id + " salio de paseo");
			try {
				Thread.sleep((long)(100+2000*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.verificarRotura();
			try {
				Thread.sleep((long)(100+2000*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("El coche " + this.id + " el paseo termino");
			for(int i=0; i<4; i++) {
				System.out.println("El coche " + this.id + " bajo al pasajero "+ pasajeros[i].getId());
				pasajeros[i].finPaseo();
				pasajeros[i] = null;
			}
		}
	}

	private synchronized void verificarRotura() {
		if (Math.random() < 0.3) {
			System.out.println("El coche " + this.id + " se rompio");
			this.roto = true;
			this.reparar.agregar(this);
			while(this.roto) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("El coche " + this.id + " ya está en condiciones");
		}
	}

	private synchronized void notificarSiguiente() {
		this.turno = true;
		this.notify();
	}

	private synchronized void esperarTurno() {
		while(!this.turno) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.turno = false;
	}

	public int getId() {
		return this.id;
	}

	public synchronized void setReparado() {
		this.roto = false;
		this.notify();
	}

}
