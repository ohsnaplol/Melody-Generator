package main;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class GenerateWindow {

	JFrame frmMelodyGenerator;
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
					window.frmMelodyGenerator.setVisible(true);
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
		frmMelodyGenerator = new JFrame();
		frmMelodyGenerator.setTitle("Melody Generator");
		frmMelodyGenerator.setBounds(100, 100, 433, 205);
		frmMelodyGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMelodyGenerator.getContentPane().setLayout(null);
		
		JLabel lblSongOutput = new JLabel("");
		lblSongOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongOutput.setBounds(6, 77, 421, 16);
		frmMelodyGenerator.getContentPane().add(lblSongOutput);
		
		tempoField = new JTextField();
		tempoField.setText("120");
		tempoField.setBounds(143, 37, 39, 28);
		frmMelodyGenerator.getContentPane().add(tempoField);
		tempoField.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo");
		lblTempo.setBounds(17, 43, 48, 16);
		frmMelodyGenerator.getContentPane().add(lblTempo);
		
		JLabel lblNumberOfNotes = new JLabel("Number of Notes");
		lblNumberOfNotes.setBounds(17, 15, 122, 16);
		frmMelodyGenerator.getContentPane().add(lblNumberOfNotes);
		
		numOfNotesField = new JTextField();
		numOfNotesField.setText("6");
		numOfNotesField.setBounds(143, 9, 33, 28);
		frmMelodyGenerator.getContentPane().add(numOfNotesField);
		numOfNotesField.setColumns(10);
		
		JButton stopPlayingButton = new JButton("■");
		stopPlayingButton.setEnabled(false);
		stopPlayingButton.setFont(new Font("Lucida Grande", Font.PLAIN, 52));
		stopPlayingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		stopPlayingButton.setBounds(310, 103, 117, 76);
		frmMelodyGenerator.getContentPane().add(stopPlayingButton);
		
		JButton playButton = new JButton("▶");
		playButton.setEnabled(false);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.play();
			}
		});
		playButton.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		playButton.setBounds(191, 103, 117, 76);
		frmMelodyGenerator.getContentPane().add(playButton);
		
		//when generate button is pushed..
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Song.tempo = Integer.parseInt(tempoField.getText());
					Song.numOfNotes = Integer.parseInt(numOfNotesField.getText());
					//generate the random notes
					Song.generate();
					//show what notes rng has chosen (for some reason this still only happens after notes are played)
					lblSongOutput.setText(Song.pattern.toString());
					Song.play();
					//make it so play button only shows up after you'd generated something to play
					playButton.setEnabled(true); //do we really need to enable every time this button is clicked?
				} catch (Exception t) {
					tempoField.setBackground(Color.red);
					numOfNotesField.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "Please enter a valid number");
					System.out.println(t);
				} finally {
					tempoField.setBackground(Color.white);
					numOfNotesField.setBackground(Color.white);
				}
			}
		});
		btnGenerate.setBounds(6, 103, 183, 76);
		frmMelodyGenerator.getContentPane().add(btnGenerate);
		
		JComboBox<KEYSIG> keyBox = new JComboBox(KEYSIG.values());
		keyBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.keyIndex = keyBox.getSelectedIndex();
			}
		});
		keyBox.setBounds(281, 11, 127, 27);
		frmMelodyGenerator.getContentPane().add(keyBox);
		
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(239, 15, 28, 16);
		frmMelodyGenerator.getContentPane().add(lblKey);
	}
}
