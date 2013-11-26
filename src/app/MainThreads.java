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
			preencherThread();
		}
	}

	private static void preencherThread() {
		
		int randomNumber = numeroAleatorio();
		System.out.println(randomNumber);
	}

	private static int numeroAleatorio() {
		Random r = new Random();
		return r.nextInt(100);
	}
}
