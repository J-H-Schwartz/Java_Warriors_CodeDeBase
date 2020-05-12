package warriors.engine.ennemies;

public abstract class Ennemi {
	String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the attackPower
	 */
	public int getAttackPower() {
		return attackPower;
	}

	/**
	 * @param attackPower the attackPower to set
	 */
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	int life;
	int attackPower;
	
	public Ennemi(String name, int life, int attackPower) {
		this.name = name;
		this.life = life;
		this.attackPower = attackPower;
	}
}
