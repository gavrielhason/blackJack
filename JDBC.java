/**
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;



/**
 * @author Gavriel Hason
 *
 */
public class JDBC {
	private String uname = "admin";
	private String password = "Gavriel2908";
	private String port = "3306";
	private String dbName = "try";
	private String insertQuery = "insert into recordsTable (Name, score, timeStamp)" + " values(?, ?, ?)";
	private String selectQuery = "select * from recordsTable";
	private String url = "jdbc:mysql://database-1.cpyskpg5a0tl.eu-west-3.rds.amazonaws.com" + ":" + port + "/" +
	dbName + "?useSSL=false";
	private ArrayList<HallOfFameEntry> entries = new ArrayList<HallOfFameEntry>();
	private int MAX_ENTRIES = 10;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//	JDialog hallDialog = new JDBC().getHallOfFameDialog();
		new JDBC().printRecords();
	//	hallDialog.setVisible(true);

	}
	
	public void connect(String name, int score) {

		try(			
			Connection con = DriverManager.getConnection(url,uname,password);
			PreparedStatement st = con.prepareStatement(insertQuery)) {
		     Calendar calendar = Calendar.getInstance();
		     Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

			st.setString(1, name);
			st.setInt(2, score);
			st.setTimestamp(3, timestamp);
			st.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadRecords() {
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statment = con.createStatement();
			ResultSet result = statment.executeQuery(selectQuery);

			while (result.next()) {
				HallOfFameEntry e = new HallOfFameEntry(result.getString(1),Integer.parseInt(result.getString(2)),result.getString(3));
				entries.add(e);
				
			}
			Collections.sort(entries);
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	
	public void printRecords() {
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statment = con.createStatement();
			ResultSet result = statment.executeQuery(selectQuery);

			String data = "";
			while (result.next()) {
				for (int i = 1; i < 4; i++) {
					data += result.getString(i) + " " ;
					
				}
				
			}
			System.out.println(data);

			
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	
	public final JDialog getHallOfFameDialog() {
		loadRecords();
		
		JDialog hallDialog = new JDialog();
		hallDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		hallDialog.setTitle("BlackJack - Hall Of Fame");
		
		if (entries.size() > 0) {
			GridBagLayout gb = new GridBagLayout();
			JPanel scoresPanel = new JPanel(gb);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx = 0.5;
			gbc.ipady = 7;
			
			TitledBorder title = BorderFactory.createTitledBorder("Top " + MAX_ENTRIES + " Scores");
			scoresPanel.setBorder(title);
			
			gbc.gridx = gbc.gridy = 1;
			
			// Add headers.
			
			// Name
			gbc.anchor = GridBagConstraints.NORTHWEST;
			scoresPanel.add(new JLabel("     Name"),gbc);
			
			// Date.
			gbc.gridx++;
			gbc.anchor = GridBagConstraints.CENTER;
			scoresPanel.add(new JLabel("Date Achieved"), gbc);
			
			// Score.
			gbc.gridx++;
			gbc.anchor = GridBagConstraints.NORTHEAST;
			scoresPanel.add(new JLabel("Score"), gbc);
			
			int row = 1;
			for (HallOfFameEntry e : entries) {
				gbc.gridx = 1;
				gbc.gridy++;
				
				// Name.
				gbc.anchor = GridBagConstraints.NORTHWEST;
				scoresPanel.add(new JLabel(String.format("%3d. %-20s",  
						row, e.getName())), gbc);
				// Date Achieved.
				gbc.gridx++;
				gbc.anchor = GridBagConstraints.CENTER;
				scoresPanel.add(new JLabel(e.getTimeStamp()), gbc);
				
				// Score.
				gbc.gridx++;
				gbc.anchor = GridBagConstraints.NORTHEAST;
				scoresPanel.add(new JLabel(String.format("%5d", e.getScore())), gbc);
				
				// Increment the row.
				row++;
				
				// If it's the top MAX_ENTRIES, stop at MAX_ENTRIES.
				if (row > MAX_ENTRIES) break; 
				
				
				
			}
			
			
			
			JScrollPane scroller = new JScrollPane(scoresPanel);
			
			hallDialog.getContentPane().add(scroller,BorderLayout.CENTER);
			
		} else {
			// No entries. Print a message that states that fact.
		
			JLabel lblNone = new JLabel(" No Hall Of Fame entries exist");
			hallDialog.getContentPane().add(lblNone, BorderLayout.CENTER);
			
		}
		
		JPanel dismissPanel = new JPanel();
		JButton btnDismiss = new JButton("Dismiss");
		dismissPanel.add(btnDismiss);
		
		btnDismiss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Close the dialog.
				hallDialog.dispose();
			}
		});
		
		hallDialog.getContentPane().add(dismissPanel, BorderLayout.SOUTH);
		
		hallDialog.setPreferredSize(new Dimension(500, 375));
		hallDialog.setModal(true);
		
		hallDialog.pack();
		
		return hallDialog;
	}


}
