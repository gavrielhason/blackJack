
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	
	private Player player = new Player();
	private Player dealer = new Player();
	
	private ArrayList<JToggleButton> dealerCardsButton = new ArrayList<JToggleButton>();

			
	private ButtonHandler[] handlers = new ButtonHandler[4]; 
	



		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BlackJackGui().go();
	}

	/**
	 * a getter and setters for bankVal,riskVal
	 * @r

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
	*/

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

		setupMenu();
		frame.pack();
		frame.setVisible(true);
		
	}
	private void setupMenu() {
		JMenuBar menu = new JMenuBar();
		
		// Create new Game menu
		JMenu gameMenu = new JMenu("Game");
		JMenu viewMenu = new JMenu("View");
	
		
		// Add the 'Game' to menu
		menu.add(gameMenu);
				
		//Add the 'View' to menu
		menu.add(viewMenu);
		
		// add new Game item
		JMenuItem newGameItem = new JMenuItem("New Game");
		gameMenu.add(newGameItem);
		
		JMenuItem exitGameItem = new JMenuItem("Exit Game", KeyEvent.VK_X);
		gameMenu.add(exitGameItem);
		
		
		//new Game listener
		newGameItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			newGame();	
			}
			
		});
		
		// Set CTRL-X as a keyboard shortcut
		exitGameItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_X, ActionEvent.CTRL_MASK
				));
		
		exitGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int result = JOptionPane.showOptionDialog(frame,"Are you sure you want to exit?", "BlackJack - Exit Game",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,null,
						JOptionPane.NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
					
			}
		});
		
		JMenuItem aboutItem = new JMenuItem("About");
		viewMenu.add(aboutItem);
		
		aboutItem.addActionListener(new ActionListener( ) {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This game created by Gavriel Hason, Mars Hill University");
			}
			
		});
		
		
		frame.setJMenuBar(menu);
		
		
	
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
			// new
			CardGenerator cg = new CardGenerator(tb);
			player.insertCard(cg);
			// end new
			
		//	cardList.add(player);
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
			// new
			CardGenerator cg = new CardGenerator(tb);
			player.insertCard(cg);
			dealer.insertCard(cg);
			// end new
			
		//	dealerCardList.add(dealer);
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
		bankText.setText(String.valueOf((player.getBankVal())));
		risk = new JLabel("Risk: ");
		riskText = new JLabel();
		riskText.setText(String.valueOf(player.getRiskVal()));
		
		bankAndRisk.add(bank);
		bankAndRisk.add(bankText);
		bankAndRisk.add(risk);
		bankAndRisk.add(riskText);
		
		return bankAndRisk;
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
			while (dealer.getCardsVal() <= 17 &&
					dealer.getCardsVal() < player.getCardsVal()) {

				newDealerCardGenerator();
				
			}
			if (dealer.getCardsVal() > 21) {
				winScenario();
				
			} else if(dealer.getCardsVal() > player.getCardsVal()) {
				loseScenario();
			} else if (player.getCardsVal() > dealer.getCardsVal()) {
				winScenario();
			}  else if (dealer.getCardsVal() == player.getCardsVal()) {
				tieScenario();
			}
		}
		
	}
	
	class AllButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (ButtonHandler h : handlers) {
				if (h.canHandle((JButton) e.getSource())) {
					h.setAmount();
					System.out.println(player.getBankVal() + "," + player.getRiskVal());
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
	
			if (player.getCardsVal() > 21) {
				loseScenario();
			}
			// if you win automatically..
			if (player.getCardsVal() == 21) {
				winScenario();
			}
			System.out.println(player.getCardsVal());

		}
	}
	
	private void newDealerCardGenerator() {
		boolean isFirstAce = true;

		if (dealer.getCardCount() == 0) {
			for (int i = 0; i < 2; i++) {
				CardGenerator thisCard = dealer.getCard(i);	
				thisCard.shuffle();
				thisCard.updateImage();
				if (thisCard.getValue() > 10) {
					thisCard.setValue(10);
				}
				if (thisCard.getValue() == 1 && isFirstAce) {
					thisCard.setValue(11);
					isFirstAce = false;
				}
				dealer.incrementCardVal(thisCard.getValue());
			
			}
		} else { 
			CardGenerator thisCard = dealer.getCard(dealer.getCardCount());
			thisCard.shuffle();
			thisCard.updateImage();
			if (thisCard.getValue() > 10)
				thisCard.setValue(10);
			if (thisCard.getValue() == 1 && (11 + player.getCardsVal()) < 22 ) { // a scenario where ace will be better as 11
				thisCard.setValue(11);
				
			}
			dealer.incrementCardVal(thisCard.getValue());
		}
	}
	
	private void newPlayerCardGenerator() {
		boolean isFirstAce = true;
		disableMoneyButtons(true);		

		if (player.getCardCount() == 0 ) {
			for (int i = 0; i < 2; i++) {
				CardGenerator thisCard = player.getCard(i);
				thisCard.shuffle();
				thisCard.updateImage();
				if (thisCard.getValue() == 1 && isFirstAce) {
					thisCard.setValue(11);
					isFirstAce = false;
				}
				if (thisCard.getValue() > 10) {
					thisCard.setValue(10);
				}
				player.incrementCardVal(thisCard.getValue());

			}
		} else { 
			CardGenerator thisCard = player.getCard(player.getCardCount());
			thisCard.shuffle();
			thisCard.updateImage();
			if (thisCard.getValue() > 10)
				thisCard.setValue(10);
			if (thisCard.getValue() == 1 && (11 + player.getCardsVal()) < 22) // a scenario where ace will be better as 11
				thisCard.setValue(11);
			player.incrementCardVal(thisCard.getValue());
		}
	}
	
	/**
	 *  TODO EndGame
	 *  what happened if the player is out of money at the end of the stay round??
	 *  TODO 
	 *  Implement it inside the stayListener
	 */
	
	private void newGame() {
		resetGame();
		player.setBankVal(5000);
		player.setRiskVal(0);
		bankText.setText(String.valueOf(player.getBankVal()));
		riskText.setText(String.valueOf(player.getRiskVal()));
		
	}
	private void resetGame() {
		disableMoneyButtons(false);		
	
		player.resetSession();
		dealer.resetSession();
	}

	private void winScenario() {
		int money = player.getBankVal();
		money += (2 * player.getRiskVal());
		player.setBankVal(money);
		bankText.setText(String.valueOf(money));
		player.setRiskVal(0);
		riskText.setText("0");
		
		JOptionPane.showMessageDialog(null, "You win!");
		resetGame();
	}
	
	private void loseScenario() {
		JOptionPane.showMessageDialog(null, "You lost!");
		player.setRiskVal(0);
		riskText.setText("0");
		resetGame();
	}
	private void tieScenario() {
		JOptionPane.showMessageDialog(null,"Its a tie... splitting isn't fun");
		player.setBankVal(player.getRiskVal() + player.getBankVal());
		bankText.setText(String.valueOf(player.getBankVal()));
		player.setRiskVal(0);
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
			int bank = player.getBankVal();
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
				player.setRiskVal(val + player.getRiskVal());
				player.setBankVal(player.getBankVal() - val);
				myRiskText.setText(String.valueOf(player.getRiskVal()));
				myBankText.setText(String.valueOf(player.getBankVal()));
			} else {
				myRiskText.setText(String.valueOf(player.getRiskVal()));
				myBankText.setText(String.valueOf(player.getBankVal()));
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