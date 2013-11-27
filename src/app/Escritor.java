package app;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Escritor implements Runnable{
	private ReentrantReadWriteLock theLock;

	public Escritor(ReentrantReadWriteLock theLock) {
		this.theLock = theLock;
	}
	
	@Override
	public void run() {
		try {
			theLock.writeLock().lock();
			System.out.println("The Writer has written the key with the value");
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//			}
		} finally {
			theLock.writeLock().unlock();
		}
	}
}
