import java.util.ArrayList;

public class Tecnico implements Runnable{

    private Museo museo;
    public Tecnico(Museo museo){
        this.museo=museo;
    }
    public void arreglarAuto(Coche coche){
        synchronized (this){
            //Simulo que estoy arreglando un auto
            System.out.println("El tecnico esta arreglando un auto");
            ArrayList<Coche> aux = new ArrayList<Coche>();
            aux.add(coche);
            museo.addCoches(aux);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.notify();
            }
        }
    }
    @Override
    public void run() {
        while (true){
            //Espero que haya un auto averiado
            Coche aux = museo.getCocheAveriado();
            arreglarAuto(aux);
        }
    }
}
