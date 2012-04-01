// import java.util.TreeMap;
/*********************************************************************
 * Class to handle a single event.
 * It seems to be the case that doing this as a real class takes too
 * much time and memory, so we are implementing this as static methods
 * to return and extract from a single string variable.
 *
 * @author Duncan A. Buell
 * Copyright (c) 2010-2012 Duncan A. Buell
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
**/
public class OneEvent
{
/*********************************************************************
 * Instance variables for the class.
**/
//  static private final String TAG = "OneEvent: "; // for testing

/*********************************************************************
 * Accessors and mutators.
**/
/*********************************************************************
 * Accessor for <code>code</code>.
 *
 * @return the value of <code>code</code>
**/
  static public String getCode(String eventString)
  {
    int START = 44;
    int END = 51;
    return eventString.substring(START, END);
  } // public String getCode()

/*********************************************************************
 * Accessor for <code>date</code>.
 *
 * @return the value of <code>date</code>
**/
  static public String getDate(String eventString)
  {
    int START = 22;
    int END = 32;
    return eventString.substring(START, END);
  } // public String getDate()

/*********************************************************************
 * Accessor for <code>date</code> and <code>time</code> together.
 *
 * @return the value of <code>date</code> concat with <code>time</code>
**/
  static public String getDateAndTime(String eventString)
  {
    int START = 22;
    int END = 41;
    return eventString.substring(START, END);
  } // static public String getDateAndTime(String eventString)

/*********************************************************************
 * Accessor for <code>ivoNumber</code>.
 *
 * @return the value of <code>ivoNumber</code>
**/
  static public String getIvoNumber(String eventString)
  {
    int START = 0;
    int END = 7;
    return eventString.substring(START, END);
  } // public String getIvoNumber()

/*********************************************************************
 * Accessor for <code>pebNumber</code>.
 *
 * @return the value of <code>pebNumber</code>
**/
  static public String getPebNumber(String eventString)
  {
    int START = 8;
    int END = 15;
    return eventString.substring(START, END);
  } // public String getPebNumber()

/*********************************************************************
 * Accessor for <code>time</code>.
 *
 * @return the value of <code>time</code>
**/
  static public String getTime(String eventString)
  {
    int START = 33;
    int END = 41;
    return eventString.substring(START, END);
  } // public String getTime()

/*********************************************************************
 * Accessor for <code>type</code>.
 *
 * @return the value of <code>type</code>
**/
  static public String getType(String eventString)
  {
    int START = 16;
    int END = 19;
//    FileUtils.logFile.printf("TYPE X%sX%n",
//                      eventString.substring(START, END));
    return eventString.substring(START, END);
  } // public String getType()

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to make an event string from a 152 line.
**/
  static public String makeEventString(String ivo, String peb, String line)
  {
    String ivoNumber;
    String pebNumber;
    String code;
    String date;
    String time;
    String type;
    String year, month, day;
    String hour, minute, second;

    String s;

    ivoNumber = line.substring( 0, 7);
    if(ivoNumber.equals("       "))
    {
      ivoNumber = ivo;
    }

    pebNumber = line.substring( 8,15);
    if(pebNumber.trim().equals(""))
    {
      pebNumber = peb;
    }

    type      = line.substring(17,20);
    date      = line.substring(23,33);
    time      = line.substring(34,42);
    code      = line.substring(45,52);

    year = date.substring(6);
    month = date.substring(0, 2);
    day = date.substring(3, 5);
    if((Integer.valueOf(month.trim()) < 0) || (Integer.valueOf(month.trim()) > 12))
    {
      FileUtils.logFile.printf("BAD MONTH %s: %s%n", month, line);
      FileUtils.logFile.flush();
    }
    if((Integer.valueOf(day.trim()) < 0) || (Integer.valueOf(day.trim()) > 31))
    {
      FileUtils.logFile.printf("BAD DAY %s: %s%n", day, line);
      FileUtils.logFile.flush();
    }

    hour = time.substring(0, 2);
    minute = time.substring(3, 5);
    second = time.substring(6, 8);

    s = String.format("%7s %7s %3s   %4s_%2s_%2s %2s:%2s:%2s   %7s",
                      ivoNumber, pebNumber, type,
                      year, month, day,
                      hour, minute, second, code);
//    FileUtils.logFile.printf("ZORK X%sX X%sX X%sX X%sX X%sX X%sX X%sX X%sX X%sX X%sX%n",
//                      ivoNumber, pebNumber, type,
//                      year, month, day,
//                      hour, minute, second, code);

    return s;

  } // static public makeEventString(String ivo, String peb, String line)

} // public class Event
