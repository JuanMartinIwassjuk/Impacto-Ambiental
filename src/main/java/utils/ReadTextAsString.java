package utils;

import exceptions.ReadFileAsStringException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadTextAsString {
  public static String readFileAsString(String fileName) {
    try {
      return new String(Files
          .readAllBytes(Paths.get(ClassLoader
              .getSystemResource(fileName).toURI())), StandardCharsets.UTF_8);
    } catch (Exception error) {
      throw new ReadFileAsStringException(
          "No se pudo leer el archivo " + fileName + " como String."
      );
    }
  }
}
