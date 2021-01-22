import java.util.ArrayList;

public class Coche implements Runnable{
    private ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
    private int id;
    private Museo museo;
    public Coche(int id,Museo museo){
        this.id = id;
        this.museo = museo;
    }
    public void pasearPasajeros(){
        if(Math.random() < 0.2) {
            System.out.println("Se rompio el coche " +this.getId());
            museo.addCocheAveriado(this);
        }
        //Simulo pasear los pasajeros
        System.out.println("El coche " +this.getId()+ " se va de paseo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (pasajeros.size()>0){
            this.pasajeros.remove(0).notify();
        }
    }
    public void pedirPasajeros(){
        System.out.println("El coche " +this.getId()+ " pide pasajeros");
        ArrayList<Pasajero> pasajeros = museo.getPasajeros();
    }

    public int getId() {
        return this.id;
    }
    @Override
    public void run() {
        while (true) {
            pedirPasajeros();
            pasearPasajeros();
        }
    }
}
