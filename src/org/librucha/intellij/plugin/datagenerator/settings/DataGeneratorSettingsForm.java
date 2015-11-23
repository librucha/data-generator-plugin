package org.librucha.intellij.plugin.datagenerator.settings;

import com.intellij.ui.components.JBList;

import javax.swing.*;

public class DataGeneratorSettingsForm {

	private JList excludedTypes;
	private JPanel rootPanel;
	private JButton addClass;

	public DataGeneratorSettingsForm(DataGeneratorSettings settings) {
		excludedTypes = new JBList(new String[]{"sdadad"});
	}

	public JComponent getRootComponent() {
		return rootPanel;
	}

	private void createUIComponents() {
		// TODO: place custom component creation code here
	}
}
