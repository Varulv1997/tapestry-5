// Copyright 2006, 2007 The Apache Software Foundation
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

package org.apache.tapestry.ioc.internal.services;

import org.apache.commons.logging.Log;
import org.apache.tapestry.ioc.services.LoggingDecorator;
import org.apache.tapestry.ioc.test.IOCTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXParseException;

/**
 * Use the LoggingDecorator in a number of ways to verify its behavior. In some ways we are testing
 * the code dynamically generated by the LoggingDecorator as much as we are testing the decorator
 * itself -- one proves the other.
 */
public class LoggingDecoratorImplTest extends IOCTestCase
{
    public interface UpcaseService
    {
        String upcase(String input);
    }

    public interface AdderService
    {
        long add(long operand1, long operand2);
    }

    public interface ToStringService
    {
        String toString();
    }

    public interface ExceptionService
    {
        void parse() throws SAXParseException;
    }

    @Test
    public void void_method()
    {
        Log log = mockLog();
        Runnable delegate = mockRunnable();

        train_isDebugEnabled(log, true);
        log.debug("[ENTER] run()");

        delegate.run();

        log.debug("[ EXIT] run");

        replay();

        LoggingDecorator ld = newLoggingDecorator();
        Runnable interceptor = ld.build(Runnable.class, delegate, "foo.Bar", log);

        interceptor.run();

        assertEquals(
                interceptor.toString(),
                "<Logging interceptor for foo.Bar(java.lang.Runnable)>");

        verify();
    }

    private LoggingDecoratorImpl newLoggingDecorator()
    {
        return new LoggingDecoratorImpl(new ClassFactoryImpl(), new ExceptionTrackerImpl());
    }

    @Test
    public void method_throws_runtime_exception()
    {
        Throwable t = new RuntimeException("From delegate.");
        Log log = mockLog();
        Runnable delegate = mockRunnable();

        train_isDebugEnabled(log, true);
        log.debug("[ENTER] run()");

        delegate.run();
        setThrowable(t);

        train_isDebugEnabled(log, true);

        log.debug("[ FAIL] run -- " + t.getClass().getName(), t);

        replay();

        LoggingDecorator ld = newLoggingDecorator();
        Runnable interceptor = ld.build(Runnable.class, delegate, "foo.Bar", log);

        try
        {
            interceptor.run();
            unreachable();
        }
        catch (RuntimeException ex)
        {
            Assert.assertSame(ex, t);
        }

        verify();
    }

    @Test
    public void method_throws_checked_exception() throws Exception
    {
        Throwable t = new SAXParseException("From delegate.", null);
        Log log = mockLog();
        ExceptionService delegate = newMock(ExceptionService.class);

        train_isDebugEnabled(log, true);
        log.debug("[ENTER] parse()");

        delegate.parse();
        setThrowable(t);

        train_isDebugEnabled(log, true);

        log.debug("[ FAIL] parse -- " + t.getClass().getName(), t);

        replay();

        LoggingDecorator ld = newLoggingDecorator();
        ExceptionService interceptor = ld.build(ExceptionService.class, delegate, "foo.Bar", log);

        try
        {
            interceptor.parse();
            unreachable();
        }
        catch (SAXParseException ex)
        {
            Assert.assertSame(ex, t);
        }

        verify();
    }

    @Test
    public void object_parameter_and_return_type()
    {
        Log log = mockLog();
        UpcaseService delegate = new UpcaseService()
        {
            public String upcase(String input)
            {
                return input.toUpperCase();
            }
        };

        train_isDebugEnabled(log, true);
        log.debug("[ENTER] upcase(\"barney\")");

        log.debug("[ EXIT] upcase [\"BARNEY\"]");

        replay();

        LoggingDecorator ld = newLoggingDecorator();
        UpcaseService interceptor = ld.build(UpcaseService.class, delegate, "foo.Bar", log);

        assertEquals(interceptor.upcase("barney"), "BARNEY");

        verify();
    }

    @Test
    public void primitive_parameter_and_return_type()
    {
        Log log = mockLog();
        AdderService delegate = new AdderService()
        {
            public long add(long operand1, long operand2)
            {
                return operand1 + operand2;
            }
        };

        train_isDebugEnabled(log, true);
        log.debug("[ENTER] add(6, 13)");

        log.debug("[ EXIT] add [19]");

        replay();

        LoggingDecorator ld = newLoggingDecorator();
        AdderService interceptor = ld.build(AdderService.class, delegate, "foo.Bar", log);

        assertEquals(interceptor.add(6, 13), 19);

        verify();
    }

    @Test
    public void to_string_method_in_service_interface_is_delegated()
    {
        Log log = mockLog();
        ToStringService delegate = new ToStringService()
        {
            @Override
            public String toString()
            {
                return "FROM DELEGATE";
            }
        };

        train_isDebugEnabled(log, true);
        log.debug("[ENTER] toString()");

        log.debug("[ EXIT] toString [\"FROM DELEGATE\"]");

        replay();

        LoggingDecorator ld = newLoggingDecorator();
        ToStringService interceptor = ld.build(ToStringService.class, delegate, "foo.Bar", log);

        assertEquals(interceptor.toString(), "FROM DELEGATE");

        verify();
    }
}
