import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Barra {

    private ArrayList<String> botellas = new ArrayList<String>();
    private ArrayList<String> opciones = new ArrayList<String>();
    private ArrayList<Bartender> bartenders = new ArrayList<Bartender>();

    public Barra(ArrayList<String> botellas, ArrayList<Bartender> bartenders) {
        this.botellas.addAll(botellas);
        this.opciones.addAll(botellas);
        this.bartenders.addAll(bartenders);

    }

    public boolean getBotellas(String b) {
        synchronized (botellas){
            return this.botellas.remove(b);
        }
    }

    public void devolverBotellas(ArrayList<String> b) {
        synchronized (botellas) {
            this.botellas.addAll(b);
            this.botellas.notifyAll();
        }
    }

    public ArrayList<String> getOpciones() {
        ArrayList<String> aux = new ArrayList<String>();
        aux.addAll(this.opciones);
        return aux;
    }
    public void devolverBartender(Bartender bartender){
        synchronized (bartenders){
            this.bartenders.add(bartender);
            this.bartenders.notifyAll();
        }
    }
    public Bartender getBartender() {
        synchronized (bartenders) {
            while (bartenders.size() == 0)
                try {
                    this.bartenders.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            return this.bartenders.remove(0);
        }
    }
}
