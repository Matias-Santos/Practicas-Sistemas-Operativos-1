public class Cajero implements Runnable {

    private Banco banco;
    private int id;
    private int min;
    private int max;
    private int cantActual;

    public Cajero(Banco banco, int id, int min, int max, int cantActual) {
        this.banco = banco;
        this.id = id;
        this.min = min;
        this.max = max;
        this.cantActual = cantActual;
    }
    public void transferir(Cliente c) {
        Cuenta cu = this.banco.cuentas.get(c.getId());
        synchronized (cu) {
            if (cu.retirarDinero(c.getCantDinero())) {
                Cuenta cuATransferir = this.banco.cuentas.get(c.getNroCuentaATransferir());
                synchronized (cuATransferir) {
                    cuATransferir.agregarDinero(c.getCantDinero());
                }
                System.out.println("El cliente " + c.getId() + " realizó la transacción satisfactoriamente");
            } else
                System.out.println("El cliente " + c.getId() + " no tiene " + c.getCantDinero() + " en su cuenta");
        }
    }
    public void depositar(Cliente c) {
        Cuenta cu = this.banco.cuentas.get(c.getId());
        synchronized (cu) {
            cu.agregarDinero(c.getCantDinero());
            this.cantActual+=c.getCantDinero();
            if (this.cantActual> max) {
                System.out.println("El cajero " +this.id+ " se va a guardar dinero ya que tiene demasiado en sus manos");
                this.cantActual=(this.min+this.max)/2;
            }
        }
    }
    public void retirar(Cliente c) {
        Cuenta cu = this.banco.cuentas.get(c.getId());
        synchronized (cu){
            if (cu.retirarDinero(c.getCantDinero())) {
                if(this.cantActual < c.getCantDinero())
                {
                    System.out.println("El cajero " +this.id+ " se va a buscar mas dinero para darle al cliente");
                    try {
                        Thread.sleep((long) (2000*Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.cantActual=this.max;
                }
                this.cantActual -=c.getCantDinero();
                System.out.println("El cliente " + c.getId() + " retiró " + c.getCantDinero() + " satisfactoriamente");
            }
            else
                System.out.println("El cliente "+c.getId()+ " no tiene " +c.getCantDinero()+ " en su cuenta");
        }
    }
    @Override
    public void run() {
        while (true) {
            Cliente c = banco.getCliente();
            String aux = c.getAccion();
            if (aux == "transferir"){
                transferir(c);
            }
            else
                if (aux == "depositar"){
                    depositar(c);
                }
                else {
                    retirar(c);
                }
            synchronized (c){
                c.notify();
            }
        }
    }
}
