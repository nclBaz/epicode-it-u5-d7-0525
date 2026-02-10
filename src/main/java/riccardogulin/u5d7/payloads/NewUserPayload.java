package riccardogulin.u5d7.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NewUserPayload {
	// Attenzione a non confondere questa classe con la rispettiva ENTITY
	// Questa classe serve solo ed esclusivamente a rappresentare come sarà fatto il payload di creazione di un nuovo utente
	// In pratica conterrà i campi del JSON che mi viene inviato dal client
	private String name;
	private String surname;
	private String email;
	private String password;
	private int age;
}
