/*********************************************************************
 * Class to handle all the keys.
 *
 * This is to put all the magic numbers in one place.
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
public class Keys
{
/*********************************************************************
 * Instance variables for the class.
**/
//  private static final String TAG = "Keys: "; // for testing

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>conCand</code>
**/
/*********************************************************************
 * Method to extract candidate from a key for <code>conCand</code>
 *
 * @return the candidate
**/
  public static String getCandidateFromConCandKey(String key)
  {
    return key.substring(40).trim();
  } // public static String getCandidateFromConCandKey(String key)

/*********************************************************************
 * Method to extract contest from a key for <code>conCand</code>
 *
 * @return the contest
**/
  public static String getContestFromConCandKey(String key)
  {
    return key.substring(0,40).trim();
  } // public static String getContestFromConCandKey(String key)

/*********************************************************************
 * Method to create a key for <code>conCand</code>
 *
 * @param contest the contest
 * @param candidate the candidate
 *
 * @return the key
**/
  public static String makeKeyForConCand(String contest, String candidate)
  {
    return String.format("%-40s %40s", contest, candidate);
  } // public static String makeKeyForConCand(String contest, String candidate)






/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>ivoPctCount</code>
**/
/*********************************************************************
 * Method to extract ivo from a key for <code>ivoPctCount</code>
 *
 * @return the ivo
**/
  public static String getIvoFromIvoPctCountKey(String key)
  {
    return key.substring(0,8).trim();
  } // public static String getIvoFromIvoPctKey(String key)

/*********************************************************************
 * Method to extract pct from a key for <code>ivoPctCount</code>
 *
 * @return the pct
**/
  public static String getPctFromIvoPctCountKey(String key)
  {
    return key.substring(9,17).trim();
  } // public static String getPctFromIvoPctKey(String key)

/*********************************************************************
 * Method to create a key for <code>ivoPctCount</code>
 *
 * @param pctNumber the pct number
 * @param ivoNumber the ivo number
 *
 * @return the key
**/
  public static String makeKeyForIvoPctCount(String pctNumber, String ivoNumber)
  {
    return String.format("%8s %8s", pctNumber, ivoNumber);
  } // public static String makeKeyForIvoPctCount(String pctNumber, String ivoNumber)





/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>pctConCand</code>
**/
/*********************************************************************
 * Method to extract candidate from a key for <code>pctConCand</code>
 *
 * @return the candidate
**/
  public static String getCandidateFromPctConCandKey(String key)
  {
    return key.substring(54).trim();
  } // public static String getCandidateFromPctConCandKey(String key)

/*********************************************************************
 * Method to extract contest from a key for <code>pctConCand</code>
 *
 * @return the contest
**/
  public static String getContestFromPctConCandKey(String key)
  {
    return key.substring(9,54).trim();
  } // public static String getContestFromPctConCandKey(String key)

/*********************************************************************
 * Method to extract pct from a key for <code>pctConCand</code>
 *
 * @return the pct
**/
  public static String getPctFromPctConCandKey(String key)
  {
    return key.substring(0,8).trim();
  } // public static String getPctFromPctConCandKey(String key)

/*********************************************************************
 * Method to create a key for <code>pctConCand</code>
 *
 * @param pct the pct
 * @param contest the contest
 * @param candidate the candidate
 *
 * @return the key
**/
  public static String makeKeyForPctConCand(String pct, String contest,
                                            String candidate)
  {
    return String.format("%8s %-45s %45s", pct, contest, candidate);
  } // public static String makeKeyForPctConCand(String pct, String contest,





/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>pctIvoConCand</code>
**/
/*********************************************************************
 * Method to extract candidate from a key for <code>pctIvoConCand</code>
 *
 * @return the candidate
**/
  public static String getCandidateFromPctIvoConCandKey(String key)
  {
    return key.substring(65).trim();
  } // public static String getCandidateFromPctIvoConCandKey(String key)

/*********************************************************************
 * Method to extract contest from a key for <code>pctIvoConCand</code>
 *
 * @return the contest
**/
  public static String getContestFromPctIvoConCandKey(String key)
  {
    return key.substring(18,64).trim();
  } // public static String getContestFromPctIvoConCandKey(String key)

/*********************************************************************
 * Method to extract ivo from a key for <code>pctIvoConCand</code>
 *
 * @return the ivo
**/
  public static String getIvoFromPctIvoConCandKey(String key)
  {
    return key.substring(9,17).trim();
  } // public static String getIvoFromPctIvoConCandKey(String key)

/*********************************************************************
 * Method to extract pct from a key for <code>pctIvoConCand</code>
 *
 * @return the pct
**/
  public static String getPctFromPctIvoConCandKey(String key)
  {
    return key.substring(0,8).trim();
  } // public static String getPctFromPctIvoConCandKey(String key)

/*********************************************************************
 * Method to create a key for <code>pctIvoConCand</code>
 *
 * @param pct the pct
 * @param ivo the ivo
 * @param contest the contest
 * @param candidate the candidate
 *
 * @return the key
**/
  public static String makeKeyForPctIvoConCand(String pct, String ivo,
                                               String contest, String candidate)
  {
    return String.format("%8s %8s %-45s %45s", pct, ivo, contest, candidate);
  } // public static String makeKeyForPctIvoConCand





/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>pctIvoCount</code>
**/
/*********************************************************************
 * Method to extract ivo from a key for <code>pctIvoCount</code>
 *
 * @return the ivo
**/
  public static String getIvoFromPctIvoCountKey(String key)
  {
    return key.substring(9,17).trim();
  } // public static String getIvoFromPctIvoKey(String key)

/*********************************************************************
 * Method to extract pct from a key for <code>pctIvoCount</code>
 *
 * @return the pct
**/
  public static String getPctFromPctIvoCountKey(String key)
  {
    return Globals.convertToPctNumber(key.substring(0,8).trim());
  } // public static String getPctFromPctIvoKey(String key)


/*********************************************************************
 * Method to create a key for <code>pctIvoCount</code>
 *
 * @param pctNumber the pct number
 * @param ivoNumber the ivo number
 *
 * @return the key
**/
  public static String makeKeyForPctIvoCount(String pctNumber, String ivoNumber)
  {
    return String.format("%8s %8s", ivoNumber, pctNumber);
  } // public static String makeKeyForPctIvoCount(String pctNumber, String ivoNumber)


/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>theVotes</code>
**/
/*********************************************************************
 * Method to extract candidate from a key for <code>theVotes</code>
 *
 * @return the candidate string
**/
  public static String getCandidateFromTheVotesKey(String key)
  {
    return key.substring(22,61).trim().replace(" ","_");
  } // public static String getCandidateFromTheVotesKey(String key)

/*********************************************************************
 * Method to extract contest from a key for <code>theVotes</code>
 *
 * @return the contest string
**/
  public static String getContestFromTheVotesKey(String key)
  {
    return key.substring(62).trim().replace(" ","_");
  } // public static String getContestFromTheVotesKey(String key)

/*********************************************************************
 * Method to extract ivo from a key for <code>theVotes</code>
 *
 * @return the ivo string
**/
  public static String getIvoFromTheVotesKey(String key)
  {
    return key.substring(5,12);
  } // public static String getIvoFromTheVotesKey(String key)

/*********************************************************************
 * Method to extract pct from a key for <code>theVotes</code>
 *
 * @return the pct string
**/
  public static String getPctFromTheVotesKey(String key)
  {
    return key.substring(0,4);
  } // public static String getPctFromTheVotesKey(String key)

/*********************************************************************
 * Method to extract style from a key for <code>theVotes</code>
 *
 * @return the style string
**/
  public static String getStyleFromTheVotesKey(String key)
  {
    return key.substring(13,15);
  } // public static String getStyleFromTheVotesKey(String key)

/*********************************************************************
 * Method to create a key for <code>theVotes</code>
 *
 * @param pctNumber the pct number
 * @param ivoNumber the ivo number
 * @param ballotStyle the ballot style
 * @param sequence the sequence number
 * @param candidate the candidate/contest string
 *
 * @return the key
**/
  public static String makeKeyForTheVotes(String pctNumber, String ivoNumber,
                                          String ballotStyle, String sequence,
                                          String candidate)
  {
    return String.format("%4s %7s %2s %3s %s", pctNumber, ivoNumber,
                                               ballotStyle, sequence, candidate);
  } // public static String getPctFromTheVotesKey(String key)






/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>ballotCountPctStyleIvo</code>
**/
/*********************************************************************
 * Method to create a key for <code>ballotCountPctStyleIvo</code>
 *
 * @param pctNumber the pct number
 * @param style the style
 * @param ivoNumber the ivo number
 *
 * @return the key
**/
  public static String makeKeyForBallotCountPctStyleIvo(String pctNumber,
                                                        String style,
                                                        String ivoNumber)
  {
    return String.format("%5s %4s %8s", pctNumber, style, ivoNumber);
  } // public static String makeKeyForBallotCountPctStyleIvo(String pctNumber,

/*********************************************************************
 * Method to create a key for <code>ballotCountPctStyle</code>
 *
 * @param pctNumber the pct number
 * @param style the style
 *
 * @return the key
**/
  public static String makeKeyForBallotCountPctStyle(String pctNumber,
                                                     String style)
  {
    return String.format("%5s %4s", pctNumber, style);
  } // public static String makeKeyForBallotCountPctStyle(String pctNumber,







/*********************************************************************
 * METHODS FOR KEY CREATION/EXTRACTION FOR <code>ballotCountPctStyleIvo</code>
**/
/*********************************************************************
 * Method to create a key for <code>pctIvoStyleCon</code>
 *
 * @param pctNumber the pct number
 * @param style the style
 * @param ivoNumber the ivo number
 *
 * @return the key
**/
  public static String makeKeyForPctIvoStyleCon(String pctNumber,
                                                String ivoNumber,
                                                String ballotStyle,
                                                String contest)
  {
    return String.format("%5s %3s %8s %s", pctNumber, ballotStyle, ivoNumber, contest);
  } // public static String makeKeyForPctIvoStyleCon(String pctNumber,

/*********************************************************************
 * Method to extract pct from a key for the undervotes.
 *
 * @return the pct string
**/
  public static String getPctFromUndervoteKey(String key)
  {
    return key.substring(0,5).trim();
  } // public static String getPctFromUndervoteKey(String key)

/*********************************************************************
 * Method to extract style from a key for the undervotes.
 *
 * @return the pct string
**/
  public static String getStyleFromUndervoteKey(String key)
  {
    return key.substring(6,9).trim();
  } // public static String getStyleFromUndervoteKey(String key)

/*********************************************************************
 * Method to extract ivo from a key for the undervotes.
 *
 * @return the pct string
**/
  public static String getIvoFromUndervoteKey(String key)
  {
    return key.substring(10,18).trim();
  } // public static String getIvoFromUndervoteKey(String key)

/*********************************************************************
 * Method to create a key for <code>ballotCountPctStyle</code>
 *
 * @param pctNumber the pct number
 * @param style the style
 *
 * @return the key
  public static String makeKeyForBallotCountPctStyle(String pctNumber,
                                                     String style)
  {
    return String.format("%5s %4s", pctNumber, style);
  } // public static String makeKeyForBallotCountPctStyle(String pctNumber,
**/

} // public class Keys
