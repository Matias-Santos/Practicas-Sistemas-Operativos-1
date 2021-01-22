package edu.unicen.so.parcial.test;

import java.util.Arrays;

import org.junit.Test;

import edu.unicen.so.parcial.Parcial;

public class ParcialTest {
	@Test
	public void test() throws Exception {
		Object[] datos = new Object[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k" };
		Object[] resultados = new Object[datos.length];
		for (int j = 0; j < datos.length; j++)
			resultados[j] = "Procesado " + datos[j];
		for (int i = 0; i < 10000; i++) {
			Integer numTrabajadores = new Integer((int) (8 * Math.random() + 1));
			Parcial parcial = new Parcial(numTrabajadores, datos);
			Object[] res = parcial.ejecutar();
			assert(Arrays.equals(resultados, res));
		}
	}
}
