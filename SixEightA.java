import java.util.ArrayList;
import java.util.Scanner;
/*********************************************************************
 * Class for handling the 68A system log file.
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
public class SixEightA
{
/*********************************************************************
 * Instance variables.
**/
  static private final String TAG = "SixEightA: "; // for testing

  ArrayList<String> context;

/*********************************************************************
 * Constructor.
**/
  public SixEightA()
  {
    context = new ArrayList<String>();
  } // public SixEightA()

/*********************************************************************
 * Accessors and mutators.
**/

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to check for nonincreasing timestamps in the 68A file.
 *
 * @param nextLine the next line in the system log
**/
  public void checkTimestamps()
  {
    FileUtils.logFile.printf("%s enter checkTimeStamps%n", TAG);
    FileUtils.logFile.flush();


    for(int i = 0; i < context.size()-1; ++i)
    {
      if(!timeIncreases(context.get(i), context.get(i+1)))
      {
        for(int j = Math.max(0, i-5); j <= Math.min(context.size()-1, i+5); ++j)
          FileUtils.logFile.printf("%s%n", context.get(j));
        FileUtils.logFile.printf("%n");
        FileUtils.logFile.flush();
      }
    }

    FileUtils.logFile.printf("%s leave checkTimeStamps%n", TAG);
    FileUtils.logFile.flush();
  } // public void checkTimestamps()

/*********************************************************************
 * Method to parse a system log line for the terminal number of the
 * audit data that has been retrieved.
 *
 * @param line the system log line to parse
**/
  public void parseAuditData(String line)
  {
    String dateAndTime;
    String ivoNumber;

//    FileUtils.logFile.printf("%s enter parseAuditData%n", TAG);
//    FileUtils.logFile.flush();

    line = line.trim();

    dateAndTime = line.substring(0, 15);
    ivoNumber = line.substring(line.length()-7);

//    FileUtils.logFile.printf("%s parsed X%sX X%sX%n", TAG,
//                             dateAndTime, ivoNumber);
//    FileUtils.logFile.flush();

    OneTerminal term = Globals.theTerms.get(ivoNumber);

    if(null == term)
    {
      term = new OneTerminal(ivoNumber);
    }
    term.setMemoryCollectionTime(dateAndTime);
    Globals.theTerms.put(ivoNumber, term);

//    FileUtils.logFile.printf("%s leave parseAuditData%n", TAG);
//    FileUtils.logFile.flush();
  } // public void parseAuditData(String line)

/*********************************************************************
 * Method to parse a system log line for the PEB number of the
 * vote totals that have been retrieved.
 *
 * @param line the system log line to parse
**/
  public void parsePEBRetrieval(String line)
  {
    String dateAndTime;
    String pebNumber;

//    FileUtils.logFile.printf("%s enter parsePEBRetrieval%n", TAG);
//    FileUtils.logFile.flush();

    line = line.trim();

    dateAndTime = line.substring(0, 15);

    // the only thing delicate here is that PEB numbers ought to be
    // six digits but we see a few that are seven so we have to be
    // careful
    pebNumber = line.substring(line.length()-7);
    if(pebNumber.substring(0,1).equals("0"))
      pebNumber = pebNumber.substring(1);
    pebNumber = String.format("%7s", pebNumber);

//    FileUtils.logFile.printf("%s number X%sX%n", TAG, pebNumber);
//    FileUtils.logFile.flush();

    OnePEB peb = Globals.thePEBs.get(pebNumber);
    if(null == peb)
    {
      peb = new OnePEB(pebNumber);
    }
    peb.setVoteCollectionTime(dateAndTime);
    peb.setFoundInSystemLog(true);
    Globals.thePEBs.put(pebNumber, peb);

//    FileUtils.logFile.printf("%s peb X%sX%n", TAG,
//                             Globals.thePEBs.get(pebNumber).toString());
//    FileUtils.logFile.flush();

//    FileUtils.logFile.printf("%s leave parsePEBRetrieval%n", TAG);
//    FileUtils.logFile.flush();
  } // public void parsePEBRetrieval(String line)

/*********************************************************************
 * Method to read the 68A file from an input <code>Scanner</code> file
 * and get the uploads for the PEBs and the memory cards.
 *
 * @param inFile the file name prefix from which to read.
**/
  public boolean read68A(String fileNamePrefix)
  {
    String logString = "";

    Scanner inFile = null;
    inFile = Globals.OpenInputFile(fileNamePrefix, "_68A");
    if(null == inFile)
    {
FileUtils.logFile.printf("%s FILE '%s' NOT FOUND%n", TAG, fileNamePrefix+"_68A");
FileUtils.logFile.flush();
      return false;
    }

    while(inFile.hasNext())
    {
      logString = inFile.nextLine();

      if(logString.indexOf("Audit Data collected for") >= 0)
      {
//        FileUtils.logFile.printf("%s CF DATA:  %s%n", TAG, logString.trim());
//        FileUtils.logFile.flush();
        this.parseAuditData(logString);
      }

      if(logString.indexOf("PEB votes retrieved for") >= 0)
      {
//        FileUtils.logFile.printf("%s PEB DATA: %s%n", TAG, logString.trim());
//        FileUtils.logFile.flush();
        this.parsePEBRetrieval(logString);
      }

      // check for nonincreasing timestamps
      if(logString.length() < 19)
      {
        continue;
      }

      if(!logString.substring(0,4).equals("    "))
      {
        continue;
      }

      if(logString.substring(4,6).equals("  "))
      {
        continue;
      }
      context.add(logString.trim());

    } // while(inFile.hasNext())
    
    checkTimestamps();

    FileUtils.CloseFile(inFile);
    return true;
  } // public boolean read68A(Scanner inFile)

/*********************************************************************
 * Method to check that a new input line is a time later than an old
 * input line.
 *
 * @param lineOne the first line
 * @param lineTwo the second line
**/
  public boolean timeIncreases(String lineOne, String lineTwo)
  {
    String month1, month2;
    String day1, day2;
    String hour1, hour2;
    String minute1, minute2;
    String ampm1, ampm2;

    int timeOfDay1, timeOfDay2;

    month1 = lineOne.substring(0,2);
    day1 = lineOne.substring(3,5);
    hour1 = lineOne.substring(7,9);
    minute1 = lineOne.substring(10,12);
    ampm1 = lineOne.substring(13,15);

    month2 = lineTwo.substring(0,2);
    day2 = lineTwo.substring(3,5);
    hour2 = lineTwo.substring(7,9);
    minute2 = lineTwo.substring(10,12);
    ampm2 = lineTwo.substring(13,15);

//    FileUtils.logFile.printf("%s '%s' '%s' '%s' '%s' '%s'%n",
//               TAG, month1, day1, hour1, minute1, ampm1);
//    FileUtils.logFile.printf("%s '%s' '%s' '%s' '%s' '%s'%n",
//               TAG, month2, day2, hour2, minute2, ampm2);
//    FileUtils.logFile.flush();

    if(month1.compareTo(month2) < 0)
    {
      return true;
    }

    if(month1.compareTo(month2) > 0)
    {
      if(!month2.equals("01"))
      {
        return false;
      }
      else
      {
        return true;
      }
    }

    if(day1.compareTo(day2) < 0)
    {
      return true;
    }

    if(day1.compareTo(day2) > 0)
    {
      return false;
    }

    timeOfDay1 = Integer.valueOf(hour1) * 60;
    if((ampm1.equals("pm")) && (timeOfDay1 != 720))
      timeOfDay1 += 720;
    if((ampm1.equals("am")) && (timeOfDay1 == 720))
      timeOfDay1 -= 720;
    timeOfDay1 += Integer.valueOf(minute1);
    
    timeOfDay2 = Integer.valueOf(hour2) * 60;
    if((ampm2.equals("pm")) && (timeOfDay2 != 720))
      timeOfDay2 += 720;
    if((ampm2.equals("am")) && (timeOfDay2 == 720))
      timeOfDay2 -= 720;
    timeOfDay2 += Integer.valueOf(minute2);
    

    if(timeOfDay1 <= timeOfDay2)
    {
      return true;
    }

    return false;
  } // public boolean timeIncreases(String lineOne, String lineTwo)

} // public class SixEightA
