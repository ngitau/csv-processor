package com.processor.csv.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Class CSVWriter provides a function to write lists of strings into a CSV file
 *
 * created by Joseph G.
 */

public class CSVWriter {

  // Declare default delimiter
  private static final char DELIMITER = ',';

  /**
   *
   * @param writer File Writer
   * @param line List of string that go on one line
   * @param delimiter Set CSV delimiter
   * @param quote Quote characters
   * @throws IOException
   */
  public static void writeLine(Writer writer, List<String> line, char delimiter, char quote) throws IOException {

    // flags
    boolean first = true;

    // Set default delimiter
    if (delimiter == ' ') {
      delimiter = DELIMITER;
    }

    // Create string builder
    StringBuilder sb = new StringBuilder();

    // Iterate for every value
    for (String value : line) {
      if (!first) {
        sb.append(delimiter);
      }
      if (quote == ' ') {
        sb.append(format(value));
      } else {
        sb.append(quote).append(format(value)).append(quote);
      }

      first = false;
    }
    sb.append("\n");

    // Write String
    writer.append(sb.toString());

  }

  /**
   *
   * @param value String
   * @return String
   */
  private static String format(String value) {
    String result = value;
    if (result.contains("\"")) result = result.replace("\"", "\"\"");
    return result;

  }

}