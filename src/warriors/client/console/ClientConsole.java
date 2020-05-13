package warriors.client.console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;
import warriors.engine.Game;
import warriors.engine.Warriors;

public class ClientConsole {

	private static String MENU_COMMENCER_PARTIE = "1";
	private static String MENU_QUITTER = "2";
	private static String MENU_DEBUG = "3";


	public static void main(String[] args) {

		WarriorsAPI warriors = new Warriors();
		Scanner sc = new Scanner(System.in);
		String menuChoice = "";
		do {
			menuChoice = displayMenu(sc);
			if (menuChoice.equals(MENU_COMMENCER_PARTIE)) {
				startGame(warriors, sc, MENU_COMMENCER_PARTIE);
			} else if (menuChoice.equals(MENU_DEBUG)){
				startGame(warriors, sc, MENU_DEBUG);
			}
		} while (!menuChoice.equals(MENU_QUITTER));
		sc.close();
		System.out.println("� bient�t");
	}

	private static void startGame(WarriorsAPI warriors, Scanner sc, String menuChoice) {
		BufferedReader br;
		String line = "";
		String splitBy = ",";
		String[] tmp;
		int[] debugFileDices = null;
		System.out.println();
		System.out.println("Entrez votre nom:");
		String playerName = sc.nextLine();

		System.out.println("Choisissez votre h�ro:");
		for (int i = 0; i < warriors.getHeroes().size(); i++) {
			Hero heroe = warriors.getHeroes().get(i);
			System.out.println(i + 1 + " - " + heroe.getName());
			System.out.println("    Force d'attaque : " + heroe.getAttackLevel());
			System.out.println("    Niveau de vie : " + heroe.getLife());
		}
		Hero chosenHeroe = warriors.getHeroes().get(Integer.parseInt(sc.nextLine()) - 1);

		System.out.println("Choisissez votre map:");
		for (int i = 0; i < warriors.getMaps().size(); i++) {
			Map map = warriors.getMaps().get(i);
			System.out.println(i + 1 + " - " + map.getName());
		}
		Map choosenMap = warriors.getMaps().get(Integer.parseInt(sc.nextLine()) - 1);
		
		GameState gameState = warriors.createGame(playerName, chosenHeroe, choosenMap);
		if(menuChoice.equals(MENU_DEBUG)) {
			try {			
				br = new BufferedReader(new FileReader("Java_Warriors_DebugFile.csv"));
				line = br.readLine();
				
				tmp = line.split(splitBy);
				debugFileDices = new int[tmp.length];
				for (int i = 0; i < tmp.length; i++) {
					debugFileDices[i] = Integer.parseInt(tmp[i]);
				}
				((Game)gameState).setDebugDicesFile(debugFileDices);
				((Game)gameState).setDebugStatus(1);
			} catch (IOException e) {
				System.out.println("Erreur de chargement du fichier, retour au menu précédent.");
				return;
			}
		}
		String gameId = gameState.getGameId();
		while (gameState.getGameStatus() == GameStatus.IN_PROGRESS) {
			System.out.println(gameState.getLastLog());
			System.out.println("\nAppuyer sur une touche pour lancer le d�");
			if (sc.hasNext()) {
				sc.nextLine();
				gameState = warriors.nextTurn(gameId);
			}
		}

		System.out.println(gameState.getLastLog());
	}

	private static String displayMenu(Scanner sc) {
		System.out.println();
		System.out.println("================== Java Warriors ==================");
		System.out.println("1 - Commencer une partie");
		System.out.println("2 - Quitter");
		System.out.println("3 - Debug");
		if (sc.hasNext()) {
			String choice = sc.nextLine();
			return choice;
		}

		return "";
	}
}
