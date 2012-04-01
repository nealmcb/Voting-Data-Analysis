import java.util.Scanner;
/*********************************************************************
 * Driver for processing an event log file.
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
public class Driver
{
  static final String TAG = "Driver: ";

  static private DetailDotText detail = null;
  static private EventLogAnalysis ela = null;
  static private OneFiveTwo f152 = null;
  static private OneFiveFive f155 = null;
  static private Globals globals = null;
  static private SixEightA f68a = null;

/*********************************************************************
 * Main method
**/
  public static void main (String[] args)
  {
    boolean stateData = false;
    boolean success = false;

    String county = "";
    String date = "";
    String inFileNamePrefix = "";
    String outFileNamePrefix = "";
    Scanner configFile = null;

    //********************************************************************
    // Open the log file for messages.
    // Open the configuration file for configuration information, which
    // includes the county names and election dates.
//    FileUtils.SetLogFile("zlogfile");
    configFile = FileUtils.ScannerOpen("zconfigfile");

    while(configFile.hasNext())
    {
      globals = new Globals(); // must create all those treemaps

      detail = new DetailDotText(); // must get new copy
      ela = new EventLogAnalysis(); // must get new copy
      f155 = new OneFiveFive(); // must get new copy
      f152 = new OneFiveTwo(); // must get new copy
      f68a = new SixEightA(); // must get new copy

      //********************************************************************
      // Read the configuration file for this execution of the program.
      // Read the county names and election dates and create the filename.
      // Note that file names are relative to current directory location.
//      FileUtils.logFile.printf("%n%s County name and election date%n", TAG);
//      FileUtils.logFile.flush();
      county = configFile.next();
      stateData = false;
      if((county.length() > 5) &&
        (county.substring(county.length()-5).equals("State")))
      {
        county = county.substring(0,county.length()-5);
        stateData = true;
      }
  
      date = configFile.next();

      //********************************************************************
      // Create the prefixes for the input and output file names.
      outFileNamePrefix = createOutFileNamePrefix(county, date, stateData);
      inFileNamePrefix = createInFileNamePrefix(county, date, stateData);
      String logFileName = String.format("%s_zlogfile.txt", outFileNamePrefix);
      FileUtils.SetLogFile(logFileName);

      Globals.setElectionDate(date);
      Globals.setCounty(county, stateData);
      FileUtils.logFile.printf("%s log file for %s%n", TAG, Globals.getCounty());
      FileUtils.logFile.flush();
  
      //********************************************************************
      // Get the list of pct names and numbers, and count the votes at the
      // finest gradation possible.
      // This requires reading the detail file for the pct names and the
      // 155 file for the pct numbers and for the vote data.
      FileUtils.logFile.printf("%s read the detail data%n", TAG);
      FileUtils.logFile.flush();
      success = detail.readPctDetail(inFileNamePrefix);

      if(!success)
      {
        FileUtils.logFile.printf("%s skipping this county%n", TAG);
        FileUtils.logFile.flush();
        continue; // go on to the next entry in the config file
      }

      FileUtils.logFile.printf("%s read the 155 file%n", TAG);
      FileUtils.logFile.flush();
      success = f155.read155(inFileNamePrefix);

      if(!success)
      {
        FileUtils.logFile.printf("%s skipping this county%n", TAG);
        FileUtils.logFile.flush();
        continue; // go on to the next entry in the config file
      }

      //********************************************************************
      // Get the 152 data, adding message lists to the terminals as well
      // as open, close, etc.
      FileUtils.logFile.printf("%s read the 152 file%n", TAG);
      FileUtils.logFile.flush();
      success = f152.read152(inFileNamePrefix);

      if(!success)
      {
        FileUtils.logFile.printf("%s skipping this county%n", TAG);
        FileUtils.logFile.flush();
        continue; // go on to the next entry in the config file
      }

      //********************************************************************
      // Get the 68A data, pulling in uploads of PEB totals and uploads
      // of memory card data
      FileUtils.logFile.printf("%s read the 68A file%n", TAG);
      FileUtils.logFile.flush();
      success = f68a.read68A(inFileNamePrefix);

      // in the case of the 68A file, we can process without it
      // this is different from not having the files in the above reads
      if(!success)
      {
        FileUtils.logFile.printf("%s 68A system log file not present%n", TAG);
        FileUtils.logFile.flush();
      }

      //********************************************************************
      // now we process the data
      FileUtils.logFile.printf("%s fill out the data across files%n", TAG);
      FileUtils.logFile.flush();
      ela.fillOutData(outFileNamePrefix);

      //********************************************************************
      // We can dump pcts by ivo and ivos by pct counts
      // output the pct totals by ivo to show missing 155 data.
      // THIS SEEMS TO BE NECESSARY TO BE RUN BEFORE THE ELA IN ORDER
      // TO SET THE PCT TOTALS OF VOTES.
      FileUtils.logFile.printf("%s output the pct totals by ivo%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputIvosAndPcts(outFileNamePrefix);

      //********************************************************************
      // now we process the data
      FileUtils.logFile.printf("%s process the data using ELA%n", TAG);
      FileUtils.logFile.flush();
      ela.doAnalysis(outFileNamePrefix, date);

      //********************************************************************
      // Output the detailed vote totals by pct,ivo,style
      FileUtils.logFile.printf("%s output detail data%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputVotes(outFileNamePrefix);
  
      //********************************************************************
      // After we read the 152 we know all the different event messages.
      // Output all the event message texts found in the 152.
      FileUtils.logFile.printf("%s output the event text list%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputEventTexts(outFileNamePrefix);

      //********************************************************************
      // After we read the 152 we have an idea of the pebs as well.
      // Output the list of PEBs found in the 152 file.
      FileUtils.logFile.printf("%s output the PEB list%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputPEBs(outFileNamePrefix);

      //********************************************************************
      // Output the event logs themselves, by ivo
      FileUtils.logFile.printf("%s output the event logs by ivo%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputEventLogs(outFileNamePrefix);

      //********************************************************************
      // Output the event log counts, by ivo
      FileUtils.logFile.printf("%s output the event log counts by ivo%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputEventLogCounts(outFileNamePrefix);
  
      //********************************************************************
      // After we do the analysis we know more about the terminals.
      // output the ivo list with full status info
      FileUtils.logFile.printf("%s output the ivo terminal list%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputTerminals(outFileNamePrefix);

      //********************************************************************
      // We can report contest candidate totals overall
      FileUtils.logFile.printf("%s output the contest-candidate totals%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputContestCandidate(outFileNamePrefix);

      //********************************************************************
      // We can report contest candidate totals by pct
      FileUtils.logFile.printf("%s output the pct-contest-candidate totals%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputPctContestCandidate(outFileNamePrefix);

      //********************************************************************
      // We can report contest candidate totals by pct and ivo
      FileUtils.logFile.printf("%s output the pct-ivo-contest-candidate totals%n",
                               TAG);
      FileUtils.logFile.flush();
      Globals.outputPctIvoContestCandidate(outFileNamePrefix);

      //********************************************************************
      // Output the precinct summaries with numbers.
      FileUtils.logFile.printf("%s output precinct list by name and number%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputPcts(outFileNamePrefix);

      //********************************************************************
      // Output the ballot counts by pct, style, and ivo
      FileUtils.logFile.printf("%s output ballot counts by pct, style, and ivo%n", TAG);
      FileUtils.logFile.flush();
      Globals.outputBallotCountPctStyleIvo(outFileNamePrefix);
  
    } // while(configFile.hasNext())

    //********************************************************************
    // We're done.  Close up and go home.
    FileUtils.CloseLogFile();
    FileUtils.logFile.printf("%s done with main%n",TAG);
  }

/*********************************************************************
 * Open the output file for printing.
**/
  static public String createInFileNamePrefix (String county,
                                                String date,
                                                boolean stateData)
  {
    String inFileNamePath = "../../VotingData/Data/";
    String inFileNamePrefix = "";

    if(stateData)
    {
      inFileNamePrefix = inFileNamePath + county + "State/" + county + "_" + date;
    }
    else
    {
      inFileNamePrefix = inFileNamePath + county + "/" + county + "_" + date;
    }

    return inFileNamePrefix;
  } // static public String createInFileNamePrefix (String county,

/*********************************************************************
 * Open the output file for printing.
**/
  static public String createOutFileNamePrefix(String county,
                                               String date,
                                               boolean stateData)
  {
    String outFileNamePrefix = "";

    if(stateData)
    {
      outFileNamePrefix = county + "State" + "_" + date;
    }
    else
    {
      outFileNamePrefix = county + "_" + date;
    }

    return outFileNamePrefix;
  } // static public String createOutFileNamePrefix (String county,

} // public class Driver
