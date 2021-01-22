public class Cliente implements Runnable {

    private String accion;
    private int nroCuentaATransferir;
    private int cantDinero;
    private Banco banco;
    private int id;
    public Cliente(int id, Banco b) {
        this.banco = b;
        this.id = id;
    }
    public void setAccion() {
        double i = Math.random();
        nroCuentaATransferir = (int)(banco.getSizeCuentas() * Math.random());
        if (i < 0.33)
            accion = "transferir";
        else {
            if (i < 0.66)
                accion = "depositar";
            else
                accion = "retirar";
            nroCuentaATransferir = -1;
        }
        cantDinero = (int)(Math.random() * 10000);
    }
    public String getAccion(){
        return this.accion;
    }

    public int getNroCuentaATransferir() {
        return this.nroCuentaATransferir;
    }

    public int getCantDinero() {
        return this.cantDinero;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void run() {
        setAccion();
        System.out.println("El cliente "+this.id+" espera a ser atendido");
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El cliente " + this.id + " se retira del banco");
    }
}
