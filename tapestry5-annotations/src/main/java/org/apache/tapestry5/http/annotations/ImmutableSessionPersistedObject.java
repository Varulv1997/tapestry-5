// Copyright 2008, 2009 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.apache.tapestry5.http.annotations;

import java.lang.annotation.*;

import static org.apache.tapestry5.ioc.annotations.AnnotationUseContext.BEAN;
import org.apache.tapestry5.ioc.annotations.UseWith;

/**
 * Marker annotation that can be placed on a session-persisted object to indicate that the object is immutable, and
 * therefore does not require end-of-request restoring into the session.
 *
 * @see org.apache.tapestry5.http.OptimizedSessionPersistedObject
 * @since 5.1.1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@UseWith(BEAN)
public @interface ImmutableSessionPersistedObject
{
}
