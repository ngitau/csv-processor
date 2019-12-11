package com.processor.csv.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CSVReader provides an array of functions for CSV operations
 *
 * created by Joseph G.
 */

public class CSVReader {
  private static final char DELIMITER = ',';
  private static final char QUOTE = '"';

  /**
   *
   * @param line  String of characters from CSV line
   * @return List of strings
   */
  public static List<String> parseLine(String line) {
    return parseLine(line, DELIMITER, QUOTE);
  }

  /**
   *
   * @param line String of characters from CSV line
   * @param delimiter Character that marks end of a CSV column
   * @param customQuote Quote characters to be escaped in column
   * @return List of strings
   */
  private static List<String> parseLine(String line, char delimiter, char customQuote) {

    List<String> result = new ArrayList<>();

    // if line is empty, return empty list!
    if (line == null && line.isEmpty()) {
      return result;
    }

    // Defines quote character to escape
    if (customQuote == ' ') {
      customQuote = QUOTE;
    }

    // Defaults to the comma as the CSV delimiter
    if (delimiter == ' ') {
      delimiter = DELIMITER;
    }

    // Creates a string buffer to allow method calls on string
    StringBuffer stringBuffer = new StringBuffer();

    // Flags
    boolean inQuotes = false;
    boolean startCollectChar = false;
    boolean doubleQuotesInColumn = false;

    // Create character array from the line
    char[] chars = line.toCharArray();

    // Loop through every character
    for (char ch : chars) {

      if (inQuotes) {
        startCollectChar = true;
        if (ch == customQuote) {
          inQuotes = false;
          doubleQuotesInColumn = false;
        } else {

          // Allows empty quotes in custom quote enclosed
          if (ch == '\"') {
            if (!doubleQuotesInColumn) {
              stringBuffer.append(ch);
              doubleQuotesInColumn = true;
            }
          } else {
            stringBuffer.append(ch);
          }

        }
      } else {
        if (ch == customQuote) {

          inQuotes = true;

          // Allows empty quotes empty quote enclosed
          if (chars[0] != '"' && customQuote == '\"') {
            stringBuffer.append('"');
          }

          // If double quotes present
          if (startCollectChar) {
            stringBuffer.append('"');
          }

        } else if (ch == delimiter) {

          result.add(stringBuffer.toString());

          stringBuffer = new StringBuffer();
          startCollectChar = false;

        } else if (ch == '\r') {
          //ignore LF characters
          continue;
        } else if (ch == '\n') {
          //the end, break!
          break;
        } else {
          stringBuffer.append(ch);
        }
      }

    }

    // Add string buffer to result array
    result.add(stringBuffer.toString());

    return result;
  }

}
