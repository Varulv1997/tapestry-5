// Copyright 2007, 2008, 2013, 2014 The Apache Software Foundation
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

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.commons.services.TypeCoercer;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFieldDemo
{
    @Persist
    @Property
    @Validate("required")
    private Date birthday;

    @Persist
    @Property
    @Validate("required")
    private Date asteroidImpact;

    @Persist
    @Property
    private Date lenient;

    @Inject
    private PersistentLocale persistentLocale;

    @Inject
    private TypeCoercer typeCoercer;
    
    public DateFormat getDateFormat()
    {
        return new SimpleDateFormat("MM/dd/yyyy");
    }


    void onActionFromClear()
    {
        birthday = null;
        asteroidImpact = null;
    }

    void onActionFromEnglish()
    {
        persistentLocale.set(Locale.ENGLISH);
    }

    void onActionFromFrench()
    {
        persistentLocale.set(Locale.FRENCH);
    }
    
    public boolean isCoercedStringToDateFormatLenient()
    {
        return typeCoercer.coerce("dd/MM/yyyy", java.text.DateFormat.class).isLenient();
    }
}
