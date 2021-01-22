import java.util.*;

public class Coche implements Runnable {

    private Museo museo;
    private int id;
    private int nroPaseo = 0;
    boolean enReparacion = false;
    ArrayList<Turista> pasajeros;
    public Coche(Museo m, int i) {
        this.museo = m;
        this.id = i;
        this.pasajeros = new ArrayList<Turista>();
    }
    public void pasear() {
        this.pasajeros = museo.pedirPasajeros();
        if (Math.random() < 0.2) {
            System.out.println("El coche " +this.id+ " se averió");
            this.enReparacion = true;
        }
        while (this.enReparacion) {
            System.out.println("El coche " +this.id+ " espera a ser reparado");
            museo.addCocheAveriado(this);
            museo.llamarTecnico();
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //Simulo el paseo
        try {
            Thread.sleep((long) (2000 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Turista t:this.pasajeros) {
            System.out.println("El turista " +t.getId()+ " termina el paseo");
            synchronized (t) {
                t.notify();
            }
        }
        this.nroPaseo++;
    }
    public void setReparado() {
        this.enReparacion = false;
    }
    public int getId() {
        return this.id;
    }
    @Override
    public void run() {
        while (true) {
            System.out.println("El coche " +this.id+ " se prepara para dar el " +this.nroPaseo+ "er paseo");
            pasear();
        }
    }
}