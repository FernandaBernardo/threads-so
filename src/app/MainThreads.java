package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainThreads {
	static Runnable[] threads;
	static EstruturaBD bd;
	private static ReentrantReadWriteLock theLock = new ReentrantReadWriteLock(true);
	private static NumeroAleatorio na= new NumeroAleatorio();
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
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
	
	private static void startThread() throws InterruptedException {
		Thread[] th = new Thread[100];
		for (int i = 0; i < th.length; i++) {
			th[i] = new Thread(threads[i]);
			th[i].start();
//			System.out.println(i);
		}
		for (int i = 0; i < th.length; i++) {
			th[i].join(); 
		}
		System.out.println();
	}
	
	private static void preencherThreads(int proporcao) throws FileNotFoundException {
		for (int i = 0; i < proporcao; i++) {
			loop(new Escritor(theLock, bd, 0));
		}
		for (int i = 0; i < 100-proporcao; i++) {
			loop(new Leitor(theLock, bd, 0));
		}
	}
	
	private static void loop(Runnable obj) {
		int randomNumber;
		boolean conseguiu = false;
		while (!conseguiu) {
			randomNumber = na.gera(100);
			if (threads[randomNumber] == null) {
				if("app.Escritor".equals(obj.getClass().getName())) {
					((Escritor) obj).setI(randomNumber);
				}
				else {
					((Leitor) obj).setI(randomNumber);
				}
				threads[randomNumber] = obj;
				conseguiu = true;
			}
		}
	}
}
