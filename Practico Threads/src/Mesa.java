import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {

    private List<Enanito> sillas;
    private int maxSize;
    private Lock mutex = new ReentrantLock();

    public Mesa(int maxSize){
        this.maxSize = maxSize;
        this.sillas = new ArrayList<>();
    }
    public boolean intentarSentarEnano(Enanito en){
        boolean seSento=false;
        try {
            mutex.lock();
            if (this.sillas.size() < getMaxSize()){
                System.out.println("El enanito " +en.getId()+ " se sento en la mesa");
                this.sillas.add(en);
                seSento=true;
            }
            mutex.unlock();
        }
        finally {
            return seSento;
        }
    }
    public void pararEnano(Enanito en){
        mutex.lock();
        this.sillas.remove(en);
        System.out.println("Cantidad de sillas ocupadas " +this.sillas.size());
        mutex.unlock();
    }
    public int getMaxSize() {
        return maxSize;
    }
    public Enanito getEnanoAAlimentarEnano(){
        synchronized (this.sillas) {
            while (this.sillas.isEmpty()) {
                try {
                    this.sillas.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.sillas.get(0);
        }
    }
}