import java.util.*;

public class Tecnico implements Runnable {

    private Museo museo;
    public Tecnico(Museo m) {
        this.museo = m;
        this.museo.addTecnico(this);
        museo.cochesAveriados = new ArrayList<Coche>();
    }
    public void arreglarCoches() {
        while (museo.cochesAveriados.isEmpty()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (museo.cochesAveriados) {
                Coche c = museo.cochesAveriados.remove(0);
                System.out.println("El tecnico se prepara para reparar el coche " + c.getId());
                try {
                    Thread.sleep((long) (2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.setReparado();
                synchronized (c) {
                    c.notify();
                }
                System.out.println("El tecnico termina de reparar el coche " + c.getId());
            }
        }
    }
    @Override
    public void run() {
        ArrayList<Coche> aux = new ArrayList<Coche>();
        while (true) {
            arreglarCoches();
        }
    }
}