package warriors.engine;

import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.engine.heroes.Character;

public class Game implements GameState{
	
	private String playerName;
	private String gameId;
	private GameStatus gameStatus;
	private Hero hero;
	private Map map;
	private String lastLog;
	private int currentCase;

	public Game(String playerName, Hero hero, Map map, String gameId) {
		this.gameId = gameId;
		this.playerName = playerName;
		this.hero = hero;
		this.map = map;
		this.currentCase = -1;
		this.gameStatus = GameStatus.IN_PROGRESS;
		this.lastLog = "Lancement d'une nouvelle partie.";
	}
	
	/**
	 * @param currentCase the currentCase to set
	 */
	public void setCurrentCase(int currentCase) {
		this.currentCase = currentCase;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String getGameId() {
		return gameId;
	}

	@Override
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	@Override
	public Hero getHero() {
		return hero;
	}

	@Override
	public Map getMap() {
		return map;
	}

	@Override
	public String getLastLog() {
		return lastLog;
	}

	@Override
	public int getCurrentCase() {
		return currentCase;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public void setLastLog(String lastLog) {
		// TODO Auto-generated method stub
		this.lastLog = lastLog;
	}
	
	public Character getCharacter() {
		return (Character)hero;
	}
}
