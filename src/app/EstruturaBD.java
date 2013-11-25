package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EstruturaBD {
	ArrayList<String> bd;
	private Scanner sc;
	
	public EstruturaBD() throws FileNotFoundException {
		bd = new ArrayList<>();
		
		sc = new Scanner(new File("src/banco/bd.txt"));
		while (sc.hasNext()) {
			bd.add(sc.nextLine());
		}
	}
}
