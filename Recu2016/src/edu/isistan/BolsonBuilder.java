package edu.isistan;

import java.util.LinkedList;
import java.util.List;
/**
 * Encargado de ir generando los bolsones ({@code List<Paquete>}) y agregarlos en las
 * cola de bolsones correspondiente 
 * @author fanat
 *
 */
public class BolsonBuilder {

	private List<Paquete> elems = new LinkedList<>();
	private Cola<List<Paquete>> repartidor;
	private int max;
	
	public BolsonBuilder(Cola<List<Paquete>> repartidor, int max) {
		super();
		this.repartidor = repartidor;
		this.max = max;
	}
	
	public synchronized void add(Paquete p){
		elems.add(p);
		if (elems.size()>=max) {
			System.out.println("Bolson lleno");
			repartidor.add(elems);
			elems = new LinkedList<>();
		}
	}
	
	public synchronized void finTanda(){
		if(elems.isEmpty())
			return;
		System.out.println("Fin de tanda");
		repartidor.add(elems);
		elems = new LinkedList<>();
	}
}
