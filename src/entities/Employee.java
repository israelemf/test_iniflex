package entities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

//2 – Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Employee extends Person implements Comparable<Employee> {

	private BigDecimal salary;
	private String function;

	public Employee() {
		super();
	}

	public Employee(String name, LocalDate birthD, BigDecimal salary, String function) {
		super(name, birthD);
		this.salary = salary;
		this.function = function;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public int hashCode() {
		return Objects.hash(function, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(function, other.function) && Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return this.getName() + " | " + "Birth Date: "
				+ this.getBirthD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | " + "Salary: "
				+ NumberFormat.getCurrencyInstance().format(this.salary);
	}

	@Override
	public int compareTo(Employee o) {
		return this.getName().toUpperCase().compareTo(o.getName().toUpperCase());
	}
}
