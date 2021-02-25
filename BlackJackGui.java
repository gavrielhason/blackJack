

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;



/**
 * 
 */

/**
 * @author Gavriel Hason
 *
 */
public class BlackJackGui {

	/**
	 * @param args
	 */
	private final ImageIcon CARD_BLANK = new ImageIcon(getClass().getResource("/blank.png"));
	
	// Gui Stuff
	JFrame frame;
	JPanel background;
	JPanel buttonPanel;
	JPanel cardsPanel;
	JPanel dealerCardsPanel;
	JPanel hitOrStayPanel;
	JPanel bankAndRisk;
	JButton stay;
	JButton hit;
	JButton doubleIt;
	JButton btnOne;
	JButton btnTwo;
	JButton btnThree;
	JButton btnFour;
	JLabel bank;
	JLabel risk;
	JLabel bankText;
	JLabel riskText;
	
	
	private ArrayList<JToggleButton> myCardsButton = new ArrayList<JToggleButton>();
	private ArrayList<Card> cardList = new ArrayList<Card>();
	
	// Dealer stuff
	private ArrayList<JToggleButton> dealerCardsButton = new ArrayList<JToggleButton>();
	private ArrayList<Card> dealerCardList = new ArrayList<Card>();
	int dealerVal = 0;
	int dealerCounter = 0;
			
	private ButtonHandler[] handlers = new ButtonHandler[4]; 
	


	int count = 0;
	int val = 0;
	private int bankVal = 5000;
	private int riskVal = 0;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BlackJackGui().go();
	}

	private void go() {
		// TODO Auto-generated method stub
		frame = new JFrame("BlackJack Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background = new JPanel();
		background.setLayout(new GridLayout(1,3));
		

		createMoneyButtons();
		createCardsPanel();
		createDealerCardsPanel();
		createhitOrStayPanel();
		createBankAndRisk();
		
		setUpHandlers();
		
		background.add(bankAndRisk);
		background.add(hitOrStayPanel);
		background.add(buttonPanel);
		
		frame.getContentPane().add(dealerCardsPanel,BorderLayout.NORTH);
		frame.getContentPane().add(cardsPanel,BorderLayout.SOUTH);
		frame.getContentPane().add(background, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
	}
	/**
	 * Create the Buttons and add listeners to them
	 * @return buttonPanel is the panel of the buttons
	 */
	private JPanel createMoneyButtons() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,2));
		
		// create four buttons for putting money
		btnOne = new JButton("50");
		btnTwo = new JButton("100");
		btnThree = new JButton("200");
		btnFour = new JButton("500");
		
		//Add a listener
		btnOne.addActionListener(new AllButtonListener());
		btnTwo.addActionListener(new AllButtonListener());
		btnThree.addActionListener(new AllButtonListener());
		btnFour.addActionListener(new AllButtonListener());


		
		// Add them to the JPanel - buttonPanel
		buttonPanel.add(btnOne);
		buttonPanel.add(btnTwo);
		buttonPanel.add(btnThree);
		buttonPanel.add(btnFour);
		
		return buttonPanel;
	}
	/**
	 * Creates the cards and set by default the icon blank
	 * 
	 * @return cardsPanel is the panel
	 *  of all the cards that is displayed(7 cards)
	 */
	private JPanel createCardsPanel() {
		cardsPanel = new JPanel();
		
		for (int i =0; i < 7; i++) {
			JToggleButton tb = new JToggleButton();
			Card c = new Card(tb);
			cardList.add(c);
			tb.setIcon(CARD_BLANK);
			myCardsButton.add(tb);
			
			
			cardsPanel.add(tb);
			
		}
		return cardsPanel;
	}
	private JPanel createDealerCardsPanel() {
		dealerCardsPanel = new JPanel();
		
		for (int i =0; i < 7; i++) {
			JToggleButton tb = new JToggleButton();
			Card c = new Card(tb);
			dealerCardList.add(c);
			tb.setIcon(CARD_BLANK);
			dealerCardsButton.add(tb);
			
			dealerCardsPanel.add(tb);
			
		}
		return dealerCardsPanel;
	}
	/**
	 * Creates the two buttons hit and stay and double
	 * add the action listener to them
	 * and add them to the panel hitOrStayPanel
	 * @return hitOrStayPanel is the panel that consist the hit/doubeIt/stay buttons
	 */
	private JPanel createhitOrStayPanel() {
		hitOrStayPanel = new JPanel();
		hitOrStayPanel.setLayout(new BoxLayout(hitOrStayPanel,BoxLayout.X_AXIS));
		
		hit = new JButton("Hit");
		stay = new JButton("Stay");
		doubleIt = new JButton("Double");
		
		hit.addActionListener(new HitButtonListener());
		stay.addActionListener(new StayButtonListener());
		
		hitOrStayPanel.add(hit);
		hitOrStayPanel.add(doubleIt);
		hitOrStayPanel.add(stay);
		
		return hitOrStayPanel;
	}
	
	private void resetGame() {
		disableMoneyButtons(false);		

		
		for (Card c : cardList) {
			c.setImage(CARD_BLANK);;
		}
		for (Card c : dealerCardList) {
			c.setImage(CARD_BLANK);
		}
		dealerCounter = 0;
		dealerVal = 0;
		count = 0;
		val = 0;
	}
	/**
	 * creates the bank and risk 
	 * it display the amount in a label and the risk in a label
	 * add all of them to the panel bankAndRisk
	 * @return bankAndRisk is the panel that consist the labels and the textfields
	 * add and risk
	 */
	private JPanel createBankAndRisk() {
		bankAndRisk = new JPanel();
		bankAndRisk.setLayout(new GridLayout(1,4));
		
		
		bank = new JLabel("Bank: ");
		bankText = new JLabel();
		bankText.setText(String.valueOf((bankVal)));
		risk = new JLabel("Risk: ");
		riskText = new JLabel();
		riskText.setText(String.valueOf(riskVal));
		
		bankAndRisk.add(bank);
		bankAndRisk.add(bankText);
		bankAndRisk.add(risk);
		bankAndRisk.add(riskText);
		
		return bankAndRisk;
	}
	
	/**
	 * a getter for bankVal
	 * @return bankVal is how much left in the bank
	 */
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

	/**
	 * Stay button listener is activated only if the player already hit once
	 * It will activate the other dealer and play until he gets card value
	 * that is greater than 17.
	 * it will then decide on the winner
	 *
	 */
	class StayButtonListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			while (dealerVal <= 17) {

				newDealerCardGenerator();
				
			}
			if (dealerVal > 21) {
				winScenario();
				
			} else if(dealerVal > val) {
				loseScenario();
			} else if (val > dealerVal) {
				winScenario();
			}  else if (dealerVal == val) {
				tieScenario();
			}
		}
		
	}
	
	class AllButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
/*			JButton button = (JButton) e.getSource();
			riskVal =+ Integer.parseInt(button.getText());
			riskText.setText(String.valueOf(riskVal));
*/
			for (ButtonHandler h : handlers) {
				if (h.canHandle((JButton) e.getSource())) {
					h.setAmount();
					System.out.println(bankVal + "," + riskVal);
				}
			}
		}
		
	}
	
	/**
	 * 
	 * find out the current val of the cards
	 * if it's the first hit generates 2 cards else it is 
	 * generates one card and assign it the image
	 * Update the val of the cards
	 *
	 */
	class HitButtonListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {

			newPlayerCardGenerator();
	
			if (val > 21) {
				loseScenario();
			}
			// if you win automatically..
			if (val == 21) {
				winScenario();
			} else {
				System.out.println(val);
			}
			System.out.println(count);
		}
	}
	
	private void newDealerCardGenerator() {
		boolean isFirstAce = true;

		// generates 2 cards and add it to dealerCards[]
		if (dealerCounter == 0 ) {
			for (int i = 0; i < 2; i++) {
				Card thisCard = dealerCardList.get(i);	
				thisCard.shuffle();
				thisCard.updateImage();
				if (thisCard.getValue() == 1 && isFirstAce) {
					thisCard.setValue(11);
					dealerVal += thisCard.getValue();
					dealerCounter++;
					isFirstAce = false;
					continue;
				
				}
				if (thisCard.getValue() > 10)
					thisCard.setValue(10);
				dealerVal += thisCard.getValue();
				dealerCounter++;
				continue;
			}
		} else { // generates a card and add it to playerCards[]
			Card thisCard = dealerCardList.get(dealerCounter);	
			thisCard.shuffle();
			thisCard.updateImage();
			if (thisCard.getValue() > 10)
				thisCard.setValue(10);
			dealerVal += thisCard.getValue();
			dealerCounter++;
	//		continue;
		}
	}
	
	private void newPlayerCardGenerator() {
		boolean isFirstAce = true;
		disableMoneyButtons(true);		
		// generates 2 cards and add it to playerCards[]
		if (count == 0) {
			for (int i = 0; i < 2; i++) {
				Card thisCard = cardList.get(i);	
				thisCard.shuffle();
				thisCard.updateImage();
				if (thisCard.getValue() == 1 && isFirstAce) {
					thisCard.setValue(11);
					val += thisCard.getValue();
					count++;
					continue;					
				}
				if (thisCard.getValue() > 10)
					thisCard.setValue(10);
				val += thisCard.getValue();
				count++;
			}
		} else { // generates a card and add it to playerCards[]
			Card thisCard = cardList.get(count);	
			thisCard.shuffle();
			thisCard.updateImage();
			if (thisCard.getValue() > 10)
				thisCard.setValue(10);
			val += thisCard.getValue();
			count++;
		}
	}
	
	private void winScenario() {
		int money = getBankVal();
		money += (2 * riskVal);
		setBankVal(money);
		bankText.setText(String.valueOf(money));
		setRiskVal(0);
		riskText.setText("0");
		
		JOptionPane.showMessageDialog(null, "You win!");
		resetGame();
	}
	
	private void loseScenario() {
		JOptionPane.showMessageDialog(null, "You lost!");
		setRiskVal(0);
		riskText.setText("0");
		resetGame();
	}
	private void tieScenario() {
		JOptionPane.showMessageDialog(null,"Its a tie... splitting isn't fun");
		setBankVal(getRiskVal() + getBankVal());
		bankText.setText(String.valueOf(getBankVal()));
		setRiskVal(0);
		riskText.setText("0");
		resetGame();
	}
	
	private void disableMoneyButtons(boolean flag) {
			for (ButtonHandler h : handlers) {
				if (flag) {
					h.disable(flag);
				} else {
					h.disable(flag);
				}
			}
	}
	
	abstract class ButtonHandler {
		protected JButton myButton;
		protected JLabel myRiskText;
		protected JLabel myBankText;
		protected int val = 0;
		protected boolean isEnough = false;
		
		public ButtonHandler(JButton btn, JLabel riskText, JLabel bankText) {
			myButton = btn;
			myRiskText = riskText;
			myBankText = bankText;
			
		}
		public boolean enoughMoney () {
			int bank = getBankVal();
			if (bank >= val) isEnough = true; 
			else {
				JOptionPane.showMessageDialog(null, "Not enough money in your bank.... :(");
				isEnough = false;
			}
			return isEnough;	
		}
		public JButton getMyButton() {
			return myButton;
		}
		public JLabel getMyTextField() {
			return myRiskText;
		}
		public void disable(boolean flag) {
			if (flag)
				myButton.setEnabled(false);
			else 
				myButton.setEnabled(true);
		}
		public boolean canHandle(JButton btn) {
			boolean isRightButton = false;
			
			if (btn == myButton) isRightButton = true;
			if (isRightButton && enoughMoney())
				return true;
			else {
				return false;
			}

		}
		
		public void setAmount() {
			if (enoughMoney()) {
				riskVal += val;
				bankVal -= val;
				myRiskText.setText(String.valueOf(riskVal));
				myBankText.setText(String.valueOf(bankVal));
			} else {
				myRiskText.setText(String.valueOf(riskVal));
				myBankText.setText(String.valueOf(bankVal));
			}
		}
		
	}
	public void setUpHandlers() {
		handlers[0] = new OneButtonHandler(btnOne, riskText, bankText);
		handlers[1] = new TwoButtonHandler(btnTwo, riskText, bankText);
		handlers[2] = new ThreeButtonHandler(btnThree,riskText, bankText);
		handlers[3] = new FourButtonHandler(btnFour,riskText, bankText);	
	}
	public class OneButtonHandler extends ButtonHandler {
		public OneButtonHandler(JButton btn, JLabel text, JLabel bankText) {
			super(btn, text, bankText);
			val = 50;
		}
	}
	public class TwoButtonHandler extends ButtonHandler {

		public TwoButtonHandler(JButton btn, JLabel text, JLabel bankText) {
			super(btn, text, bankText);
			val = 100;
		}
	}
	public class ThreeButtonHandler extends ButtonHandler {

		public ThreeButtonHandler(JButton btn, JLabel text, JLabel bankText) {
			super(btn, text, bankText);
			val = 200;
		}
	}
	public class FourButtonHandler extends ButtonHandler {

		public FourButtonHandler(JButton btn, JLabel text, JLabel bankText) {
			super(btn, text, bankText);
			val = 500;
		}
	}


}