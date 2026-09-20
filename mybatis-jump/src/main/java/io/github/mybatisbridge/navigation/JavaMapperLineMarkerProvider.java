package io.github.mybatisbridge.navigation;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.xml.XmlTag;

import java.util.Collection;
import java.util.List;

public class JavaMapperLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    public void collectNavigationMarkers(PsiElement element, Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (element.getParent() instanceof PsiClass && element.equals(((PsiClass) element.getParent()).getNameIdentifier())) {
            PsiClass mapperClass = (PsiClass) element.getParent();
            String namespace = mapperClass.getQualifiedName();
            if (namespace != null) {
                List<XmlTag> targets = MapperLookup.mapperTags(element.getProject(), namespace);
                if (!targets.isEmpty()) {
                    result.add(NavigationGutterIconBuilder.create(MyBatisIcons.MAPPER_BRIDGE)
                            .setTargets(targets)
                            .setTooltipText("Navigate to MyBatis XML mapper")
                            .createLineMarkerInfo(element));
                }
            }
            return;
        }
        if (!(element.getParent() instanceof PsiMethod) || !element.equals(((PsiMethod) element.getParent()).getNameIdentifier())) {
            return;
        }
        List<XmlTag> targets = MapperLookup.statementTags((PsiMethod) element.getParent());
        if (!targets.isEmpty()) {
            result.add(NavigationGutterIconBuilder.create(MyBatisIcons.MAPPER_BRIDGE)
                    .setTargets(targets)
                    .setTooltipText("Navigate to MyBatis XML statement")
                    .createLineMarkerInfo(element));
        }
    }
}