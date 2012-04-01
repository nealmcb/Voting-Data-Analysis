import java.io.PrintWriter;
import java.util.ArrayList;
/*********************************************************************
 * Class to handle all the statistics.
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
public class Statistics
{
/*********************************************************************
 * Instance variables for the class.
**/
  private static final String TAG = "Stats: "; // for testing

/*********************************************************************
 * General methods.
**/

/*********************************************************************
 * Method to do the statistics.
 *
 * @param labels the list of lists of labels
 * @param numbers the list of numbers
**/
  public static void doStats(PrintWriter outFile,
                             ArrayList<ArrayList<String>> labels,
                             ArrayList<Integer> numbers,
                             int len, int bin,
                             boolean yesPrint)
  {
    int max, min;
    int totalInt;
    double dev;
    double mean;
    double tempDouble;
    double totalDouble;
//    FileUtils.logFile.printf("%s enter doStats%n", TAG);
//    FileUtils.logFile.flush();

    dev = 0.0;
    mean = 0.0;
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;

//    dumpLists(labels, numbers, mean, dev);

    totalInt = 0;
    for(int i = 0; i < labels.size(); ++i)
    {
      totalInt += numbers.get(i);
      if(numbers.get(i) < min)
        min = numbers.get(i);
      if(numbers.get(i) > max)
        max = numbers.get(i);
    }
    mean = Double.valueOf(totalInt)/numbers.size();
    outFile.printf("%s mean %d/%d = %f%n", TAG,
                             totalInt, numbers.size(), mean);
    outFile.flush();

    totalDouble = 0;
    for(int i = 0; i < labels.size(); ++i)
    {
      tempDouble = (mean - numbers.get(i)) * (mean - numbers.get(i)); 
      totalDouble += tempDouble;
    }
    dev = Math.sqrt(totalDouble/(numbers.size()-1));
    outFile.printf("%s deviation %f%n", TAG, dev);
    outFile.flush();

    if(yesPrint)
      dumpLists(outFile, labels, numbers, mean, dev);

    outFile.printf("%s min and max %d %d%n", TAG, min, max);
    outFile.flush();

    histo(outFile, len, bin, labels, numbers, mean, dev, min, max);

//    FileUtils.logFile.printf("%s leave doStats%n", TAG);
//    FileUtils.logFile.flush();

  } // public static void doStats(ArrayList<ArrayList<String>> labels,

/*********************************************************************
 * Method to dump the arraylists.
 *
 * @param labels the list of lists of labels
 * @param numbers the list of numbers
**/
  public static void dumpLists(PrintWriter outFile,
                               ArrayList<ArrayList<String>> labels,
                               ArrayList<Integer> numbers,
                               double mean, double dev)
  {
    boolean allSmaller = true;
    boolean allLarger = true;
    String oldKey = "";
    String newKey = "";
    ArrayList<String> larger = null;
    ArrayList<String> smaller = null;

//    FileUtils.logFile.printf("%s enter dumpLists%n", TAG);
//    FileUtils.logFile.flush();

    if(labels.size() != numbers.size())
    {
      FileUtils.logFile.printf("%s ERROR labels, numbers sizes disagree %d %d %n",
                               TAG, labels.size(), numbers.size());
      FileUtils.logFile.flush();
    }

    if(0 == labels.size())
    {
      FileUtils.logFile.printf("%s NO LIST TO PRINT%n", TAG);
      FileUtils.logFile.flush();
      return;
    }

    for(int i = 0; i < labels.size(); ++i)
    {
      outFile.printf("%s %s %d%n", TAG,
                               labels.get(i), numbers.get(i));
      outFile.flush();
    }

    // Now we print out the list of leading sortkey all smaller or all
    // larger than the mean.
    newKey = "";
    allSmaller = true;
    allLarger = true;
    larger = new ArrayList<String>();
    smaller = new ArrayList<String>();
    oldKey = labels.get(0).get(0);
    for(int i = 0; i < labels.size(); ++i)
    {
//      FileUtils.logFile.printf("%s %s %d%n", TAG,
//                               labels.get(i), numbers.get(i));
//      FileUtils.logFile.flush();
      newKey = labels.get(i).get(0);
//      if(oldKey.equals(""))
//      {
//        oldKey = newKey;
//        continue;
//      }

      if(oldKey.equals(newKey))
      {
        if(numbers.get(i) > mean) allSmaller = false;
        if(numbers.get(i) < mean) allLarger = false;
//        FileUtils.logFile.printf("%s oldkey,newkey, numbers, mean %s %s %d %f ",
//                                  TAG, oldKey, newKey, numbers.get(i), mean);
//        FileUtils.logFile.printf(" S L %s %s%n",
//                                  allSmaller, allLarger);
//        FileUtils.logFile.flush();
      }
      else
      {
        if(allSmaller)
        {
          smaller.add(oldKey);
//          FileUtils.logFile.printf("%s %s all smaller than the mean%n", TAG,
//                                   oldKey);
//          FileUtils.logFile.flush();
        }
        if(allLarger)
        {
          larger.add(oldKey);
//          FileUtils.logFile.printf("%s %s all larger than the mean%n", TAG,
//                                   oldKey);
//          FileUtils.logFile.flush();
        }
        oldKey = newKey;
        newKey = "";
        allSmaller = true;
        allLarger = true;
        if(numbers.get(i) > mean) allSmaller = false;
        if(numbers.get(i) < mean) allLarger = false;
      }
    }

    outFile.printf("%s The following are all larger than the mean%n", TAG);
    outFile.flush();
    for(String s: larger)
    {
      outFile.printf("%s %10s%n", TAG, s);
      outFile.flush();
    }

    outFile.printf("%s The following are all smaller than the mean%n", TAG);
    outFile.flush();
    for(String s: smaller)
    {
      outFile.printf("%s %10s%n", TAG, s);
      outFile.flush();
    }

//    FileUtils.logFile.printf("%s leave dumpLists%n", TAG);
//    FileUtils.logFile.flush();

  } // public static void dumpLists(ArrayList<ArrayList<String>> labels,

/*********************************************************************
 * Method to do a histogram.
 *
 * @param outFile the file to which to print
 * @param len the length of the bin array
 * @param bin the number of bins to combine in each line of the histo 
 * @param labels the list of lists of labels
 * @param numbers the list of numbers
 * @param mean the mean value
 * @param dev the standard deviation
 * @param min the min value of a bin with positive frequency count
 * @param max the max value of a bin with positive frequency count
**/
  public static void histo(PrintWriter outFile,
                           int len, int bin,
                           ArrayList<ArrayList<String>> labels,
                           ArrayList<Integer> numbers,
                           Double mean, Double dev, int min, int max)
  {
    int maxBinCount = 0;
    int[] histo = new int[len+bin+1];

//    FileUtils.logFile.printf("%s enter histo%n", TAG);
//    FileUtils.logFile.flush();

    //****************************************************************
    // Zero the histogram array.
    for(int i = 0; i <= len+bin; ++i) histo[i] = 0;

    //****************************************************************
    // Add the counts into the histogram array.
    for(int i = 0; i < labels.size(); ++i)
    {
      if(numbers.get(i) < 0)
      {
        ++histo[0];
      }
      else if(numbers.get(i) < len)
      {
        ++histo[numbers.get(i)];
      }
      else
      {
        ++histo[len];
      }
    }

    //****************************************************************
    // Find the maxBinCount so we can create a readable histo.
    maxBinCount = 0;
    for(int i = 0; i <= len; i += bin)
    {
      int subtotal = 0;
      for(int j = i; j <= i+bin-1; ++j)
        subtotal += histo[j];
      if(subtotal > maxBinCount)
        maxBinCount = subtotal;
    } // for(int i = 0; i <= len; i += bin)
    outFile.printf("%s The max value in any bin is %d %n", TAG, maxBinCount);
    outFile.flush();

    //****************************************************************
    // Print the histogram.
    // We divide down by the maxBinCount to create a readable histo
    // that doesn't wrap too far off the right end.
    for(int i = 0; i <= len; i += bin)
    {
      outFile.printf("%s %4d - %4d", TAG, i, i+bin-1);
      int subtotal = 0;
      for(int j = i; j <= i+bin-1; ++j)
        subtotal += histo[j];
      outFile.printf(" %4d", subtotal);

      if((Double.valueOf(mean-dev).intValue() >= i) &&
         (Double.valueOf(mean-dev).intValue() <= i+bin-1))
      {
        outFile.printf("D:");
      }
      else if((Double.valueOf(mean+dev).intValue() >= i) &&
              (Double.valueOf(mean+dev).intValue() <= i+bin-1))
      {
        outFile.printf("D:");
      }
      else if((mean.intValue() >= i) &&
              (mean.intValue() <= i+bin-1))
      {
        outFile.printf("M:");
      }
      else
      {
        outFile.printf(" :");
      }

      for(int addin = 0; addin < bin; ++addin)
      {
        for(int j = 1; j <= histo[i+addin]; ++j)
        {
          outFile.printf("*");
        }
      } // for(int addin = 0; addin < bin; ++addin)

      outFile.printf("%n");
      outFile.flush();
    }

//    FileUtils.logFile.printf("%s leave histo%n", TAG);
//    FileUtils.logFile.flush();

  } // public static void histo(ArrayList<ArrayList<String>> labels,

} // public class Statistics
