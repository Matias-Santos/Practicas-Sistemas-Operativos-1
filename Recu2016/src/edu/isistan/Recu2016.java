package edu.isistan;

import java.util.LinkedList;
import java.util.List;

public class Recu2016 {

	public static void main(String[] args){
		int repartidores = 10;
		int clasificadores = 3;
		int max = 10;
		int paquetes = 100;
		//Hay un solo camión representado por una cola de paquetes
		Cola<Paquete> camion = new Cola<>();
		@SuppressWarnings("unchecked")
		//Creo los repartidores, sus BolsonBuilder y Cola de paquetes asociados
		//Asumo que la posición en el arreglo se corresponde con el código del
		//tipo de paquetes que manejan
		Cola<List<Paquete>>[] cRepartidores = new Cola[repartidores];
		BolsonBuilder[] bRepartidores = new BolsonBuilder[repartidores];
		Repartidor[] rep = new Repartidor[repartidores];
		for(int i = 0; i < cRepartidores.length; i++){
			cRepartidores[i] = new Cola<>();
			bRepartidores[i] = new BolsonBuilder(cRepartidores[i], max);
			rep[i] = new Repartidor(cRepartidores[i], "Repartidor "+i);
			(new Thread(rep[i])).start();
		}
		//Creo los Clasificadores con todos los BolsonBuilders asociados.
		for(int i=0; i < clasificadores; i++){
			(new Thread(new Clasificador(camion, bRepartidores, clasificadores))).start();
		}
		//Genero los paquetes con un tipo aleatorio
		List<Paquete> lP = new LinkedList<>();
		for (int i=0; i<paquetes;i++){
			lP.add(new Paquete(i, (int) (repartidores*Math.random())));
		}
		//Agrego todos los paquetes al camión
		camion.addAll(lP);
		//Espero a que todos los paquetes esten entregados
		//para finalizar el programa
		for(Paquete p: lP)
			p.esperarEntregado();
		System.out.println("Se entregaron todos los paquetes.");
		System.exit(0);
	}
}
