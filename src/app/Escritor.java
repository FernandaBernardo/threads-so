package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Escritor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private int i;
	private LeitorEscritor controlador;
	private int implementacao;
	
	public Escritor(int i, LeitorEscritor controlador, ReentrantReadWriteLock theLock, int implementacao) throws FileNotFoundException {
		this.controlador = controlador;
		this.implementacao = implementacao;
		this.setI(i);
		this.theLock = theLock;
	}
	
	@Override
	public void run() {
		if(implementacao == 1) leitorEescritor();
		else if(implementacao == 2) naoLeitorEescritor();
	}

	private void naoLeitorEescritor() {
		theLock.writeLock().lock();
		bdEsleep();
		theLock.writeLock().unlock();
	}

	private void leitorEescritor() {
		controlador.comecarEscrita();
		bdEsleep();
		controlador.pararEscrita();
	}

	private void bdEsleep() {
		EstruturaBD.acessosAleatoriosEscritor();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
