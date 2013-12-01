package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Escritor implements Runnable{
//	private ReentrantReadWriteLock theLock;
	private int i;
	private LeitorEscritor controlador;
	
	public Escritor(ReentrantReadWriteLock theLock, int i, LeitorEscritor controlador) throws FileNotFoundException {
//		this.theLock = theLock;
		this.controlador = controlador;
		this.setI(i);
	}
	
	@Override
	public void run() {
//		theLock.writeLock().lock();
		controlador.comecarLeitura();
		EstruturaBD.acessosAleatoriosEscritor();		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controlador.pararLeitura();
//		theLock.writeLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
