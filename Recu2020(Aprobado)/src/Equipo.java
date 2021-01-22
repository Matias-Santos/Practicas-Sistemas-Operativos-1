public class Equipo {

    int mililitros = 0;

    public Equipo(int mililitros) {
        this.mililitros = mililitros;
    }
    public void recargarTermo(int m) {
        this.mililitros = m;
    }
    public boolean cebarMate(int c) {
        if ((this.mililitros - c) > 0) {
            this.mililitros -= c;
            return true;
        }
        return false;
    }
}
