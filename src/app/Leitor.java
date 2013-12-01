package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Leitor implements Runnable{
//	private ReentrantReadWriteLock theLock;
	private int i;
	private LeitorEscritor controlador;

	public Leitor(ReentrantReadWriteLock theLock, int i, LeitorEscritor controlador) throws FileNotFoundException {
//		this.theLock = theLock;
		this.controlador = controlador;
		this.setI(i);
	}
	
	@Override
	public void run() {
//		theLock.readLock().lock();
		controlador.comecarLeitura();
		EstruturaBD.acessosAleatoriosLeitor();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		theLock.readLock().unlock();
		controlador.pararLeitura();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
