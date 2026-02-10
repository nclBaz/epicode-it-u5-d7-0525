package riccardogulin.u5d7.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@ToString
public class User {
	private long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private int age;
	private LocalDateTime createdAt;

	public User(String name, String surname, String email, String password, int age) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.age = age;
		this.createdAt = LocalDateTime.now();
		Random rndm = new Random();
		this.id = rndm.nextInt(1, 1000);
	}
}
