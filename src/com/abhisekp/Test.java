package com.abhisekp;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Test Serialization</h1>
 * <p/>
 * Creation Date: 25-05-2014 04:49 PM
 *
 * @version 0.1.0
 * @since 0.1.0
 */
public class Test {

	private String name;
	private int age;

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
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			setDefaultLookAndFeelDecorated(true);
			add(mainPanel);
			setTitle(name + " - " + age);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}

		private void createGUI() {
			/**
			 * Configure Panels & Components
			 */
			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

			// Create temporary panels
			JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 2));
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 2));

			// Create Labels
			nameLabel = new JLabel(name, SwingConstants.CENTER);
			ageLabel = new JLabel("Age: ", SwingConstants.LEFT);
			ageValueLabel = new JLabel(Integer.toString(age), SwingConstants.RIGHT);

			// Create Buttons
			randomBTN = new JButton("Randomize");
			saveBTN = new JButton("Save");
			loadBTN = new JButton("Load");

			/**
			 * Add Components to Panels
			 */
			// Add Labels to Label Panel
			agePanel.add(ageLabel);
			agePanel.add(ageValueLabel);

			// Add Buttons to Button Panel
			buttonPanel.add(saveBTN);
			buttonPanel.add(loadBTN);

			// Add Components to Main Panel
			mainPanel.add(nameLabel);
			mainPanel.add(agePanel);
			mainPanel.add(randomBTN);
			mainPanel.add(buttonPanel);
		}

		private void addListeners() {
			// Add listeners for Buttons
		}
	}
}
