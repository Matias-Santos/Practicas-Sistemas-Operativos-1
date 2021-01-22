import java.util.*;

public class Banco {
    HashMap<Integer, Cuenta> cuentas = new HashMap<Integer, Cuenta>();
    ArrayList<Cliente> clientes;

    public Banco() {
        this.cuentas = new HashMap<Integer, Cuenta>();
        this.clientes = new ArrayList<Cliente>();
    }
    public Cliente getCliente() {
        synchronized (this.clientes) {
            while (this.clientes.isEmpty()) {
                try {
                    this.clientes.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.clientes.remove(0);
        }
    }
    public void addCliente(Cliente c) {
        synchronized (this.clientes){
            this.clientes.add(c);
            this.clientes.notifyAll();
        }
    }
    public void addCuenta(Cuenta c) {
        synchronized (this.cuentas){
            this.cuentas.put(c.idCliente,c);
        }
    }
    public int getSizeCuentas() {
        return cuentas.size();
    }
}
