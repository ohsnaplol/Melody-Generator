package main;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;

public class GenerateWindow {

	JFrame frmMelodyGenerator;
	private JTextField tempoField;
	private JTextField numOfNotesField;
	JFileChooser jFileChooser = new JFileChooser();
	private boolean isTonic = false;


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
	 * @throws MidiUnavailableException 
	 */
	public GenerateWindow() throws MidiUnavailableException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MidiUnavailableException 
	 */
	private void initialize() throws MidiUnavailableException {
		frmMelodyGenerator = new JFrame();
		frmMelodyGenerator.setResizable(false);
		frmMelodyGenerator.setTitle("Melody Generator");
		frmMelodyGenerator.setBounds(100, 100, 443, 264);
		frmMelodyGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMelodyGenerator.getContentPane().setLayout(null);
		
		JLabel lblSongOutput = new JLabel();
		lblSongOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblSongOutput.setBounds(6, 117, 421, 22);
		frmMelodyGenerator.getContentPane().add(lblSongOutput);
		
		tempoField = new JTextField();
		tempoField.setText("120");
		tempoField.setBounds(135, 68, 39, 28);
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
		numOfNotesField.setBounds(135, 40, 28, 28);
		frmMelodyGenerator.getContentPane().add(numOfNotesField);
		numOfNotesField.setColumns(10);
		
		JButton stopPlayingButton = new JButton("Stop");
		stopPlayingButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		stopPlayingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.reset();
			}
		});
		stopPlayingButton.setBounds(310, 150, 117, 76);
		frmMelodyGenerator.getContentPane().add(stopPlayingButton);
		
		JButton playButton = new JButton("Play");
		playButton.setEnabled(false);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.play();
				
			}
		});
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		playButton.setBounds(191, 150, 117, 76);
		frmMelodyGenerator.getContentPane().add(playButton);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox<KEYSIG> keyBox = new JComboBox(KEYSIG.values());
		keyBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.keyIndex = keyBox.getSelectedIndex();
			}
		});
		keyBox.setBounds(310, 42, 117, 27);
		frmMelodyGenerator.getContentPane().add(keyBox);
		
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(220, 46, 28, 16);
		frmMelodyGenerator.getContentPane().add(lblKey);
		
		JButton loadButton = new JButton("Load pattern");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Load file
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
//				lblSongOutput.setText(Song.pattern.toString()); // Why is this here?
				playButton.setEnabled(true);
				}
			}
		);
		loadButton.setBounds(144, 6, 117, 29);
		frmMelodyGenerator.getContentPane().add(loadButton);
		
		JButton saveButton = new JButton("Save pattern");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFileChooser.setCurrentDirectory(new File("/User"));				
				int result = jFileChooser.showSaveDialog(new JFrame());
				
				if (result == JFileChooser.APPROVE_OPTION) {
					File fileToSave = jFileChooser.getSelectedFile();
				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					try {
						Song.save(fileToSave);
					} catch (IOException e1) {
						System.out.println("error");
						e1.printStackTrace();
					}
				}
				
			}
		});
		saveButton.setBounds(17, 6, 117, 29);
		frmMelodyGenerator.getContentPane().add(saveButton);
		
		//when generate button is pushed..
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Song.tempo = Integer.parseInt(tempoField.getText());
					Song.numOfNotes = Integer.parseInt(numOfNotesField.getText());
					//generate the random notes
					Song.generate(isTonic);
					//show what notes rng has chosen
					lblSongOutput.setText(Song.pattern.toString());
					Song.play();
					//make it so play button only shows up after you've generated something to play
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
		btnGenerate.setBounds(6, 150, 183, 76);
		frmMelodyGenerator.getContentPane().add(btnGenerate);
		
		JCheckBox chckbxTonic = new JCheckBox("Tonic");
		chckbxTonic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isTonic)
					isTonic = false;
				else
					isTonic = true;
			}
		});
		chckbxTonic.setBounds(329, 9, 69, 23);
		frmMelodyGenerator.getContentPane().add(chckbxTonic);
		
		// Setup for our instrument list
		List<String> instrumentList = new ArrayList<String>();
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        Instrument[] orchestra = synthesizer.getAvailableInstruments();
        for (int i = 0; i < 128; i++) { // There are more than 128 instruments, however setIntstrument only allows up to 127
        	instrumentList.add(orchestra[i].toString().substring(12, 24));
        }
        synthesizer.close();
        
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboBox = new JComboBox(instrumentList.toArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Song.pattern.setInstrument(comboBox.getSelectedIndex());
				System.out.println(comboBox.getSelectedIndex());
			    lblSongOutput.setText(Song.pattern.toString());
			}
		});
		comboBox.setBounds(310, 69, 117, 27);
		frmMelodyGenerator.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Instrument");
		lblNewLabel.setBounds(220, 74, 69, 16);
		frmMelodyGenerator.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 107, 421, 12);
		frmMelodyGenerator.getContentPane().add(separator);
		
		
	}
}
