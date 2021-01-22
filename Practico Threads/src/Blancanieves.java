public class Blancanieves implements Runnable{

    public Mesa mesa;

    public Blancanieves(Mesa mesa){
        this.mesa=mesa;
    }
    public void servirComida(Enanito en){
        en.notify();
    }
    public void run(){
        while (true){
            Enanito en = this.mesa.getEnanoAAlimentarEnano();
            System.out.println("Blancanieves le sirve el plato de comida al enanito " +en.getId());
            this.servirComida(en);
        }
    }
}