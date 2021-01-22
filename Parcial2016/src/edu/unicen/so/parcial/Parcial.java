package edu.unicen.so.parcial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parcial {
	private List<Trabajador> trabajadores = new ArrayList<>();
	private HiloPrincipal hp;

	public Parcial(Integer numTrabajadores, Object[] datos) {
		this.hp = new HiloPrincipal(datos);

		for (int i = 0; i < numTrabajadores; i++) {
			Trabajador trabajador = new Trabajador(this.hp);
			trabajador.setName("Trabajador " + (i + 1));
			trabajador.setDaemon(true);
			trabajadores.add(trabajador);
		}

		this.hp.setTrabajadores(trabajadores);

	}

	public Object[] ejecutar() {
		this.hp.start();
		for (Trabajador trabajador : trabajadores) {
			trabajador.start();
		}
		Object[] res = this.hp.getResultados();
		for (Trabajador trabajador : trabajadores) {
			trabajador.frenar();
		}
		return res;
	}

	public static void main(String[] args) {
		Integer numTrabajadores = new Integer(Runtime.getRuntime().availableProcessors());
		Object[] datos = new Object[20];
		for(int i=0; i<datos.length;i++)
			datos[i]=i;
		Parcial parcial = new Parcial(numTrabajadores, datos);
		System.out.println(Arrays.toString(parcial.ejecutar()));
	}
}
