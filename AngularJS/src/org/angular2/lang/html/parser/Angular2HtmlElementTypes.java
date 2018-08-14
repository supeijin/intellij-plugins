// Copyright 2000-2018 JetBrains s.r.o.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package org.angular2.lang.html.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.javascript.types.JSFileElementType;
import com.intellij.psi.tree.ICompositeElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.xml.XmlElementType;
import org.angular2.lang.html.Angular2HtmlLanguage;
import org.angular2.lang.html.lexer.Angular2HtmlTokenTypes;
import org.angular2.lang.html.psi.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface Angular2HtmlElementTypes extends XmlElementType, Angular2HtmlTokenTypes {

  IFileElementType FILE = JSFileElementType.create(Angular2HtmlLanguage.INSTANCE);

  IElementType EXPANSION_FORM = new Angular2ElementType("NG:EXPANSION_FORM", Angular2HtmlExpansionFormImpl::new);
  IElementType EXPANSION_FORM_CASE = new Angular2ElementType("NG:EXPANSION_FORM_CASE", Angular2HtmlExpansionFormCaseImpl::new);
  IElementType EXPANSION_FORM_CASE_CONTENT = new Angular2ElementType("NG:EXPANSION_FORM_CASE_CONTENT", Angular2HtmlExpansionFormCaseContentImpl::new);

  IElementType ANIMATION = new Angular2ElementType("NG:ANIMATION", Angular2HtmlAnimationImpl::new);
  IElementType ANIMATION_EVENT = new Angular2ElementType("NG:ANIMATION_EVENT", Angular2HtmlAnimationEventImpl::new);
  IElementType EVENT = new Angular2ElementType("NG:EVENT", Angular2HtmlEventImpl::new);
  IElementType PROPERTY_BINDING = new Angular2ElementType("NG:PROPERTY_BINDING", Angular2HtmlPropertyBindingImpl::new);
  IElementType REFERENCE = new Angular2ElementType("NG:REFERENCE", Angular2HtmlReferenceImpl::new);
  IElementType VARIABLE = new Angular2ElementType("NG:VARIABLE", Angular2HtmlVariableImpl::new);
  IElementType TEMPLATE_BINDINGS = new Angular2ElementType("NG:TEMPLATE_BINDINGS", Angular2HtmlTemplateBindingsImpl::new);

  class Angular2ElementType extends IElementType implements ICompositeElementType {

    private final Function<Angular2ElementType, ASTNode> myClassConstructor;

    public Angular2ElementType(@NotNull String debugName, Function<Angular2ElementType, ASTNode> classConstructor) {
      super(debugName, Angular2HtmlLanguage.INSTANCE);
      myClassConstructor = classConstructor;
    }

    @NotNull
    @Override
    public ASTNode createCompositeNode() {
      return myClassConstructor.apply(this);
    }
  }

}