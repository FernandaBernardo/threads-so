package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumeroAleatorio {
	List<Integer> numeros;
	Random r = new Random();
	
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
		return numeros.remove(r.nextInt(numeros.size()));
	}
	
	int gera(int size) {
		return r.nextInt(size);
	}
}
