public class Main {
    public static void main(String[] args) {

        Museo m = new Museo();
        Tecnico t = new Tecnico(m);
        Thread tn = new Thread(t);
        tn.start();
        for (int i = 0; i < 2; i++){
            Thread th = new Thread(new Coche(m, i));
            th.start();
        }
        for (int i = 0; i < 28; i++){
            Thread th = new Thread(new Turista(m, i));
            th.start();
        }
    }
}