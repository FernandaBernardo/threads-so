package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Escritor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private int i;
	
	public Escritor(ReentrantReadWriteLock theLock, int i) throws FileNotFoundException {
		this.theLock = theLock;
		this.setI(i);
	}
	
	@Override
	public void run() {
		theLock.writeLock().lock();
		EstruturaBD.acessosAleatoriosEscritor();		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		theLock.writeLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
