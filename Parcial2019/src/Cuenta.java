public class Cuenta {
    int idCliente;
    int cantDinero;
    public Cuenta(int idCliente, int cantDinero) {
        this.idCliente = idCliente;
        this.cantDinero = cantDinero;
    }
    public void agregarDinero(int d) {
        this.cantDinero+=d;
    }
    public boolean retirarDinero(int d) {
        if (this.cantDinero >= d) {
            this.cantDinero -= d;
            return true;
        }
        return false;
    }
}
