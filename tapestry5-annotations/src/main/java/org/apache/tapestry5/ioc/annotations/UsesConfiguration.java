//  Copyright 2008, 2009 The Apache Software Foundation
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

package org.apache.tapestry5.ioc.annotations;

import java.lang.annotation.*;


/**
 * A documentation-only interface placed on service interfaces for services which have an 
 * <a href="https://tapestry.apache.org/current/apidocs/org/apache/tapestry5/commons/Configuration.html">unordered configuration</a>, to identify the type of contribution.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
@UseWith(AnnotationUseContext.SERVICE)
public @interface UsesConfiguration
{
    /**
     * The type of object which may be contributed into the service's configuration.
     */
    Class value();
}
