package io.github.mybatisbridge.navigation;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class MapperLookup {
    private MapperLookup() {
    }

    static List<XmlTag> statementTags(PsiMethod method) {
        if (method.getContainingClass() == null || method.getContainingClass().getQualifiedName() == null) {
            return Collections.emptyList();
        }
        List<XmlTag> result = new ArrayList<XmlTag>();
        for (XmlTag mapper : mapperTags(method.getProject(), method.getContainingClass().getQualifiedName())) {
            for (XmlTag statement : mapper.getSubTags()) {
                if (method.getName().equals(statement.getAttributeValue("id"))) {
                    result.add(statement);
                }
            }
        }
        return result;
    }

    static List<XmlTag> mapperTags(Project project, String namespace) {
        List<XmlTag> result = new ArrayList<XmlTag>();
        // ponytail: scans project XML on navigation; add a file-based index only if profiling shows this is slow.
        for (VirtualFile file : FilenameIndex.getAllFilesByExt(project, "xml", GlobalSearchScope.projectScope(project))) {
            if (PsiManager.getInstance(project).findFile(file) instanceof XmlFile) {
                XmlTag root = ((XmlFile) PsiManager.getInstance(project).findFile(file)).getRootTag();
                if (root != null && "mapper".equals(root.getLocalName()) && namespace.equals(root.getAttributeValue("namespace"))) {
                    result.add(root);
                }
            }
        }
        return result;
    }
}
