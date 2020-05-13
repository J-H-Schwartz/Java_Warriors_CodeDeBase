package warriors.engine;

import java.util.ArrayList;
import java.util.List;

import warriors.contracts.GameState;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;
import warriors.engine.board.Board;
import warriors.engine.board.BoardCase;
import warriors.engine.heroes.Warrior;
import warriors.engine.heroes.Wizard;
import warriors.engine.heroes.HeroCharacter;

public class Warriors implements WarriorsAPI {

	private static final int MAP_END = 63;
	private static final int DICE_6 = 6;

	private ArrayList<Hero> warriors;
	private ArrayList<Map> maps;
	private ArrayList<Game> games;

	private int debugDicesIndex;

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
		debugDicesIndex = 0;

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
		return warriors;
	}

	@Override
	public List<? extends Map> getMaps() {
		return maps;
	}

	@Override
	public GameState createGame(String playerName, Hero hero, Map map) {
		if (!(hero instanceof HeroCharacter)) {
			throw new IllegalArgumentException("Le type de héros n'est pas supporté");
		}
		Game newGame = new Game(playerName, hero, map, String.format("Game %d", games.size()));
		games.add(newGame);
		return newGame;
	}

	@Override
	public GameState nextTurn(String gameID) {
		int gameIndex = gameSearch(gameID);
		Game game = games.get(gameIndex);
		String tmp = "";
		int dice = 0;
		if (game.getDebugStatus() == 1) {
			dice = game.getDebugDicesFile()[debugDicesIndex];
			tmp = "Dé de " + dice + " prédéfini (mode Debug).\n";
		} else {
			dice = GetRandomInt.getRandomInt(DICE_6) + 1;
			tmp = "Vous lancez le dé et faites un " + dice + "\n";
		}
		if (game.getCurrentCase() + dice > MAP_END) {
			tmp = game.manageGameWin(tmp);
			debugDicesIndex = 0;
		} else {
			tmp = getNextCase(dice, game, tmp);
			tmp = manageCaseEvent(game, tmp);
			debugDicesIndex += 1;
		}
		if (game.getCharacter().getLife() <= 0) {
			tmp = game.manageGameLoss(tmp);
			debugDicesIndex = 0;
		}
		game.setLastLog(tmp);
		return game;
	}

	private String getNextCase(int dice, Game game, String tmp) {
		game.setCurrentCase(game.getCurrentCase() + dice);
		tmp = tmp + "Vous attérissez sur la case " + game.getCurrentCase() + "\n" + String.format(
				"Cette case contient %s", game.getBoard().getMapCases().get(game.getCurrentCase()).getContains());
		return tmp;
	}

	private String manageCaseEvent(Game game, String tmp) {
		BoardCase actualCase = game.getBoard().getMapCases().get(game.getCurrentCase());
		tmp = actualCase.manageCaseEvent(game.getCharacter(), tmp);
		return tmp;
	}

}
