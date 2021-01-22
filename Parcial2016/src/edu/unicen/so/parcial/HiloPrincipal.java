package edu.unicen.so.parcial;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class HiloPrincipal extends Thread {

	private List<Trabajador> trabajadores;
	private Object[] datos;
	private Object[] resultados;

	private int tamañoTandaActual = 0;

	private Semaphore finDeTanda = new Semaphore(0);
	private Semaphore resultadoDisponible = new Semaphore(0);
	private Semaphore mutexContadorTanda = new Semaphore(1);

	public HiloPrincipal(Object[] datos) {
		this.datos = datos;
		this.resultados = new Object[datos.length];
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public void run() {
		try {
			int indiceDatos = 0;
			while (indiceDatos < datos.length) {
				mutexContadorTanda.acquire();
				Iterator<Trabajador> it = trabajadores.iterator();
				while (it.hasNext() && indiceDatos < datos.length) {
					it.next().asignarTrabajo(indiceDatos, datos[indiceDatos]);
					indiceDatos++;
					tamañoTandaActual++;
				}
				mutexContadorTanda.release();
				finDeTanda.acquire();
			}
			resultadoDisponible.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void setTrabajo(int pos, Object res) {
		try {
			mutexContadorTanda.acquire();
			resultados[pos] = res;
			if (--tamañoTandaActual == 0)
				finDeTanda.release();
			mutexContadorTanda.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object[] getResultados() {
		try {
			resultadoDisponible.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return resultados;
	}

}
