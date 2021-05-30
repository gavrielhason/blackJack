import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * 
 */

/**
 * @author gavrielhas
 *
 */
public class Player {

	/**
	 * @param args
	 */

	private int bankVal = 5000;
	private int riskVal;
	private ArrayList<CardGenerator> cardList = new ArrayList<CardGenerator>();
	private int cardsVal;
	private final ImageIcon CARD_BLANK = new ImageIcon(getClass().getResource("/blank.png"));
	private int cardCount;
	private int record;


	public static void main(String[] args) {

	}
	public Player () {
	}

	public int getBankVal() {
		return bankVal;
	}
	public void setBankVal(int bankVal) {
		this.bankVal = bankVal;
	}
	public int getRiskVal() {
		return riskVal;
	}
	public void setRiskVal(int riskVal) {
		this.riskVal = riskVal;
	}
	public void insertCard (CardGenerator c) {
		cardList.add(c);
	}
	/**
	 * @return the cardList
	 */
	public CardGenerator getCard(int i) {
		 return cardList.get(i);
	}
	public int getCardsVal() {
		return cardsVal;
	}
	public void setCardsVal(int cardsVal) {
		this.cardsVal = cardsVal;
	}
	/*
	 * adds up the sums of all cards.
	 * Increment the count of cards holding.
	 */
	public void incrementCardVal(int cardVal) {
		setCardsVal(getCardsVal() + cardVal);
		incrementCardCount();
	}
	
	public void resetPlayer() {
		setBankVal(5000);
		setRiskVal(0);
		cardList.clear();
		cardsVal = 0;
	}
	
	public void setBlankCards() {
		for (CardGenerator c : cardList) {
			c.setImage(CARD_BLANK);
		}
	}
	public int getCardCount() {
		return cardCount;
	}
	public void incrementCardCount() {
		cardCount++;
	}
	public void resetSession() {
		setBlankCards();
		cardCount = 0;
		cardsVal = 0;
	}
	
	public void isRecord(int score) {
		if (score > record) {
			record = score;
		}
	}
	

}
