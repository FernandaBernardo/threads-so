package app;

import java.io.FileNotFoundException;
import java.util.Random;

public class MainThreads {
	static Objeto[] threads;
	static EstruturaBD bd;

	public static void main(String[] args) throws FileNotFoundException {
		threads = new Objeto[100];
		bd = new EstruturaBD();
		
		for (int i = 0; i < threads.length; i++) {
			preencherThreads(i); 
			long tempoInicial = System.currentTimeMillis();
			//ARRUMAR
			
			long tempoFinal = System.currentTimeMillis();
		}
		
	}

	private static void preencherThreads(int proporcao) {
		for (int i = 0; i < proporcao; i++) {
			loop(new Escritor());
		}
		for (int i = 0; i < 100-proporcao; i++) {
			loop(new Leitor());
		}
	}

	private static void loop(Objeto obj) {
		int randomNumber;
		boolean conseguiu;
		conseguiu = false;
		while (!conseguiu) {
			randomNumber = numeroAleatorio();
			if (threads[randomNumber] == null) {
				threads[randomNumber] = obj;
				conseguiu = true;
			}
		}
	}

	private static int numeroAleatorio() {
		Random r = new Random();
		return r.nextInt(100);
	}
}
