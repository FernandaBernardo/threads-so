package app;

import java.util.Random;

public class NumeroAleatorio {
	int gera(int intervalo) {
		Random r = new Random();
		return r.nextInt(intervalo);
	}
}
