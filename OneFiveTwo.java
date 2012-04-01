// import java.io.PrintWriter;
// import java.lang.StringBuilder;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.Iterator;
import java.util.Scanner;
// import java.util.TreeMap;
/*********************************************************************
 * Class for handling ballot file parsing.
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
public class OneFiveTwo
{
/*********************************************************************
 * Instance variables.
**/
  static private final String TAG = "OneFiveTwo: "; // for testing

/*********************************************************************
 * Constructor.
**/
  public OneFiveTwo()
  {
  } // public OneFiveTwo()

/*********************************************************************
 * Accessors and mutators.
**/

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to add message text to the messages treemap if the line
 * is not already there, and to print error messages if we have
 * conflicting text.
**/
  public void addEventText(String code, String text)
  {
//    FileUtils.logFile.printf("%s enter addtext X%sX X%sX%n",
//                                  TAG, code, text);
//    FileUtils.logFile.flush();

    String storedMsg = Globals.eventTexts.get(code);
    if(null == storedMsg)
    {
      Globals.eventTexts.put(code, text);
    }
    else
    {
      if(!storedMsg.equals(text))
      {
        FileUtils.logFile.printf("%s ERROR IN MSG TEXT (stored,new) X%sX X%sX%n",
                                  TAG, storedMsg, text);
      }
    }
//    FileUtils.logFile.printf("%s leave addtext%n", TAG);
//    FileUtils.logFile.flush();
  } // public void addEventText(String code, String msg)

/*********************************************************************
 * Method to read the 152 file from an input <code>Scanner</code> file
 * and synchronize precinct names and numbers.
 *
 * @param inFile the <code>Scanner</code> from which to read.
**/
//  public void read152(Scanner inFile)
  public boolean read152(String fileNamePrefix)
  {
    String eventString = "";
    String eventMsgTextLine = "";

    String ivoNumber = "";
    String pebNumber = "";
    String line = "";

    Scanner inFile = null;
    inFile = Globals.OpenInputFile(fileNamePrefix, "_152");
    if(null == inFile)
    { 
      FileUtils.logFile.printf("%s FILE '%s' NOT FOUND%n", TAG,
                               fileNamePrefix+"152");
      FileUtils.logFile.flush();
      return false;
    }

    
// read and trash the first line
    inFile.nextLine();

    ivoNumber = Globals.DUMMYIVONUMBER;
    pebNumber = Globals.DUMMYPEBNUMBER;
    while(inFile.hasNextLine())
    {
      line = inFile.nextLine();

      if(line.length() < 20) continue;
      if(line.substring(0,8).equals("RUN DATE"))
      {
//        FileUtils.logFile.printf("%s ZORK152RUN DATEX%sX\n", TAG, line);
//        FileUtils.logFile.flush();
        continue;
      }

      if(line.substring(0,8).equals("Votronic"))
      {
//        FileUtils.logFile.printf("%s ZORK152VotronicX%sX\n", TAG, line);
//        FileUtils.logFile.flush();
        continue;
      }

      //
      eventString = OneEvent.makeEventString(ivoNumber, pebNumber, line);
//      FileUtils.logFile.printf("%s JUSTMADEX%sX\n", TAG, eventString);
//      FileUtils.logFile.flush();
//      FileUtils.logFile.printf("%s EXTRACT X%sX X%sX X%sX X%sX X%sX\n", TAG,
//                               OneEvent.getIvoNumber(eventString),
//                               OneEvent.getPebNumber(eventString),
//                               OneEvent.getType(eventString),
//                               OneEvent.getDateAndTime(eventString),
//                               OneEvent.getCode(eventString));
//      FileUtils.logFile.flush();

      if((OneEvent.getType(eventString).equals("SUP")) ||
         (OneEvent.getType(eventString).equals("UNK")))
      {
//        FileUtils.logFile.printf("%s GOOD X%sX\n", TAG, line.substring(0,20));
      }

//      FileUtils.logFile.printf("%s LINE X%sX%sX%sX%sX%sX%sX%sX\n", TAG,
//                               ivoLine, pebLine, typeLine, dateLine,
//                               timeLine, eventMsgCodeLine, eventMsgTextLine);

      if(!OneEvent.getIvoNumber(eventString).equals(ivoNumber))
      {
        ivoNumber = OneEvent.getIvoNumber(eventString);
//        FileUtils.logFile.printf("%s FRESHIVO X%sX\n", TAG, ivoNumber);
      }

      if(!OneEvent.getPebNumber(eventString).equals(pebNumber))
      {
        pebNumber = OneEvent.getPebNumber(eventString);
//        FileUtils.logFile.printf("%s FRESHPEB X%sX\n", TAG, pebNumber);
      }

      //**************************************************************
      // Add the message text to the stored message texts if it is new.
      // The one part of the input line we don't send to all events
      // is the text.  We extract it here and add to the global text
      // list if it's new.
      // GLITCH:  We don't have 0001510 messages from C'ton, so we test
      if(line.length() >= 53)
        eventMsgTextLine = line.substring(53);
      else
        eventMsgTextLine = "missing message text";
      this.addEventText(OneEvent.getCode(eventString), eventMsgTextLine);

      //**************************************************************
      // add the peb if it is new
      OnePEB peb = Globals.thePEBs.get(OneEvent.getPebNumber(eventString));
      if(null == peb)
      {
        peb = new OnePEB(OneEvent.getPebNumber(eventString),
                         OneEvent.getType(eventString));
        Globals.thePEBs.put(OneEvent.getPebNumber(eventString), peb);
      }
      //**************************************************************
      // flag the peb as a closer if it is
      if(OneEvent.getCode(eventString).equals("0001673"))
      {
        peb.setUsedForClosing(true);
        Globals.thePEBs.put(OneEvent.getPebNumber(eventString), peb);
      }

      //**************************************************************
      // add the ivo if it is new
      OneTerminal term = Globals.theTerms.get(OneEvent.getIvoNumber(eventString));
      if(null == term)
      {
        term = new OneTerminal(OneEvent.getIvoNumber(eventString));
        Globals.theTerms.put(OneEvent.getIvoNumber(eventString), term);
//        FileUtils.logFile.printf("%s ZORK 152IVO X%sX\n", TAG,
//                                 OneEvent.getIvoNumber(eventString));
        FileUtils.logFile.flush();
      }

      //**************************************************************
      // set the ivoNumber to say that we have found it in 152 
      Globals.theTerms.get(ivoNumber).setFoundIn152(true);

      //**************************************************************
      // add the message text to the stored message texts if it is new
      Globals.theTerms.get(ivoNumber).addToEventLog(eventString);
    }

    FileUtils.CloseFile(inFile);
    return true;
  } // public boolean read152(Scanner inFile)

} // public class OneFiveTwo
