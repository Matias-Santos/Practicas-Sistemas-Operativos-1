public class Main {
    public static void main(String args[]) {

        Banco b = new Banco();
        for (int i=0; i< 4; i++) {
            Thread th = new Thread(new Cajero(b,i, (int)(1000*Math.random()), (int)(4000*Math.random()+5000), 5000));
            th.start();
        }
        for (int i=0; i<30; i++) {
            Cuenta c = new Cuenta(i,(int)(10000 * Math.random()));
            b.addCuenta(c);
            Cliente cl = new Cliente(i,b);
            b.addCliente(cl);
            Thread th = new Thread(cl);
            th.start();
        }
    }
}
