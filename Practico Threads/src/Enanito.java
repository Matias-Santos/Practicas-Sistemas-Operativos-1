import java.sql.SQLOutput;

public class Enanito implements Runnable {

    private int id;
    private Mesa mesa;
    private boolean atendido;

    public Enanito(int id, Mesa mesa){
        this.id = id;
        this.mesa = mesa;
    }
    public int getId(){
        return this.id;
    }
    public void trabajar(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El enanito "+this.getId()+ " esta trabajando");
    }
    public void esperarComida() {
        while (!this.atendido) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void comer(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        this.mesa.pararEnano(this);
        System.out.println("El enanito " +this.getId()+ " se levanto de la mesa");
    }
    @Override
    public void run(){
        System.out.println("El enanito " +this.getId()+ " espera para comer");
        boolean pudoSentarse = this.mesa.intentarSentarEnano(this);
        while (!pudoSentarse)
        {
            pudoSentarse = this.mesa.intentarSentarEnano(this);//wait down
        }
        this.comer();
        while (true)
            this.trabajar();
    }
}
