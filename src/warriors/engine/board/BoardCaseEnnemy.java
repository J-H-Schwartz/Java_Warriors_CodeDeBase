package warriors.engine.board;

import warriors.engine.ennemies.Ennemi;
import warriors.engine.ennemies.EnnemiDragon;
import warriors.engine.ennemies.EnnemiGoblin;
import warriors.engine.ennemies.EnnemiSorcerer;

public class BoardCaseEnnemy extends BoardCase {
	private Ennemi ennemi;
	
	public BoardCaseEnnemy(int id, int type) {
		super(id);
		this.caseStatus = 1;
		if (type == 0) {
			this.contains = "Ennemi-Goblin-1";
			this.ennemi = new EnnemiGoblin();
		} else if (type == 1) {
			this.contains = "Ennemi-Sorcerer-2";
			this.ennemi = new EnnemiSorcerer();
		} else if (type == 2) {
			this.contains = "Ennemi-Dragon-3";
			this.ennemi = new EnnemiDragon();
		}
	}

	/**
	 * @return the ennemi
	 */
	public Ennemi getEnnemi() {
		return ennemi;
	}

	/**
	 * @param ennemi the ennemi to set
	 */
	public void setEnnemi(Ennemi ennemi) {
		this.ennemi = ennemi;
	}
}
