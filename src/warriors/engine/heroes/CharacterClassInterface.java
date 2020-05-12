package warriors.engine.heroes;

public interface CharacterClassInterface {

	void setName(String newName);

	void setLife(int newLife);

	void setAttackLevel(int newAttackPower);

	void setImageUrl(String newImageUrl);


	String getClassName();

	void setClassName(String className);

	String getRaceName();

	void setRaceName(String raceName);

}