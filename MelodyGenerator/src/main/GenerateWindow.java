package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GenerateWindow {

	JFrame frame;
	private JTextField tempoField;
	private JTextField numOfNotesField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateWindow window = new GenerateWindow();
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
	public GenerateWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 433, 205);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSongOutput = new JLabel("");
		lblSongOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongOutput.setBounds(6, 77, 421, 16);
		frame.getContentPane().add(lblSongOutput);
		
		tempoField = new JTextField();
		tempoField.setText("120");
		tempoField.setBounds(143, 37, 55, 28);
		frame.getContentPane().add(tempoField);
		tempoField.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo");
		lblTempo.setBounds(17, 43, 48, 16);
		frame.getContentPane().add(lblTempo);
		
		JLabel lblNumberOfNotes = new JLabel("Number of Notes");
		lblNumberOfNotes.setBounds(17, 15, 122, 16);
		frame.getContentPane().add(lblNumberOfNotes);
		
		numOfNotesField = new JTextField();
		numOfNotesField.setText("6");
		numOfNotesField.setBounds(143, 9, 55, 28);
		frame.getContentPane().add(numOfNotesField);
		numOfNotesField.setColumns(10);
		
		JButton stopPlayingButton = new JButton("■");
		stopPlayingButton.setEnabled(false);
		stopPlayingButton.setFont(new Font("Lucida Grande", Font.PLAIN, 52));
		stopPlayingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		stopPlayingButton.setBounds(310, 103, 117, 76);
		frame.getContentPane().add(stopPlayingButton);
		
		JButton playButton = new JButton("▶");
		playButton.setEnabled(false);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SongData.play(); //how embarassing
			}
		});
		playButton.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		playButton.setBounds(191, 103, 117, 76);
		frame.getContentPane().add(playButton);
		
		//when generate button is pushed..
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//set tempo and num of notes in SongData from fields
					SongData.tempo = Integer.parseInt(tempoField.getText());
					SongData.numOfNotes = Integer.parseInt(numOfNotesField.getText());
					//generate the random notes
					MG_Main.generate(SongData.tempo, SongData.numOfNotes);
					//show what notes rng has chosen (for some reason this still only happens after notes are played)
					lblSongOutput.setText(SongData.storedPattern.toString());
					//let's hear it
					SongData.play();
					//make it so play button only shows up after you'd generated something to play
					playButton.setEnabled(true); //do we really need to enable every time this button is clicked?
				} catch (Exception ex) {
					//would like border to be red if unsuccessful
					tempoField.setBackground(Color.red);
					numOfNotesField.setBackground(Color.red);
					System.out.println(ex);
					JOptionPane.showMessageDialog(null,  "Please enter a valid number");
				}
			}
		});
		btnGenerate.setBounds(6, 103, 183, 76);
		frame.getContentPane().add(btnGenerate);
	}
}
