package org.librucha.intellij.plugin.datagenerator;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.actions.BaseCodeInsightAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class DataGeneratorAction extends BaseCodeInsightAction {

	private final DataGeneratorActionHandler actionHandler = new DataGeneratorActionHandler();

	@NotNull
	@Override
	protected CodeInsightActionHandler getHandler() {
		return actionHandler;
	}

	@Override
	protected boolean isValidForFile(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile file) {
		return actionHandler.isValidFor(editor, file);
	}
}
