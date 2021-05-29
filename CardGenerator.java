import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 * 
 */

/**
 * @author gavrielhas
 *
 */
public class CardGenerator {

	/**
	 * @param args
	 */
	private int value;

	private JToggleButton myCard;

	

	
	private final ImageIcon CARD_1 = 
			new ImageIcon(getClass().getResource("/1.png"));
	private final ImageIcon CARD_2 = 
			new ImageIcon(getClass().getResource("/2.png"));
	private final ImageIcon CARD_3 = 
			new ImageIcon(getClass().getResource("/3.png"));
	private final ImageIcon CARD_4 = 
			new ImageIcon(getClass().getResource("/4.png"));
	private final ImageIcon CARD_5 = 
			new ImageIcon(getClass().getResource("/5.png"));
	private final ImageIcon CARD_6 = 
			new ImageIcon(getClass().getResource("/6.png"));
	private final ImageIcon CARD_7 = 
			new ImageIcon(getClass().getResource("/7.png"));
	private final ImageIcon CARD_8 = 
			new ImageIcon(getClass().getResource("/8.png"));
	private final ImageIcon CARD_9 = 
			new ImageIcon(getClass().getResource("/9.png"));
	private final ImageIcon CARD_10 = 
			new ImageIcon(getClass().getResource("/10.png"));
	private final ImageIcon CARD_11 = 
			new ImageIcon(getClass().getResource("/11.png"));
	private final ImageIcon CARD_12 = 
			new ImageIcon(getClass().getResource("/12.png"));
	private final ImageIcon CARD_13 = 
			new ImageIcon(getClass().getResource("/13.png"));
	private final ImageIcon CARD_IMAGES[] = {
			CARD_1,
			CARD_2,
			CARD_3,
			CARD_4,
			CARD_5,
			CARD_6,
			CARD_7,
			CARD_8,
			CARD_9,
			CARD_10,
			CARD_11,
			CARD_12,
			CARD_13,
	};
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public CardGenerator () {
		shuffle();
	}
	public CardGenerator(JToggleButton card) {
		myCard = card;
		shuffle();
	}
	
	/**
	 * 
	 * The shuffle method will return and store a random number
	 * between 1 to 13.
	 * @return a random number between 1 and 13
	 * 
	 */
	public int shuffle() {
		value = (int) (Math.random() * 13 + 1);
		return value;
	}
	
	/**
	 *  Return the last value shuffled of this cards.
	 * @return the last value shuffled.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return the myCard
	 */
	public JToggleButton getMyCard() {
		return myCard;
	}
	/**
	 * @param myCard the myCard to set
	 */
	public void setMyCard(JToggleButton myCard) {
		this.myCard = myCard;
	}
	
	
	public ImageIcon getImage() {
		return CARD_IMAGES[value - 1];
	}

	public void updateImage() {
		myCard.setIcon(getImage());
	}
	public void setImage(ImageIcon image) {
		myCard.setIcon(image);
	}
	public void setValue(int i) {
		// TODO Auto-generated method stub
		value = i;
	}
	



}
