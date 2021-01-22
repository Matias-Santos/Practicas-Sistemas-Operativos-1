package edu.so;

public class Parcialito {

	public static void main(String[] args) {
		Cola<Coche> parking = new Cola<>();
		Cola<Coche> roto = new Cola<>();
		Cola<Visitante> visitantes = new Cola<>();
		
		Thread tecnico = new Thread(new Tecnico(roto));
		tecnico.setDaemon(true);
		tecnico.start();
		
		for (int i = 0; i < 3; i++) {
			Thread coche = new Thread(new Coche(i, visitantes, roto, parking));
			coche.setDaemon(true);
			coche.start();
		}
		
		for (int i = 0; i < 100; i++) {
			Thread vis = new Thread(new Visitante(i, visitantes));
			vis.start();
		}
	}

}
