import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> b = new ArrayList<String>();
        b.add("Cerveza");
        b.add("Whisky");
        b.add("Fernet");
        b.add("Vodka");
        b.add("Coca-Cola");
        b.add("Jugo de Naranja");

        ArrayList<Bartender> barts = new ArrayList<Bartender>();
        for (int i=0; i<3; i++){
            Bartender bart = new Bartender(i);
            barts.add(bart);
        }
        Barra br = new Barra(b,barts);
        for (int i = 0; i<barts.size(); i++)
            barts.get(i).addBarra(br);
        for (int i=0; i<7; i++){
            Thread t = new Thread(new Cliente(i, br));
            t.start();
        }
    }
}
