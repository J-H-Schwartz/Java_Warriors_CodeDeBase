package warriors.engine.board;

import java.util.ArrayList;
import java.util.Collections;

import warriors.contracts.Map;

public class Board implements Map{

	private String name;
	private int numberOfCase;
	private ArrayList<BoardCase> map;
	
	

	public ArrayList<BoardCase> getMapCases() {
		return map;
	}

	public Board(String name){
		this.map = new ArrayList<BoardCase>();
		this.name = name;
		generateMap();
	}

	private void generateMap() {
		numberOfCase = 64;
		int index = 0;
		for (int i = 0; i < 4; i++) {
			map.add(new BoardCaseEnnemy(index, 2));
			index++;
		}
		for (int i = 0; i < 10; i++) {
			map.add(new BoardCaseEnnemy(index, 0));
			index++;
		}
		for (int i = 0; i < 10; i++) {
			map.add(new BoardCaseEnnemy(index, 1));
			index++;
		}
		for (int i = 0; i < 5; i++) {
			map.add(new BoardCaseUpgrade(index, 0));
			index++;
		}
		for (int i = 0; i < 3; i++) {
			map.add(new BoardCaseUpgrade(index, 1));
			index++;
		}
		for (int i = 0; i < 2; i++) {
			map.add(new BoardCaseUpgrade(index, 2));
			index++;
		}
		for (int i = 0; i < 5; i++) {
			map.add(new BoardCaseUpgrade(index, 3));
			index++;
		}
		for (int i = 0; i < 2; i++) {
			map.add(new BoardCaseUpgrade(index, 4));
			index++;
		}
		for (int i = 0; i < 5; i++) {
			map.add(new BoardCaseUpgrade(index, 5));
			index++;
		}
		for (int i = 0; i < 3; i++) {
			map.add(new BoardCaseUpgrade(index, 6));
			index++;
		}
		for (int i = 0; i < 1; i++) {
			map.add(new BoardCaseUpgrade(index, 7));
			index++;
		}
		for (int i = 0; i < 14; i++) {
			map.add(new BoardCase(index));
			index++;
		}
		Collections.shuffle(map);
		for (int i = 0; i < map.size(); i++) {
			map.get(i).setCaseID(i);
		}
		System.out.println(String.format("Plateau de %d cases généré !", index));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getNumberOfCase() {
		// TODO Auto-generated method stub
		return numberOfCase;
	}
	
	public BoardCase getEnnemiBoardCase() {
		
		return null;
	}
}
