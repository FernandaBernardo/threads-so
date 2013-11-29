package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Leitor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private NumeroAleatorio na;
	private EstruturaBD bd;
	private int i;

	public Leitor(ReentrantReadWriteLock theLock, EstruturaBD bd, int i) throws FileNotFoundException {
		this.theLock = theLock;
		this.bd = bd;
		this.na = new NumeroAleatorio();
		this.setI(i);
	}
	
	@Override
	public void run() {
		theLock.readLock().lock();
		int size = bd.bd.size();
		for (int i = 0; i < 100; i++) {
			String string = bd.bd.get(na.gera(size));
		}
		System.out.println(getI() + " The Reader has read the key with a value");
		theLock.readLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
