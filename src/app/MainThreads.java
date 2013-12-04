package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainThreads {
	private static ReentrantReadWriteLock theLock;
	private static NumeroAleatorio numAleatorio;
	private static LeitorEscritor controlador;

	private static int todasProporcoes = 101;
	private static int vezesCadaProp = 50;
	private static Thread[] threads;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		theLock = new ReentrantReadWriteLock(true);
		controlador = new LeitorEscritor();
		
		for (int k = 1; k < 3; k++) { //fazer os 2 tipos de implementação
			long inicioPrograma = System.currentTimeMillis();
			EstruturaBD.inicializa();
			for (int i = 0; i < todasProporcoes; i++) { //fazer todas as proporções de leitores e escritores
				int media = 0;
				for (int j = 0; j < vezesCadaProp; j++) { //fazer 50 vezes cada uma das proporções
					numAleatorio = new NumeroAleatorio(); //gerando arranjo com números de 1 a 100 para pegar aleatórios
					newThreads(i, k);
					long tempoInicial = System.currentTimeMillis();
					startThreads();
					joinThreads();
					long tempoFinal = System.currentTimeMillis();
					media += tempoFinal - tempoInicial;
				}
				media /= vezesCadaProp;
				System.out.println("Média - " + i + " escritores e " + (100 - i) + " leitores - " + media);
			}
			long fimPrograma = System.currentTimeMillis();
			System.out.println("Demorou " + ((fimPrograma - inicioPrograma) / 60000) + " min");
		}
	}

	private static void newThreads(int proporcao, int implementacao) throws FileNotFoundException {
		threads = new Thread[100]; //inicializando array de threads
		/*gerando objetos da proporção em lugares aleatórios*/
		for (int i = 0; i < proporcao; i++) { 
			loop(new Escritor(0, controlador, theLock, implementacao));
		}
		for (int i = 0; i < 100 - proporcao; i++) {
			loop(new Leitor(0, controlador, theLock, implementacao));
		}
	}
	
	/*inicializando as threads*/
	private static void startThreads() { 
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	/*fazer com que o main espere as threads*/
	private static void joinThreads() throws InterruptedException { 
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}

	/*procurando lugar aleatório para colocar o novo objeto*/
	private static void loop(Runnable obj) { 
		int randomNumber = numAleatorio.gera();
		threads[randomNumber] = new Thread(obj);
	}
}
