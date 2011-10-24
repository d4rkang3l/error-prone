/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.errorprone.matchers;

import com.google.errorprone.VisitorState;
import com.sun.source.tree.Tree;

/**
 * Wraps another matcher and holds the reference to the matched AST node if it matches.
 * @author alexeagle@google.com (Alex Eagle)
 */
public class CapturingMatcher<T extends Tree> implements Matcher<T> {
  private final Matcher<Tree> matcher;
  private final TreeHolder<T> holder;

  public CapturingMatcher(Matcher<Tree> matcher, TreeHolder<T> holder) {
    this.matcher = matcher;
    this.holder = holder;
  }

  @Override public boolean matches(T item, VisitorState state) {
    boolean matches = matcher.matches(item, state);
    if (matches) {
      holder.set(item);
    }
    return matches;
  }

}
