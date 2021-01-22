import java.util.ArrayList;

public class Almacen {
    ArrayList<Jarra> jarras;
    Integer estacionDeMezcla;
    Integer unidadParaFermentacion;
    Integer jugoDeFrutaAzucarado;
    Integer levaduraDeVino;

    public Almacen(ArrayList<Jarra> jarras, Integer estacionDeMezcla, Integer unidadParaFermentacion, Integer jugoDeFrutaAzucarado, Integer levaduraDeVino){
        this.jarras=jarras;
        this.estacionDeMezcla = estacionDeMezcla;
        this.unidadParaFermentacion = unidadParaFermentacion;
        this.jugoDeFrutaAzucarado = jugoDeFrutaAzucarado;
        this.levaduraDeVino = levaduraDeVino;
    }
    public ArrayList<Jarra> getJarras(int cantPedidas){
        int i = 0;
        ArrayList<Jarra> conseguidas = new ArrayList<Jarra>();
        synchronized (jarras){
            while (conseguidas.size()<cantPedidas){
                if (!jarras.isEmpty())
                    conseguidas.add(jarras.remove(0));
                else
                    try {
                        this.jarras.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
            }
        }
        return conseguidas;
    }
    public void returnJarras(ArrayList<Jarra> j){
        synchronized (jarras){
            this.jarras.addAll(j);
            this.jarras.notifyAll();
        }
    }
    public boolean getUnidadParaFermentacion(){
        synchronized (unidadParaFermentacion){
            if (this.unidadParaFermentacion > 0){
                this.unidadParaFermentacion--;
                return true;
            }
        }
        return false;
    }
    public void returnUnidadParaFermentacion(){
        synchronized (unidadParaFermentacion){
            this.unidadParaFermentacion++;
        }
    }
}