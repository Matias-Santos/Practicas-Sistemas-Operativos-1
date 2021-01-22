import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Coche> coches = new ArrayList<Coche>();
        Museo m = new Museo();
        for (int i = 0; i < 3; i++){
            Thread t = new Thread(new Coche(i, m));
            t.start();
        }
        for (int i=0; i<100; i++){
            Thread t = new Thread(new Pasajero(i, m));
            t.start();
        }
        Tecnico tec = new Tecnico(m);
        m.setTecnico(tec);
        m.addCoches(coches);
        Thread t = new Thread(tec);
    }
}
