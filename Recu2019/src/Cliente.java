import java.util.ArrayList;

public class Cliente implements Runnable{

    private int id;
    private Barra barra;

    public Cliente(int id, Barra barra){
        this.id = id;
        this.barra = barra;
    }
    private ArrayList<Integer> getPedidoRandom(){
        int cant = (int) (Math.random()*2);
        ArrayList<Integer> eleccion = new ArrayList<Integer>();
        int i=0;
        while (i<cant) {
            int aux = (int) (Math.random() * this.barra.getOpciones().size());
            if (!eleccion.contains(aux)) {
                eleccion.add(aux);
                i++;
            }
        }
        return eleccion;
    }
    public ArrayList<String> pedirTrago(){
        ArrayList<Integer> pedido = getPedidoRandom();
        ArrayList<String> aux = new ArrayList<String>();
        ArrayList<String> opciones = this.barra.getOpciones();
        while (!pedido.isEmpty())
            aux.add(opciones.get(pedido.remove(0)));
        return aux;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<String> aux = new ArrayList<String>();
            aux.addAll(pedirTrago());
            System.out.println("El cliente " +this.id+ " genera un trago");
            Bartender b = barra.getBartender();
            System.out.println("El cliente " +this.id+ " pide un trago");
            b.darTrago(aux);
            barra.devolverBartender(b);
        }
    }
}
