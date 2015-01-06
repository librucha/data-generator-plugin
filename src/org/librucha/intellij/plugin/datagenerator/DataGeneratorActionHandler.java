package org.librucha.intellij.plugin.datagenerator;

import com.intellij.lang.LanguageCodeInsightActionHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

public class DataGeneratorActionHandler implements LanguageCodeInsightActionHandler {

	@Override
	public boolean isValidFor(Editor editor, PsiFile psiFile) {
		return true;
	}

	@Override
	public void invoke(Project project, Editor editor, PsiFile psiFile) {
		System.out.println("invoke");
	}

	@Override
	public boolean startInWriteAction() {
		System.out.println("startInWriteAction");
		return false;
	}
}
