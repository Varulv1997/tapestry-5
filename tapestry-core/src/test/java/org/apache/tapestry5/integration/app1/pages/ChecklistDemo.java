// Copyright 2011 The Apache Software Foundation
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

package org.apache.tapestry5.integration.app1.pages;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.util.CollectionFactory;
import org.apache.tapestry5.internal.services.StringValueEncoder;

import java.util.Collections;
import java.util.List;

public class ChecklistDemo
{
    @Property
    @Persist
    private List<String> selected;

    public ValueEncoder getEncoder()
    {
        return new StringValueEncoder();
    }

    public List<String> getSorted()
    {
        if(selected == null)
            return null;

        final List<String> result = CollectionFactory.newList(selected);

        Collections.sort(result);

        return result;
    }
}
