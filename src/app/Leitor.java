package app;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Leitor implements Runnable{
	private ReentrantReadWriteLock theLock;
	private int i;
	private LeitorEscritor controlador;
	private int implementacao;

	public Leitor(int i, LeitorEscritor controlador, int implementacao) throws FileNotFoundException {
		this.controlador = controlador;
		this.implementacao = implementacao;
		this.setI(i);
		theLock = null;
	}
	
	public Leitor(int i, ReentrantReadWriteLock theLock, int implementacao) {
		this.theLock = theLock;
		this.controlador = null;
		this.setI(i);
		this.implementacao = implementacao;
	}
	
	@Override
	public void run() {
		if(implementacao == 1) leitorEescritor(implementacao);
		else if(implementacao == 2) leitorEescritor(implementacao);
	}

	private void leitorEescritor(int implementacao) {
		if(implementacao == 1) controlador.comecarLeitura();
		else if (implementacao == 2) theLock.readLock().lock();
		
		EstruturaBD.acessosAleatoriosLeitor();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (implementacao == 1) controlador.pararLeitura();
		else if(implementacao == 2) theLock.readLock().unlock();
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
