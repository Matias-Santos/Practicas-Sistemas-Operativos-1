package edu.isistan;


public class Clasificador implements Runnable {
	private static int CANTIDAD_CHEQUEADO=0;
	
	private Cola<Paquete> camion;
	private BolsonBuilder[] bolsones;
	private int cantClasificadores;
	
	
	public Clasificador(Cola<Paquete> camion, BolsonBuilder[] bolsones,	int cantClasificadores) {
		super();
		this.camion = camion;
		this.bolsones = bolsones;
		this.cantClasificadores = cantClasificadores;
	}


	@Override
	public void run() {
		while(true){
			Paquete p = null;
			synchronized (camion) {
				//Cuento cuantos estamos chequeando que si el camión está vacio
				CANTIDAD_CHEQUEADO++;
				//Si hay paquetes en el camión CANTIDAD_CHEQUEADO estará entre 0 y 1
				//Si es el total de clasificadores y el camión está vacio (por si hay un solo clasificador)
				//Notifico el fin de tanda porque 
				//no hay otro clasificador que pueda estar asignando paquetes en este momento.
				if (camion.isEmpty() && CANTIDAD_CHEQUEADO==cantClasificadores) {
					System.out.println("Clasificador ["+Thread.currentThread().getId()+"] Notificando fin de tanda ");
					for(BolsonBuilder b: bolsones) {
						b.finTanda();
					}
				}
				//saco el paquete siguiente (si hay)
				p = camion.siguiente();
				//Decremento el número de clasificadores que están chequeando si se vació el camión
				CANTIDAD_CHEQUEADO--;
			}
			try {
				Thread.sleep((long) (500*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Clasificador ["+Thread.currentThread().getId()+"] Clasificando "+p);
			//Asigno el paquete al bolson correspondiente
			bolsones[p.getTipo()].add(p);
		}
	}

}
