package warriors.engine.board;


public class BoardCase {
	
	protected int caseID;
	protected int caseStatus;
	protected String contains;
	
	public BoardCase(int id) {
		this.caseID = id;
		this.caseStatus = 0;
		this.contains = "Empty";
	}

	/**
	 * @return the caseID
	 */
	public int getCaseID() {
		return caseID;
	}

	/**
	 * @param caseID the caseID to set
	 */
	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	/**
	 * @return the caseStatus
	 */
	public int getCaseStatus() {
		return caseStatus;
	}

	/**
	 * @param caseStatus the caseStatus to set
	 */
	public void setCaseStatus(int caseStatus) {
		this.caseStatus = caseStatus;
	}

	/**
	 * @return the contains
	 */
	public String getContains() {
		return contains;
	}

	/**
	 * @param contains the contains to set
	 */
	public void setContains(String contains) {
		this.contains = contains;
	}
}
