package org.concordion.plugin.idea.action;

import org.concordion.plugin.idea.ConcordionNavigationService;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

public class NavigateBetweenConcordionSpecFiles extends EditorAction {

    public NavigateBetweenConcordionSpecFiles() {
        super(new NavigateBetweenConcordionSpecFilesHandler());
    }

    private static final class NavigateBetweenConcordionSpecFilesHandler extends EditorActionHandler {
        @Override
        protected void doExecute(Editor editor, Caret caret, DataContext dataContext) {

            VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(editor.getDocument());
            if (virtualFile == null || editor.getProject() == null) {
                return;
            }
            PsiFile file = PsiManager.getInstance(editor.getProject()).findFile(virtualFile);
            PsiFile correspondingFile = ConcordionNavigationService.getInstance(editor.getProject()).correspondingSpecFile(file);
            if (correspondingFile != null) {
                correspondingFile.navigate(true);
            }
        }
    }
}