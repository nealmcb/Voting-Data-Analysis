import java.util.ArrayList;
/*********************************************************************
 * Class to handle a single instance of a PEB.
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
public class OnePEB
{
/*********************************************************************
 * Instance variables for the class.
**/
//  private static final String TAG = "OnePEB: "; // for testing

  private boolean usedForClosing;
  private boolean foundInSystemLog;
  private String pebNumber;
  private String type;
  private ArrayList<String> voteCollectionTime;

/*********************************************************************
 * Constructor.
**/
  public OnePEB()
  {
    this.usedForClosing = false;
    this.foundInSystemLog = false;
    this.pebNumber = "dummypebnumber";
    this.type = "dummytype";
    this.voteCollectionTime = new ArrayList<String>();
  } // public OnePEB()

/*********************************************************************
 * Constructor.
**/
  public OnePEB(String number)
  {
    this.usedForClosing = false;
    this.foundInSystemLog = false;
    this.pebNumber = number.trim();
    this.type = "dummytype";
    this.voteCollectionTime = new ArrayList<String>();
  } // public OnePEB(String number)

/*********************************************************************
 * Constructor.
**/
  public OnePEB(String number, String type)
  {
    this.usedForClosing = false;
    this.foundInSystemLog = false;
    this.pebNumber = number.trim();
    this.type = type;
    this.voteCollectionTime = new ArrayList<String>();
  } // public OnePEB(String number, String type)

/*********************************************************************
 * Accessors and mutators.
**/
/*********************************************************************
 * Accessor for <code>foundInSystemLog</code>.
 *
 * @return the value of <code>foundInSystemLog</code>
**/
  public boolean getFoundInSystemLog()
  {
    return this.foundInSystemLog;
  } // public boolean getFoundInSystemLog()

/*********************************************************************
 * Mutator for <code>foundInSystemLog</code>.
 *
 * @param the value of <code>foundInSystemLog</code>
**/
  public void setFoundInSystemLog(boolean what)
  {
    this.foundInSystemLog = what;
  } // public void setFoundInSystemLog(boolean what)

/*********************************************************************
 * Accessor for <code>pebNumber</code>.
 *
 * @return the value of <code>pebNumber</code>
**/
  public String getPebNumber()
  {
    return this.pebNumber;
  } // public String getPebNumber()

/*********************************************************************
 * Mutator for <code>pebNumber</code>.
 *
 * @param the value of <code>pebNumber</code>
**/
  public void setPebNumber(String what)
  {
    this.pebNumber = what;
  } // public void setPebNumber(String what)

/*********************************************************************
 * Accessor for <code>type</code>.
 *
 * @return the value of <code>type</code>
**/
  public String getType()
  {
    return this.type;
  } // public String getType()

/*********************************************************************
 * Mutator for <code>type</code>.
 *
 * @param the value of <code>type</code>
**/
  public void setType(String what)
  {
    this.type = what;
  } // public void setType(String what)

/*********************************************************************
 * Accessor for <code>usedForClosing</code>.
 *
 * @return the value of <code>usedForClosing</code>
**/
  public boolean getUsedForClosing()
  {
    return this.usedForClosing;
  } // public boolean getUsedForClosing()

/*********************************************************************
 * Mutator for <code>usedForClosing</code>.
 *
 * @param the value of <code>usedForClosing</code>
**/
  public void setUsedForClosing(boolean what)
  {
    this.usedForClosing = what;
  } // public void setUsedForClosing(boolean what)

/*********************************************************************
 * Accessor for <code>voteCollectionTime</code>.
 *
 * @return the value of <code>voteCollectionTime</code>
**/
  public ArrayList<String> getVoteCollectionTime()
  {
    return this.voteCollectionTime;
  } // public ArrayList<String> getVoteCollectionTime()

/*********************************************************************
 * Mutator for <code>voteCollectionTime</code>.
 *
 * We only record the first collection time.
 *
 * @param the value of <code>voteCollectionTime</code>
**/
  public void setVoteCollectionTime(String what)
  {
    this.voteCollectionTime.add(what);
  } // public void setVoteCollectionTime(String what)

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to convert a <code>PEB</code> to a
 * <code>String</code>.
 *
 * @return the formatted <code>String</code> value.
**/
  public String toString()
  {
    String s = "";

    s += String.format("%7s", this.pebNumber);
    s += String.format(" %3s", this.type);

    s += String.format("   Closing in 152, In 68A: ");
    if(this.usedForClosing)
      s += String.format("YES ");
    else
      s += String.format("NO  ");

    if(this.foundInSystemLog)
      s += String.format("YES %s", this.toStringVoteCollectionTime());
    else
      s += String.format("NO ");

    return s;
  } // public String toString()

/*********************************************************************
 * Method to toString the the vote collection times.
 *
 * @return the formatted <code>String</code> value.
**/
  public String toStringVoteCollectionTime()
  {
    String s = "";

    for(String vct: voteCollectionTime)
      s += String.format("(%s)", vct);

    return s;
  } // public String toStringVoteCollectionTime()

} // public class OnePEB
