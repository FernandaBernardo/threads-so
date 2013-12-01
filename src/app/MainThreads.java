package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainThreads {
	private static ReentrantReadWriteLock theLock = new ReentrantReadWriteLock(true);
	private static NumeroAleatorio numAleatorio;
	private static LeitorEscritor controlador;

	private static int todasProporcoes = 101;
	private static int vezesCadaProp = 50;
	private static Thread[] threads;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		long inicioPrograma = System.currentTimeMillis();
		controlador = new LeitorEscritor();

		EstruturaBD.inicializa();
		for (int i = 0; i < todasProporcoes; i++) {
			int media = 0;
			for (int j = 0; j < vezesCadaProp; j++) {
				numAleatorio = new NumeroAleatorio();
				newThread(i);
				long tempoInicial = System.currentTimeMillis();
				startThread();
				joinThread();
				long tempoFinal = System.currentTimeMillis();
				media += tempoFinal - tempoInicial;
			}
			media /= vezesCadaProp;
			System.out.println("Média - " + i + " escritores e " + (100 - i) + " leitores - " + media);
		}
		long fimPrograma = System.currentTimeMillis();
		System.out.println("Demorou " + ((fimPrograma - inicioPrograma) / 60000) + " min");
	}

	private static void startThread() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	private static void newThread(int proporcao) throws FileNotFoundException {
		threads = new Thread[100];
		for (int i = 0; i < proporcao; i++) {
			loop(new Escritor(theLock, 0, controlador));
		}
		for (int i = 0; i < 100 - proporcao; i++) {
			loop(new Leitor(theLock, 0, controlador));
		}
	}

	private static void joinThread() throws InterruptedException {
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}

	private static void loop(Runnable obj) {
		int randomNumber = numAleatorio.gera();
		// if("app.Escritor".equals(obj.getClass().getName())) {
		// ((Escritor) obj).setI(randomNumber);
		// }
		// else {
		// ((Leitor) obj).setI(randomNumber);
		// }
		threads[randomNumber] = new Thread(obj);
	}
}
