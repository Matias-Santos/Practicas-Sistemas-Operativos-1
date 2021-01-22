package edu.so;

public class Tecnico implements Runnable {
	private Cola<Coche> rotos;
	
	public Tecnico(Cola<Coche> rotos) {
		this.rotos = rotos;
	}
	
	@Override
	public void run() {
		while(true) {
			Coche sig = rotos.siguiente();
			System.out.println("El técnico está reparando el coche " + sig.getId());
			try {
				Thread.sleep(100+(long)(1000*Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sig.setReparado();
		}
	}

}
