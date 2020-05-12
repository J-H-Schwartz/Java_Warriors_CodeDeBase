package warriors.engine.heroes;

import warriors.engine.equipements.LeftHandEquipement;
import warriors.engine.equipements.Potion;
import warriors.engine.equipements.RightHandEquipement;
import warriors.engine.equipements.Weapon;

/**
 * Warrior object Data class.
 * 
 */
public class Warrior extends Character implements WarriorClassInterface {

	/** Warrior max life constant */
	public static final int WARRIOR_MAX_LIFE = 10;

	/** Warrior min life constant */
	public static final int WARRIOR_MIN_LIFE = 5;

	/** Warrior max Attack power constant */
	public static final int WARRIOR_MAX_ATTACK_POWER = 10;

	/** Warrior max Attack power constant */
	public static final int WARRIOR_MIN_ATTACK_POWER = 5;

	
	public Warrior() {
		this("Undefined", WARRIOR_MIN_LIFE, WARRIOR_MIN_ATTACK_POWER);
	}

	public Warrior(String nameArg) {
		this(nameArg, WARRIOR_MIN_LIFE, WARRIOR_MIN_ATTACK_POWER);
	}

	public Warrior(String nameArg, int lifeArg, int attackPowerArg) {
		super();
		if (nameArg.isEmpty() || WARRIOR_MIN_LIFE > lifeArg || WARRIOR_MAX_LIFE < lifeArg
				|| WARRIOR_MIN_ATTACK_POWER > attackPowerArg || WARRIOR_MAX_ATTACK_POWER < attackPowerArg) {
			throw new IllegalArgumentException("Invalid Parameters.");
		}
		this.className = "Warrior";
		this.name = nameArg;
		this.life = lifeArg;
		this.attackLevel = attackPowerArg;
		this.rightHand = new Weapon();
		this.leftHand = new Potion();

	}

	@Override
	public String toString() {
		return "Personnage " + this.name + " " + this.raceName + " " + this.className + "\nLife : " + this.life
				+ " Attack Power : " + this.attackLevel + " Weapon : " + this.rightHand.getName() + " Potion : "
				+ this.leftHand.getName();
	}

	/**
	 * @return the weapon
	 */
	public RightHandEquipement getWeapon() {
		return rightHand;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(Weapon weapon) {
		this.rightHand = weapon;
	}

	/**
	 * @return the shield
	 */
	public LeftHandEquipement getShield() {
		return leftHand;
	}

	/**
	 * @param shield the shield to set
	 */
	public void setPotion(Potion potion) {
		this.leftHand = potion;
	}
}