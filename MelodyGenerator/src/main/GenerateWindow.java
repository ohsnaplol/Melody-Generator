package main;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class GenerateWindow {

	JFrame frmMelodyGenerator;
	private JTextField tempoField;
	private JTextField numOfNotesField;
	JFileChooser jFileChooser = new JFileChooser();


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
		frmMelodyGenerator.setBounds(100, 100, 433, 240);
		frmMelodyGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMelodyGenerator.getContentPane().setLayout(null);
		
		JLabel lblSongOutput = new JLabel();
		lblSongOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongOutput.setBounds(6, 102, 421, 22);
		frmMelodyGenerator.getContentPane().add(lblSongOutput);
		
		tempoField = new JTextField();
		tempoField.setText("120");
		tempoField.setBounds(143, 68, 39, 28);
		frmMelodyGenerator.getContentPane().add(tempoField);
		tempoField.setColumns(10);
		
		JLabel lblTempo = new JLabel("Tempo");
		lblTempo.setBounds(17, 74, 48, 16);
		frmMelodyGenerator.getContentPane().add(lblTempo);
		
		JLabel lblNumberOfNotes = new JLabel("Number of Notes");
		lblNumberOfNotes.setBounds(17, 46, 122, 16);
		frmMelodyGenerator.getContentPane().add(lblNumberOfNotes);
		
		numOfNotesField = new JTextField();
		numOfNotesField.setText("6");
		numOfNotesField.setBounds(143, 40, 33, 28);
		frmMelodyGenerator.getContentPane().add(numOfNotesField);
		numOfNotesField.setColumns(10);
		
		JButton stopPlayingButton = new JButton("■");
		stopPlayingButton.setFont(new Font("Lucida Grande", Font.PLAIN, 52));
		stopPlayingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.reset();
			}
		});
		stopPlayingButton.setBounds(310, 136, 117, 76);
		frmMelodyGenerator.getContentPane().add(stopPlayingButton);
		
		JButton playButton = new JButton("▶");
		playButton.setEnabled(false);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.play();
			}
		});
		playButton.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		playButton.setBounds(191, 136, 117, 76);
		frmMelodyGenerator.getContentPane().add(playButton);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox<KEYSIG> keyBox = new JComboBox(KEYSIG.values());
		keyBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.keyIndex = keyBox.getSelectedIndex();
			}
		});
		keyBox.setBounds(281, 42, 127, 27);
		frmMelodyGenerator.getContentPane().add(keyBox);
		
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(239, 46, 28, 16);
		frmMelodyGenerator.getContentPane().add(lblKey);
		
		JButton loadButton = new JButton("Load pattern");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Can someone help me make it so that it asks if you want to erase current song before you save?
				// If there is a pattern output..
//				if(Song.pattern != null) {
//					System.out.println("There is nothing in the song pattern");
//					int dialogueResult = JOptionPane.showConfirmDialog(null, "Erase current output?");
//					// If they don't click yes..
//					if (dialogueResult == JOptionPane.YES_OPTION) {
//						return;
//					}
//				}
				// Load
				jFileChooser.setCurrentDirectory(new File("/User"));
				int result = jFileChooser.showOpenDialog(new JFrame());
			
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = jFileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    try {
						Song.load(selectedFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    lblSongOutput.setText(Song.pattern.toString());
				}
				lblSongOutput.setText(Song.pattern.toString());
				playButton.setEnabled(true);
				}
			}
		);
		loadButton.setBounds(6, 6, 117, 29);
		frmMelodyGenerator.getContentPane().add(loadButton);
		
		JButton saveButton = new JButton("Save pattern");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setCurrentDirectory(new File("/User"));
				//jFileChooser.setFileSelectionMode(jFileChooser.DIRECTORIES_ONLY);
				
				int result = jFileChooser.showSaveDialog(new JFrame());
				
				if (result == JFileChooser.APPROVE_OPTION) {
					File fileToSave = jFileChooser.getSelectedFile();
				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					try {
						Song.save(fileToSave);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("error");
						e1.printStackTrace();
					}
				}
				
			}
		});
		saveButton.setBounds(135, 6, 117, 29);
		frmMelodyGenerator.getContentPane().add(saveButton);
		
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
					playButton.setEnabled(true);
					saveButton.setEnabled(true);
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
		btnGenerate.setBounds(6, 136, 183, 76);
		frmMelodyGenerator.getContentPane().add(btnGenerate);
	}
}
