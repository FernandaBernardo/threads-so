package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Escritor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private NumeroAleatorio na;
	private EstruturaBD bd;
	private int i;
	
	public Escritor(ReentrantReadWriteLock theLock, EstruturaBD bd, int i) throws FileNotFoundException {
		this.theLock = theLock;
		this.bd = bd;
		this.na = new NumeroAleatorio();
		this.setI(i);
	}
	
	@Override
	public void run() {
		theLock.writeLock().lock();
		int size = bd.bd.size();
		for (int i = 0; i < 100; i++) {
			bd.bd.set(na.gera(size), "MODIFICADO");
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getI() + " The Writer has written the key with the value");
		theLock.writeLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
