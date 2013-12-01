package app;

class LeitorEscritor {
	int leitoresAtivos = 0;
	boolean escritorPresente = false;

	boolean condicaoEscrita() {
		return leitoresAtivos == 0 && !escritorPresente;
	}

	boolean condicaoLeitura() {
		return !escritorPresente;
	}

	synchronized void comecarLeitura() {
		while (!condicaoLeitura())
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		leitoresAtivos++;
	}

	synchronized void pararLeitura() {
		leitoresAtivos--;
		notifyAll();
	}

	synchronized void comecarEscrita() {
		while (!condicaoEscrita())
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		escritorPresente = true;
	}

	synchronized void pararEscrita() {
		escritorPresente = false;
		notifyAll();
	}
}