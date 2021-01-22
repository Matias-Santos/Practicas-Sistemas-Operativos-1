public class Main {

    public static void main(String[] args) {
        // write your code here
        Mesa m = new Mesa(4);
        Thread blancanieves = new Thread(new Blancanieves(m));
        blancanieves.start();
        for (int i=0; i<7; i++){
            Thread enanito = new Thread(new Enanito(i,m));
            enanito.start();
        }
    }
}