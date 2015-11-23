package org.librucha.intellij.plugin.datagenerator;

import com.intellij.openapi.components.*;
import com.intellij.openapi.options.*;
import org.jetbrains.annotations.*;
import org.librucha.intellij.plugin.datagenerator.settings.*;

import javax.swing.*;

@State(name = "DataGenerator", storages = {@Storage(id = "DataGenerator", file = "$APP_CONFIG$/data-generator.xml")})
public class DataGeneratorComponent implements ApplicationComponent, Configurable, PersistentStateComponent<DataGeneratorSettings> {

	private DataGeneratorSettings settings = DataGeneratorSettings.defaultSettings();

	private DataGeneratorSettingsForm form;

	@Override
	public void initComponent() {

	}

	@Override
	public void disposeComponent() {

	}

	@Nls
	@Override
	public String getDisplayName() {
		return "Data generator";
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;
	}

	@NotNull
	@Override
	public String getComponentName() {
		return "DataGeneratorComponent";
	}

	@Nullable
	@Override
	public DataGeneratorSettings getState() {
		if (settings == null) {
			settings = DataGeneratorSettings.defaultSettings();
		}
		return settings;
	}

	@Override
	public void loadState(DataGeneratorSettings dataGeneratorSettings) {

	}

	@Nullable
	@Override
	public JComponent createComponent() {
		form = new DataGeneratorSettingsForm(getState());
		return form.getRootComponent();
	}

	@Override
	public boolean isModified() {
		return false;
	}

	@Override
	public void apply() throws ConfigurationException {

	}

	@Override
	public void reset() {

	}

	@Override
	public void disposeUIResources() {
		form = null;
	}
}
