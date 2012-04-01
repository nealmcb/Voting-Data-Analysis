import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
/*********************************************************************
 * Class to handle the global variables.
 * This includes
 * ** the static list of iVotronic terminals discovered in the data
 * ** the static list of messages discovered in the data
 * ** the static list of PEBs discovered in the data
 *
 * We are going to violate some of the rules of modern programming 
 * and use these as globals because we need to access and modify
 * them from several places.
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
public class Globals
{
/*********************************************************************
 * Instance variables for the class.
**/
  private static final String TAG = "Globals: "; // for testing

  static public final int DUMMYINT = Integer.MIN_VALUE;

  static public final String DUMMYCODE = "dummycode";
  static public final String DUMMYCLOSEPEB = "dummyclosepeb";
  static public final String DUMMYCOUNTY = "dummycounty";
  static public final String DUMMYDATE = "dummydate";
  static public final String DUMMYIVONUMBER = "dummyivonumber";
  static public final String DUMMYOPENPEB = "dummyopenpeb";
  static public final String DUMMYPEBNUMBER = "dummypebnumber";
  static public final String DUMMYPEBTYPE = "dummytype";
  static public final String DUMMYSTRING = "dummy";
  static public final String DEFAULTTIME = "1970_01_01 00:00:00";

  static private String county = Globals.DUMMYCOUNTY;
  static private String electionDate = Globals.DUMMYDATE;

  static private int ballotCount152 = Integer.MIN_VALUE;
  static private int ballotCount155 = Integer.MIN_VALUE;

  static public TreeMap<String, Integer> conCand; //contest,candidate
  static public TreeMap<String, Integer> ivoPctCount;
  static public TreeMap<String, Integer> pctConCand; //pct,contest,candidate
  static public TreeMap<String, Integer> pctIvoConCand; //pct,ivo,contest,candidate
  static public TreeMap<String, Integer> pctIvoCount;
  static public TreeMap<String, Integer> theVotes; //pct,ivo,style,seq,cand

  // the following is added to be able to compute undervotes
  static public TreeMap<String, Integer> ballotCountPctStyleIvo; //pct,style,ivo
  static public TreeMap<String, Integer> ballotCountPctStyle; //pct,style
  static public TreeMap<String, Integer> pctIvoStyleCon; //pct,style

  static public TreeMap<String, String> eventTexts;

  static public TreeMap<String, OnePEB> thePEBs;

  static public TreeMap<String, OnePrecinct> pctsByName;
  static public TreeMap<String, OnePrecinct> pctsByNumber;

  static public TreeMap<String, OneTerminal> theTerms;

/*********************************************************************
 * Constructor.
**/
  public Globals()
  {
    county = Globals.DUMMYCOUNTY;
    electionDate = Globals.DUMMYDATE;

    ballotCount152 = Integer.MIN_VALUE;
    ballotCount155 = Integer.MIN_VALUE;

    conCand  = new TreeMap<String, Integer>();
    ivoPctCount  = new TreeMap<String, Integer>();
    pctConCand  = new TreeMap<String, Integer>();
    pctIvoConCand  = new TreeMap<String, Integer>();
    pctIvoCount  = new TreeMap<String, Integer>();
    theVotes     = new TreeMap<String, Integer>();

    ballotCountPctStyleIvo = new TreeMap<String, Integer>();
    ballotCountPctStyle = new TreeMap<String, Integer>();
    pctIvoStyleCon = new TreeMap<String, Integer>();

    eventTexts = new TreeMap<String, String>();

    thePEBs      = new TreeMap<String, OnePEB>();

    pctsByName   = new TreeMap<String, OnePrecinct>();
    pctsByNumber = new TreeMap<String, OnePrecinct>();
    theTerms     = new TreeMap<String, OneTerminal>();
  } // public Globals()

/*********************************************************************
 * Accessors and mutators.
**/
/*********************************************************************
 * Method to get the ballot count from the 152 file.
 *
 * @return the ballot count from the 152 file
**/
  public static int getBallotCount152()
  {
    return ballotCount152;
  } // public static int getBallotCount152()

/*********************************************************************
 * Method to add to the ballot count from the 152 file.
 *
 * @param howMany the number of ballots to add
**/
  public static void addToBallotCount152(int howMany)
  {
    ballotCount152 += howMany;
  } // public static void addToBallotCount152(int howMany)

/*********************************************************************
 * Method to get the ballot count from the 155 file.
 *
 * @return the ballot count from the 155 file
**/
  public static int getBallotCount155()
  {
    return ballotCount155;
  } // public static int getBallotCount155()

/*********************************************************************
 * Method to set the ballot count 155 from the 155 file.
 *
 * @param what the value to set.
**/
  public static void setBallotCount155(int count)
  {
    ballotCount155 = count;
  } // public static void setBallotCount155(int count)

/*********************************************************************
 * Method to get the global county label.
 *
 * @return the county
**/
  public static String getCounty()
  {
    return county;
  } // public static String getCounty()

/*********************************************************************
 * Method to set the county label.
 *
 * @param what the county.
 * @param state whether to add 'State' to the county 
**/
  public static void setCounty(String county, boolean state)
  {
    Globals.county = county;
    if(state)
      Globals.county = Globals.county + "State";
  } // public static void setCounty(String county, boolean state)

/*********************************************************************
 * Method to get the election date.
 *
 * @return the election date.
**/
  public static String getElectionDate()
  {
    return electionDate;
  } // public static String getElectionDate()

/*********************************************************************
 * Method to set the election date.
 *
 * This assumes that the date comes in as 'yyyymmdd' and this code
 * converts that to 'yyyy_mm_dd'
 *
 * @param the unformatted date string.
**/
  public static void setElectionDate(String date)
  {
    electionDate = String.format("%4s_%2s_%2s", date.substring(0,4),
                                                date.substring(4,6),
                                                date.substring(6));
    FileUtils.logFile.printf("%s election date is set to %s%n", TAG, electionDate);
  } // public static String convertToPctNumber(int number)

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to compare two date strings.
 *
 * @param s1 first date
 * @param s2 second date
 * @return -1, 0, 1 as usual
**/
  public static int compareDateTime(String s1, String s2)
  {
    int returnValue = 0; // default is that s1 equals s2
    String day1, day2, month1, month2, year1, year2;
    String hour1, hour2, minute1, minute2, second1, second2;

    year1 = s1.substring(0,4);
    month1 = s1.substring(5,7);
    day1 = s1.substring(8,10);
    hour1 = s1.substring(11,13);
    minute1 = s1.substring(14,16);
    second1 = s1.substring(17);

    year2 = s2.substring(0,4);
    month2 = s2.substring(5,7);
    day2 = s2.substring(8,10);
    hour2 = s2.substring(11,13);
    minute2 = s2.substring(14,16);
    second2 = s2.substring(17);
// FileUtils.logFile.printf("ONE %s %s %s %s %s %s%n", year1, month1, day1, hour1, minute1, second1);
// FileUtils.logFile.printf("TWO %s %s %s %s %s %s%n", year2, month2, day2, hour2, minute2, second2);

    if(year1.compareTo(year2) < 0)
    {
      returnValue = -1;
    }
    else if(year1.compareTo(year2) > 0)
    {
      returnValue =  1;
    }
    else if(month1.compareTo(month2) < 0)
    {
      returnValue = -1;
    }
    else if(month1.compareTo(month2) > 0)
    {
      returnValue =  1;
    }
    else if(day1.compareTo(day2) < 0)
    {
      returnValue = -1;
    }
    else if(day1.compareTo(day2) > 0)
    {
      returnValue =  1;
    }
    else if(hour1.compareTo(hour2) < 0)
    {
      returnValue = -1;
    }
    else if(hour1.compareTo(hour2) > 0)
    {
      returnValue =  1;
    }
    else if(minute1.compareTo(minute2) < 0)
    {
      returnValue = -1;
    }
    else if(minute1.compareTo(minute2) > 0)
    {
      returnValue =  1;
    }
    else if(second1.compareTo(second2) < 0)
    {
      returnValue = -1;
    }
    else if(second1.compareTo(second2) > 0)
    {
      returnValue =  1;
    }

    return returnValue;
  } // public static int compareDate(String s1, String s2)

/*********************************************************************
 * Method to convert a DateTime to an seconds interval after opening. 
 *
 * By default, if the date is not on the election date, we set the
 * return value to the max integer.
 *
 * @param s the Datetime
 * @return the seconds after 7am on the election date 
**/
  public static int convertDateTime(String s)
  {
    int returnValue = 0; // default is that s1 equals s2
    String hour, minute, second;

    if(!s.substring(0,10).equals(Globals.electionDate))
    {
//      FileUtils.logFile.printf("WRONG DAY %s%n", s);
      return Integer.MAX_VALUE;
    }

    hour = s.substring(11,13);
    minute = s.substring(14,16);
    second = s.substring(17);

    returnValue += (Integer.valueOf(hour.trim()) - 7) * 3600;
    returnValue += (Integer.valueOf(minute.trim())) * 60;
    returnValue += (Integer.valueOf(second.trim()));

//    FileUtils.logFile.printf("TIME %s %s %s %d%n", hour, minute, second, returnValue);

    return returnValue;
  } // public static int convertDateTime(String s)

/*********************************************************************
 * Method to convert an int to a precinct number string.
 *
 * @return the formatted <code>String</code> value.
**/
  public static String convertToPctNumber(int number)
  {
    return String.format("%04d", number).trim();
  } // public static String convertToPctNumber(int number)

/*********************************************************************
 * Method to convert a string to a precinct number string.
 *
 * @return the formatted <code>String</code> value.
**/
  public static String convertToPctNumber(String number)
  {
    String s = "0000";
    s += number.trim();
    return s.substring(s.length()-4).trim();
  } // public static String convertToPctNumber(String number)

/*********************************************************************
 * Method to convert a vote count to a string or else 'XXXXXX'.
 * This is to flag the vote count values that have never been set
 * to anything other that the 'Globals.DUMMYINT' value or else
 * have had that value added in or subtracted off and are thus bogus. 
 *
 * @param what the number to convert
 *
 * @return the converted value
**/
  public static String convertToGoodVoteNumber(int what)
  {
    String s = "";

    if((-999999 < what) && (what < 9999999))
      s = String.format("%d", what);
    else
      s = "XXXXXX";
    return s;
  } // public static String convertToGoodVoteNumber(int what)

/*********************************************************************
 * Method to initialize or increment a TreeMap.
 *
 * @param key the key for the <code>Treemap</code>.
 * @param treemap the actual <code>TreeMap</code>.
 * @param howMany how much to increment or store.
**/
  public static void incrementTreeMap(String key,
                                      TreeMap<String, Integer> treemap,
                                      Integer howMany)
  {
    Integer value = treemap.get(key);
    if(null == value)
    {
      treemap.put(key, howMany);
    }
    else
    {
      treemap.put(key, treemap.get(key)+howMany);
    }
  } // public static String convertToPctNumber(String number)

/*********************************************************************
 * Method to open and return a <code>Scanner</code> from a 
 * filename prefix and a filename suffix.
 *
 * @return the opened <code>Scanner</code>
**/
  public static Scanner OpenInputFile(String prefix, String suffix)
  {
    String inFileName = prefix + suffix;
    Scanner inFile = null;
    FileUtils.logFile.printf("%s open input file '%s'%n", TAG, inFileName);
    FileUtils.logFile.flush();

    inFile =  FileUtils.ScannerOpen(inFileName);
    if(null == inFile)
    {
      FileUtils.logFile.printf("%s FILE '%s' NOT FOUND%n", TAG, inFileName);
      FileUtils.logFile.flush();
    }

    return inFile;

  } // public static Scanner OpenInputFile(String prefix, String suffix)

/*********************************************************************
 * Method to output the PctStyleIvo ballot counts.
 *
 * Contrary to what the method name actually says, this outputs not
 * just one but two lists of totals, first in complete detail of
 * counts by pct, style, and ivo, and then only by pct and style.
 *
 * @param outFile the output file to which to write
**/
  public static void outputBallotCountPctStyleIvo(String prefix)
  {
    int localCount;
    String contest;
    String candidate;

    String fileName = prefix + "_BallotCountPctStyleIvo.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Ballot counts by Pct, Style, Ivo for %s%n%n",
                   Globals.getCounty());

    for(String bspsikey: Globals.ballotCountPctStyleIvo.keySet())
    {
      localCount = Globals.ballotCountPctStyleIvo.get(bspsikey);
      outFile.printf("%s %7d%n", bspsikey, localCount);
      outFile.flush();
    }

    outFile.printf("%n%nBallot counts by Pct, Style for %s%n%n",
                   Globals.getCounty());

    for(String bspskey: Globals.ballotCountPctStyle.keySet())
    {
      localCount = Globals.ballotCountPctStyle.get(bspskey);
      outFile.printf("%s %7d%n", bspskey, localCount);
      outFile.flush();
    }

    outFile.printf("%n%nBallot counts by Pct, Ivo, Style, Contest for %s%n%n",
                   Globals.getCounty());

    for(String thekey: Globals.pctIvoStyleCon.keySet())
    {
      int contestCount = Globals.pctIvoStyleCon.get(thekey);
      String localPct = Keys.getPctFromUndervoteKey(thekey);
      String localStyle = Keys.getStyleFromUndervoteKey(thekey);
      String localIvo = Keys.getIvoFromUndervoteKey(thekey);
      String bspsikey = Keys.makeKeyForBallotCountPctStyleIvo(localPct, localStyle, localIvo);
      int voteCount = Globals.ballotCountPctStyleIvo.get(bspsikey);
      outFile.printf("%7d %7d %7d %s%n", voteCount, contestCount, voteCount-contestCount, thekey);
      outFile.flush();
    }

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputBallotCountPctStyleIvo(String prefix)

/*********************************************************************
 * Method to output the contest-candidate totals
 *
 * @param outFile the output file to which to write
**/
  public static void outputContestCandidate(String prefix)
  {
    int localCount;
    String contest;
    String candidate;

    String fileName = prefix + "_ContestCandidate.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Contest-Candidate totals for %s%n%n",
                   Globals.getCounty());

    for(String concand: Globals.conCand.keySet())
    {
      localCount = Globals.conCand.get(concand);
      contest = Keys.getContestFromConCandKey(concand);
      candidate = Keys.getCandidateFromConCandKey(concand);
      outFile.printf("%7d %s %s %n", localCount, contest, candidate);
      outFile.flush();
    }

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputContestCandidate(String prefix)

/*********************************************************************
 * Method to output the event logs for all terminals.
 *
 * @param outFile the output file to which to write
**/
  public static void outputEventLogs(String prefix)
  {
    int count;

    String fileName = prefix + "_EventLogs.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Event logs by ivo for %s%n%n", Globals.getCounty());

    count = 0;
    outFile.printf("Event logs%n");
    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      outFile.printf("%s%n", term.dumpEvents());
      count += term.getEventLogSize();
    }
    outFile.printf("Total number of terminals dumped: %8d%n",
                   Globals.theTerms.size());
    outFile.printf("Total number of events dumped: %8d%n%n", count);

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputEventLogs(String prefix)

/*********************************************************************
 * Method to output the event log counts for all terminals.
 *
 * @param outFile the output file to which to write
**/
  public static void outputEventLogCounts(String prefix)
  {
    int count;

    String fileName = prefix + "_EventLogCounts.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Event log counts by ivo for %s%n%n", Globals.getCounty());

    count = 0;
    outFile.printf("Event log counts%n");
    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      outFile.printf("%s%n", term.dumpEventCounts());
      count += term.getEventLogSize();
    }
    outFile.printf("Total number of terminals dumped: %8d%n",
                   Globals.theTerms.size());
    outFile.printf("Total number of events dumped: %8d%n%n", count);

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputEventLogCounts(String prefix)

/*********************************************************************
 * Method to output the event texts.
 *
 * @param outFile the output file to which to write
**/
  public static void outputEventTexts(String prefix)
  {
    String fileName = prefix + "_EventLogText.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);

    outFile.printf("Event text list is for %s%n%n", Globals.getCounty());

    outFile.printf("Event texts%n");
    for(String textKey: Globals.eventTexts.keySet())
    {
       outFile.printf("%8s %s%n",
                      textKey, Globals.eventTexts.get(textKey).toString());
    }
    outFile.printf("Total number of event texts: %8d%n%n",
                   Globals.eventTexts.size());

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputEventTexts(String prefix)

/*********************************************************************
 * Method to output the ivos by pct and pcts by ivo lists
 *
 * @param outFile the output file to which to write
**/
  public static void outputIvosAndPcts(String prefix)
  {
    boolean firstTime = true;
    int ivoCount;
    int localTotal;
    int pctTotal;
    int votesCast;
    String ivo = ""; 
    String name = ""; 
    String oldpct = Globals.convertToPctNumber(0); 
    String pct = ""; 

    String fileName = prefix + "_IvosAndPcts.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);

    // First we do by ivos within precincts.
    localTotal = 0; 
    pctTotal = 0; 
    outFile.printf("Pct Ivo Count for %s%n", Globals.getCounty());
    outFile.printf(" PCT   IVO    PCTTOT     155     152 : RUNNINGTOTAL%n");
    for(String pctivoKey: Globals.pctIvoCount.keySet())
    {
      pct = Keys.getPctFromPctIvoCountKey(pctivoKey);
      ivo = Keys.getIvoFromPctIvoCountKey(pctivoKey);
      ivoCount = Globals.pctIvoCount.get(pctivoKey);

      if(!oldpct.equals(pct))
      {
        if(!firstTime)
        {
          name = Globals.pctsByNumber.get(oldpct).getName();
          votesCast = Globals.pctsByNumber.get(oldpct).getVotesCast();
          outFile.printf("PCT TOTAL %25s %4s %7s: %7s", name, oldpct,
                         Globals.convertToGoodVoteNumber(pctTotal),
                         Globals.convertToGoodVoteNumber(votesCast));

          if(pctTotal != votesCast)
            outFile.printf(" ( %8s) ZORK",
                           Globals.convertToGoodVoteNumber(votesCast - pctTotal));
          outFile.printf("%n");

          // set the votes from 155 in the precinct instance
          Globals.pctsByNumber.get(oldpct).setVotes155(pctTotal);

          oldpct = pct;
          pctTotal = 0;
        }
        else
        {
          firstTime = false;
          oldpct = pct;
        }
      } // if(!oldpct.equals(pct))

      pctTotal += ivoCount;
      localTotal += ivoCount;
      outFile.printf("%4s %7s %7s %7s %7s : %7s", pct, ivo,
        Globals.convertToGoodVoteNumber(ivoCount),
        Globals.convertToGoodVoteNumber(Globals.theTerms.get(ivo).getVotesCast155()),
        Globals.convertToGoodVoteNumber(Globals.theTerms.get(ivo).getVotesCast152()),
        Globals.convertToGoodVoteNumber(pctTotal));

      if((ivoCount != Globals.theTerms.get(ivo).getVotesCast152()) &&
         (Globals.theTerms.get(ivo).isNotAbsentee()))
      {
        outFile.printf(" (ZORK PCTTOT-152)");
      }
      if((ivoCount != Globals.theTerms.get(ivo).getVotesCast155()) &&
         (Globals.theTerms.get(ivo).isNotAbsentee()))
      {
        outFile.printf(" (ZORK PCTTOT-155)");
      }
      if((Globals.theTerms.get(ivo).getVotesCast152() !=
           Globals.theTerms.get(ivo).getVotesCast155()) &&
         (Globals.theTerms.get(ivo).isNotAbsentee()))
      {
        outFile.printf(" (ZORK 155-152)");
      }
      outFile.printf("%n");
    }
    name = Globals.pctsByNumber.get(pct).getName();
    votesCast = Globals.pctsByNumber.get(pct).getVotesCast();
    outFile.printf("PCT TOTAL %25s %4s %7s: %7s", name, oldpct,
                   Globals.convertToGoodVoteNumber(pctTotal),
                   Globals.convertToGoodVoteNumber(votesCast));

    if(pctTotal != votesCast)
      outFile.printf(" ( %8s) ZORK",
                     Globals.convertToGoodVoteNumber(votesCast - pctTotal));
    outFile.printf("%n");

    // set the votes from 155 in the precinct instance
    Globals.pctsByNumber.get(oldpct).setVotes155(pctTotal);

    outFile.printf("%n");
    outFile.printf("COUNTY TOTAL %7d%n%n", localTotal);


    // Now we do the other way around for the second half of the file.
    localTotal = 0;
    outFile.printf("Pcts By Ivo%n");
    outFile.printf("  IVO    PCT   COUNT%n");
    for(String ivopctKey: Globals.ivoPctCount.keySet())
    {
      outFile.printf("%7s %4s %7d%n",
                     Keys.getIvoFromIvoPctCountKey(ivopctKey),
                     Keys.getPctFromIvoPctCountKey(ivopctKey),
                     Globals.ivoPctCount.get(ivopctKey));
      localTotal += Globals.ivoPctCount.get(ivopctKey);
    }
    outFile.printf("%nCOUNTY TOTAL   %7d%n%n%n", localTotal);
    outFile.flush();
    FileUtils.CloseFile(outFile);

  } // public static void outputIvosAndPcts(String prefix)

/*********************************************************************
 * Method to output the pcts by name and then by number.
 *
 * @param outFile the output file to which to write
**/
  public static void outputPcts(String prefix)
  {
    String fileName = prefix + "_PctDetail.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Precinct Detail List By Name and Number for %s%n%n", Globals.getCounty());

    //****************************************************************
    // first by name
    outFile.printf("Pcts By Name%n");
    for(String pctKey: Globals.pctsByName.keySet())
    {
      outFile.printf("%s",
                Globals.pctsByName.get(pctKey).toString().substring(0,63));
      if(null == Globals.pctsByName.get(pctKey).getPctNumber())
      {
        outFile.printf(" PCT MISSING%n");
      }
      else if(Globals.pctsByName.get(pctKey).getVotes155() !=
                Globals.pctsByName.get(pctKey).getVotesCast())
      {
        outFile.printf(" DATA MISSING%n");
      }
      else
      {
        outFile.printf("%n");
      }

      outFile.printf("     terminals: %s%n",
                Globals.pctsByName.get(pctKey).toStringTerminals());
      outFile.printf("     open/close pebs: %s %s%n",
                Globals.pctsByName.get(pctKey).toStringOpenPEBs(),
                Globals.pctsByName.get(pctKey).toStringClosePEBs());
    }
    outFile.printf("%n");

    //****************************************************************
    // then by number
    outFile.printf("Pcts By Number%n");
    for(String pctKey: Globals.pctsByNumber.keySet())
    {
//      outFile.printf("%s%n", Globals.pctsByNumber.get(pctKey).toString());

      outFile.printf("%s",
                Globals.pctsByNumber.get(pctKey).toString().substring(0,63));
      if(null == Globals.pctsByNumber.get(pctKey).getPctNumber())
      {
        outFile.printf(" PCT MISSING%n");
      }
      else if(Globals.pctsByNumber.get(pctKey).getVotes155() !=
                Globals.pctsByNumber.get(pctKey).getVotesCast())
      {
        outFile.printf(" DATA MISSING%n");
      }
      else
      {
        outFile.printf("%n");
      }

      outFile.printf("     terminals: %s%n",
                Globals.pctsByNumber.get(pctKey).toStringTerminals());
      outFile.printf("     open/close pebs: %s %s%n",
                Globals.pctsByNumber.get(pctKey).toStringOpenPEBs(),
                Globals.pctsByNumber.get(pctKey).toStringClosePEBs());
    }
    outFile.printf("%n");
    outFile.flush();
    FileUtils.CloseFile(outFile);

  } // public static void outputPcts(String prefix)

/*********************************************************************
 * Method to output the pct-contest-candidate totals
 *
 * @param outFile the output file to which to write
**/
  public static void outputPctContestCandidate(String prefix)
  {
    int localCount;
    String pct;
    String contest;
    String candidate;

    String fileName = prefix + "_PctContestCandidate.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Pct-Contest-Candidate totals for %s%n%n", Globals.getCounty());

    for(String pctconcand: Globals.pctConCand.keySet())
    {
      localCount = Globals.pctConCand.get(pctconcand);
      pct = Keys.getPctFromPctConCandKey(pctconcand);
      contest = Keys.getContestFromPctConCandKey(pctconcand);
      candidate = Keys.getCandidateFromPctConCandKey(pctconcand);
      outFile.printf("%7d %s %s %s %n",
                     localCount, pct, contest, candidate);
      outFile.flush();
    }

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputPctContestCandidate(String prefix)

/*********************************************************************
 * Method to output the pct-ivo-contest-candidate totals
 *
 * @param outFile the output file to which to write
**/
  public static void outputPctIvoContestCandidate(String prefix)
  {
    int localCount;
    String pct;
    String ivo;
    String contest;
    String candidate;

    String fileName = prefix + "_PctIvoContestCandidate.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Pct-Ivo-Contest-Candidate totals for %s%n%n", Globals.getCounty());

    for(String pctivoconcand: Globals.pctIvoConCand.keySet())
    {
      localCount = Globals.pctIvoConCand.get(pctivoconcand);
      pct = Keys.getPctFromPctIvoConCandKey(pctivoconcand);
      ivo = Keys.getIvoFromPctIvoConCandKey(pctivoconcand);
      contest = Keys.getContestFromPctIvoConCandKey(pctivoconcand);
      candidate = Keys.getCandidateFromPctIvoConCandKey(pctivoconcand);
      outFile.printf("%7d %s %s %s %s %n", localCount, pct, ivo, contest, candidate);
      outFile.flush();
    }

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputPctIvoContestCandidate(String prefix)

/*********************************************************************
 * Method to output the PEB list.
 *
 * @param outFile the output file to which to write
**/
  public static void outputPEBs(String prefix)
  {
    String fileName = prefix + "_PEBDetail.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("PEB list for %s%n%n", Globals.getCounty());

    outFile.printf("PEBs not used in closing in 152 and not in 68A%n");
    for(String pebKey: Globals.thePEBs.keySet())
    {
      if((!Globals.thePEBs.get(pebKey).getUsedForClosing()) &&
         (!Globals.thePEBs.get(pebKey).getFoundInSystemLog()))
      {
        outFile.printf("%s%n", Globals.thePEBs.get(pebKey).toString());
      }
    }

    outFile.printf("%nPEBs used in closing in 152 but not in 68A%n");
    for(String pebKey: Globals.thePEBs.keySet())
    {
      if(( Globals.thePEBs.get(pebKey).getUsedForClosing()) &&
         (!Globals.thePEBs.get(pebKey).getFoundInSystemLog()))
      {
        outFile.printf("%s%n", Globals.thePEBs.get(pebKey).toString());
      }
    }

    outFile.printf("%nPEBs not used in closing in 152 but present in 68A%n");
    for(String pebKey: Globals.thePEBs.keySet())
    {
      if((!Globals.thePEBs.get(pebKey).getUsedForClosing()) &&
         ( Globals.thePEBs.get(pebKey).getFoundInSystemLog()))
      {
        outFile.printf("%s%n", Globals.thePEBs.get(pebKey).toString());
      }
    }

    outFile.printf("%nPEBs used in closing in 152 and present in 68A%n");
    for(String pebKey: Globals.thePEBs.keySet())
    {
      if(( Globals.thePEBs.get(pebKey).getUsedForClosing()) &&
         ( Globals.thePEBs.get(pebKey).getFoundInSystemLog()))
      {
        outFile.printf("%s%n", Globals.thePEBs.get(pebKey).toString());
      }
    }
    outFile.printf("Total number of PEBs: %8d%n%n", Globals.thePEBs.size());

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputPEBs(String prefix)

/*********************************************************************
 * Method to output the terminals list.
 *
 * @param outFile the output file to which to write
**/
  public static void outputTerminals(String prefix)
  {
    //****************************************************************
    // First we output the terminal list with all the data.
    String fileName1 = prefix + "_IvoDetail.txt";
    PrintWriter outFile1 = FileUtils.PrintWriterOpen(fileName1);
    outFile1.printf("output the ivo terminal list for %s%n%n", Globals.getCounty());

    outFile1.printf("Terminals%n");;
    for(String termKey: Globals.theTerms.keySet())
    {
      outFile1.printf("%s%n", Globals.theTerms.get(termKey).toString());
    }
    outFile1.printf("Total number of terminals: %8d%n%n", Globals.theTerms.size());
    
    outFile1.flush();
    FileUtils.CloseFile(outFile1);

    //****************************************************************
    // Then we output the list of memory card collection times.
    String fileName2 = prefix + "_IvoMemoryCards.txt";
    PrintWriter outFile2 = FileUtils.PrintWriterOpen(fileName2);
outFile2.printf("output the times of ivo memory card data collection for %s%n%n", Globals.getCounty());

    // get the times
    ArrayList<String> list = new ArrayList<String>();
    for(String termKey: Globals.theTerms.keySet())
    {
      String s = Globals.theTerms.get(termKey).toStringMemoryCollectionTime() +
                   " " + termKey;
      list.add(s);
      if(Globals.theTerms.get(termKey).toStringMemoryCollectionTime().equals(""))
      {
        outFile2.printf("NO MEMORY CARD DATA COLLECTION FOR TERMINAL %s%n", termKey);
      }
    }

    // sort the times
    Collections.sort(list);

    for(int i = 0; i < list.size(); ++i)
    {
      outFile2.printf("%s%n", list.get(i));
    } // for(int i = 0; i < list.size()-1; ++i)
    
    outFile2.flush();
    FileUtils.CloseFile(outFile2);

  } // public static void outputTerminals(String prefix)

/*********************************************************************
 * Method to output the votes list.
 *
 * @param outFile the output file to which to write
**/
  public static void outputVotes(String prefix)
  {
    String fileName = prefix + "_PctIvoBSContestCandidate.txt";
    PrintWriter outFile = FileUtils.PrintWriterOpen(fileName);
    outFile.printf("Vote Detail for %s%n%n", Globals.getCounty());

    outFile.printf("Votes%n");
    for(String votesKey: Globals.theVotes.keySet())
    {
      outFile.printf("%6d %s%n", Globals.theVotes.get(votesKey), votesKey);
    }
    outFile.printf("Total number of vote items: %8d%n", Globals.theVotes.size());
    outFile.printf("Total ballot count in 155: %8d%n%n", ballotCount155);

    outFile.flush();
    FileUtils.CloseFile(outFile);
  } // public static void outputVotes(String prefix)

} // public class Globals
