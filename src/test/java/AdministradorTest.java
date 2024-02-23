import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import admin.Usuario;
import admin.Registro;
import exceptions.UsuarioInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ReadTextAsString;

public class AdministradorTest {

	/*@Test
	public void successfulRegistryOfAdmin() {
		assertEquals(adminRegistry().size(), 1);
	}

	@Test
	void adminRegistryWithInvalidPasswordException() {
		UsuarioInvalidoException thrown = Assertions.assertThrows(UsuarioInvalidoException.class, () -> {
			userWIthInvalidPassword();
		});

		Assertions.assertEquals("Eligio una de las contraseñas mas usadas. Por favor ingrese otra",
				thrown.getMessage());
	}

	@Test
	void adminRegistryWithLengthPassInvalid() {
		UsuarioInvalidoException thrown = Assertions.assertThrows(UsuarioInvalidoException.class, () -> {
			usuarioLongitudInvalida();
		});

		Assertions.assertEquals("La contraseña debe tener una longitudad minima de ocho caracteres",
				thrown.getMessage());
	}

	@Test
	void testSubstring() {
		String periodo = "122022";
		System.out.println(periodo.substring(2, 6));
	}

	@Test
	void testListaNegra() {
		String[] lineas = ReadTextAsString.readFileAsString("top_10000_worst_passwords.txt").split("\\n");
		for(int i=0; i < lineas.length-1;i++) {
			System.out.println(lineas[i]);
		}
	}
	private List<Usuario> adminRegistry() {
		Usuario administrador = new Usuario("grupo14", "Promocion10", "28610511","organizacion");
		Registro registro = new Registro();
		registro.registrarAdministrador(administrador);
		return registro.getAdministradores();
	}

	private void userWIthInvalidPassword() {
		Usuario administrador = new Usuario("grupo14", "123456qwerty", "28610511","organizacion");
		Registro registro = new Registro();
		registro.registrarAdministrador(administrador);
	}

	private void usuarioLongitudInvalida() {
		Usuario administrador = new Usuario("grupo14", "N13a", "28610511","organizacion");
		Registro registro = new Registro();
		registro.registrarAdministrador(administrador);
	}*/
}