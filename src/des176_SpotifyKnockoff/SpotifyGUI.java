package des176_SpotifyKnockoff;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class SpotifyGUI {

	private JFrame frame;
	private JTextField txtSearch;
	private JRadioButton radShowAlbums;
	private JRadioButton radShowArtists;
	private JRadioButton radShowSongs;
	private JTable tblData;
	private DefaultTableModel musicData;
	private static JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpotifyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Spotify");
		frame.setBounds(100, 100, 1000, 600);
		frame.getContentPane().setLayout(null);
		
		// Create label for "Select View"
		JLabel lblViewSelector = new JLabel("Select View");
		lblViewSelector.setBounds(20, 30, 99, 16);
		frame.getContentPane().add(lblViewSelector);
		
		// Creates Combo Box 
		String[] artistSearch = new String[] {"First Name", "Last Name", "Band Name"};
		setComboBox(new JComboBox<String>(artistSearch));
		getComboBox().setEnabled(false);
		getComboBox().setModel(new DefaultComboBoxModel<String>(new String[] {"First Name", "Last Name", "Band Name"}));
		getComboBox().setBounds(50, 169, 190, 20);
		frame.getContentPane().add(getComboBox());
		
		// Creates Search By Label
		JLabel lblSearchBy = new JLabel("Search by:");
		lblSearchBy.setEnabled(false);
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchBy.setBounds(40, 142, 75, 20);
		frame.getContentPane().add(lblSearchBy);
		
		// Creates a table with the data from the database
		// Album is the default on start
		musicData = Spotify.getAlbumData("");
		tblData = new JTable(musicData);
		tblData.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		tblData.setBounds(299, 45, 600, 400);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		tblData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		frame.getContentPane().add(tblData);
		
		// Creates a Albums radio button
		radShowAlbums = new JRadioButton("Albums");
		
		// Using an ActionListener when pressed the button will clear the search box, then grab the data for the table
		// It finishes by disabling the comboBox and other radio buttons
		radShowAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowAlbums.isSelected()){
					txtSearch.setText("");
					musicData = Spotify.getAlbumData(txtSearch.getText());
					tblData.setModel(musicData);
					getComboBox().setEnabled(false);
					radShowArtists.setSelected(false);
					radShowSongs.setSelected(false);
				}
			}
		});
		radShowAlbums.setBounds(40, 60, 150, 25);
		radShowAlbums.setSelected(true);
		frame.getContentPane().add(radShowAlbums);
		
		// Creates a Radio button
		radShowArtists = new JRadioButton("Artists");
		// Using an ActionListener when pressed the button will clear the search box, then grab the data for the table
		// It finishes by enabling the comboBox while disabling other radio buttons
		radShowArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowArtists.isSelected()){
					txtSearch.setText("");
					musicData = Spotify.getArtistData(txtSearch.getText());
					tblData.setModel(musicData);
					getComboBox().setEnabled(true);
					radShowAlbums.setSelected(false);
					radShowSongs.setSelected(false);
				}
			}
		});
		radShowArtists.setBounds(40, 85, 150, 25);
		frame.getContentPane().add(radShowArtists);
		
		radShowSongs = new JRadioButton("Songs");
		
		// Using an ActionListener when pressed the button will clear the search box, then grab the data for the table
		// It finishes by disabling the comboBox and other radio buttons
		radShowSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowSongs.isSelected()){
					txtSearch.setText("");
					musicData = Spotify.getSongData(txtSearch.getText());
					tblData.setModel(musicData);
					getComboBox().setEnabled(false);
					radShowArtists.setSelected(false);
					radShowAlbums.setSelected(false);
				}
			}
		});
		radShowSongs.setBounds(40, 110, 150, 25);
		frame.getContentPane().add(radShowSongs);
		
		// Search Label
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(20, 290, 100, 20);
		frame.getContentPane().add(lblSearch);
		
		frame.getContentPane().add(lblViewSelector);
		txtSearch = new JTextField();
		txtSearch.setBounds(20, 315, 245, 30);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		// Creates a search button that will search the data table appropriate to the radio button selected
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowSongs.isSelected()){
					musicData = Spotify.getSongData(txtSearch.getText());
					tblData.setModel(musicData);
				}
				if(radShowAlbums.isSelected()){
					musicData = Spotify.getAlbumData(txtSearch.getText());
					tblData.setModel(musicData);
				}
				if(radShowArtists.isSelected()){
					musicData = Spotify.getArtistData(txtSearch.getText());
					tblData.setModel(musicData);
				}
			}
		});
		
		btnSearch.setBounds(20, 356, 117, 29);
		
		frame.getContentPane().add(btnSearch);
		
		// Creates clear button which clears txtSearch
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtSearch.setText(null);
			}
		});
		btnClear.setBounds(148, 356, 117, 29);
		frame.getContentPane().add(btnClear);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Gets a value from a private variable to promote more secure code
	 * @return comboBox - the search criteria that is being used by the user for the Artist Data
	 */
	public static JComboBox<String> getComboBox() {
		return comboBox;
	}
	
	/**
     * Sets a value in a private variable to promote more secure code
     * @param comboBox - the search criteria that is being used by the user for the Artist Data
     */
	public void setComboBox(JComboBox<String> comboBox) {
		SpotifyGUI.comboBox = comboBox;
	}
}
