/**
 * Copyright (c) 2011, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.model.property;

import junit.framework.TestSuite;
import net.fortuna.ical4j.model.PropertyTest;

/**
 * Created: [19/11/2008]
 *
 * @author fortuna
 */
public class ActionTest extends PropertyTest {

    /**
     * @param property
     * @param expectedValue
     */
    public ActionTest(Action action, String expectedValue) {
        super(action, expectedValue);
    }

    /**
     * @param testMethod
     * @param property
     */
    public ActionTest(String testMethod, Action property) {
        super(testMethod, property);
    }

    /**
     * @return
     */
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new ActionTest(Action.AUDIO, "AUDIO"));
        suite.addTest(new ActionTest(Action.DISPLAY, "DISPLAY"));
        suite.addTest(new ActionTest(Action.EMAIL, "EMAIL"));
        suite.addTest(new ActionTest(Action.PROCEDURE, "PROCEDURE"));

        suite.addTest(new ActionTest("testEquals", Action.AUDIO));
        suite.addTest(new ActionTest("testEquals", Action.DISPLAY));
        suite.addTest(new ActionTest("testEquals", Action.EMAIL));
        suite.addTest(new ActionTest("testEquals", Action.PROCEDURE));

        suite.addTest(new ActionTest("testValidation", Action.AUDIO));
        suite.addTest(new ActionTest("testValidation", Action.DISPLAY));
        suite.addTest(new ActionTest("testValidation", Action.EMAIL));
        suite.addTest(new ActionTest("testValidation", Action.PROCEDURE));

        suite.addTest(new ActionTest("testImmutable", Action.AUDIO));
        suite.addTest(new ActionTest("testImmutable", Action.DISPLAY));
        suite.addTest(new ActionTest("testImmutable", Action.EMAIL));
        suite.addTest(new ActionTest("testImmutable", Action.PROCEDURE));
        return suite;
    }
}
