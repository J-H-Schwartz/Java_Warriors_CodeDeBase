package warriors.engine;

import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.engine.board.Board;
import warriors.engine.heroes.HeroCharacter;

public class Game implements GameState {

	private static final int START_CASE = -1;

	private String playerName;
	private String gameId;
	private GameStatus gameStatus;
	private Hero hero;
	private Map map;
	private String lastLog;
	private int currentCase;
	private int heroDefaultLife;

	public Game(String playerName, Hero hero, Map map, String gameId) {
		this.gameId = gameId;
		this.playerName = playerName;
		this.hero = hero;
		this.heroDefaultLife = hero.getLife();
		this.map = map;
		this.currentCase = START_CASE;
		this.gameStatus = GameStatus.IN_PROGRESS;
		this.lastLog = "Lancement d'une nouvelle partie.";
	}

	public String manageGameWin(String tmp) {
		this.setGameStatus(GameStatus.FINISHED);
		this.setCurrentCase(START_CASE);
		this.getCharacter().setLife(this.heroDefaultLife);
		tmp = tmp + "Vous êtes sorti du donjon et avez gagné la partie !.\n ";
		return tmp;
	}

	public String manageGameLoss(String tmp) {
		this.setGameStatus(GameStatus.GAME_OVER);
		this.setCurrentCase(START_CASE);
		this.getCharacter().setLife(this.heroDefaultLife);
		tmp = tmp + String.format("\nVous n'avez plus de vie. Partie terminée.");
		return tmp;
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
		this.lastLog = lastLog;
	}

	public HeroCharacter getCharacter() {
		return (HeroCharacter) hero;
	}

	public Board getBoard() {
		return (Board) map;
	}
}
