import java.util.TreeMap;
/*********************************************************************
 * Class to handle a single instance of precinct data.
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
public class OnePrecinct
{
/*********************************************************************
 * Instance variables for the class.
**/
//   private static final String TAG = "OnePrecinct: "; // for testing

  private int voters;
  private int votes155;
  private int votesCast;
  private String turnout;
  private String pctNumber;
  private String name;

  private TreeMap<String, String> closePEBs;
  private TreeMap<String, String> openPEBs;
  private TreeMap<String, String> terminals;

/*********************************************************************
 * Constructor.
**/
  public OnePrecinct()
  {
    this.pctNumber = Globals.convertToPctNumber(0);
    this.voters = Globals.DUMMYINT;
    this.votes155 = Globals.DUMMYINT;
    this.votesCast = Globals.DUMMYINT;
    this.turnout = Globals.DUMMYSTRING;
    this.name = Globals.DUMMYSTRING;

    closePEBs = new TreeMap<String, String>();
    openPEBs = new TreeMap<String, String>();
    terminals = new TreeMap<String, String>();

  } // public OnePrecinct()

/*********************************************************************
 * Constructor.
**/
  public OnePrecinct(String line, int break1, int break2, int break3)
  {
    this.name = line.substring(0,break1).trim().toUpperCase();
    this.name = Constants.filterPctName(this.name);
//    FileUtils.logFile.printf("%s NAME Y%sY%n", TAG, this.name);
//    FileUtils.logFile.flush();
    if(this.name.equals("ABSENTEE"))
    {
      this.name = "ABSENTEE";
    }
    if(this.name.equals("EMERGENCY"))
    {
      this.name = "EMERGENCY";
    }
    if(this.name.equals("FAILSAFE"))
    {
      this.name = "FAILSAFE";
    }
    if(this.name.equals("FAILSAFE_PROVISIONAL"))
    {
      this.name = "FAILSAFE_PROVISIONAL";
    }
    if(this.name.equals("PROVISIONAL"))
    {
      this.name = "PROVISIONAL";
    }
    if(this.name.equals("TOTAL:"))
    {
      this.name = "TOTAL";
    }

    this.voters = Integer.valueOf(line.substring(break1,break2).trim());
    this.votes155 = Globals.DUMMYINT;
    this.votesCast = Integer.valueOf(line.substring(break2,break3).trim());
    this.turnout = line.substring(break3).trim();

    closePEBs = new TreeMap<String, String>();
    openPEBs = new TreeMap<String, String>();
    terminals = new TreeMap<String, String>();

  } // public OnePrecinct()

/*********************************************************************
 * Accessors and mutators.
**/
/*********************************************************************
 * Accessor for the size of <code>getClosePEBs</code>.
 *
 * @return the size of <code>getClosePEBs</code>
**/
  public int getClosePEBSize()
  {
    return this.closePEBs.size();
  } // public int getClosePEBSize()

/*********************************************************************
 * Accessor for <code>name</code>.
 *
 * @return the value of <code>name</code>
**/
  public String getPctName()
  {
    return this.name;
  } // public String getPctName()

/*********************************************************************
 * Accessor for <code>pctNumber</code>.
 *
 * @return the value of <code>pctNumber</code>
**/
  public String getPctNumber()
  {
    return this.pctNumber;
  } // public String getPctNumber()

/*********************************************************************
 * Mutator for <code>pctNumber</code>.
 *
 * @param the value of <code>pctNumber</code>
**/
  public void setPctNumber(String what)
  {
    this.pctNumber = Globals.convertToPctNumber(what);
//    FileUtils.logFile.printf("SET PRECINCT X%sX%n", this.pctNumber);
  } // public void setPctNumber(String what)

/*********************************************************************
 * Accessor for <code>terminals</code>.
 *
 * @return the value of <code>terminals</code>
**/
  public TreeMap<String, String> getTerminals()
  {
    return this.terminals;
  } // public TreeMap<String, String> getTerminals()

/*********************************************************************
 * Accessor for <code>voters</code>.
 *
 * @return the value of <code>voters</code>
**/
  public int getVoters()
  {
    return this.voters;
  } // public int getVoters()

/*********************************************************************
 * Accessor for <code>votes155</code>.
 *
 * @return the value of <code>votes155</code>
**/
  public int getVotes155()
  {
    return this.votes155;
  } // public int getVotes155()

/*********************************************************************
 * Mutator for <code>votes155</code>.
 *
 * @parm the value of <code>votes155</code> to set
**/
  public void setVotes155(int howMany)
  {
// FileUtils.logFile.printf("set155 %s %s %d%n", this.pctNumber, this.name, howMany);
    this.votes155 = howMany;
  } // public void setVotes155(int howMany)

/*********************************************************************
 * Accessor for <code>votesCast</code>.
 *
 * @return the value of <code>votesCast</code>
**/
  public int getVotesCast()
  {
    return this.votesCast;
  } // public int getVotesCast()

/*********************************************************************
 * Accessor for <code>turnout</code>.
 *
 * @return the value of <code>turnout</code>
**/
  public String getTurnout()
  {
    return this.turnout;
  } // public String getTurnout()

/*********************************************************************
 * Accessor for the <code>name</code>.
 *
 * @return the value of <code>name</code>.
**/
  public String getName()
  {
    return this.name;
  } // public String getName()

/*********************************************************************
 * General methods.
**/
/*********************************************************************
 * Method to set a closing peb number for this pct.
 *
 * @param the peb number to set
**/
  public void setClosePEB(String peb)
  {
    this.closePEBs.put(peb, peb);
  } // public void setClosePEB(String peb)

/*********************************************************************
 * Method to set an opening peb number for this pct.
 *
 * @param the peb number to set
**/
  public void setOpenPEB(String peb)
  {
    this.openPEBs.put(peb, peb);
  } // public void setOpenPEB(String peb)

/*********************************************************************
 * Method to set a terminal number for this pct.
 *
 * @param the terminal number to set
**/
  public void setTerm(String term)
  {
    this.terminals.put(term, term);
  } // public void setTerm(String term)

/*********************************************************************
 * Method to convert a <code>OnePrecinct</code> to a
 * <code>String</code>.
 *
 * @return the formatted <code>String</code> value.
**/
  public String toString()
  {
    String s = "";

    s += String.format(" %4s", this.pctNumber);
    s += String.format(" %-30s", this.name);

    s += String.format(" %8s", Globals.convertToGoodVoteNumber(this.voters));
    s += String.format(" %8s", Globals.convertToGoodVoteNumber(this.votesCast));

    s += String.format(" %8s", this.turnout);

    s += String.format(" (");
    for(String term: this.terminals.keySet())
    {
      s += String.format(" %7s", term);
    }
    s += String.format(")");

    s += String.format(" (");
    for(String peb: this.openPEBs.keySet())
    {
      s += String.format(" %7s", peb);
    }
    s += String.format(")");

    s += String.format(" (");
    for(String peb: this.closePEBs.keySet())
    {
      s += String.format(" %7s", peb);
    }
    s += String.format(")");

    return s;
  } // public String toString()

/*********************************************************************
 * Method to <code>toString</code> <code>closePEBs</code>.
 *
 * @return the expected string
**/
  public String toStringClosePEBs()
  {
    String s = String.format(" (");
    for(String peb: this.closePEBs.keySet())
    {
      s += String.format(" %7s", peb);
    }
    s += String.format(")");

    return s;
  } // public String toStringClosePEBs()

/*********************************************************************
 * Method to <code>toString</code> <code>openPEBs</code>.
 *
 * @return the expected string
**/
  public String toStringOpenPEBs()
  {
    String s = String.format(" (");
    for(String peb: this.openPEBs.keySet())
    {
      s += String.format(" %7s", peb);
    }
    s += String.format(")");

    return s;
  } // public String toStringOpenPEBs()

/*********************************************************************
 * Method to <code>toString</code> the <code>terminals</code>.
 *
 * @return the expected string
**/
  public String toStringTerminals()
  {
    String s = String.format(" (");
    for(String term: this.terminals.keySet())
    {
      s += String.format(" %7s", term);
    }
    s += String.format(")");

    return s;
  } // public String toStringTerminals()

} // public class OnePrecinct
