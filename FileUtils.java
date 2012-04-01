import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*********************************************************************
 * FileUtils class of static constants and basic file checking routines.
 * This is the header file of constants and methods that might be
 * used in any and all contexts.
 *
 * Methods:
 * public static void CheckArgs(int countArgs,String [] args,String usage)
 * public static void CloseFile(PrintWriter theFile)
 * public static void CloseFile(Scanner theFile)
 * public static void CloseLogFile()
 * public static PrintWriter PrintWriterOpen(String outFileName)
 * public static PrintWriter PrintWriterOpen(String outFileName)
 * public static Scanner ScannerOpen(String inFileName)
 * public static void SetLogFile(String logFileName)
 * public static void SetLogFile(PrintWriter logFile)
 *
 * @author Duncan Buell
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
public class FileUtils
{
  private static final String TAG = "FileUtils: ";

  public static PrintWriter logFile = null;

/*********************************************************************
 * Accessor methods
**/
/*********************************************************************
 * Mutator methods
**/

/*********************************************************************
 * General methods
**/
/*********************************************************************
 * Method to check the number of arguments to the main.
 *
 * @param countArgs the number of required arguments
 * @param args the argument list at the time the class is invoked
 * @param usage descriptions of the required arguments
**/
  public static void CheckArgs(int countArgs,String [] args,String usage)
  {
    String message;
    if(countArgs > args.length)
    {
      if(1 == countArgs)
      {
        message  = "ERROR: required " + countArgs +
                   " (" + usage + ") arg not present";
      }
      else
      {
        message  = "ERROR: required " + countArgs +
                   " (" + usage + ") args not present";
      }
      FileUtils.logFile.printf("%s%n",message);
      FileUtils.logFile.flush();
      System.exit(1);
    }
  } // public static void checkArgs(int countArgs,String [] args,String usage)

/*********************************************************************
 * Method to close a PrintWriter file.
 * @param theFile the <code>PrintWriter</code> file to close.
**/
  public static void CloseFile(PrintWriter theFile)
  {
//    FileUtils.logFile.printf("%s enter (PrintWriter) CloseFile%n",TAG);
    theFile.flush();
    theFile.close();
//    FileUtils.logFile.printf("%s leave (PrintWriter) CloseFile%n",TAG);
  } // public static void CloseFile(PrintWriter theFile)

/*********************************************************************
 * Method to close a Scanner file.
 * @param theFile The <code>Scanner</code> file to close.
**/
  public static void CloseFile(Scanner theFile)
  {
//    FileUtils.logFile.printf("%s enter (Scanner) CloseFile%n",TAG);
    theFile.close();
//    FileUtils.logFile.printf("%s leave (Scanner) CloseFile%n",TAG);
  } // public static void CloseFile(Scanner theFile)

/*********************************************************************
 * Method to close the PrintWriter class log file.
**/
  public static void CloseLogFile()
  {
//    FileUtils.logFile.printf("%s enter (PrintWriter) CloseLogFile%n",TAG);
    FileUtils.logFile.flush();
    FileUtils.logFile.close();
//    FileUtils.logFile.printf("%s leave (PrintWriter) CloseLogFile%n",TAG);
  } // public static void CloseLogFile()

/*********************************************************************
 * PrintWriterOpen method to open a file as a PrintWriter.
 * 
 * The main purpose of this method is to do the error checking in
 * a subordinate method so as not to clutter up the code flow
 * in methods that have to open files.
 * 
 * @param  outFileName the <code>String</code> name of the file to open
 * @return The opened <code>PrintWriter</code> known to be not null
**/
  public static PrintWriter PrintWriterOpen(String outFileName)
  {
    PrintWriter localPrintWriter = null;

//    FileUtils.logFile.printf("%s enter PrintWriterOpen%n",TAG);

    if(outFileName.equals("System.out"))
    {
      localPrintWriter = new PrintWriter(System.out);
    }
    else
    {
      try
      {
        localPrintWriter = new PrintWriter(new File(outFileName));
      }
      catch (FileNotFoundException fileException)
      {
        FileUtils.logFile.println(TAG + "FILE ERROR opening outFile " +
                                         outFileName);
        FileUtils.logFile.println(fileException.getMessage());
        FileUtils.logFile.println("in" + System.getProperty("user.dir"));
        FileUtils.logFile.flush();
        System.exit(1);
      }
      catch (SecurityException secException)
      {
        FileUtils.logFile.println(TAG + "SECURITY ERROR opening outFile " +
                                         outFileName);
        FileUtils.logFile.println(secException.getMessage());
        FileUtils.logFile.println("in" + System.getProperty("user.dir"));
        FileUtils.logFile.flush();
        System.exit(1);
      }
    }

//    FileUtils.logFile.printf("%s leave PrintWriterOpen%n",TAG);
    return localPrintWriter;
  } // public static PrintWriter PrintWriterOpen(String outFileName)

/*********************************************************************
 * ScannerOpen method to open a file as a Scanner.
 * 
 * The main purpose of this method is to do the error checking in
 * a subordinate method so as not to clutter up the code flow
 * in methods that have to open files.
 * 
 * @param  inFileName the <code>String</code> name of the file to open
 * @return The opened <code>Scanner</code> known to be not null
**/
  public static Scanner ScannerOpen(String inFileName)
  {
    Scanner localScanner = null;

//    FileUtils.logFile.printf("%s enter ScannerOpen%n",TAG);
    if(inFileName.equals("System.in"))
    {
      localScanner = new Scanner(System.in);
    }
    else
    {
      try
      {
        localScanner = new Scanner(new File(inFileName));
      }
      catch (FileNotFoundException ex)
      {
        FileUtils.logFile.println("TAG + ERROR opening inFile " + inFileName);
        FileUtils.logFile.println(ex.getMessage());
        FileUtils.logFile.println("in" + System.getProperty("user.dir"));
        FileUtils.logFile.flush();
//        System.exit(1);
      }
    }

//    FileUtils.logFile.printf("%s leave ScannerOpen%n",TAG);
    return localScanner;
  } // public static Scanner ScannerOpen(String inFileName)

/*********************************************************************
 * Method to set the logFile given the name of the file.
 *
 * @param logFileName the <code>String</code> name of the log file.
**/
  public static void SetLogFile(String logFileName)
  {
    FileUtils.logFile = FileUtils.PrintWriterOpen(logFileName);
  }

/*********************************************************************
 * Method to set the logFile given the <code>PrintWriter</code> file.
 *
 * @param logFile the <code>PrintWriter</code> log file.
**/
  public static void SetLogFile(PrintWriter logFile)
  {
    FileUtils.logFile = logFile;
  }

/*********************************************************************
 * Method to convert a string to a double with error checking.
 *
 * @param ss the <code>String</code> value to convert.
 * @param value the default value for an empty string.
 * @return the converted value as a <code>Double</code>.
**/
  static public Double StringToDouble(String ss, Double value)
  {
    Double returnValue;

    returnValue = value;
    ss = ss.trim();
    if(0 != ss.length())
    {
//    FileUtils.logFile.printf("nonempty string %s%n", ss);
      returnValue = Double.valueOf(ss);
    }

    return returnValue;
  } // static public Double stringToDouble(String s, Double value)

/*********************************************************************
 * Method to convert a string to an integer with error checking.
 *
 * @param ss the <code>String</code> value to convert.
 * @param value the default value for an empty string.
 * @return the converted value as an <code>Integer</code>.
**/
  static public Integer StringToInteger(String ss, Integer value)
  {
    Integer returnValue;

    returnValue = value;
    ss = ss.trim();
    if(0 != ss.length())
    {
//    FileUtils.logFile.printf("nonempty string %s%n", ss);
      returnValue = Integer.valueOf(ss);
    }

    return returnValue;
  } // static public Integer stringToInteger(String s, Integer value)

} // public class FileUtils
