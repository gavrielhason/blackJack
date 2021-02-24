package src;

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
	private ArrayList<JToggleButton> dealerCardsButton = new ArrayList<JToggleButton>();
	private ButtonHandler[] handlers = new ButtonHandler[4]; 
	
	private int playerCards[] = { 0 , 0 , 0 , 0 , 0 , 0 , 0 ,0 , 0 , 0 , 0 , 0 , 0};


	private int bankVal = 5000;
	private int riskVal = 100;
	
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
		frame.setSize(800,300);
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
		
		hitOrStayPanel.add(hit);
		hitOrStayPanel.add(doubleIt);
		hitOrStayPanel.add(stay);
		
		return hitOrStayPanel;
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
			// TODO Auto-generated method stub

			int count = 0;
			int val = 0;
			
			// Go over playerCards and check the total value
			// Check how many cards player has
			for (int i =0; i < playerCards.length; i++) {
				if (playerCards[i] > 0) {
					val = (i + 1) * playerCards[i];
					count++;
				}
			}

			// generates 2 cards and add it to playerCards[]
			if (count == 0) {
				for (int i = 0; i < 2; i++) {
					JToggleButton thisCard = myCardsButton.get(i);	
					Card c = new Card();
					c.shuffle();
					playerCards[c.getValue() - 1]++;
					thisCard.setIcon(c.getImage());
					if (c.getValue() > 10)
						c.setValue(10);
				}
			} else { // generates a card and add it to playerCards[]
				JToggleButton card = myCardsButton.get(count);
				Card c = new Card();
				c.shuffle();
				card.setIcon(c.getImage());
				playerCards[c.getValue() - 1]++;
				if (c.getValue() > 10)
					c.setValue(10);
				}
			if (val > 21) 
				System.out.println("You Lost");
			
			if (val == 21)
				System.out.println("You Win");
			else {
				System.out.println(val);
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
