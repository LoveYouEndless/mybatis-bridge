package io.github.mybatisbridge.navigation;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTokenType;

import java.util.Collection;

public class XmlMapperLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    public void collectNavigationMarkers(PsiElement element, Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (!(element.getParent() instanceof XmlTag) || element.getNode().getElementType() != XmlTokenType.XML_NAME) {
            return;
        }
        XmlTag tag = (XmlTag) element.getParent();
        XmlTag mapper = "mapper".equals(tag.getLocalName()) ? tag : tag.getParentTag();
        if (mapper == null || !"mapper".equals(mapper.getLocalName())) {
            return;
        }
        String namespace = mapper.getAttributeValue("namespace");
        if (namespace == null) {
            return;
        }
        PsiClass mapperClass = JavaPsiFacade.getInstance(element.getProject())
                .findClass(namespace, GlobalSearchScope.projectScope(element.getProject()));
        if (mapperClass == null) {
            return;
        }
        if (tag == mapper) {
            result.add(NavigationGutterIconBuilder.create(MyBatisIcons.MAPPER_BRIDGE)
                    .setTarget(mapperClass)
                    .setTooltipText("Navigate to MyBatis mapper interface")
                    .createLineMarkerInfo(element));
            return;
        }
        String statementId = tag.getAttributeValue("id");
        if (statementId == null) {
            return;
        }
        PsiMethod[] methods = mapperClass.findMethodsByName(statementId, false);
        if (methods.length > 0) {
            result.add(NavigationGutterIconBuilder.create(MyBatisIcons.MAPPER_BRIDGE)
                    .setTargets(methods)
                    .setTooltipText("Navigate to MyBatis mapper method")
                    .createLineMarkerInfo(element));
        }
    }
}
