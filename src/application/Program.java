package application;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import entities.Employee;

public class Program {

	public static void main(String[] args) throws ParseException {

		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
		// acima.
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Maria", LocalDate.parse("18/10/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(2009.44), "Operador"));
		employees.add(new Employee("João", LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(2284.38), "Operador"));
		employees.add(new Employee("Caio", LocalDate.parse("02/05/1961", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(9836.14), "Coordenador"));
		employees.add(new Employee("Miguel", LocalDate.parse("14/10/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(19119.88), "Diretor"));
		employees.add(new Employee("Alice", LocalDate.parse("05/01/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(2234.68), "Recepcionista"));
		employees.add(new Employee("Heitor", LocalDate.parse("19/11/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(1582.72), "Operador"));
		employees.add(new Employee("Arthur", LocalDate.parse("31/03/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(4071.84), "Contador"));
		employees.add(new Employee("Laura", LocalDate.parse("08/07/1994", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(3017.45), "Gerente"));
		employees.add(new Employee("Heloísa", LocalDate.parse("24/05/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(1606.85), "Eletricista"));
		employees.add(new Employee("Helena", LocalDate.parse("02/09/1996", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				new BigDecimal(2799.93), "Gerente"));

		employees.removeIf(employee -> employee.getName() == "João"); // Remover o funcionário “João” da lista.

		// 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
		// informação de data deve ser exibido no formato dd/mm/aaaa;
		// informação de valor numérico deve ser exibida no formatado com separador de
		// milhar como ponto e decimal como vírgula.

		System.out.println("Lista com todos os funcionários");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%7s %15s %12s %15s", "Nome", "Data Nascimento", "Salário", "Função");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");

		employees.stream().forEach((e) -> {
			System.out.format("%7s %12s %15s %15s", e.getName(),
					e.getBirthD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					NumberFormat.getCurrencyInstance().format(e.getSalary()), e.getFunction());
			System.out.println();
		});
		System.out.println("-----------------------------------------------------------------------------");

		// 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista
		// de funcionários com novo valor.
		employees.stream().forEach((e) -> {
			BigDecimal salary = e.getSalary().add(e.getSalary().multiply(new BigDecimal(0.1)));
			e.setSalary(salary);
		});

		// 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função”
		// e o valor a “lista de funcionários”.
		Map<String, List<Employee>> result = employees.stream().collect(Collectors.groupingBy(Employee::getFunction));

		// 3.6 – Imprimir os funcionários, agrupados por função.
		System.out.println();
		System.out.println("Funcionários agrupados por função");
		System.out.println("-----------------------------------------------------------------------------");
		result.entrySet().stream().forEach((e) -> {
			System.out.println(e.getKey() + ":" + e.getValue());
		});
		System.out.println("-----------------------------------------------------------------------------");

		// 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		List<Employee> birth = employees.stream()
				.filter(e -> e.getBirthD().getMonth().getValue() == 10 | e.getBirthD().getMonth().getValue() == 12)
				.collect(Collectors.toList());

		System.out.println();
		System.out.println("Lista atualizada com funcionários que fazem aniversário no mês 10 e 12");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%7s %15s %12s %15s", "Nome", "Data Nascimento", "Salário", "Função");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");

		birth.stream().forEach((e) -> {
			System.out.format("%7s %12s %15s %15s", e.getName(),
					e.getBirthD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					NumberFormat.getCurrencyInstance().format(e.getSalary()), e.getFunction());
			System.out.println();
		});

		System.out.println("-----------------------------------------------------------------------------");

		// 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e
		// idade.
		System.out.println();
		System.out.println("Funcionário com a maior idade");
		System.out.println("-----------------------------------------------------------------------------");
		Employee s = employees.stream().min(Comparator.comparing(Employee::getBirthD)).get();

		int years = Period.between(s.getBirthD(), LocalDate.now()).getYears();
		int months = Period.between(s.getBirthD(), LocalDate.now()).getMonths();
		int days = Period.between(s.getBirthD(), LocalDate.now()).getDays();

		System.out.println("Funcionário mais velho: " + s + " | " + "Idade: " + years + " anos, " + months + " meses, "
				+ days + " dias");

		// 3.10 – Imprimir a lista de funcionários por ordem alfabética.
		System.out.println();
		System.out.println("Lista de funcionários em ordem alfabética");
		System.out.println("-----------------------------------------------------------------------------");
		employees.stream().sorted().forEach((e) -> {
			System.out.format("%7s %12s %15s %15s", e.getName(),
					e.getBirthD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					NumberFormat.getCurrencyInstance().format(e.getSalary()), e.getFunction());
			System.out.println();
		});

		// 3.11 – Imprimir o total dos salários dos funcionários.
		Optional<BigDecimal> soma = employees.stream().map(e -> e.getSalary()).reduce((x, y) -> x.add(y));
		System.out.println(
				"\nTotal dos salários dos funcionários: " + NumberFormat.getCurrencyInstance().format(soma.get()));

		// 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando
		// que o salário mínimo é R$1212.00.
		System.out.println();
		System.out.println(
				"Quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00");
		System.out.println("-----------------------------------------------------------------------------");

		employees.stream().forEach((e) -> {
			if (e.getSalary().doubleValue() >= 1212) {
				BigInteger quantidade = e.getSalary().divideToIntegralValue(new BigDecimal(1212)).toBigInteger();
				System.out.println(e.getName() + " Qtd salários minimos: " + quantidade);
			}
		});
		System.out.println("-----------------------------------------------------------------------------");
	}
}
