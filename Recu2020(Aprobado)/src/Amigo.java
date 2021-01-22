import java.util.ArrayList;

public class Amigo implements Runnable {

    private ArrayList<Amigo> amigos = new ArrayList<Amigo>();
    private Equipo equipo = null;
    private int id;
    private boolean mate = false;
    private int posSiguiente = 0;
    private boolean cebador = false;
    private boolean siguiente = false;

    public Amigo(int id) {
        this.id = id;
    }

    public void devolverMate() {
        synchronized (this) {
            this.mate = false;
        }
        if (Math.random() < 0.2) {
            //Le doy el mate a cualquiera de los chicos, menos al cebador
            int i = (int)(Math.random() * (amigos.size() - 1) + 1);
            Amigo a = amigos.get(i);
            System.out.println("El amigo " +this.id+ " se confundio y le devolvio el mate al amigo " + a.id);
            synchronized (a) {
                a.mate = true;
                a.notify();
            }
        }
        else {
            System.out.println("El amigo " + this.id + " devuelve el mate correctamente");
            Amigo a = amigos.get(0);
            synchronized (a) {
                a.mate = true;
                a.notify();
            }
        }
    }
    public void tomarMate() {
        //Simulo tomar el mate
        System.out.println("El amigo " + this.id + " está tomando el mate");
        synchronized (this) {
            try {
                Thread.sleep((long) (2000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        devolverMate();
    }
    public void darMate() {
        Amigo a = amigos.get(posSiguiente);
        synchronized (a) {
            a.siguiente = true;
            a.mate = true;
            a.notify();
        }
    }
    public void cebarMate() {
        while(true) {
            posSiguiente = (posSiguiente + 1) % amigos.size();
            System.err.println("siguiente: " + posSiguiente);
            if (!(equipo.cebarMate(50))) {
                System.out.println("El termo se quedó sin agua y hay que recargarlo");
                equipo.recargarTermo(1000);
            }
            if (posSiguiente == 0) {
                System.out.println("El cebador toma un mate");
                synchronized (this) {
                    try {
                        Thread.sleep((long) (2000 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                darMate();
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void addAmigos(ArrayList<Amigo> amigos){
        this.amigos.addAll(amigos);
    }
    public void addEquipo(Equipo e){
        this.equipo = e;
    }
    public void setCebador(boolean cebador) {
        this.cebador = cebador;
    }
    public int getId() {
        return  this.id;
    }
    @Override
    public void run() {
        if (this.cebador) {
            System.out.println("El amigo " +this.id+ " se declara cebador");
            cebarMate();
        }
        while (true) {
            while (!mate) {
                synchronized (this) {
                    //Espero que me llegue el un mate
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (siguiente) {
                tomarMate();
                synchronized (this) {
                    this.siguiente = false;
                }
            }
            else
                devolverMate();
        }
    }
}
