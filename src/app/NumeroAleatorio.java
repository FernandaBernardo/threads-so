package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumeroAleatorio {
	List<Integer> numeros;
	
	public NumeroAleatorio() {
		numeros =  new ArrayList<Integer>();
		preenche();
	}

	private void preenche() {
		for (int i = 0; i < 100; i++) {
			numeros.add(i);
		}
	}

	int gera() {
		Random r = new Random();
		int aux = numeros.remove(r.nextInt(numeros.size()));
		return aux;
	}
	
	int gera(int size) {
		Random r = new Random();
		return r.nextInt(size);
	}
}
