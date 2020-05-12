package warriors.engine.heroes;

import warriors.contracts.Hero;
import warriors.engine.ennemies.Ennemi;
import warriors.engine.equipements.Equipements;
import warriors.engine.equipements.LeftHandEquipement;
import warriors.engine.equipements.RightHandEquipement;

/**
 * Character abstract SuperClass
 *
 */
public abstract class HeroCharacter implements Hero, CharacterClassInterface {

	/** Character class name */
	protected String className;

	/** Character object name */
	protected String name;

	/** Character object life */
	protected int life;

	/** Character object Attack Power */
	protected int attackLevel;

	/** Character object Image url */
	protected String imageUrl;

	/** Character object Race Name */
	protected String raceName;

	/** Warrior object Weapon object */
	protected RightHandEquipement rightHand;

	/** Warrior object Shield object */
	protected LeftHandEquipement leftHand;

	protected int boardPosition;

	public HeroCharacter() {
		boardPosition = -1;
	}

	@Override
	public void setName(String newName) {
		this.name = newName;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setLife(int newLife) {
		if (newLife > 10) {
			this.life = 10;
		} else if (newLife < 0) {
			this.life = 0;
		} else {
			this.life = newLife;
		}
	}
	
	public int getAttackMove() {
		return this.attackLevel + this.rightHand.getEffect();
	}
	
	@Override
	public int getLife() {
		return this.life;
	}

	@Override
	public void setAttackLevel(int newAttackPower) {
		if (newAttackPower > 10) {
			this.attackLevel = 10;
		} else {
			this.attackLevel = newAttackPower;
		}
	}

	@Override
	public int getAttackLevel() {
		return this.attackLevel;
	}

	@Override
	public void setImageUrl(String newImageUrl) {
		this.imageUrl = newImageUrl;
	}

	@Override
	public String getImage() {
		return this.imageUrl;
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getRaceName() {
		return raceName;
	}

	@Override
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	/**
	 * @return the rightHand
	 */
	public RightHandEquipement getRightHand() {
		return rightHand;
	}

	/**
	 * @param rightHand the rightHand to set
	 */
	public void setRightHand(RightHandEquipement rightHand) {
		this.rightHand = rightHand;
	}

	/**
	 * @return the leftHand
	 */
	public LeftHandEquipement getLeftHand() {
		return leftHand;
	}

	/**
	 * @param leftHand the leftHand to set
	 */
	public void setLeftHand(LeftHandEquipement leftHand) {
		this.leftHand = leftHand;
	}

	/**
	 * @return the boardPosition
	 */
	public int getBoardPosition() {
		return boardPosition;
	}

	/**
	 * @param boardPosition the boardPosition to set
	 */
	public void setBoardPosition(int boardPosition) {
		this.boardPosition = boardPosition;
	}
	
	public String attack(String tmp, Ennemi ennemi) {
		ennemi.setLife(ennemi.getLife() - this.getAttackMove());
		tmp = tmp + String.format(
				"\nVous attaquez le %s ennemi et lui infligez %d dégats.\nSes points de vie sont maintenant à %d",
				ennemi.getName(), this.getAttackMove(), ennemi.getLife());
		return tmp;
	}
	
	public abstract String manageLoot(Equipements loot, String tmp);
}
