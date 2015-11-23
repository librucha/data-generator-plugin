package org.librucha.intellij.plugin.datagenerator.settings;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class DataGeneratorSettings {

	private List<Class<?>> excludedClasses;

	public static DataGeneratorSettings defaultSettings() {
		DataGeneratorSettings settings = new DataGeneratorSettings();
		List<Class<?>> excludedClasses = new ArrayList<>();
		excludedClasses.add(UUID.class);
		excludedClasses.add(Timestamp.class);
		settings.setExcludedClasses(excludedClasses);
		return settings;
	}

	public List<Class<?>> getExcludedClasses() {
		return excludedClasses;
	}

	public void setExcludedClasses(@NotNull List<Class<?>> excludedClasses) {
		requireNonNull(excludedClasses, "excludedClasses must not be null");
		this.excludedClasses = excludedClasses;
	}
}
