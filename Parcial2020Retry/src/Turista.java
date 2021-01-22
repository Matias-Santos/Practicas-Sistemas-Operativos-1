public class Turista implements Runnable{

    private int id;
    private Museo museo;
    public Turista(Museo m, int i){
        this.id = i;
        this.museo = m;
    }
    public int getId(){
        return this.id;
    }
    @Override
    public void run() {
        try {
            Thread.sleep((long) (1000 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El turista " + this.id + " entra al museo");
        museo.addTurista(this);
        System.out.println("El turista " + this.id + " espera para pasear");
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El turista " +this.id+ " se retira del museo");
    }
}