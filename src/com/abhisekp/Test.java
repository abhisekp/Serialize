package com.abhisekp;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <h1>Test Serialization</h1>
 * <p/>
 * Creation Date: 25-05-2014 04:49 PM
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class Test implements Serializable {

	private String name;
	private int age;
	transient private final GUI gui;
	private static Test test;
	transient private File file;
	static final long serialVersionUID = 1;

	public static void main(String[] args) {
		test = new Test();
	}

	public Test() {
		file = new File("test.ser");
		name = "Abhisek Pattnaik";
		age = 23;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		gui = new GUI();
	}

	private class GUI extends JFrame {
		private JPanel mainPanel;

		private JLabel nameLabel,
				ageLabel,
				ageValueLabel;

		private JButton randomBTN,
				saveBTN,
				loadBTN;

		private GUI() throws HeadlessException {
			createGUI();
			addListeners();

			/**
			 * Configure frame
			 */
			setSize(300, 200);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			add(mainPanel);
			updateTitle();
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
		}

		private void createGUI() {
			/**
			 * Configure Panels & Components
			 */
			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			// Create temporary panels
			JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 2));
			namePanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			namePanel.setSize(300, 10);

			JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 2));
			agePanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 2));
			buttonPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

			JPanel randomBTNPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 2));
			randomBTNPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

			// Create Labels
			nameLabel = new JLabel(name, SwingConstants.CENTER);
			nameLabel.setFont(new Font("Georgia", Font.BOLD, 24));

			ageLabel = new JLabel("Age: ", SwingConstants.LEFT);
			ageLabel.setFont(new Font("Verdana", Font.BOLD, 16));

			ageValueLabel = new JLabel(Integer.toString(age, 10), SwingConstants.RIGHT);
			ageValueLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

			// Create Buttons
			randomBTN = new JButton("Randomize");
			saveBTN = new JButton("Save");
			loadBTN = new JButton("Load");

			/**
			 * Add Components to Panels
			 */
			// Add NameLabel to namePanel
			namePanel.add(nameLabel);

			// Add Labels to Age Panel
			agePanel.add(ageLabel);
			agePanel.add(ageValueLabel);

			// Add Random Button to RandomBTN Panel
			randomBTNPanel.add(randomBTN);

			// Add Buttons to Button Panel
			buttonPanel.add(saveBTN);
			buttonPanel.add(loadBTN);

			// Add Components to Main Panel
			mainPanel.add(namePanel);
			mainPanel.add(agePanel);
			mainPanel.add(randomBTNPanel);
			mainPanel.add(buttonPanel);
		}

		private void addListeners() {
			// Add listeners for Buttons
			randomBTN.addActionListener(e -> randomizeAge());
			saveBTN.addActionListener(e -> save());
			loadBTN.addActionListener(e -> load());
		}

		/**
		 * Randomize age
		 */
		public void randomizeAge() {
			age = 1 + (int) (Math.random() * 150);
			updateGUI();
			System.out.println("Randomized age = " + age);
		}

		public void updateTitle() {
			setTitle(name + " - Age " + age);
		}

		public void updateAgeLabel() {
			ageValueLabel.setText(Integer.toString(age, 10));
		}
	}

	/**
	 * Saves file
	 * @since 0.1.0
	 */
	public void save() {
		// serialization
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			System.out.println("File Location: " + file.getAbsolutePath());
			System.out.println("Saved Age = " + age);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads saved file
	 * @since 0.1.0
	 */
	public void load() {
		// deserialization
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Test test1 = (Test) ois.readObject();
			age = test1.age;
			ois.close();

			System.out.println("File Location: " + file.getAbsolutePath());
			System.out.println("Loaded Age = " + age);
			updateGUI();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void updateGUI() {
		gui.updateAgeLabel();
		gui.updateTitle();
	}
}
