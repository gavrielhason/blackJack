
/**
 * 
 */

/**
 * @author gavrielhas
 *
 */
public class HallOfFameEntry implements Comparable<HallOfFameEntry> {

	/**
	 * @param args
	 */
	private String name;
	private int score;
	private String timeStamp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public HallOfFameEntry(String name, int score, String timestamp) {
		this.name = name;
		this.score = score;
		this.timeStamp = timestamp;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int compareTo(HallOfFameEntry o) {
		// TODO Auto-generated method stub
		if (this.score > o.getScore())
			return -1;
		if (this.score < o.getScore())
			return 1;

		return 0;
	}
}
