// import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.TreeMap;
/*********************************************************************
 * Class to handle a single instance of an iVotronic terminal.
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
public class OneTerminal
{
/*********************************************************************
 * Instance variables for the class.
**/
//   private static final String TAG = "OneTerminal: "; // for testing

  private boolean foundIn152;
  private boolean foundIn155;

  // The default setting is that a terminal is both 'absentee=true'
  // 'notAbsentee=true'.  If we find a terminal in the 155 file, we
  // change these settings to 'false' and then reset the settings
  // when we know the precincts for which we are counting votes.
  // If we only find the terminal in the 152 file, then the settings
  // will be both 'true'.
  private boolean absentee;
  private boolean notAbsentee;

  private int lateVotesCast152;
  private int votesCast152;
  private int votesCast155;

  private String closePEB;
  private String ivoNumber;
  private String openPEB;

//  private GregorianCalendar openDateTime;
//  private GregorianCalendar closeDateTime;

  // let's not get fancy yet
  private String openDateTime;
  private String closeDateTime;

  private ArrayList<String> memoryCollectionTime; // note nonstandard time format

  private ArrayList<String> events;

  private TreeMap<String, String> ballotStyles;
  private TreeMap<String, String> pcts;
  private TreeMap<String, Integer> eventCounts; // code and count of code

/*********************************************************************
 * Constructor.
**/
  public OneTerminal()
  {
    this.absentee = true;
    this.notAbsentee = true;

    this.foundIn152 = false;
    this.foundIn155 = false;

    this.lateVotesCast152 = 0;
    this.votesCast152 = Globals.DUMMYINT;
    this.votesCast155 = Globals.DUMMYINT;

    this.closePEB = Globals.DUMMYCLOSEPEB;
    this.ivoNumber = Globals.DUMMYIVONUMBER;
    this.openPEB = Globals.DUMMYOPENPEB;

//    openDateTime = new GregorianCalendar();
//    openDateTime.set(1970, 0, 0, 0, 0, 0);
//    closeDateTime = new GregorianCalendar();
//    closeDateTime.set(1970, 0, 0, 0, 0, 0);

    this.openDateTime = "1970_01_01 00:00:00";
    this.closeDateTime = "1970_01_01 00:00:00";

    this.memoryCollectionTime = new ArrayList<String>();

    this.events = new ArrayList<String>();

    this.ballotStyles = new TreeMap<String, String>();
    this.pcts = new TreeMap<String, String>();
    this.eventCounts = new TreeMap<String, Integer>();
  } // public OneTerminal()

/*********************************************************************
 * Constructor.
**/
  public OneTerminal(String number)
  {
    this.absentee = true;
    this.notAbsentee = true;

    this.foundIn152 = false;
    this.foundIn155 = false;

    this.lateVotesCast152 = 0;
    this.votesCast152 = Globals.DUMMYINT;
    this.votesCast155 = Globals.DUMMYINT;

    this.closePEB = Globals.DUMMYCLOSEPEB;
    this.ivoNumber = Globals.DUMMYIVONUMBER;
    this.openPEB = Globals.DUMMYOPENPEB;

//    openDateTime = new GregorianCalendar();
//    openDateTime.set(1970, 0, 0, 0, 0, 0);
//    closeDateTime = new GregorianCalendar();
//    closeDateTime.set(1970, 0, 0, 0, 0, 0);

    openDateTime = "1970_01_01 00:00:00";
    closeDateTime = "1970_01_01 00:00:00";

    this.memoryCollectionTime = new ArrayList<String>();

    this.events = new ArrayList<String>();

    this.ballotStyles = new TreeMap<String, String>();
    this.pcts = new TreeMap<String, String>();
    this.eventCounts = new TreeMap<String, Integer>();

    this.ivoNumber = number;
  } // public OneTerminal()

/*********************************************************************
 * Accessors and mutators.
**/
/*********************************************************************
 * Accessor for <code>absentee</code>.
 *
 * @return the value of <code>absentee</code>
**/
  public boolean isAbsentee()
  {
    return this.absentee;
  } // public boolean isAbsentee()

/*********************************************************************
 * Mutator for <code>absentee</code>.
 *
 * @param the value to set
**/
  public void setAbsentee(boolean what)
  {
    this.absentee = what;
  } // public void setAbsentee(boolean what)

/*********************************************************************
 * Accessor for <code>ballotStyles</code> list.
 *
 * @return the value of <code>ballotStyles</code>
**/
  public TreeMap<String, String> getBallotStyles()
  {
    return this.ballotStyles;
  } // public Treemap<String, String> getBallotStyles()

/*********************************************************************
 * Mutator to add a <code>ballotStyles</code>.
 *
 * @param key the ivoNumber for the value to add.
 * @param style the value to add.
**/
  public void addBallotStyles(String ivoNumber, String style)
  {
    if(null == this.ballotStyles.get(ivoNumber+style))
    {
      this.ballotStyles.put(ivoNumber+style, style);
    }
  } // public void addBallotStyles(String ivoNumber, String style)

/*********************************************************************
 * Mutator for <code>ballotStyles</code>.
 *
 * @param the value of <code>ballotStyles</code>
**/
  public void setBallotStyles(TreeMap<String, String> what)
  {
    this.ballotStyles = what;
  } // public void setBallotStyles(TreeMap<String, String> what)

/*********************************************************************
 * Accessor for <code>closePEB</code>.
 *
 * @return the value of <code>closePEB</code>
**/
  public String getClosePEB()
  {
    return this.closePEB;
  } // public String getClosePEB()

/*********************************************************************
 * Mutator for <code>closePEB</code>.
 *
 * @param the value of <code>closePEB</code>
**/
  public void setClosePEB(String what)
  {
    this.closePEB = what;
  } // public void setClosePEB(String what)

/*********************************************************************
 * Accessor for <code>closeDateTime</code>.
 *
 * @return the value of <code>closeDateTime</code>
**/
  public String getCloseDateTime()
  {
    return this.closeDateTime;
  } // public String getCloseDateTime()

/*********************************************************************
 * Accessor for an event.
 *
 * @param which the subscript of the event to return
**/
  public String getEvent(int which)
  {
    return this.events.get(which);
  } // public String getEvent(int which)

/*********************************************************************
 * Accessor for event log size.
 *
 * @return the size of the event log 
**/
  public int getEventLogSize()
  {
    return this.events.size();
  } // public void getLogSize()

/*********************************************************************
 * Accessor for an event count.
 *
 * @param which the code of the event count to return
**/
  public int getEventCount(String whichCode)
  {
    int returnValue = 0;

    if(null == this.eventCounts.get(whichCode))
      returnValue = 0;
    else
      returnValue = this.eventCounts.get(whichCode);

    return returnValue;
  } // public int getEventCount(String whichCode)

/*********************************************************************
 * Accessor for <code>foundIn152</code>.
 *
 * @return the value of <code>foundIn152</code>
**/
  public boolean getFoundIn152()
  {
    return this.foundIn152;
  } // public boolean getFoundIn152()

/*********************************************************************
 * Mutator for <code>foundIn152</code>.
 *
 * @param the value to be set
**/
  public void setFoundIn152(boolean what)
  {
    this.foundIn152 = what;
  } // public void setFoundIn152()

/*********************************************************************
 * Accessor for <code>foundIn155</code>.
 *
 * @return the value of <code>foundIn155</code>
**/
  public boolean getFoundIn155()
  {
    return this.foundIn155;
  } // public boolean getFoundIn155()

/*********************************************************************
 * Mutator for <code>foundIn155</code>.
 *
 * @param the value to be set
**/
  public void setFoundIn155(boolean what)
  {
    this.foundIn155 = what;
  } // public void setFoundIn155()

/*********************************************************************
 * Accessor for <code>ivoNumber</code>.
 *
 * @return the value of <code>ivoNumber</code>
**/
  public String getIvoNumber()
  {
    return this.ivoNumber;
  } // public String getIvoNumber()

/*********************************************************************
 * Mutator for <code>ivoNumber</code>.
 *
 * @param the value of <code>ivoNumber</code>
**/
  public void setIvoNumber(String what)
  {
    this.ivoNumber = what;
  } // public void setIvoNumber(String what)

/*********************************************************************
 * Accessor for first <code>memoryCollectionTime</code>.
 *
 * @return the first memory card data collection time, or 'never'
**/
  public String getMemoryCollectionFirstTime()
  {
    String returnValue = "never";
    if(this.memoryCollectionTime.size() > 0)
      returnValue = this.memoryCollectionTime.get(0);
    return returnValue;
  } // public String getMemoryCollectionFirstTime()

/*********************************************************************
 * Accessor for <code>memoryCollectionTime</code>.
 *
 * @return the memory card data collection time
**/
  public ArrayList<String> getMemoryCollectionTime()
  {
    return this.memoryCollectionTime;
  } // public ArrayList<String> getMemoryCollectionTime()

/*********************************************************************
 * Mutator for <code>memoryCollectionTime</code>.
 *
 * Note that if there is already data then we do nothing because we
 * are only interested in the earliest collection time.
 *
 * Note also that the time is nonstandard because the system log does
 * not have a year in its timestamp information and it doesn't run
 * on a 24-hour clock.  The input string has
 *        the date as mm-dd
 *        the time as hh:mm
 *        either 'am' or 'pm'
 *
 * @param dateAndTime
**/
  public void setMemoryCollectionTime(String dateAndTime)
  {
    this.memoryCollectionTime.add(dateAndTime);
  } // public void setMemoryCollectionTime(String dateAndTime)

/*********************************************************************
 * Accessor for <code>lateVotesCast</code>
 *
 * @return the number of late votes
**/
  public int getLateVotesCast152()
  {
    return this.lateVotesCast152;
  } // public int getLateVotesCast152()

/*********************************************************************
 * Accessor for <code>notAbsentee</code>.
 *
 * @return the value of <code>notAbsentee</code>
**/
  public boolean isNotAbsentee()
  {
    return this.notAbsentee;
  } // public boolean isNotAbsentee()

/*********************************************************************
 * Mutator for <code>notAbsentee</code>.
 *
 * @param the value to set
**/
  public void setNotAbsentee(boolean what)
  {
    this.notAbsentee = what;
  } // public void setNotAbsentee(boolean what)

/*********************************************************************
 * Accessor for <code>openPEB</code>.
 *
 * @return the value of <code>openPEB</code>
**/
  public String getOpenPEB()
  {
    return this.openPEB;
  } // public String getOpenPEB()

/*********************************************************************
 * Mutator for <code>openPEB</code>.
 *
 * @param the value of <code>openPEB</code>
**/
  public void setOpenPEB(String what)
  {
    this.openPEB = what;
  } // public void setOpenPEB(String what)

/*********************************************************************
 * Accessor for <code>openDateTime</code>.
 *
 * @return the value of <code>openDateTime</code>
**/
  public String getOpenDateTime()
  {
    return this.openDateTime;
  } // public String getOpenDateTime()

/*********************************************************************
 * Accessor for <code>pcts</code> list.
 *
 * @return the value of <code>pcts</code>
**/
  public TreeMap<String, String> getPcts()
  {
    return this.pcts;
  } // public TreeMap<String, String> getPcts()

/*********************************************************************
 * Mutator to add a pct.
 * In this method and the other add methods, we only add if it's a 
 * new instance of the data.
 *
 * @param ivoNumber the ivoNumber for the value to add.
 * @param pct the value to add.
**/
  public void addPcts(String ivoNumber, String pct)
  {
    if(null == this.pcts.get(ivoNumber+pct))
    {
      this.pcts.put(ivoNumber+pct, pct);
    }
  } // public void addPcts(String ivoNumber, String pct)

/*********************************************************************
 * Mutator for <code>pcts</code>.
 *
 * @param the value of <code>pcts</code>
**/
  public void setPcts(TreeMap<String, String> what)
  {
    this.pcts = what;
  } // public void setPcts(TreeMap<String, String> what)

/*********************************************************************
 * Accessor for <code>votesCast152</code>.
 *
 * @return the value of <code>votesCast152</code>
**/
  public int getVotesCast152()
  {
    return this.votesCast152;
  } // public int getVotesCast152()

/*********************************************************************
 * Accessor for <code>votesCast155</code>.
 *
 * @return the value of <code>votesCast155</code>
**/
  public int getVotesCast155()
  {
    return this.votesCast155;
  } // public int getVotesCast155()

/*********************************************************************
 * Mutator for <code>votesCast155</code>.
 * We need this because we don't store votes local to the terminal.
 *
 * @param howMany the number of votes cast on this terminal.
**/
  public void setVotesCast155(int howMany)
  {
    this.votesCast155 = howMany;
  } // public void setVotesCast155(int howMany)

/*********************************************************************
 * General methods.
**/
/*********************************************************************
 * Method to add a 152 event to the event log for this terminal.
 *
 * @param theEvent the event to add
**/
  public void addToEventLog(String eventString)
  {
    this.events.add(eventString);
    Globals.incrementTreeMap(OneEvent.getCode(eventString), eventCounts, 1);
  } // 

/*********************************************************************
 * Method to dump the event log counts for this terminal.
 *
 * @return the formatted <code>String</code> value.
**/
  public String dumpEventCounts()
  {
    String s = "";

    for(String key: this.eventCounts.keySet())
    {
      s += String.format("'%s' '%s' '%s' '%s'%n",
                         this.ivoNumber, this.eventCounts.get(key), key,
                         Globals.eventTexts.get(key));
    }

    return s;
  } // public String dumpEventCounts()

/*********************************************************************
 * Method to dump the event log for this terminal.
 *
 * @return the formatted <code>String</code> value.
**/
  public String dumpEvents()
  {
    String s = "";

    for(String eventString: this.events)
    {
      s += String.format("'%s'%n", eventString);
    }

    return s;
  } // public String dumpEvents()

/*********************************************************************
 * Accessor for an sequence of event counts.
 *
 * @param c1 the first code of the sequence
 * @param c2 the second code of the sequence
 * @param c3 the third code of the sequence
 * @param c4 the fourth code of the sequence
**/
  public int getEventCountForSequence(String c1, String c2, String c3, String c4)
  {
    int returnValue = 0;

    returnValue = 0;
    for(int i = 0; i < this.events.size()-3; ++i)
    {
      if((OneEvent.getCode(this.events.get(i  )).equals(c1)) &&
         (OneEvent.getCode(this.events.get(i+1)).equals(c2)) &&
         (OneEvent.getCode(this.events.get(i+2)).equals(c3)) &&
         (OneEvent.getCode(this.events.get(i+3)).equals(c4)))
      {
        ++returnValue;
      }
    }

    return returnValue;
  } // public int getEventCountForSequence(String c1, String c2, String c3, String c4)

/*********************************************************************
 * Method to process the 152 events.
 *
 * This method runs through the event list.
 *   We count the number of 'vote cast' events.
 *   We count the number of late votes cast.
 *   We get the open and close time and PEB.
**/
  public void process152events()
  {
    String code;
//     FileUtils.logFile.printf("%s enter process152events%n", TAG);

    // let's record the fact that we had a 152 file for the votes cast
    // if in fact we have events for this terminal
    if(this.events.size() > 0) this.votesCast152 = 0;

    for(String eventString: this.events)
    {
//       FileUtils.logFile.printf("%s event %s%n", TAG, event);
//       FileUtils.logFile.flush();

      code = OneEvent.getCode(eventString);
      if((code.equals("0001510")) || //vote cast by voter
         (code.equals("0001511")) || //vote cast by poll worker
         (code.equals("0001512")))   //BLANK vote cast by poll worker
      {
        ++this.votesCast152;

        if(OneEvent.getTime(eventString).compareTo("19:00:00") > 0)
        {
          ++this.lateVotesCast152;
        } // if(OneEvent.getTime(eventString).compareTo("19:00:00") > 0)

      } // if((code.equals("0001510")) || //vote cast by voter

      if(code.equals("0001672"))
      {
        this.openDateTime = OneEvent.getDateAndTime(eventString);
        this.openPEB = OneEvent.getPebNumber(eventString);
      } 

      if(code.equals("0001673"))
      {
        this.closeDateTime = OneEvent.getDateAndTime(eventString);
        this.closePEB = OneEvent.getPebNumber(eventString);
      } 

    } // for(OneEvent event: this.events)

    Globals.addToBallotCount152(this.votesCast152);

//     FileUtils.logFile.printf("%s leave process152events%n", TAG);
  } // public void process152events()

/*********************************************************************
 * Method to convert a <code>OneTerminal</code> to a
 * <code>String</code>.
 *
 * @return the formatted <code>String</code> value.
**/
  public String toString()
  {
    String s = "";

    if(this.foundIn152)
    {
      s += String.format("Y ");
    }
    else
    {
      s += String.format("N ");
    }
    if(this.foundIn155)
    {
      s += String.format("Y ");
    }
    else
    {
      s += String.format("N ");
    }
    s += String.format("%7s", this.ivoNumber);
    s += String.format(" (%7s", this.openPEB);
    s += String.format(" %s)", this.openDateTime);
    s += String.format(" (%7s", this.closePEB);
    s += String.format(" %s)", this.closeDateTime);

    s += String.format(" %8s", Globals.convertToGoodVoteNumber(this.votesCast152));
    s += String.format(" %8s", Globals.convertToGoodVoteNumber(this.votesCast155));

    s += String.format(" (");
    for(String key: this.ballotStyles.keySet())
    {
      s += String.format(" %s", this.ballotStyles.get(key));
    }
    s += String.format(") (");
    for(String key: this.pcts.keySet())
    {
      s += String.format(" %s", this.pcts.get(key));
    }
    s += String.format(")");
    s += this.toStringMemoryCollectionTime();

    return s;
  } // public String toString()

/*********************************************************************
 * Method to <code>toString</code> the memory collection times.
 *
 * @return the formatted <code>String</code> value.
**/
  public String toStringMemoryCollectionTime()
  {
    String s = "";

    s += String.format(" (");
    for(String mct: this.memoryCollectionTime)
    {
      s += String.format("(%s)", mct);
    }
    s += String.format(") ");

    return s;
  } // public String toStringMemoryCollectionTime()

/*********************************************************************
 * Method to <code>toString</code> the map of precincts.
 *
 * @return the formatted <code>String</code> value.
**/
  public String toStringPcts()
  {
    String s = "";

    s += String.format(" (");
    for(String key: this.pcts.keySet())
    {
      s += String.format(" %s", this.pcts.get(key));
    }
    s += String.format(") ");

    return s;
  } // public String toStringPcts()

} // public class OneTerminal
