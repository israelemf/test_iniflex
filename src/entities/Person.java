package entities;

import java.time.LocalDate;

//1– Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
public class Person {

	private String name;
	private LocalDate birthD;

	public Person() {
	}

	public Person(String name, LocalDate birthD) {
		this.name = name;
		this.birthD = birthD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthD() {
		return birthD;
	}

	public void setBirthD(LocalDate birthD) {
		this.birthD = birthD;
	}

}
