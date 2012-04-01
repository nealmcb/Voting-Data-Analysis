import java.util.Scanner;
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
public class OneFiveFive
{
/*********************************************************************
 * Instance variables.
**/
  static final String TAG = "OneFiveFive: "; // for testing

/*********************************************************************
 * Constructor.
**/
  public OneFiveFive()
  {
  } // public OneFiveFive()

/*********************************************************************
 * Accessors and mutators.
**/

/*********************************************************************
 * General methods.
**/
/*********************************************************************
 * Method to increment votes for each precinct, style, and ivo.
**/
  public void addBallotCountPctStyleIvo(String pctNumber,
                                        String ballotStyle,
                                        String ivoNumber)
  {
    String keya = Keys.makeKeyForBallotCountPctStyleIvo(pctNumber, ballotStyle, ivoNumber);
    Globals.incrementTreeMap(keya, Globals.ballotCountPctStyleIvo, 1);

    String keyb = Keys.makeKeyForBallotCountPctStyle(pctNumber, ballotStyle);
    Globals.incrementTreeMap(keyb, Globals.ballotCountPctStyle, 1);

  } // public void addBallotCountPctStyleIvo(String pctNumber,

/*********************************************************************
 * Method to add votes to the machines for each precinct and conversely.
**/
  public void addIvosAndPcts(String pctNumber, String ivoNumber)
  {
    String keyip = Keys.makeKeyForIvoPctCount(ivoNumber, pctNumber);
    String keypi = Keys.makeKeyForPctIvoCount(ivoNumber, pctNumber);

    Globals.incrementTreeMap(keyip, Globals.ivoPctCount, 1);
    Globals.incrementTreeMap(keypi, Globals.pctIvoCount, 1);
  } // public void addIvosAndPcts(String keyip, String keypi)

/*********************************************************************
 * Method to read the 155 file from an input <code>Scanner</code>.
 * This was originally just to synchronize precinct names and numbers.
 * Now it reads all the data.
 *
 * @param inFile the <code>Scanner</code> from which to read.
**/
//  public void read155(Scanner inFile)
  public boolean read155(String fileNamePrefix)
  {
    int ballotCount;
    String ballotStyle;
    String candidate;
    String ivoNumber;
    String newBallot;
    String pctNumber;
    String sequence;
    String line;

    Scanner inFile = null;
    inFile = Globals.OpenInputFile(fileNamePrefix, "_155");
    if(null == inFile)
    { 
      FileUtils.logFile.printf("%s FILE '%s' NOT FOUND%n", TAG,
                               fileNamePrefix+"_155");
      FileUtils.logFile.flush();
      return false;
    }


// read and trash the first line
    inFile.nextLine();

    pctNumber = Globals.convertToPctNumber(0);
    ballotCount = 0;
    while(inFile.hasNextLine())
    {
      line = inFile.nextLine();
      if(line.trim().compareTo("") == 0)
      {
//        FileUtils.logFile.printf("%s EMPTY %s\n", TAG, line);
//        FileUtils.logFile.flush();
        continue;
      }

      if((line.trim().length() >= 5) &&
         (line.trim().substring(0,5).compareTo("VOTR.") == 0))
      {
//        FileUtils.logFile.printf("%s VOTR %s\n", TAG, line);
//        FileUtils.logFile.flush();
        continue;
      }

      if((line.trim().length() >= 9) &&
         (line.trim().substring(0,9).compareTo("RUN DATE:") == 0))
      {
//        FileUtils.logFile.printf("%s HEADER %s\n", TAG, line);
//        FileUtils.logFile.flush();
        int i1 = line.indexOf("PRECINCT");
        int i5 = line.indexOf("-");
        int i9 = line.indexOf("ELECTION");
        String pctNum = line.substring(i1+8,i5).trim();
        pctNum = Globals.convertToPctNumber(pctNum);
        String pctName = line.substring(i5+1,i9).trim().toUpperCase();
//        FileUtils.logFile.printf("%s NAME X%sX\n", TAG, pctName);
//        FileUtils.logFile.flush();
        pctName = Constants.filterPctName(pctName);
//        FileUtils.logFile.printf("%s NAME X%sX\n", TAG, pctName);
//        FileUtils.logFile.flush();

        pctNumber = Globals.convertToPctNumber(pctNum);
//        FileUtils.logFile.printf("%s HEADER X%dX X%sX\n",
//                                 TAG, pctNumber, pctName);
//        FileUtils.logFile.flush();

        OnePrecinct thePct = Globals.pctsByName.get(pctName);
        if(null == thePct)
        {
          thePct = new OnePrecinct();
        }
        thePct.setPctNumber(pctNumber);
        Globals.pctsByNumber.put(pctNumber, thePct);

        continue;
      }

// new code for full read is here
      try
      {
        if(line.trim().length() >= 7)
        {
          // this next line is to catch lines that don't start with a valid
          // seven-digit ivo number
          int ivo = Integer.valueOf(line.substring(0,7));
          if(line.trim().substring(0,1).equals("5"))
          {
//            FileUtils.logFile.printf("%s newcode %d %s\n", TAG, ballotCount, line.toString());
            ivoNumber = line.substring(0,7); 
            ballotStyle = line.substring(10,12); 
            newBallot = line.substring(13,14);
            if(newBallot.equals("*"))
            {
              ++ballotCount;
            }
            sequence = line.substring(14,19); 
            candidate = line.substring(20); 

//            FileUtils.logFile.printf("%s %d %s\n", TAG, ballotCount, line.toString());
//            FileUtils.logFile.printf("%s %d X%sX X%sX X%sX X%sX X%sX\n", TAG,
//                                     ballotCount, ivoNumber, ballotStyle,
//                                     newBallot, sequence, candidate);
//            FileUtils.logFile.flush();

            // we have data, so we load the structures.
            OneTerminal term = Globals.theTerms.get(ivoNumber);
            if(null == term)
            {
              term = new OneTerminal(ivoNumber);
              term.setVotesCast155(0);
              term.setAbsentee(false);
              term.setNotAbsentee(false);
            }
            // remember that addPcts only adds if it's a new pct
            term.addPcts(ivoNumber, String.format("%s", pctNumber));

            if(pctNumber.compareTo("0750") < 0)
            {
              term.setNotAbsentee(true);
            }
            else
            {
              term.setAbsentee(true);
            }

            term.addBallotStyles(ivoNumber, ballotStyle);

            term.setFoundIn155(true);
            if(newBallot.equals("*"))
            {
              term.setVotesCast155(term.getVotesCast155()+1);
              this.addIvosAndPcts(pctNumber, ivoNumber);
              this.addBallotCountPctStyleIvo(pctNumber, ballotStyle, ivoNumber);
            }
            Globals.theTerms.put(ivoNumber, term);
            String votesKey = Keys.makeKeyForTheVotes(pctNumber, ivoNumber,
                                                      ballotStyle, sequence,
                                                      candidate);
            Globals.incrementTreeMap(votesKey, Globals.theVotes, 1);
          }
        }
      }
      catch (NumberFormatException ex)
      {
        continue;
      }
    }

    Globals.setBallotCount155(ballotCount);

//    FileUtils.logFile.printf("%s Votes%n%s%n", TAG, Globals.toStringVotes());
//    FileUtils.logFile.printf("%s Total votes in 155 %d\n", TAG, ballotCount);

    return true;
  } // public void read155(Scanner inFile)

} // public class OneFiveFive
