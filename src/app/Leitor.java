package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Leitor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private int i;

	public Leitor(ReentrantReadWriteLock theLock, int i) throws FileNotFoundException {
		this.theLock = theLock;
		this.setI(i);
	}
	
	@Override
	public void run() {
		theLock.readLock().lock();
		EstruturaBD.acessosAleatoriosLeitor();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		theLock.readLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
