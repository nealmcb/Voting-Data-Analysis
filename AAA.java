/*********************************************************************
 * This "class" is really just the global documentation for the code.
 *
 * Global variables and declared and manipulated in 'Globals'.
 * 
 * The 'electionDate' is the formatted date of the election.  This
 * is read from the configuration file as 'yyyymmdd' but is then
 * reformatted and stored as 'yyyy_mm_dd'.  This is set by the
 * 'Driver' as soon as the date is read from the configuration file.
 *
 *      static private String electionDate = Globals.DUMMYDATE;
 *
 *********************************************************************
 *
 * The 'ballotCount155' is the total number of votes in the 155 file.
 * This value is set in the 'OneFiveFive' class at the end of the 
 * first pass reading the file.
 *
 *      static private int ballotCount155 = Integer.MIN_VALUE;
 *
 *********************************************************************
 *
 * The 'pctsByName' and 'pctsByNumber' maps store instances of
 * 'OnePrecinct' indexed by name and number respectively.
 *
 * 'pctsByName' stores using
 *      Globals.pctsByName.put(pct.getName(), pct);
 *
 * 'pctsByNumber' stores using
 *      Globals.pctsByNumber.put(pctNumber, pct);
 *
 * We store by name when we read the 'detail.txt' file, because
 * that's what we define to be the official precinct name.
 *
 * We store by number when we read the 155 file because that's the
 * only way to get precinct numbers.
 *
 * The precinct name is always filtered through
 * 'Constants.filterPctName' to make sure we have synced the
 * names in the 'detail' and the 155 files.
 *
 * The precinct number is always taken to be 'xxxx' with the first
 * characters as leading zeros, and this is created and returned
 * by the 'Globals.convertToPctNumber(pctNum)' method.
 *
 * I suppose we ought to do an 'ArrayList' of precincts and store
 * subscripts, not store different instances of 'OnePrecinct'.
 * But the only time we set these two maps are once in reading
 * the 'detail' file and then once in reading the 155 file, and
 * the only change is to set the 'pctNumber' value, which we do.
 *
 *      static public TreeMap<String, OnePrecinct> pctsByName;
 *      static public TreeMap<String, OnePrecinct> pctsByNumber;
 *
 *********************************************************************
 *
 * The 'pctIvoConCand' map stores vote counts by (pct, ivo, contest-candidate).
 * The 'pctConCand' map stores vote counts by (pct, contest-candidate).
 * The 'conCand' map stores vote counts by (contest-candidate).
 *
 *      static public TreeMap<String, Integer> conCand;
 *      static public TreeMap<String, Integer> pctConCand;
 *      static public TreeMap<String, Integer> pctIvoConCand;
 *
 * The keys for these are managed in 'Keys'
 *      public static String getCandidateFromPctIvoConCandKey(String key)
 *      public static String getContestFromPctIvoConCandKey(String key)
 *      public static String getIvoFromPctIvoConCandKey(String key)
 *      public static String getPctFromPctIvoConCandKey(String key)
 *      public static String makeKeyForPctIvoConCand(String pct, String ivo,
 *                                                   String contest, String candidate)
 *
 *      public static String makeKeyForPctConCand(String pct, String contest,
 *                                                String candidate)
 *      public static String getCandidateFromPctConCandKey(String key)
 *      public static String getContestFromPctConCandKey(String key)
 *      public static String getPctFromPctConCandKey(String key)
 *
 *      public static String makeKeyForConCand(String contest, String candidate)
 *      public static String getCandidateFromConCandKey(String key)
 *      public static String getContestFromConCandKey(String key)
 *
 * These are counted in the 'EventLogAnalysis.countTheVotes' method,
 * but perhaps should be counted when reading the 155 file.
 *
 *********************************************************************
 *
 *      static public TreeMap<String, Integer> ivoPctCount;
 *      static public TreeMap<String, Integer> pctIvoCount;
 *      public static String makeKeyForIvoPctCount(String pctNumber, String ivoNumber)
 *      public static String getIvoFromIvoPctCountKey(String key)
 *      public static String getPctFromIvoPctCountKey(String key)
 *
 *      public static String makeKeyForPctIvoCount(String pctNumber, String ivoNumber)
 *      public static String getIvoFromPctIvoCountKey(String key)
 *      public static String getPctFromPctIvoCountKey(String key)
 *
 *
 *********************************************************************
 *
 * WHY IS THERE NO ballotCount152 entry?

 * static public TreeMap<String, Integer> theVotes; //pct,ivo,style,seq,cand

 * static public TreeMap<String, String> eventTexts;

 * static public TreeMap<String, OnePEB> thePEBs;

 * static public TreeMap<String, OneTerminal> theTerms;
 *
 * 
 * 
 * 
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
public class AAA
{

} // public class AAA
