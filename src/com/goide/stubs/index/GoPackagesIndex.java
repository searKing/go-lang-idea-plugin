/*
 * Copyright 2013-2014 Sergey Ignatov, Alexander Zolotov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goide.stubs.index;

import com.goide.GoFileElementType;
import com.goide.psi.GoFile;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.stubs.StringStubIndexExtension;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.psi.stubs.StubIndexKey;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class GoPackagesIndex extends StringStubIndexExtension<GoFile> {
  public static final StubIndexKey<String, GoFile> KEY = StubIndexKey.createIndexKey("go.packages");

  @Override
  public int getVersion() {
    return GoFileElementType.VERSION + 2;
  }

  @NotNull
  @Override
  public StubIndexKey<String, GoFile> getKey() {
    return KEY;
  }

  public static Collection<String> getAllPackages(@NotNull final Project project) {
    return ApplicationManager.getApplication().runReadAction(new Computable<Collection<String>>() {
      @NotNull
      public Collection<String> compute() {
        return StubIndex.getInstance().getAllKeys(KEY, project);
      }
    });
  }
}
