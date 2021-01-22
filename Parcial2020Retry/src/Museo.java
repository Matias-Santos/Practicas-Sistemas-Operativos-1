import java.util.*;

public class Museo {

    public ArrayList<Coche> cochesAveriados;
    private ArrayList<Turista> turistas;
    private Tecnico tecnico;
    public Museo() {
        this.cochesAveriados = new ArrayList<Coche>();
        this.turistas = new ArrayList<Turista>();
    }
    public void addCocheAveriado(Coche c) {
        synchronized (this.cochesAveriados) {
            this.cochesAveriados.add(c);
        }
    }
    public void llamarTecnico() {
        synchronized (this.tecnico) {
            System.out.println("Notifico al tecnico");
            this.tecnico.notifyAll();
        }
    }
    public ArrayList<Turista> pedirPasajeros() {
        ArrayList<Turista> aux = new ArrayList<Turista>();
        synchronized (this.turistas) {
            while (this.turistas.size() < 4) {
                try {
                    this.turistas.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=0; i<4; i++)
                aux.add(this.turistas.remove(0));
        }
        return aux;
    }
    public void addTurista(Turista t) {
        synchronized (this.turistas) {
            this.turistas.add(t);
            this.turistas.notifyAll();
        }
    }
    public void addTecnico(Tecnico t){
        this.tecnico = t;
    }
}