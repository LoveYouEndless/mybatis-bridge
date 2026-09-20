package io.github.mybatisbridge.navigation;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;

public class MapperLookupTest extends BasePlatformTestCase {
    public void testFindsStatementForMapperMethod() {
        myFixture.addFileToProject("mapper/UserMapper.xml",
                "<mapper namespace=\"demo.UserMapper\"><select id=\"findById\"/></mapper>");
        PsiJavaFile javaFile = (PsiJavaFile) myFixture.addFileToProject("demo/UserMapper.java",
                "package demo; public interface UserMapper { Object findById(); }");
        PsiClass mapper = javaFile.getClasses()[0];
        PsiMethod method = mapper.findMethodsByName("findById", false)[0];

        assertEquals(1, MapperLookup.statementTags(method).size());
    }
}