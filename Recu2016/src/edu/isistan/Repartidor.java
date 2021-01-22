package edu.isistan;

import java.util.List;

public class Repartidor implements Runnable {
	
	private Cola<List<Paquete>> bolsones;
	private String name;
	
	
	public Repartidor(Cola<List<Paquete>> bolsones, String name) {
		super();
		this.bolsones = bolsones;
		this.name = name;
	}


	@Override
	public void run() {
		while(true) { 
			//Espero hasta tener el siguiente bolson
			List<Paquete> bolson = bolsones.siguiente();
			System.out.println("El repartidor "+name+" tomó un nuevo bolson");
			for(Paquete p: bolson){
				try {
					Thread.sleep((long) (1000*Math.random()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//entrega el paquete
				p.entregar();
				System.out.println("El repartidor "+name+" entregó "+p);
			}
		}
	}

}
