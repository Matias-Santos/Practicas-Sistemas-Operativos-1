import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
          /*
    Considere un club de fabricantes de vino con 8 miembros y un almacén con los ingredientes (jugo y
levadura) al que puede acceder sólo una persona por vez.
    Para que uno de los miembros pueda hacer vino, necesita utilizar 2 jarras, 1 unidad para fermentación/descanso de vino, jugo de fruta azucarado
y levadura de vinificación.
    El proceso de la mezcla inicial requiere una estación de mezcla.
    El proceso de fermentación/descanso requiere 4 semanas para producir el vino una vez que los tres ingredientes
se hayan mezclado correctamente.
    La segunda jarra se necesita solamente en el final del proceso para decantar el vino de la levadura muerta.
    El almacén contiene 2 estaciones de mezcla, 6 jarras (de 10 litros), 7 unidades para fermentación/descanso,
15 envases (de 5 litros) de jugo de fruta azucarado, y 20 paquetes de levadura de vino (para 10 litros de vino cada uno).
    Una vez que un miembro haya terminado su vino, todos prueban antes que el miembro comience a hacer más vino.
    Además, en el club existe un administrador que se encarga de reponer los ingredientes del almacén.
     */
        ArrayList<Jarra> jarras = new ArrayList<Jarra>();
        for (int i=0; i<6; i++){
            jarras.add(new Jarra(10));
        }
        Almacen a = new Almacen(jarras,2,7, 15, 20);
    }
}
