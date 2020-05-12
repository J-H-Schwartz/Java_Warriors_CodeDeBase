package warriors.engine;

import java.util.ArrayList;
import java.util.List;

import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;
import warriors.engine.board.Board;
import warriors.engine.board.BoardCaseEnnemy;
import warriors.engine.board.BoardCaseUpgrade;
import warriors.engine.ennemies.Ennemi;
import warriors.engine.equipements.Equipements;
import warriors.engine.equipements.RightHandEquipement;
import warriors.engine.heroes.Warrior;
import warriors.engine.heroes.Wizard;
import warriors.engine.heroes.Character;

public class Warriors implements WarriorsAPI {

	private ArrayList<Hero> warriors;
	private ArrayList<Map> maps;
	private ArrayList<Game> games;

	public Warriors() {
		warriors = new ArrayList<Hero>();
		maps = new ArrayList<Map>();
		games = new ArrayList<Game>();
		Hero warrior = new Warrior("WAR", 5, 5);
		Hero wizard = new Wizard("WIZ", 3, 8);
		warriors.add(warrior);
		warriors.add(wizard);
		Board map = new Board("default");
		maps.add(map);

	}

	public int gameSearch(String gameId) {
		int result = -1;
		for (int index = 0; index < games.size(); index++) {
			if (games.get(index).getGameId().equals(gameId)) {
				result = index;
			}
		}
		return result;
	}

	@Override
	public List<? extends Hero> getHeroes() {
		// TODO Auto-generated method stub
		return warriors;
	}

	@Override
	public List<? extends Map> getMaps() {
		// TODO Auto-generated method stub
		return maps;
	}

	@Override
	public GameState createGame(String playerName, Hero hero, Map map) {
		if (!(hero instanceof Character)) {
			throw new IllegalArgumentException("Le type de héros n'est pas supporté");
		}
		Game newGame = new Game(playerName, hero, map, String.format("Game %d",games.size()));
		games.add(newGame);
		newGame.setGameStatus(GameStatus.IN_PROGRESS);
		return newGame;
	}

	@Override
	public GameState nextTurn(String gameID) {
		int dice = GetRandomInt.getRandomInt(6) + 1;
		int gameIndex = gameSearch(gameID);
		String tmp = "";
		String[] tmpCaseContainment;
//		System.out.println("Vous lancez le dé et faites un " + dice);
	    tmp = "Vous lancez le dé et faites un " + dice + "\n";
		if (games.get(gameIndex).getCurrentCase() + dice > 63) {
			games.get(gameIndex).setCurrentCase(63);
			tmp = tmp + "Vous êtes sorti du donjon et avez gagné la partie !.\n ";
//			System.out.println("Vous attérissez sur la dernière case.");
//			System.out.println(String.format("Cette case contient %s",
//					games.get(gameIndex).getMap().getMapCases().get(games.get(gameIndex).getCurrentCase()).getContains()));
			games.get(gameIndex).setGameStatus(GameStatus.FINISHED);
			games.get(gameIndex).setCurrentCase(-1);
			games.get(gameIndex).getCharacter().setLife(5);
		} else {
			games.get(gameIndex).setCurrentCase(games.get(gameIndex).getCurrentCase() + dice);
			tmp = tmp + "Vous attérissez sur la case " + games.get(gameIndex).getCurrentCase() + "\n" + String.format("Cette case contient %s",
					((Board)games.get(gameIndex).getMap()).getMapCases().get(games.get(gameIndex).getCurrentCase()).getContains());
			tmpCaseContainment = ((Board)games.get(gameIndex).getMap()).getMapCases().get(games.get(gameIndex).getCurrentCase()).getContains().split("-");
			if (tmpCaseContainment[0].equals("Ennemi")){
				Ennemi ennemi = ((BoardCaseEnnemy)((Board)games.get(gameIndex).getMap()).getMapCases().get(games.get(gameIndex).getCurrentCase())).getEnnemi();
				ennemi.setLife(ennemi.getLife() - games.get(gameIndex).getCharacter().getAttackMove());
				if (ennemi.getLife() <= 0) {
					tmp = tmp + String.format("\nVous avez tué le %s ennemi.", ennemi.getName());
				} else {
					games.get(gameIndex).getCharacter().setLife(games.get(gameIndex).getCharacter().getLife() - ennemi.getAttackPower());
					tmp = tmp + String.format("\nLe %s ennemi vous inflige %d dégats.", ennemi.getName(), ennemi.getAttackPower());
					if (games.get(gameIndex).getCharacter().getLife() <= 0) {
						tmp = tmp + String.format("\nVous n'avez plus de vie. Partie terminée.");
						games.get(gameIndex).setGameStatus(GameStatus.GAME_OVER);
						games.get(gameIndex).setCurrentCase(-1);
						games.get(gameIndex).getCharacter().setLife(5);
						return games.get(gameIndex);
					}
					tmp = tmp + String.format("\nLe %s ennemi s'enfuit. Il vous reste %d points de vie.", ennemi.getName(), games.get(gameIndex).getCharacter().getLife());
				}
			} else if (tmpCaseContainment[0].equals("Bonus")) {
				Equipements loot = ((BoardCaseUpgrade)((Board)games.get(gameIndex).getMap()).getMapCases().get(games.get(gameIndex).getCurrentCase())).getLoot();
				if (tmpCaseContainment[1].equals("Arme") && games.get(gameIndex).getCharacter().getClassName().equals("Warrior")) {
					if (loot.getEffect() > games.get(gameIndex).getCharacter().getRightHand().getEffect()){
						games.get(gameIndex).getCharacter().setRightHand((RightHandEquipement)loot);
						tmp = tmp + String.format("\nVous avez trouvé une nouvelle arme ! %s, bonus d'attaque: %d\nVotre puissance d'attaque s'élève à %d", loot.getName(), loot.getEffect(), games.get(gameIndex).getCharacter().getAttackMove());
					} else {
						tmp = tmp + String.format("\nVous avez trouvé une nouvelle arme, mais la votre est meilleure. %s, bonus d'attaque: %d", loot.getName(), loot.getEffect());
					}
				} else if (tmpCaseContainment[1].equals("Sort") && games.get(gameIndex).getCharacter().getClassName().equals("Wizard")) {
					if (loot.getEffect() > games.get(gameIndex).getCharacter().getRightHand().getEffect()){
						games.get(gameIndex).getCharacter().setRightHand((RightHandEquipement)loot);
						tmp = tmp + String.format("\nVous avez trouvé un nouveau sort ! %s, bonus d'attaque: %d", loot.getName(), loot.getEffect());
					} else {
						tmp = tmp + String.format("\nVous avez trouvé un nouveau sort, mais le votre est meilleur. %s, bonus d'attaque: %d", loot.getName(), loot.getEffect());
					}
				} else if (tmpCaseContainment[1].equals("Potion")) {
					games.get(gameIndex).getCharacter().setLife(games.get(gameIndex).getCharacter().getLife() + loot.getEffect());
					tmp = tmp + String.format("\nVous avez trouvé une potion de soin. %s, soin: %d\nVotre vie passe à %d.", loot.getName(), loot.getEffect(), games.get(gameIndex).getCharacter().getLife());
				}
			}
//			System.out.println("Vous attérissez sur la case " + games.get(gameIndex).getCurrentCase());
//			System.out.println(String.format("Cette case contient %s",
//					games.get(gameIndex).getMap().getMapCases().get(games.get(gameIndex).getCurrentCase()).getContains()));
		}
		games.get(gameIndex).setLastLog(tmp);
		return games.get(gameIndex);
	}
}
