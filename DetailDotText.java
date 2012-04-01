import java.util.Scanner;
/*********************************************************************
 * Class for handling the detail.txt file of SCSEC results.
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
public class DetailDotText
{
/*********************************************************************
 * Instance variables.
**/
  static final String TAG = "DDT: "; // for testing

/*********************************************************************
 * Constructor.
**/
  public DetailDotText()
  {
  } // public DetailDotText()

/*********************************************************************
 * Accessors and mutators.
**/

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to read the precinct detail data.
 *
 * @param the <code>Scanner</code> from which to read.
 * @return true or false according as the file exists
**/
  public boolean readPctDetail(String fileNamePrefix)
  {
    int break1, break2, break3;
    String detailLine;

    FileUtils.logFile.printf("%s enter readpctdetail\n", TAG);
    FileUtils.logFile.flush();

    Scanner inFile = null;
    inFile = Globals.OpenInputFile(fileNamePrefix, "_detail.txt");
    if(null == inFile)
    {
      FileUtils.logFile.printf("%s FILE '%s' NOT FOUND%n", TAG,
                               fileNamePrefix+"_detail.txt");
      FileUtils.logFile.flush();
      return false;
    }

    break1 = -1;
    break2 = -1;   
    break3 = -1;
    while(inFile.hasNext())
    {
      detailLine = inFile.nextLine();
//      FileUtils.logFile.printf("%s line X%sX\n", TAG, detailLine);
//      FileUtils.logFile.flush();
      if(detailLine.trim().compareTo("") == 0) continue;

      if(detailLine.substring(0,8).compareTo("Precinct") == 0)
      {
        break1 = detailLine.indexOf("Registered");
        break2 = detailLine.indexOf("Ballots");
        break3 = detailLine.indexOf("Voter Turnout");
      }
      else
      {
        OnePrecinct pct = new OnePrecinct(detailLine, break1, break2, break3);
        Globals.pctsByName.put(pct.getName(), pct);
      }

      if(detailLine.substring(0,6).compareTo("Total:") == 0)
      {
        break;
      }
      
    } // while(inFile.hasNext())

    FileUtils.logFile.printf("%s leave readpctdetail\n", TAG);
    FileUtils.logFile.flush();
    return true;
  } // public boolean readPctDetail(Scanner inFile)

} // public class DetailDotText
