package warriors.engine.heroes;

import warriors.engine.equipements.Equipements;
import warriors.engine.equipements.LeftHandEquipement;
import warriors.engine.equipements.Potion;
import warriors.engine.equipements.RightHandEquipement;
import warriors.engine.equipements.Spell;

/**
 * Wizard object Data class.
 * 
 */
public class Wizard extends HeroCharacter implements WizardClassInterface {

	/** Wizard max life constant */
	public static final int WIZARD_MAX_LIFE = 6;

	/** Wizard min life constant */
	public static final int WIZARD_MIN_LIFE = 3;

	/** Wizard max Attack power constant */
	public static final int WIZARD_MAX_ATTACK_POWER = 15;

	/** Wizard min Attack power constant */
	public static final int WIZARD_MIN_ATTACK_POWER = 8;

	public Wizard() {
		this("Undefined", WIZARD_MIN_LIFE, WIZARD_MIN_ATTACK_POWER);
	}

	public Wizard(String nameArg) {
		this(nameArg, WIZARD_MIN_LIFE, WIZARD_MIN_ATTACK_POWER);
	}

	public Wizard(String nameArg, int lifeArg, int attackPowerArg) {
		if (nameArg.isEmpty() || WIZARD_MIN_LIFE > lifeArg || WIZARD_MAX_LIFE < lifeArg
				|| WIZARD_MIN_ATTACK_POWER > attackPowerArg || WIZARD_MAX_ATTACK_POWER < attackPowerArg) {
			throw new IllegalArgumentException("Invalid Parameters.");
		}
		this.className = "Wizard";
		this.name = nameArg;
		this.life = lifeArg;
		this.attackLevel = attackPowerArg;
		this.rightHand = new Spell();
		this.leftHand = new Potion();
	}

	@Override
	public String manageLoot(Equipements loot, String tmp) {
		if (!(loot instanceof Potion) && loot instanceof Spell) {
			if (loot.getEffect() > this.getRightHand().getEffect()) {
				this.setRightHand((RightHandEquipement) loot);
				tmp = tmp + String.format(
						"\nVous avez trouvé un nouveau sort ! %s, bonus d'attaque: %d\nVotre puissance d'attaque s'élève à %d",
						loot.getName(), loot.getEffect(), this.getAttackMove());
			} else {
				tmp = tmp + String.format(
						"\nVous avez trouvé un nouveau sort, mais le votre est meilleur. %s, bonus d'attaque: %d",
						loot.getName(), loot.getEffect());
			}
		} else {
			this.setLife(this.getLife() + loot.getEffect());
			tmp = tmp + String.format("\nVous avez trouvé une potion de soin. %s, soin: %d\nVotre vie passe à %d.",
					loot.getName(), loot.getEffect(), this.getLife());
		}
		return tmp;
	}

	@Override
	public String toString() {
		return "Personnage " + this.name + " " + this.raceName + " " + this.className + "\nLife : " + this.life
				+ " Attack Power : " + this.attackLevel + " Weapon : " + this.rightHand.getName() + " Shield : "
				+ this.leftHand.getName();
	}

	/**
	 * @return the spell
	 */
	public RightHandEquipement getSpell() {
		return rightHand;
	}

	/**
	 * @param spell the spell to set
	 */
	public void setSpell(Spell spell) {
		this.rightHand = spell;
	}

	/**
	 * @return the potion
	 */
	public LeftHandEquipement getPotion() {
		return leftHand;
	}

	/**
	 * @param potion the potion to set
	 */
	public void setPotion(Potion potion) {
		this.leftHand = potion;
	}
}
