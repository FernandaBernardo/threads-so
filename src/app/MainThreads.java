package app;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainThreads {
	static Runnable[] threads;
	static EstruturaBD bd;
	private static ReentrantReadWriteLock theLock = new ReentrantReadWriteLock(true);
	
	public static void main(String[] args) throws FileNotFoundException {
		bd = new EstruturaBD();
		
		for (int i = 0; i < 100; i++) {
			threads = new Runnable[100];
			
			preencherThreads(i);
			startThread();
			long tempoInicial = System.currentTimeMillis();
			//ARRUMAR
			
			long tempoFinal = System.currentTimeMillis();
			
		}
	}

	private static void startThread() {
		Thread[] th = new Thread[100];
		for (int i = 0; i < th.length; i++) {
			th[i] = new Thread(threads[i]);
			th[i].start();
//			System.out.println(i);
		}
	}

	private static void preencherThreads(int proporcao) {
		for (int i = 0; i < proporcao; i++) {
			loop(new Escritor(theLock));
		}
		for (int i = 0; i < 100-proporcao; i++) {
			loop(new Leitor(theLock));
		}
	}

	private static void loop(Runnable obj) {
		int randomNumber;
		boolean conseguiu = false;
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
