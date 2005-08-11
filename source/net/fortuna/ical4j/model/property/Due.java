/*
 * $Id$
 * 
 * Created: [Apr 6, 2004]
 *
 * Copyright (c) 2004, Ben Fortuna
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
 * A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.model.property;

import java.text.ParseException;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.util.ParameterValidator;
import net.fortuna.ical4j.util.StringUtils;

/**
 * Defines a DUE iCalendar component property.
 * 
 * <pre>
 * 4.8.2.3 Date/Time Due
 * 
 *    Property Name: DUE
 * 
 *    Purpose: This property defines the date and time that a to-do is
 *    expected to be completed.
 * 
 *    Value Type: The default value type is DATE-TIME. The value type can
 *    be set to a DATE value type.
 * 
 *    Property Parameters: Non-standard, value data type, time zone
 *    identifier property parameters can be specified on this property.
 * 
 *    Conformance: The property can be specified once in a "VTODO" calendar
 *    component.
 * 
 *    Description: The value MUST be a date/time equal to or after the
 *    DTSTART value, if specified.
 * 
 *    Format Definition: The property is defined by the following notation:
 * 
 *      due        = "DUE" dueparam":" dueval CRLF
 * 
 *      dueparam   = *(
 *                 ; the following are optional,
 *                 ; but MUST NOT occur more than once
 * 
 *                 (";" "VALUE" "=" ("DATE-TIME" / "DATE")) /
 *                 (";" tzidparam) /
 * 
 *                 ; the following is optional,
 *                 ; and MAY occur more than once
 * 
 *                   *(";" xparam)
 * 
 *                 )
 * 
 * 
 * 
 *      dueval     = date-time / date
 *      ;Value MUST match value type
 * </pre>
 *
 * @author Ben Fortuna
 */
public class Due extends DateProperty {
    
    private static final long serialVersionUID = -2965312347832730406L;

    private Date time;

    /**
     * Default constructor. The time value is initialised to
     * the time of instantiation.
     */
    public Due() {
        super(DUE);
        // defaults to UTC time..
        time = new DateTime(true);
    }
    
    /**
     * @param aList
     *            a list of parameters for this component
     * @param aValue
     *            a value string for this component
     * @throws ParseException
     *             when the specified string is not a valid date/date-time
     *             representation
     */
    public Due(final ParameterList aList, final String aValue)
            throws ParseException {
        super(DUE, aList);
        setValue(aValue);
    }

    /**
     * Constructor. Date or Date-Time format is determined based
     * on the presence of a VALUE parameter.
     * @param aDate
     *            a date
     */
    public Due(final Date aDate) {
        super(DUE);
        time = aDate;
    }

    /**
     * Constructor. Date or Date-Time format is determined based
     * on the presence of a VALUE parameter.
     * @param aList
     *            a list of parameters for this component
     * @param aDate
     *            a date
     */
    public Due(final ParameterList aList, final Date aDate) {
        super(DUE, aList);
        time = aDate;
    }

    /**
     * @see net.fortuna.ical4j.model.Property#validate()
     */
    public final void validate() throws ValidationException {

        /*
         * ; the following are optional, ; but MUST NOT occur more than once
         *
         * (";" "VALUE" "=" ("DATE-TIME" / "DATE")) / (";" tzidparam) /
         */
        ParameterValidator.getInstance().validateOneOrLess(Parameter.VALUE,
                getParameters());

        Parameter valueParam = getParameters().getParameter(Parameter.VALUE);

        if (valueParam != null
                && !Value.DATE_TIME.equals(valueParam)
                && !Value.DATE.equals(valueParam)) {
            throw new ValidationException(
                "Parameter [" + Parameter.VALUE + "] is invalid");
        }

        if (isUtc()) {
            ParameterValidator.getInstance().validateNone(Parameter.TZID,
                    getParameters());
            
        }
        else {
            ParameterValidator.getInstance().validateOneOrLess(Parameter.TZID,
                    getParameters());
        }

        /*
         *  ; the following is optional, ; and MAY occur more than once
         *
         * (";" xparam)
         */
    }

    /**
     * @return Returns the time.
     */
    public final Date getTime() {
        return time;
    }
    
    
    /* (non-Javadoc)
     * @see net.fortuna.ical4j.model.Property#setValue(java.lang.String)
     */
    public final void setValue(final String aValue) throws ParseException {

        // value can be either a date-time or a date..
        if (Value.DATE.equals(getParameters().getParameter(Parameter.VALUE))) {
            time = new Date(aValue);
        }
        else {
            time = new DateTime(aValue);
        }
    }

    /* (non-Javadoc)
     * @see net.fortuna.ical4j.model.Property#getValue()
     */
    public final String getValue() {
        return StringUtils.valueOf(getTime());
    }
    
    /**
     * @param time The time to set.
     */
    public final void setTime(final Date time) {
        this.time = time;
    }
}
