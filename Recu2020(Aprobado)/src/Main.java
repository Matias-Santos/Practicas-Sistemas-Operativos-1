import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {

        int cantAmigos = 4;
        Equipo e = new Equipo(1000);
        ArrayList<Amigo> amigos = new ArrayList<Amigo>();
        for (int i = 0; i < cantAmigos; i++) {
            Amigo a = new Amigo(i);
            //Agrego los amigos a la lista en posiciones aleatorias
            //El primero siempre será el cebador
            amigos.add((int)(amigos.size()*Math.random()), a);
        }

        //Imprimo el orden de los amigos
        for(int i = 0; i < amigos.size(); i++)
            System.out.println(amigos.get(i).getId());

        //Le doy el equipo de mate al primero
        amigos.get(0).addEquipo(e);
        amigos.get(0).setCebador(true);

        //Agrego todos los amigos para que puedan pasarse el mate
        for (int i = 0; i < cantAmigos; i++) {
            amigos.get(i).addAmigos(amigos);
        }

        //Inicializo toods los threads
        for (int i = 0; i < cantAmigos; i++) {
            Thread th = new Thread(amigos.get(i));
            th.start();
        }
    }
}
