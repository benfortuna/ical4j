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

import java.io.IOException;

import junit.framework.TestSuite;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyTest;
import net.fortuna.ical4j.util.Calendars;

/**
 * $Id$
 *
 * Created on 20/02/2006
 *
 * Unit tests for Summary property.
 * @author Ben Fortuna
 */
public class SummaryTest extends PropertyTest {

    /**
	 * @param property
	 * @param expectedValue
	 */
	public SummaryTest(Summary property, String expectedValue) {
		super(property, expectedValue);
	}

	/**
	 * @param testMethod
	 * @param property
	 */
	public SummaryTest(String testMethod, Summary property) {
		super(testMethod, property);
	}
    
    /**
     * @return
     * @throws ParserException 
     * @throws IOException 
     */
    public static TestSuite suite() throws IOException, ParserException {
    	TestSuite suite = new TestSuite();
    	// Test correct parsing of quoted text..
        Calendar calendar = Calendars.load("etc/samples/valid/mansour.ics");
        Component event = calendar.getComponent(Component.VEVENT);
        Summary summary = (Summary) event.getProperty(Property.SUMMARY);
        suite.addTest(new SummaryTest(summary, "A colon with spaces on either side : like that"));
        
        suite.addTest(new SummaryTest("testValidation", summary));
        suite.addTest(new SummaryTest("testEquals", summary));
    	return suite;
    }
}
