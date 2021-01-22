public class Pasajero implements Runnable{

    private int id;
    private Museo museo;
    public Pasajero(int id, Museo museo){
        this.id=id;
        this.museo = museo;
    }
    public int getId() {
        return id;
    }
    @Override
    public void run() {
        System.out.println("El pasajero " +this.getId()+ " se agrega al museo");
        museo.addPasajero(this);
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El pasajero" +this.getId()+ " se va del museo");
    }
}
