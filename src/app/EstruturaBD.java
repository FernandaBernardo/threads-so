package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EstruturaBD {
	static ArrayList<String> bd;
	private static Scanner sc;
	private static NumeroAleatorio numAleatorio;
	
	public static void inicializa() throws FileNotFoundException {
		bd = new ArrayList<>();
		numAleatorio =  new NumeroAleatorio();
		
		sc = new Scanner(new File("src/banco/bd.txt"));
		while (sc.hasNext()) {
			bd.add(sc.nextLine());
		}
	}

	public static void acessosAleatoriosLeitor() {
		int size = bd.size();
		for (int i = 0; i < 100; i++) {
			String string = bd.get(numAleatorio.gera(size));
		}
	}

	public static void acessosAleatoriosEscritor() {
		int size = bd.size();
		for (int i = 0; i < 100; i++) {
			bd.set(numAleatorio.gera(size), "MODIFICADO");
		}

	}
}
