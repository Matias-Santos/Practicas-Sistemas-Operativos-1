import java.util.*;

public class Bartender{

    private int id;
    private Barra barra;

    public Bartender(int id){
        this.id = id;
    }
    public void addBarra(Barra b){
        this.barra = b;
    }
    public void darTrago(ArrayList<String> pedido){
        ArrayList<String> aux = pedido;
        ArrayList<String> botellasPedidas = new ArrayList<String>();
        Collections.sort(aux);

        while (aux.size()>0){
            String b = aux.remove(0);
            if (barra.getBotellas(b))
                botellasPedidas.add(b);
            System.out.println("El bartender " +this.id+ " retira una botella");
            if (Thread.activeCount() > (Math.random() * 1000)){
                barra.devolverBotellas(botellasPedidas);
                aux=pedido;
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El bartender " +this.id+ " devuelve todas las botellas");
        barra.devolverBotellas(botellasPedidas);
        System.out.println("El bartender " +this.id+ " le da el trago al cliente");
    }
}
