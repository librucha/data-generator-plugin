package org.librucha.intellij.plugin.datagenerator;

import com.google.gson.*;
import com.intellij.lang.LanguageCodeInsightActionHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.util.TypeConversionUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DataGeneratorActionHandler implements LanguageCodeInsightActionHandler {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public boolean isValidFor(Editor editor, PsiFile psiFile) {
		return psiFile instanceof PsiJavaFile && !PsiPackage.PACKAGE_INFO_FILE.equals(psiFile.getName());
	}

	@Override
	public void invoke(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile psiFile) {
		JsonObject jsonObject = constructJson(psiFile);
		System.out.println(GSON.toJson(jsonObject));
	}

	private JsonObject constructJson(PsiFile psiFile) {
		return processFields(psiFile);
	}

	private JsonObject processFields(PsiFile psiFile) {
		JsonObject jsonObject = new JsonObject();
		PsiField[] fields = getAllInstanceFields(psiFile);
		for (PsiField field : fields) {
			jsonObject.add(field.getName(), createFieldValue(field.getType()));
		}
		return jsonObject;
	}

	private JsonElement createFieldValue(PsiType fieldType) {
		if (String.class.getCanonicalName().equals(fieldType.getCanonicalText())) {
			return new JsonPrimitive("text");
		} else if (PsiType.CHAR.equals(fieldType) || Character.class.getCanonicalName().equals(fieldType.getCanonicalText())) {
			return new JsonPrimitive('c');
		} else if (TypeConversionUtil.isBooleanType(fieldType)) {
			return new JsonPrimitive(false);
		} else if (TypeConversionUtil.isNumericType(fieldType)) {
			return new JsonPrimitive(0);
		} else if (fieldType instanceof PsiArrayType) {
			JsonArray jsonArray = new JsonArray();
			JsonElement arrayItemValue = createFieldValue(((PsiArrayType) fieldType).getComponentType());
			for (int i = 0; i < 3; i++) {
				jsonArray.add(arrayItemValue);
			}
			return jsonArray;
		}
		if (fieldType instanceof PsiClassReferenceType) {
			PsiClassReferenceType referenceType = (PsiClassReferenceType) fieldType;
			PsiClass psiClass = referenceType.resolve();
			if (psiClass != null) {
				return processFields(psiClass.getContainingFile());
			}
		}
		return JsonNull.INSTANCE;
	}

	private PsiField[] getAllInstanceFields(PsiFile psiFile) {
		if (psiFile instanceof PsiJavaFile) {
			PsiClass[] classes = ((PsiJavaFile) psiFile).getClasses();
			if (classes.length > 0) {
				PsiField[] allFields = classes[0].getAllFields();
				List<PsiField> filteredFields = new ArrayList<>(allFields.length);
				for (PsiField field : allFields) {
					if (!field.hasModifierProperty(PsiModifier.STATIC) && !field.hasModifierProperty(PsiModifier.TRANSIENT)) {
						filteredFields.add(field);
					}
				}
				return filteredFields.toArray(new PsiField[filteredFields.size()]);
			}
		}
		return new PsiField[0];
	}

	@Override
	public boolean startInWriteAction() {
		return false;
	}
}
