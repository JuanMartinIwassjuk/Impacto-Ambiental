import org.junit.jupiter.api.Test;

import exceptions.ReadFileAsStringException;
import org.junit.jupiter.api.Assertions;
import utils.ReadTextAsString;

public class ReadFileTest {

	@Test
	void ReadFIleAsStringException() {
		String fileName = "no existe";
		ReadFileAsStringException thrown = Assertions.assertThrows(ReadFileAsStringException.class, () -> {
			ReadTextAsString.readFileAsString(fileName);
		});

		Assertions.assertEquals("No se pudo leer el archivo " + fileName + " como String.", thrown.getMessage());
	}
}
