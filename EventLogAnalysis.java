import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
/*********************************************************************
 * Class for doing analysis of the data.
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
public class EventLogAnalysis
{
/*********************************************************************
 * Instance variables.
**/
  static final String TAG = "ELA: "; // for testing

/*********************************************************************
 * Constructor.
**/
  public EventLogAnalysis()
  {
  } // public EventLogAnalysis()

/*********************************************************************
 * Accessors and mutators.
**/

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to compare closing PEBs in the 152 and PEBs in the 68A.
**/
  private void compare152And68APEBS(PrintWriter outFile)
  {
    boolean noExceptions = true;

//    outFile.printf("%s enter compare152And68APEBS%n", TAG);
//    outFile.flush();

    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("Listing PEBs found only in 68A or in 152 (includes 750+)%n");
    outFile.flush();

    for(String pebKey: Globals.thePEBs.keySet())
    {
      OnePEB peb = Globals.thePEBs.get(pebKey);

      if(peb.getUsedForClosing() && !peb.getFoundInSystemLog())
      {
        outFile.printf("PEB %s not in 68A but closed terminals in 152%n",
                        pebKey);
        outFile.flush();
        noExceptions = false;
      }
    }

    outFile.printf("%n");
    for(String pebKey: Globals.thePEBs.keySet())
    {
      OnePEB peb = Globals.thePEBs.get(pebKey);

      if(!peb.getUsedForClosing() && peb.getFoundInSystemLog())
      {
        outFile.printf("          PEB %s in 68A but not in the 152 file%n", pebKey);
        outFile.flush();
        noExceptions = false;
      }
    }

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

//    outFile.printf("%s leave compare152And68APEBS%n%n", TAG);
//    outFile.flush();
  } // private void compare152And68APEBS(PrintWriter outFile)

/*********************************************************************
 * Method to compare terminal serial numbers from the 152 and 155.
**/
  private void compareIvos152V155(PrintWriter outFile)
  {
    boolean noExceptions = true;

//    outFile.printf("%s enter compareIvos152V155%n", TAG);
//    outFile.flush();

    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("Listing terminals found only in 152 or in 155 (includes 750+)%n");
    outFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      if(term.getFoundIn152() && !term.getFoundIn155())
      {
        outFile.printf("%s in 152 but not 155%n", termKey);
        outFile.flush();
        noExceptions = false;
      }
      else if(!term.getFoundIn152() && term.getFoundIn155())
      {
        outFile.printf("           %s in 155 but not 152%n", termKey);
        outFile.flush();
        noExceptions = false;
      }

      // The default setting is that a terminal is both absentee and
      // not absentee unless we set it otherwise.  This means that if
      // a terminal is not in the 155 file we will always have the default
      // values set.
      if(term.getFoundIn155() && term.isAbsentee() && term.isNotAbsentee())
      {
        outFile.printf("TERMINAL BOTH ABSENTEE AND NOT ABSENTEE: %s%n",
                       term.getIvoNumber());
        outFile.flush();
        noExceptions = false;
      }
    }

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

//    outFile.printf("%s leave compareIvos152V155%n%n", TAG);
//    outFile.flush();
  } // private void compareIvos152V155(PrintWriter outFile)

/*********************************************************************
 * Method to compare open and close PEBs.
**/
  private void compareOpenAndClosePEB(PrintWriter outFile)
  {
    boolean noExceptions = true;

//    outFile.printf("%s enter compareOpenAndClosePEB%n", TAG);
//    outFile.flush();

    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("Listing terminals opened and closed with different PEBs (includes 750+)%n");
    outFile.printf("This list comes from the 152 file%n");
    outFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      if(!term.getOpenPEB().equals(term.getClosePEB()))
      {
        outFile.printf("%s Opening PEB %s not closing PEB %s: %s%n",
                        termKey, term.getOpenPEB(), term.getClosePEB(),
                        term.toStringPcts());
        outFile.flush();
        noExceptions = false;
      }
    }

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    noExceptions = true;
    outFile.printf("%n");
    outFile.printf("Listing precincts with multiple PEBs used for closing (includes 750+)%n");
    for(String pctKey: Globals.pctsByNumber.keySet())
    {
      OnePrecinct pct = Globals.pctsByNumber.get(pctKey);
      if(pct.getClosePEBSize() > 1)
      {
        outFile.printf("%s %s%n", pct.getPctNumber(), pct.getName());
        outFile.printf("    %s%n", pct.toStringTerminals());
        outFile.printf("    %s %s %n", pct.toStringOpenPEBs(),
                                       pct.toStringClosePEBs());
        outFile.flush();
        noExceptions = false;
      }
    }

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

//    outFile.printf("%s leave compareOpenAndClosePEB%n%n", TAG);
//    outFile.flush();
  } // private void compareOpenAndClosePEB(PrintWriter outFile)

/*********************************************************************
 * Method to compare the vote totals.
**/
  private void compareVoteTotals(PrintWriter outFile)
  {
    int count152;
    int count155;

//    outFile.printf("%s enter compareVoteTotals%n", TAG);
//    outFile.flush();

    count152 = Globals.getBallotCount152();
    if(count152 < 0) count152 -= Integer.MIN_VALUE;
    outFile.printf("Ballot total in 152: %10d%n", count152);

    count155 = Globals.getBallotCount155();
    if(count155 < 0) count155 -= Integer.MIN_VALUE;
    outFile.printf("Ballot total in 155: %10d%n", count155);

    if(count152 != count155)
      outFile.printf("BALLOT TOTALS DIFFER%n");
    outFile.flush();

//    outFile.printf("%s leave compareVoteTotals%n%n", TAG);
//    outFile.flush();
  } // private void compareVoteTotals(PrintWriter outFile)

/*********************************************************************
 * Method to count pairs of events.
**/
  private void countEventPairs(PrintWriter outFile)
  {
    String multKey = "";

    String first = "";
    String second = "";

    TreeMap<String, Integer> pairs = new TreeMap<String, Integer>();

//    outFile.printf("%s enter countEventPairs%n", TAG);
//    outFile.flush();

    outFile.printf("%sListing pairs of successive events (includes 750+)%n", TAG);
    outFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      for(int i = 0; i < term.getEventLogSize()-1; ++i)
      {
        first = term.getEvent(i);
        first = OneEvent.getCode(first);
        second = term.getEvent(i+1);
        second = OneEvent.getCode(second);

        if(first.equals("0001510")) continue;
        if(second.equals("0001510")) continue;

        if(first.equals("0001511")) continue;
        if(second.equals("0001511")) continue;

        if(first.equals("0001512")) continue;
        if(second.equals("0001512")) continue;

//        outFile.printf("%s PAIR '%s' '%s'%n", TAG, first, second);
//        outFile.flush();

        multKey = first + second;
        Globals.incrementTreeMap(multKey, pairs, 1);
      }
    }

    ArrayList<String> pairsReverse = new ArrayList<String>();
    for(String key: pairs.keySet())
    {
//      outFile.printf("%s PAIRCOUNT '%s' '%d'%n", TAG,
//                     key, pairs.get(key));
//      outFile.flush();
      String ss = String.format("%8d %s", pairs.get(key), key);
      pairsReverse.add(ss);
    }

    Collections.sort(pairsReverse, Collections.reverseOrder());

    for(String ss: pairsReverse)
    {
      if(Integer.valueOf(ss.substring(0,8).trim()) <= 2) continue;

      outFile.printf("%s PAIRS '%s'%n", TAG, ss);

      first = ss.substring( 9,16);
      second = ss.substring(16,23);
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     first, Globals.eventTexts.get(first));
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     second, Globals.eventTexts.get(second));
      outFile.printf("%n");
      outFile.flush();
    }

//    outFile.printf("%s leave countEventPairs%n", TAG);
//    outFile.flush();
  } // private void countEventPairs(PrintWriter outFile)

/*********************************************************************
 * Method to count quadruples of events.
**/
  private void countEventQuads(PrintWriter outFile)
  {
    String multKey = "";

    String first = "";
    String second = "";
    String third = "";
    String fourth = "";

    TreeMap<String, Integer> quads = new TreeMap<String, Integer>();

//    outFile.printf("%s enter countEventQuads%n", TAG);
//    outFile.flush();

    outFile.printf("%sListing quadruples of successive events (includes 750+)%n", TAG);
    outFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      for(int i = 0; i < term.getEventLogSize()-3; ++i)
      {
        first = term.getEvent(i);
        first = OneEvent.getCode(first);
        second = term.getEvent(i+1);
        second = OneEvent.getCode(second);
        third = term.getEvent(i+2);
        third = OneEvent.getCode(third);
        fourth = term.getEvent(i+3);
        fourth = OneEvent.getCode(fourth);

        if(first.equals("0001510")) continue;
        if(second.equals("0001510")) continue;
        if(third.equals("0001510")) continue;
        if(fourth.equals("0001510")) continue;

        if(first.equals("0001511")) continue;
        if(second.equals("0001511")) continue;
        if(third.equals("0001511")) continue;
        if(fourth.equals("0001510")) continue;

        if(first.equals("0001512")) continue;
        if(second.equals("0001512")) continue;
        if(third.equals("0001512")) continue;
        if(fourth.equals("0001510")) continue;

//        outFile.printf("%s QUAD '%s' '%s' '%s' '%s'%n", TAG,
//                       first, second, third, fourth);
//        outFile.flush();

        multKey = first + second + third + fourth;
        Globals.incrementTreeMap(multKey, quads, 1);
      }
    }

    ArrayList<String> quadsReverse = new ArrayList<String>();
    for(String key: quads.keySet())
    {
//      outFile.printf("%s QUADCOUNT '%s' '%d'%n", TAG,
//                     key, quads.get(key));
//      outFile.flush();
      String ss = String.format("%8d %s", quads.get(key), key);
      quadsReverse.add(ss);
    }

    Collections.sort(quadsReverse, Collections.reverseOrder());

    for(String ss: quadsReverse)
    {
      if(Integer.valueOf(ss.substring(0,8).trim()) <= 2) continue;

      outFile.printf("%s QUADS '%s'%n", TAG, ss);

      first   = ss.substring( 9,16);
      second  = ss.substring(16,23);
      third   = ss.substring(23,30);
      fourth  = ss.substring(30,37);
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     first, Globals.eventTexts.get(first));
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     second, Globals.eventTexts.get(second));
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     third, Globals.eventTexts.get(third));
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     fourth, Globals.eventTexts.get(fourth));
      outFile.printf("%n");
      outFile.flush();
    }

//    outFile.printf("%s leave countEventQuads%n", TAG);
//    outFile.flush();
  } // private void countEventQuads(PrintWriter outFile)

/*********************************************************************
 * Method to count triples of events.
**/
  private void countEventTriples(PrintWriter outFile)
  {
    String first = "";
    String pairKey = "";
    String second = "";
    String third = "";
    TreeMap<String, Integer> triples = new TreeMap<String, Integer>();

//    outFile.printf("%s enter countEventTriples%n", TAG);
//    outFile.flush();

    outFile.printf("%sListing triples of successive events (includes 750+)%n", TAG);
    outFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      for(int i = 0; i < term.getEventLogSize()-2; ++i)
      {
        first = term.getEvent(i);
        first = OneEvent.getCode(first);
        second = term.getEvent(i+1);
        second = OneEvent.getCode(second);
        third = term.getEvent(i+2);
        third = OneEvent.getCode(third);

        if(first.equals("0001510")) continue;
        if(second.equals("0001510")) continue;
        if(third.equals("0001510")) continue;

        if(first.equals("0001511")) continue;
        if(second.equals("0001511")) continue;
        if(third.equals("0001511")) continue;

        if(first.equals("0001512")) continue;
        if(second.equals("0001512")) continue;
        if(third.equals("0001512")) continue;

//        outFile.printf("%s TRIP '%s' '%s' '%s'%n", TAG, first, second, third);
//        outFile.flush();

        pairKey = first + second + third;
        Globals.incrementTreeMap(pairKey, triples, 1);
      }
    }

    ArrayList<String> triplesReverse = new ArrayList<String>();
    for(String key: triples.keySet())
    {
//      outFile.printf("%s TRIPCOUNT '%s' '%d'%n", TAG,
//                     key, triples.get(key));
//      outFile.flush();
      String ss = String.format("%8d %s", triples.get(key), key);
      triplesReverse.add(ss);
    }

    Collections.sort(triplesReverse, Collections.reverseOrder());

    for(String ss: triplesReverse)
    {
      if(Integer.valueOf(ss.substring(0,8).trim()) <= 2) continue;

      outFile.printf("%s TRIPS '%s'%n", TAG, ss);

      first  = ss.substring( 9,16);
      second = ss.substring(16,23);
      third  = ss.substring(23,30);
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     first, Globals.eventTexts.get(first));
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     second, Globals.eventTexts.get(second));
      outFile.printf("%s                '%s' '%s'%n", TAG,
                     third, Globals.eventTexts.get(third));
      outFile.printf("%n");
      outFile.flush();
    }

//    outFile.printf("%s leave countEventTriples%n", TAG);
//    outFile.flush();
  } // private void countEventTriples(PrintWriter outFile)

/*********************************************************************
 * Method to count the opening and closing PEBs in each pct.
**/
  private void countOpenClosePerPct()
  {
    FileUtils.logFile.printf("%s enter countOpenClosePerPct%n", TAG);
    FileUtils.logFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);

      TreeMap<String, String> pcts = term.getPcts();
      for(String key: pcts.keySet())
      {
        OnePrecinct pct = Globals.pctsByNumber.get(pcts.get(key));
//        FileUtils.logFile.printf("%s PCTKEY %s %s%n", TAG,
//                                  key, pcts.get(key));
//        FileUtils.logFile.flush();
        pct.setTerm(term.getIvoNumber());

        pct.setOpenPEB(term.getOpenPEB());
        pct.setClosePEB(term.getClosePEB());

//        FileUtils.logFile.printf("%s PCTNOW %s%n", TAG,
//                                  pct.toString());
//        FileUtils.logFile.flush();
        
      }
    }

    FileUtils.logFile.printf("%s leave countOpenClosePerPct%n", TAG);
    FileUtils.logFile.flush();
  } // private void countOpenClosePerPct()

/*********************************************************************
 * Method to count the votes per terminal.
**/
  private void countTheVotes()
  {
    Integer localCount = 0;
    String candidate = "";
    String contest = "";
    String ivo = "";
    String keyconcand = "";
    String keypctconcand = "";
    String keypctivoconcand = "";
    String keypctivostylecon = "";
    String pct = "";
    String style = "";

    FileUtils.logFile.printf("%s enter countTheVotes%n", TAG);
    FileUtils.logFile.flush();

    for(String votesKey: Globals.theVotes.keySet())
    {
      candidate = Keys.getCandidateFromTheVotesKey(votesKey);
      contest = Keys.getContestFromTheVotesKey(votesKey);
      ivo = Keys.getIvoFromTheVotesKey(votesKey);
      pct = Keys.getPctFromTheVotesKey(votesKey);
      style = Keys.getStyleFromTheVotesKey(votesKey);

      localCount = Globals.theVotes.get(votesKey);

      // add in to the contest-candidate map
      keyconcand = Keys.makeKeyForConCand(contest, candidate);
      Globals.incrementTreeMap(keyconcand, Globals.conCand, localCount);

      // add in to the pct-contest-candidate map
      keypctconcand = Keys.makeKeyForPctConCand(pct, contest, candidate);
      Globals.incrementTreeMap(keypctconcand, Globals.pctConCand, localCount);

      // add in to the pct-ivo-contest-candidate map
      keypctivoconcand = Keys.makeKeyForPctIvoConCand(pct, ivo, contest, candidate);
      Globals.incrementTreeMap(keypctivoconcand, Globals.pctIvoConCand, localCount);

      // add in to the pct-ivo-style-contest map
      keypctivostylecon = Keys.makeKeyForPctIvoStyleCon(pct, ivo, style, contest);
      Globals.incrementTreeMap(keypctivostylecon, Globals.pctIvoStyleCon, localCount);
    }

    FileUtils.logFile.printf("%s leave countTheVotes%n", TAG);
    FileUtils.logFile.flush();
  } // private void countTheVotes()

/*********************************************************************
 * Method to do the analysis.
 *
 * @param prefix the file name prefix
**/
  public void doAnalysis(String prefix, String date)
  {
    FileUtils.logFile.printf("%s enter doAnalysis%n", TAG);
    FileUtils.logFile.flush();

    //****************************************************************
    // The first task is to produce the exceptions list of things
    // that should not happen but did.
    String exceptionFileName = prefix + "_EXCEPTIONS.txt";
    PrintWriter exceptionFile = FileUtils.PrintWriterOpen(exceptionFileName);
    exceptionFile.printf("%n%n%n%n%n");
    exceptionFile.printf("********** ********** ********** ");
    exceptionFile.printf("********** ********** **********%n");
    exceptionFile.printf("********** ********** ********** ");
    exceptionFile.printf("********** ********** **********%n%n");
    exceptionFile.printf("EXCEPTIONS file for %s%n%n", Globals.getCounty());
    exceptionFile.flush();

    this.compareVoteTotals(exceptionFile);

    this.compareIvos152V155(exceptionFile);

    this.compareOpenAndClosePEB(exceptionFile);

    this.compare152And68APEBS(exceptionFile);

    this.findAnomalousOpenCloseTimes(exceptionFile, date);

    this.verifyMemoryCardCollection(exceptionFile);

    FileUtils.CloseFile(exceptionFile);

    //****************************************************************
    // Now we do the histograms and statistics on regular things.
    String statsFileName = prefix + "_Statistics.txt";
    PrintWriter statsFile = FileUtils.PrintWriterOpen(statsFileName);
    statsFile.printf("%sProcess statistics file for %s%n%n",
                   TAG, Globals.getCounty());
    statsFile.flush();

    this.statisticsProcess(statsFile);

    FileUtils.CloseFile(statsFile);

    //****************************************************************
    // Next we do histos and stats on things we think are anomalous.
    String anomalyFileName = prefix + "_StatisticsAnomalies.txt";
    PrintWriter anomalyFile = FileUtils.PrintWriterOpen(anomalyFileName);
    anomalyFile.printf("%sStatistical anomalies file for %s%n%n",
                   TAG, Globals.getCounty());
    anomalyFile.flush();

    this.statisticsAnomalies(anomalyFile);

    FileUtils.CloseFile(anomalyFile);

    FileUtils.logFile.printf("%s leave doAnalysis%n", TAG);
    FileUtils.logFile.flush();
  } // public void doAnalysis(String prefix, String date)

/*********************************************************************
 * Method to fill out data values from multiple locations and files.
 *
 * @param prefix the file name prefix
**/
  public void fillOutData(String prefix)
  {
    FileUtils.logFile.printf("%s enter fillOutData%n", TAG);
    FileUtils.logFile.flush();

    this.process152events();

    this.countTheVotes();

    this.countOpenClosePerPct();

    String combosFileName = prefix + "_EventLogCombos.txt";
    PrintWriter combosFile = FileUtils.PrintWriterOpen(combosFileName);
    combosFile.printf("%sSuccessive event combinations for %s%n%n",
                   TAG, Globals.getCounty());
    combosFile.flush();
    this.countEventPairs(combosFile);
    this.countEventTriples(combosFile);
    this.countEventQuads(combosFile);
    FileUtils.CloseFile(combosFile);

    FileUtils.logFile.printf("%s leave fillOutData%n", TAG);
    FileUtils.logFile.flush();

  } // public void fillOutData(String prefix)

/*********************************************************************
 * Method to find anomalous opening and closing times.
**/
  private void findAnomalousOpenCloseTimes(PrintWriter outFile, String electionDate)
  {
    boolean noExceptions = true;

    String time = "";
    OneTerminal term = null;
//    outFile.printf("%s enter anomaloustimes%n", TAG);
//    outFile.flush();

    String earlyTime = String.format("%s 05:30:00", Globals.getElectionDate());
    String lateTime = String.format("%s 19:45:00", Globals.getElectionDate());

    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("Listing terminals with anomalous opening/closing times%n");
    outFile.printf("Early time means time before %s%n", earlyTime);
    outFile.printf("Late time means time after   %s%n", lateTime);

    // no open time and/or no close time
    noExceptions = true;
    outFile.printf("%nListing terminals with no opening/closing times (includes 750+)%n");
    outFile.printf("All terminals not in the 152 will appear here%n");
    outFile.flush();
    for(String termKey: Globals.theTerms.keySet())
    {
      term = Globals.theTerms.get(termKey);

      if(term.getOpenDateTime().equals(Globals.DEFAULTTIME))
      {
        outFile.printf("NO OPENTIME     %s %s %s%n",
                       term.getIvoNumber(), term.getOpenDateTime(),
                       term.toStringPcts());
        outFile.flush();
        noExceptions = false;
      }
      if(term.getCloseDateTime().equals(Globals.DEFAULTTIME))
      {
        outFile.printf("NO CLOSETIME    %s %s %s%n",
                       term.getIvoNumber(), term.getCloseDateTime(),
                       term.toStringPcts());
        outFile.flush();
        noExceptions = false;
      }
    } // for(String termKey: Globals.theTerms.keySet())

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    // early open time
    noExceptions = true;
    outFile.printf("%nListing terminals with early opening times (EXCLUDES 750+)%n");
    outFile.flush();
    for(String termKey: Globals.theTerms.keySet())
    {
      term = Globals.theTerms.get(termKey);

      if(!term.isNotAbsentee()) continue;

      time = term.getOpenDateTime();
      if((Globals.compareDateTime(time, earlyTime) < 0) &&
         (!time.equals(Globals.DEFAULTTIME)))
      {
        outFile.printf("Early opentime  %s %s %s%n",
                       term.getIvoNumber(), time, term.toStringPcts());
        outFile.flush();
        noExceptions = false;
      }
    } // for(String termKey: Globals.theTerms.keySet())

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    // late open time
    noExceptions = true;
    outFile.printf("%nListing terminals with late opening times (EXCLUDES 750+)%n");
    outFile.flush();
    for(String termKey: Globals.theTerms.keySet())
    {
      term = Globals.theTerms.get(termKey);

      if(!term.isNotAbsentee()) continue;

      time = term.getOpenDateTime();
      if((Globals.compareDateTime(time, lateTime) > 0) &&
         (!time.equals(Globals.DEFAULTTIME)))
      {
        outFile.printf("Late opentime  %s %s %s%n",
                       term.getIvoNumber(), time, term.toStringPcts());
        outFile.flush();
        noExceptions = false;
      }
    } // for(String termKey: Globals.theTerms.keySet())

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    // early close time
    noExceptions = true;
    outFile.printf("%nListing terminals with early closing times (EXCLUDES 750+)%n");
    outFile.flush();
    for(String termKey: Globals.theTerms.keySet())
    {
      term = Globals.theTerms.get(termKey);

      if(!term.isNotAbsentee()) continue;

      time = term.getCloseDateTime();
      if((Globals.compareDateTime(time, earlyTime) < 0) &&
         (!time.equals(Globals.DEFAULTTIME)))
      {
        outFile.printf("Early closetime %s %s %s%n",
                       term.getIvoNumber(), time, term.toStringPcts());
        outFile.flush();
        noExceptions = false;
      }
    } // for(String termKey: Globals.theTerms.keySet())

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    // late close time
    outFile.printf("%nListing terminals with late closing times (EXCLUDES 750+)%n");
    outFile.flush();
    ArrayList<OneTerminal> lateTerminalList = new ArrayList<OneTerminal>(); 
    outFile.printf("%nListing terminals by serial number (EXCLUDES 750+)%n");
    outFile.flush();
    for(String termKey: Globals.theTerms.keySet())
    {
      term = Globals.theTerms.get(termKey);

      if(!term.isNotAbsentee()) continue;

      time = term.getCloseDateTime();
      if((Globals.compareDateTime(time, lateTime) > 0) &&
         (!time.equals(Globals.DEFAULTTIME)))
      {
        outFile.printf("Late closetime  %s %s %s%n",
                       term.getIvoNumber(), time, term.toStringPcts());
        outFile.flush();
        noExceptions = false;
        lateTerminalList.add(term);
      }
    } // for(String termKey: Globals.theTerms.keySet())

    CompareLateClose compareLateClose = new CompareLateClose();
    Collections.sort(lateTerminalList, compareLateClose);
    outFile.printf("%nListing terminals by late closing time (EXCLUDES 750+)%n");
    outFile.flush();
    for(int i = 0; i < lateTerminalList.size(); ++i)
    {
//      outFile.printf("%s%n", lateTerminalList.get(i));
      outFile.printf("Late closetime  %s %s %s%n",
                     lateTerminalList.get(i).getIvoNumber(),
                     lateTerminalList.get(i).getCloseDateTime(),
                     lateTerminalList.get(i).toStringPcts());
    }

    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

//    outFile.printf("%s leave anomaloustimes%n%n", TAG);
//    outFile.flush();
  } // private void findAnomalousOpenCloseTimes(PrintWriter outFile)

/*********************************************************************
 * Method to process the events in the event log for each terminal.
 *
 * This counts the number of votes cast and the number of late votes
 * and it sets the open and close time and PEB for a given terminal. 
**/
  private void process152events()
  {
    FileUtils.logFile.printf("%s enter process152events%n", TAG);
    FileUtils.logFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);
      term.process152events();
    }

    FileUtils.logFile.printf("%s leave process152events%n", TAG);
    FileUtils.logFile.flush();
  } // private void process152events()

/*********************************************************************
 * Method to do statistics, histograms, etc., of various sorts.
**/
  private void statisticsProcess(PrintWriter outFile)
  {
    ArrayList<ArrayList<String>> labels = null;
    ArrayList<String> temp = null;
    ArrayList<Integer> numbers = null;

    //****************************************************************
    // The first stats we do are of votes per terminal
    labels = new ArrayList<ArrayList<String>>();
    numbers = new ArrayList<Integer>();

    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%sChecking that terminal vote counts are consistent (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);

      // we test that the term is not a not-absentee term
      // because the default is to set both the booleans to true
      // so if the terminal isn't in the list then notAbsentee=true
      if(!term.isNotAbsentee()) continue;

      temp = new ArrayList<String>();
      temp.add(term.toStringPcts()); // sort first on pct number(s)
      temp.add(term.getIvoNumber()); // then on ivo number

      if((term.getVotesCast152() == Globals.DUMMYINT) &&
         (term.getVotesCast155() == Globals.DUMMYINT))
      {
        outFile.printf("%n%s Term %s votes 152=XXXXXX and 155=XXXXXX both dummy values%n",
                       TAG, term.getIvoNumber());
        outFile.flush();
      }
      else if(term.getVotesCast152() == Globals.DUMMYINT)
      {
        outFile.printf("%n%s Term %s votes 152=XXXXXX and 155=%d disagree, using 155%n",
                       TAG, term.getIvoNumber(), term.getVotesCast155());
        outFile.flush();
        labels.add(temp);
        numbers.add(term.getVotesCast155());
      }
      else if(term.getVotesCast155() == Globals.DUMMYINT)
      {
        outFile.printf("%n%s Term %s votes 152=%d and 155=XXXXXX disagree, using 152%n",
                       TAG, term.getIvoNumber(), term.getVotesCast152());
        outFile.flush();
        labels.add(temp);
        numbers.add(term.getVotesCast152());
      }
      else // if they agree, we use the 155 number
      {
        labels.add(temp);
        numbers.add(term.getVotesCast155());
      }

    }
    outFile.printf("%n%s Histogram of votes per terminal (EXCLUDES 750+)%n", TAG);
    outFile.flush();

    // silly bubble sort by pct number 
    for(int i = 0; i < labels.size()-1; ++i)
    {
      for(int j = i+1; j < labels.size(); ++j)
      {
        if(labels.get(i).get(0).compareTo(labels.get(j).get(0)) > 0)
        {
          ArrayList<String> tempList = labels.get(i);
          labels.set(i, labels.get(j));
          labels.set(j, tempList);
          Integer tempInt = numbers.get(i);
          numbers.set(i, numbers.get(j));
          numbers.set(j, tempInt);
        }
      }
    }
    Statistics.doStats(outFile, labels, numbers, 200, 4, true); // last is print

    //****************************************************************
    // Now we do stats on votes cast after closing
    labels = new ArrayList<ArrayList<String>>();
    numbers = new ArrayList<Integer>();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);

      temp = new ArrayList<String>();
      temp.add(term.getIvoNumber());
      labels.add(temp);
      numbers.add(term.getLateVotesCast152()); 
    }

    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%s Histogram of votes after closing time (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers, 50, 1, true); // last is print

    //****************************************************************
    // Now we do a histo of the closing times themselves
    labels = new ArrayList<ArrayList<String>>();
    numbers = new ArrayList<Integer>();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);

      // we test that the term is not a not-absentee term
      // because the default is to set both the booleans to true
      // so if the terminal isn't in the list then notAbsentee=true
      if(!term.isNotAbsentee()) continue;

      temp = new ArrayList<String>();
      temp.add(term.getIvoNumber());
      if(term.getCloseDateTime().equals(Globals.DEFAULTTIME))
      {
        outFile.printf("%n%s SKIPPING terminal %s, pcts %s, never closed%n",
                       TAG, term.getIvoNumber(), term.toStringPcts());
        outFile.flush();
      }
      else if(Globals.convertDateTime(term.getCloseDateTime()) < 43200)
      {
        outFile.printf("%n%s SKIPPING terminal %s, pcts %s, closed early%n",
                       TAG, term.getIvoNumber(), term.toStringPcts());
        outFile.flush();
      }
      else if(Globals.convertDateTime(term.getCloseDateTime()) > 2*43200)
      {
        outFile.printf("%n%s SKIPPING terminal %s, pcts %s, closed more than one day late%n",
                       TAG, term.getIvoNumber(), term.toStringPcts());
        outFile.flush();
      }
      else
      {
        labels.add(temp);
        // the magic number 43200 is 12*3600
        // the conversion method returns seconds after 7am, and closing
        // time is 7pm, so this, with the divide by 60, lets us histo
        // the closing times in minutes after 7pm
        numbers.add((Globals.convertDateTime(term.getCloseDateTime())-43200)/60); 
      }
//  FileUtils.logFile.printf("%s %s%n", temp, numbers.get(numbers.size()-1));
//  FileUtils.logFile.flush();
    }

    outFile.printf("%n%s Histogram of closing times (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    outFile.printf("%n%s Label is closing time in # of minutes after 7pm%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers, 90, 5, true); // last is print

    //****************************************************************
    // Now we do a histo of the actual vote times, by precinct
    labels = new ArrayList<ArrayList<String>>();
    numbers = new ArrayList<Integer>();

    outFile.printf("%n%s Histogram of vote event times during the day (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    for(String pctKey: Globals.pctsByNumber.keySet())
    {
      // we are not interested in the 750 and higher pcts
      if(pctKey.compareTo("0750") >= 0) continue;

// This is currently set up as if to do a single histogram for the
// whole county.  We really need to break this off so we can do a separate
// histo for each pct.

      temp = new ArrayList<String>();
      temp.add(pctKey);

//      outFile.printf("%n%s precincts %s%n", TAG, pctKey);
//      outFile.flush();
      OnePrecinct pct = Globals.pctsByNumber.get(pctKey);
      for(String termKey: pct.getTerminals().keySet())
      {
//        outFile.printf("%n%s precinct and terminal %s %s%n", TAG,
//                       pctKey, termKey);
//        outFile.flush();
        OneTerminal term = Globals.theTerms.get(termKey);
//        outFile.printf("%n%s precinct, terminal, 152 count %s %s %d%n", TAG,
//                       pctKey, termKey, term.getVotesCast152());
//        outFile.flush();
        for(int i = 0; i < term.getEventLogSize(); ++i)
        {
          if((OneEvent.getCode(term.getEvent(i)).equals("0001510")) ||
             (OneEvent.getCode(term.getEvent(i)).equals("0001511")) ||
             (OneEvent.getCode(term.getEvent(i)).equals("0001512")))
          {
//            String localCode = OneEvent.getCode(term.getEvent(i));
//            String localTime = OneEvent.getTime(term.getEvent(i));
            String localDateTime = OneEvent.getDateAndTime(term.getEvent(i));
            int localTimeInt = Globals.convertDateTime(localDateTime)/900;
//            outFile.printf("%n%s pct,term,vote %s %s %s %s %s %d%n", TAG,
//                     pctKey, termKey, localDateTime, localCode,
//                     localTime, localTimeInt);
//            outFile.flush();
            labels.add(temp);
            numbers.add(localTimeInt);
          }
        }
      } // for(String termKey: pct.getTerminals().keySet())
    } // for(String pctKey: Globals.pctsByNumber.keySet())

    outFile.printf("%n%s Histogram of vote event times (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    outFile.printf("%s Label is # of 15-minute intervals after 7am%n", TAG);
    outFile.printf("%s For example, '12' means 12*15=180 minutes%n", TAG);
    outFile.printf("%s    which is 3 hours after 7am, and thus is%n", TAG);
    outFile.printf("%s    the interval 10:00-10:15am%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers, 60, 1, false); // last is print

    // now we do the histos by pct
    ArrayList<ArrayList<String>> pctLabels = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> pctNumbers = new ArrayList<Integer>();
    ArrayList<String> pctTemp = new ArrayList<String>();
    String oldPct = labels.get(0).get(0);
    String newPct = "";
    for(int i = 0; i < labels.size(); ++i)
    {
      newPct = labels.get(i).get(0);
//      outFile.printf("old,new %s %s %n", oldPct, newPct);
//      outFile.flush();
      if(!oldPct.equals(newPct))
      {
//        outFile.printf("break %s %s %n", oldPct, newPct);
//        outFile.flush();
        outFile.printf("%n%s Histogram of vote event times, pct %s (EXCLUDES 750+)%n", TAG, oldPct);
        outFile.printf("%n%s There were %d vote events%n", TAG, pctNumbers.size());
        outFile.flush();
        outFile.printf("%s Label is # of 15-minute intervals after 7am%n", TAG);
        outFile.printf("%s For example, '12' means 12*15=180 minutes%n", TAG);
        outFile.printf("%s    which is 3 hours after 7am, and thus is%n", TAG);
        outFile.printf("%s    the interval 10:00-10:15am%n", TAG);
        outFile.flush();

        Statistics.doStats(outFile, pctLabels, pctNumbers, 60, 1, false);

        pctLabels = new ArrayList<ArrayList<String>>();
        pctNumbers = new ArrayList<Integer>();
        pctTemp = new ArrayList<String>();
      }

      pctTemp.add(oldPct);
      pctLabels.add(temp);
      pctNumbers.add(numbers.get(i));
      oldPct = newPct;
    } // for(int i = 0; i < labels.size(); ++i)
//    outFile.printf("break %s %s %n", oldPct, newPct);
//    outFile.flush();
    outFile.printf("%n%s Histogram of vote event times, pct %s (EXCLUDES 750+)%n", TAG, oldPct);
    outFile.printf("%n%s There were %d vote events%n", TAG, pctNumbers.size());
    outFile.flush();
    outFile.printf("%s Label is # of 15-minute intervals after 7am%n", TAG);
    outFile.printf("%s For example, '12' means 12*15=180 minutes%n", TAG);
    outFile.printf("%s    which is 3 hours after 7am, and thus is%n", TAG);
    outFile.printf("%s    the interval 10:00-10:15am%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, pctLabels, pctNumbers, 60, 1, false);

  } // private void statisticsProcess(PrintWriter outFile)

/*********************************************************************
 * Method to do statistics, histograms, etc., of various sorts.
 *
 * This is the method that we use to go hunting for anomalies. 
 *
 * @param outFile the output file to print to
**/
  private void statisticsAnomalies(PrintWriter outFile)
  {
    FileUtils.logFile.printf("%s enter statisticsAnomalies%n", TAG);

    this.statisticsAnomaliesTerminalShutdown(outFile);

    this.statisticsAnomalies2400240007061635(outFile);

    FileUtils.logFile.printf("%s leave statisticsAnomalies%n", TAG);
  } // private void statisticsAnomalies(PrintWriter outFile)

/*********************************************************************
 * Method to do statistics, histograms, etc., on the most common
 * PEB access failure quadruple.
 *
 * This is the quadruple:
 *   2400 PEB access failed
 *   2400 PEB access failed
 *   0706 Failed to retrieve EQC from PEB
 *   1635 Terminal shutdown - IPS exit
 *
 * @param outFile the output file to print to
**/
  private void statisticsAnomalies2400240007061635(PrintWriter outFile)
  {
    ArrayList<ArrayList<String>> labels = null;

    ArrayList<Integer> numbers= null;

    ArrayList<ArrayList<String>> pctLabels = null;
    ArrayList<Integer> pctNumbers = null; // totals for each pct

    ArrayList<String> temp = null;

    FileUtils.logFile.printf("%s enter statistics2400240007061635%n", TAG);

    //****************************************************************
    // We go hunting for instances of 2400, 2400, 0706, 1635
    // 2400 PEB access failed
    // 2400 PEB access failed
    // 0706 Failed to retrieve EQC from PEB
    // 1635 Terminal shutdown - IPS exit
    labels = new ArrayList<ArrayList<String>>();
    numbers = new ArrayList<Integer>();

    pctLabels = new ArrayList<ArrayList<String>>();
    pctNumbers = new ArrayList<Integer>();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);

      temp = new ArrayList<String>();
      temp.add(term.toStringPcts()); // sort first on pct number(s)
      temp.add(term.getIvoNumber()); // then on ivo number

      labels.add(temp);
      numbers.add(term.getEventCountForSequence("0002400",
                                                "0002400",
                                                "0000706",
                                                "0001635"));

    }
    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%s Histogram of '2400 2400 0706 1635' messages (includes 750+)%n", TAG);
    outFile.flush();

    // silly bubble sort by pct number 
    for(int i = 0; i < labels.size()-1; ++i)
    {
      for(int j = i+1; j < labels.size(); ++j)
      {
        if(labels.get(i).get(0).compareTo(labels.get(j).get(0)) > 0)
        {
          ArrayList<String> tempList = labels.get(i);
          labels.set(i, labels.get(j));
          labels.set(j, tempList);
          Integer tempInt = numbers.get(i);
          numbers.set(i, numbers.get(j));
          numbers.set(j, tempInt);
        }
      }
    }

    // total things up by precinct instead of by terminal
    int pctSub = 0;
    String oldPct = "";
    for(int i = 0; i < labels.size(); ++i)
    {
      String newPct = labels.get(i).get(0);
      if(oldPct.equals(newPct))
      {
        pctNumbers.set(pctSub, pctNumbers.get(pctSub) + numbers.get(i));
      }
      else
      {
        temp = new ArrayList<String>();
        temp.add(newPct);
        pctLabels.add(temp);

        pctNumbers.add(numbers.get(i));

        pctSub = pctLabels.size() - 1;

      }
      oldPct = newPct;
    } 

    // the counts by themselves
    outFile.printf("%n%s Histogram by terminal of '2400 2400 0706 1635' messages (includes 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers, 100, 4, true);

    // the counts by precinct
    outFile.printf("%n%s Histogram by precinct of '2400 2400 0706 1635' messages (includes 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, pctLabels, pctNumbers, 100, 4, true);

    FileUtils.logFile.printf("%s leave statistics2400240007061635%n", TAG);
  } // private void statisticsAnomalies2400240007061635(PrintWriter outFile)

/*********************************************************************
 * Method to do statistics, histograms, etc., on 'Terminal shutdown'.
 *
 * @param outFile the output file to print to
**/
  private void statisticsAnomaliesTerminalShutdown(PrintWriter outFile)
  {
    ArrayList<ArrayList<String>> labels = null;

    ArrayList<Integer> numbers1633 = null;
    ArrayList<Integer> numbers1634 = null;
    ArrayList<Integer> numbers1635 = null;

    ArrayList<ArrayList<String>> pctLabels = null;
    ArrayList<Integer> pctNumbers1633 = null; // totals for each pct
    ArrayList<Integer> pctNumbers1634 = null; // totals for each pct
    ArrayList<Integer> pctNumbers1635 = null; // totals for each pct

    ArrayList<String> temp = null;

    FileUtils.logFile.printf("%s enter statisticsAnomaliesTerminalShutdown%n", TAG);

    //****************************************************************
    // 1633 Terminal shutdown
    // 1634 Terminal shutdown - DIE exit
    // 1635 Terminal shutdown - IPS exit
    labels = new ArrayList<ArrayList<String>>();
    numbers1633 = new ArrayList<Integer>();
    numbers1634 = new ArrayList<Integer>();
    numbers1635 = new ArrayList<Integer>();

    pctLabels = new ArrayList<ArrayList<String>>();
    pctNumbers1633 = new ArrayList<Integer>();
    pctNumbers1634 = new ArrayList<Integer>();
    pctNumbers1635 = new ArrayList<Integer>();

    for(String termKey: Globals.theTerms.keySet())
    {
      OneTerminal term = Globals.theTerms.get(termKey);

      // we test that the term is not a not-absentee term
      // because the default is to set both the booleans to true
      // so if the terminal isn't in the list then notAbsentee=true
      if(!term.isNotAbsentee()) continue;

      temp = new ArrayList<String>();
      temp.add(term.toStringPcts()); // sort first on pct number(s)
      temp.add(term.getIvoNumber()); // then on ivo number

      labels.add(temp);
      numbers1633.add(term.getEventCount("0001633"));
      numbers1634.add(term.getEventCount("0001634"));
      numbers1635.add(term.getEventCount("0001635"));

    }
    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%s Histogram of 'Terminal shutdown' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();

    // silly bubble sort by pct number 
    for(int i = 0; i < labels.size()-1; ++i)
    {
      for(int j = i+1; j < labels.size(); ++j)
      {
        if(labels.get(i).get(0).compareTo(labels.get(j).get(0)) > 0)
        {
          ArrayList<String> tempList = labels.get(i);
          labels.set(i, labels.get(j));
          labels.set(j, tempList);
          Integer tempInt = numbers1633.get(i);
          numbers1633.set(i, numbers1633.get(j));
          numbers1633.set(j, tempInt);
          tempInt = numbers1634.get(i);
          numbers1634.set(i, numbers1634.get(j));
          numbers1634.set(j, tempInt);
          tempInt = numbers1635.get(i);
          numbers1635.set(i, numbers1635.get(j));
          numbers1635.set(j, tempInt);
        }
      }
    }

    // total things up by precinct instead of by terminal
    int pctSub = 0;
    String oldPct = "";
    for(int i = 0; i < labels.size(); ++i)
    {
      String newPct = labels.get(i).get(0);
      if(oldPct.equals(newPct))
      {
        pctNumbers1633.set(pctSub, pctNumbers1633.get(pctSub) + numbers1633.get(i));
        pctNumbers1634.set(pctSub, pctNumbers1634.get(pctSub) + numbers1634.get(i));
        pctNumbers1635.set(pctSub, pctNumbers1635.get(pctSub) + numbers1635.get(i));
      }
      else
      {
        temp = new ArrayList<String>();
        temp.add(newPct);
        pctLabels.add(temp);

        pctNumbers1633.add(numbers1633.get(i));
        pctNumbers1634.add(numbers1634.get(i));
        pctNumbers1635.add(numbers1635.get(i));

        pctSub = pctLabels.size() - 1;

      }
      oldPct = newPct;
    } 

    // the 1633 by themselves
    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%s Histogram by terminal of '0001633 Terminal shutdown' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers1633, 100, 4, true);

    // the 1633 by precinct
    outFile.printf("%n%s Histogram by precinct of '0001633 Terminal shutdown' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, pctLabels, pctNumbers1633, 100, 4, true);

    // the 1634 by themselves
    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%s Histogram by terminal of '0001634 Terminal shutdown - DIE exit' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers1634, 100, 4, true);

    // the 1634 by precinct
    outFile.printf("%n%s Histogram by precinct of '0001634 Terminal shutdown' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, pctLabels, pctNumbers1634, 100, 4, true);

    // the 1635 by themselves
    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("%s Histogram by terminal of '0001635 Terminal shutdown - IPS exit' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, labels, numbers1635, 100, 4, true);

    // the 1635 by precinct
    outFile.printf("%n%s Histogram by precinct of '0001635 Terminal shutdown' messages (EXCLUDES 750+)%n", TAG);
    outFile.flush();
    Statistics.doStats(outFile, pctLabels, pctNumbers1635, 100, 4, true);

    FileUtils.logFile.printf("%s leave statisticsAnomaliesTerminalShutdown%n", TAG);
  } // private void statisticsAnomaliesTerminalShutdown(PrintWriter outFile)

/*********************************************************************
 * Method to verify memory card collection from the terminals.
**/
  private void verifyMemoryCardCollection(PrintWriter outFile)
  {
    boolean noExceptions = true;
//    outFile.printf("%s enter verifyMemoryCardCollection%n", TAG);
//    outFile.flush();

    // terminals with no memory card collection
    outFile.printf("%n********** ********** ********** ");
    outFile.printf("********** ********** **********%n%n");
    outFile.printf("Listing of terminals whose memory card data was not collected (includes 750+)%n");
    outFile.printf("This list is of all known terminals that are not in the 68A file%n");
    outFile.flush();

    for(String termKey: Globals.theTerms.keySet())
    {              
      if(Globals.theTerms.get(termKey).toStringMemoryCollectionTime().equals(" () "))
      {
        outFile.printf("No 68A mem card data coll for term %s, pct %s%n", 
                       termKey, Globals.theTerms.get(termKey).toStringPcts());
        noExceptions = false;
      }
    }
    
    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    // dates of memory card data collection
    noExceptions = true;
    outFile.printf("%nDates of 68A first coll of mem card data (includes 750+)%n");
    outFile.flush();

    TreeMap<String, Integer> collectionMap = new TreeMap<String, Integer>();
    for(String termKey: Globals.theTerms.keySet())
    {              
      String firstCollection = Globals.theTerms.get(termKey).getMemoryCollectionFirstTime();
      firstCollection = firstCollection.substring(0,5);
      Globals.incrementTreeMap(firstCollection, collectionMap, 1);
    }

    for(String mapKey: collectionMap.keySet())
    {              
      outFile.printf(" %6d %s%n", collectionMap.get(mapKey), mapKey);
    }
    outFile.flush();

    // precincts with some data missing from the 155 file
    noExceptions = true;
    outFile.printf("%nListing of precincts with missing memory card data (EXCLUDES 750+)%n");
    outFile.flush();

    for(String pctKey: Globals.pctsByNumber.keySet())
    {              
      String pctName = Globals.pctsByNumber.get(pctKey).getPctName();
      if((pctName.trim().equals("ABSENTEE")) ||
         (pctName.trim().equals("EMERGENCY")) ||
         (pctName.trim().equals("FAILSAFE")) ||
         (pctName.trim().equals("FAILSAFE_PROVISIONAL")) ||
         (pctName.trim().equals("FAILSAFE/PROVISIONAL")) ||
         (pctName.trim().equals("FAILSAFE/PROV")) ||
         (pctName.trim().equals("EMERGENCY / PROVISIONAL")) ||
         (pctName.trim().equals("PROVISIONAL")) ||
         (pctName.trim().equals("TOTAL")))
        continue;

      if(pctKey.trim().compareTo("0750") >= 0)
        continue;

//      outFile.printf("%sZZZZ %5d %5d%n", TAG,
//                     Globals.pctsByNumber.get(pctKey).getVotes155(),
//                     Globals.pctsByNumber.get(pctKey).getVotesCast());
      if(Globals.pctsByNumber.get(pctKey).getVotes155() !=
                Globals.pctsByNumber.get(pctKey).getVotesCast())
      {
        outFile.printf("DATA MISSING ");
        outFile.printf("%s %-30s  %6s vote(s)%n",
                       pctKey, Globals.pctsByNumber.get(pctKey).getPctName(),
                       Globals.convertToGoodVoteNumber(
                       Globals.pctsByNumber.get(pctKey).getVotesCast() -
                       Globals.pctsByNumber.get(pctKey).getVotes155()
                                                      ));
        noExceptions = false;
      }
    }
    
    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

    // precincts entirely missing from the 155 file
    noExceptions = true;
    outFile.printf("%nListing of precincts entirely missing memory card data (EXCLUDES 750+)%n");
    outFile.flush();

    for(String pctKey: Globals.pctsByName.keySet())
    {              
      if((pctKey.trim().equals("ABSENTEE")) ||
         (pctKey.trim().equals("EMERGENCY")) ||
         (pctKey.trim().equals("FAILSAFE")) ||
         (pctKey.trim().equals("FAILSAFE_PROVISIONAL")) ||
         (pctKey.trim().equals("FAILSAFE/PROVISIONAL")) ||
         (pctKey.trim().equals("FAILSAFE/PROV")) ||
         (pctKey.trim().equals("EMERGENCY / PROVISIONAL")) ||
         (pctKey.trim().equals("PROVISIONAL")) ||
         (pctKey.trim().equals("TOTAL")))
        continue;
      if(null == Globals.pctsByName.get(pctKey).getPctNumber())
      {
        outFile.printf("PCT MISSING  ");
        outFile.printf("%s %s%n",
                       Globals.pctsByName.get(pctKey).getPctNumber(), pctKey);
        noExceptions = false;
      }
    }
    
    if(noExceptions)
    {
      outFile.printf("No exceptions of this type.%n");
      outFile.flush();
    }

//    outFile.printf("%s leave verifyMemoryCardCollection%n", TAG);
//    outFile.flush();
  } // private void verifyMemoryCardCollection(PrintWriter outFile)

} // public class EventLogAnalysis
