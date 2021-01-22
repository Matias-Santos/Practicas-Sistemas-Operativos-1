package edu.unicen.so.parcial;

import java.util.concurrent.Semaphore;

public class Trabajador extends Thread {

	private Object dato;
	private int pos;
	private HiloPrincipal ppal;
	private Semaphore procesarDatos = new Semaphore(0);
	private boolean stop = false;

	public Trabajador(HiloPrincipal ppal) {
		this.ppal = ppal;
	}

	@Override
	public void run() {
		try {
			while (!stop) {
				procesarDatos.acquire();
				if (stop)
					return;
				Object res = processBlock(dato);
				ppal.setTrabajo(pos, res);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Object processBlock(Object dato2) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + ": Procesando " + dato2.toString());
		Thread.sleep((long) (Math.random() * 5000));
		return "Procesado " + dato2.toString();
	}

	public void asignarTrabajo(int pos, Object dato) {
		this.dato = dato;
		this.pos = pos;
		procesarDatos.release();
	}

	public void frenar() {
		this.stop = true;
		procesarDatos.release();
	}

}
