import java.util.ArrayList;

public class Museo {

    private ArrayList<Coche> cochesAveriados = new ArrayList<Coche>();
    private ArrayList<Coche> coches = new ArrayList<Coche>();
    private ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
    private Tecnico tecnico;
    public Museo(){
    }
    public Coche getCocheAveriado(){
        synchronized (cochesAveriados) {
                if (!this.cochesAveriados.isEmpty()) {
                    try {
                        this.cochesAveriados.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Coche aux = this.cochesAveriados.remove(0);
                return aux;
            }
    }
    public void addCocheAveriado(Coche coche) {
        synchronized (this.cochesAveriados) {
            this.coches.remove(coche);
            this.cochesAveriados.add(coche);
            this.cochesAveriados.notifyAll();
        }
    }
    public void addCoches(ArrayList<Coche> coche){
        synchronized (coches){
            this.coches.addAll(coche);
        }
    }
    public void addPasajero(Pasajero pasajero){
        synchronized (pasajeros){
            this.pasajeros.add(pasajero);
            if (this.pasajeros.size()>=4)
                this.pasajeros.notifyAll();
        }
    }
    public ArrayList<Pasajero> getPasajeros(){
        synchronized (this.pasajeros){
            while (this.pasajeros.size() < 4){
                try {
                    System.out.println("El museo espera que haya mas de 3 pasajeros para darselos a un coche");
                    this.pasajeros.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ArrayList<Pasajero> aux = new ArrayList<Pasajero>();
            for (int i=0; i<3; i++){
                aux.add(this.pasajeros.remove(0));
            }
            return aux;
        }
    }
    public void setTecnico(Tecnico tecnico){
        this.tecnico = tecnico;
    }
}
