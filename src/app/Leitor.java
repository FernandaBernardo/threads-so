package app;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Leitor implements Runnable{
	private ReentrantReadWriteLock theLock;

	public Leitor(ReentrantReadWriteLock theLock) {
		this.theLock = theLock;
	}
	
	@Override
	public void run() {
		try {
			theLock.readLock().lock();
			System.out.println("The Reader has read the key with a value");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//			}
		} finally {
			theLock.readLock().unlock();
		}

	}
}
