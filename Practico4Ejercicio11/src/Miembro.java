import java.util.ArrayList;

public class Miembro implements Runnable{

    private Almacen a;

    public Miembro(Almacen a){
        this.a=a;
    }
    public synchronized boolean pedirIngredientes(){
        boolean consigioTodosLosIngredientes= false;
        while (!consigioTodosLosIngredientes){
            ArrayList<Jarra> aux1 = a.getJarras(2);
            boolean aux2 = a.getUnidadParaFermentacion();
            if(Thread.activeCount() > 2000)
            {
                a.returnJarras(aux1);
                a.returnUnidadParaFermentacion();
                return false;
            }
        }
        return true;
    }/*necesita utilizar 2 jarras, 1 unidad para fermentación/descanso de vino, jugo de fruta azucarado
    y levadura de vinificación.*/
    @Override
    public void run() {

    }
}