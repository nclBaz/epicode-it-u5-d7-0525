package riccardogulin.u5d7.controllers;

import org.springframework.web.bind.annotation.*;
import riccardogulin.u5d7.entities.User;
import riccardogulin.u5d7.payloads.NewUserPayload;

@RestController // @RestController != @Controller!!!!
// @Controller è l'annotazione da NON USARE! E'meno specifica di @RestController per la creazione di endpoints REST quindi ci costringerebbe
// a fare del lavoro extra
// Entrambe le annotazioni sono specializzazioni di @Component pertanto all'avvio Spring istanzierà un oggetto di questa classe e lo inserirà
// nello scatolone
@RequestMapping("/examples") // <-- Così assegno un "prefisso" comune a tutti gli endpoint di questo controller
public class ExamplesController {

	@GetMapping // GET su http://localhost:3001/examples
	public String getExample1() {
		return "Ciao sono l'endpoint che risponde alle richieste GET su http://localhost:3001/examples";
	}

	@PostMapping // POST su http://localhost:3001/examples
	public String postExample1() {
		return "Ciao sono l'endpoint che risponde alle richieste POST su http://localhost:3001/examples";
	}

	@GetMapping("/getExample") // <-- Tra le parentesi posso definire la parte finale dell'indirizzo (quella che viene dopo /examples)
	// GET su http://localhost:3001/examples/getExample
	public String getExample() {
		return "Ciao sono l'endpoint che risponde alle richieste GET su http://localhost:3001/examples/getExample";
	}

	@PostMapping("/postExample") // POST su http://localhost:3001/examples/postExample
	public String postExample() {
		return "Ciao sono l'endpoint che risponde alle richieste POST su http://localhost:3001/examples/postExample";
	}

	@PutMapping("/putExample") // PUT su http://localhost:3001/examples/putExample
	public String putExample() {
		return "Ciao sono l'endpoint che risponde alle richieste PUT su http://localhost:3001/examples/putExample";
	}

	@PatchMapping("/patchExample") // PATCH su http://localhost:3001/examples/patchExample
	public String patchExample() {
		return "Ciao sono l'endpoint che risponde alle richieste PATCH su http://localhost:3001/examples/patchExample";
	}

	@DeleteMapping("/deleteExample") // DELETE su http://localhost:3001/examples/deleteExample
	public String deleteExample() {
		return "Ciao sono l'endpoint che risponde alle richieste DELETE su http://localhost:3001/examples/deleteExample";
	}

	// ************************************************ QUERY PARAMETERS ******************************************************
	@GetMapping("/queryParametersExample")
	// Per contattare questo endpoint dovrò mandare una richiesta GET su
	// http://localhost:3001/examples/queryParametersExample?name=Aldo&surname=Baglio&age=20
	public String queryParamsExample(@RequestParam(required = false) String name, @RequestParam String surname, @RequestParam int age) {
		// Di default i parametri sono tutti OBBLIGATORI, cioè se non li passo ottengo una risposta 400 BAD REQUEST
		// Volendo posso cambiare questo comportamento e renderli OPZIONALI @RequestParam(required = false)
		// N.B. Se un parametro opzionale non viene passato il suo valore sarà NULL con tutte le conseguenze del caso se non gestito --> NullPointerException
		return "I parametri inseriti sono " + name.toLowerCase() + ", " + surname + "," + age;
	}

	// *********************************************** PATH PARAMETERS *********************************************************
	@GetMapping("/pathParameterExample/{pathParameter}")
	// Per contattare questo endpoint dovrò mandare una richiesta GET su
	// http://localhost:3001/examples/pathParameterExample/1234
	// http://localhost:3001/examples/pathParameterExample/4121
	// N.B. Il segnaposto tra le graffe deve chiamarsi in maniera IDENTICA al parametro specificato nelle tonde del metodo
	public String pathParamExample(@PathVariable String pathParameter) {
		return "Il parametro che hai inserito è: " + pathParameter;
	}

	// ************************************************** REQUEST BODY **********************************************************
	@PostMapping("/requestBodyExample")
	public User requestBodyExample(@RequestBody NewUserPayload body) {
		// Cosa fondamentale oltre ad usare l'annotazione @RequestBody, è quella di definire correttamente il TIPO del payload
		// String non va proprio bene con contenuti JSON, una mossa ben più furba è quella di dichiarare un tipo ad hoc, cioè
		// una classe apposita che rappresenti le caratteristiche di quel payload specifico
		// Così facendo Spring Web convertirà il JSON in entrata in un oggetto di quella classe che potrò quindi poi usare nel codice
		// (ad es. più avanti lo salveremo nel DB)
		System.out.println(body);
		return new User(body.getName(), body.getSurname(), body.getEmail(), body.getPassword(), body.getAge());
		// Anche per quanto riguarda il payload della response, invece che usare String (il quale mi tornerebbe payload
		// di tipo text/plain) usare una MIA CLASSE AD HOC
		// Spring Web mi convertirà l'oggetto JAVA in un payload JSON che verrà inviato come risposta
	}
}
