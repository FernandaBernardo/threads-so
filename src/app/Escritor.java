package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Escritor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private int i;
	private LeitorEscritor controlador;
	private int implementacao;
	
	public Escritor(int i, LeitorEscritor controlador, int implementacao) throws FileNotFoundException {
		this.controlador = controlador;
		this.implementacao = implementacao;
		this.setI(i);
		this.theLock = null;
	}
	
	public Escritor(int i, ReentrantReadWriteLock theLock, int implementacao) {
		this.theLock = theLock;
		this.implementacao = implementacao;
		this.setI(i);
		this.controlador = null;
	}
	
	@Override
	public void run() {
		if(implementacao == 1) leitorEescritor(implementacao);
		else if(implementacao == 2) leitorEescritor(implementacao);
	}

	private void leitorEescritor(int implementacao) {
		if(implementacao == 1) controlador.comecarEscrita();
		else if (implementacao == 2) theLock.writeLock().lock();
		
		EstruturaBD.acessosAleatoriosEscritor();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (implementacao == 1) controlador.pararEscrita();
		else if(implementacao == 2) theLock.writeLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
