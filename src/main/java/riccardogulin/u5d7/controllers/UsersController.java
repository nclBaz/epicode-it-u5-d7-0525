package riccardogulin.u5d7.controllers;

// ********************************** USERS CRUD **************************************
/*

1. GET http://localhost:3001/users --> 200 OK con array di utenti
2. POST http://localhost:3001/users (+payload) --> 201 CREATED con utente appena creato
3. GET http://localhost:3001/users/{userId} --> 200 OK con utente trovato (404 se utente non trovato ma lo faremo domani)
4. PUT http://localhost:3001/users/{userId} (+payload) --> 200 OK con utente modificato (404 se utente non trovato ma lo faremo domani)
5. DELETE http://localhost:3001/users/{userId} --> 204 NO CONTENT (404 se utente non trovato ma lo faremo domani)

*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardogulin.u5d7.entities.User;
import riccardogulin.u5d7.payloads.NewUserPayload;
import riccardogulin.u5d7.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

	private final UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	// 1. GET http://localhost:3001/users
	@GetMapping
	public List<User> findAll() {
		return this.usersService.findAll();
	}

	// 2. POST http://localhost:3001/users (+payload)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public User createUser(@RequestBody NewUserPayload payload) {
		return this.usersService.saveUser(payload);
	}

	// 3. GET http://localhost:3001/users/{userId}
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable long userId) {
		return this.usersService.findById(userId);
	}

	// 4. PUT http://localhost:3001/users/{userId} (+payload)
	@PutMapping("/{userId}")
	public User getUserByIdAndUpdate(@PathVariable long userId, @RequestBody NewUserPayload payload) {
		return this.usersService.findByIdAndUpdate(userId, payload);
	}

	//	// 5. DELETE http://localhost:3001/users/{userId}
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void getUserByIdAndDelete(@PathVariable long userId) {
		this.usersService.findByIdAndDelete(userId);
	}
}
