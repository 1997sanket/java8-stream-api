package com.kamble.streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Java8StreamApiApplication {

	private static List<Employee> employees = new ArrayList<>();

	static {
		employees = Arrays.asList(
				new Employee("Sanket", 550000.0, Arrays.asList("Hotel Booking", "TODO", "Movie Catalog")),
				new Employee("Ashok", 600000.0, Arrays.asList("Ortho", "MBBS")),
				new Employee("Martin", 800000.0, Arrays.asList("Insurance", "Accenture Legacy App")),
				new Employee("Atif", 700000.0, Arrays.asList("Diploma project", "B.E project"))
				);
	}

	public static void main(String[] args) {

		//forEach (print all employees)
		employees.stream()
				.forEach(employee -> System.out.println(employee));

		System.out.println("---------------------------------------------------------------------------");

		//filter (employees with sal < 8 lac) and collect
		List<Employee> salLessThanEightLacs =
				employees.stream()
						.filter(employee -> employee.getSalary() < 800000.0).collect(Collectors.toList());
		System.out.println(salLessThanEightLacs);

		System.out.println("---------------------------------------------------------------------------");



		//map Employee object to String object (get names only)
		List<String> names = employees.stream()
				.map(employee -> employee.getName()).collect(Collectors.toList());
		System.out.println(names);

		System.out.println("---------------------------------------------------------------------------");




		//flatMap (convert List of Lists of Projects to List of Projects, merging all lists of projects to 1 big list)
		List<String> allProjects = employees.stream() //Stream of Employees
				.map(employee -> employee.getProjects()) //Stream of Lists of Projects
				.flatMap(listOfProjects -> listOfProjects.stream()).collect(Collectors.toList());	//List of Projects

		System.out.println(allProjects);

		System.out.println("---------------------------------------------------------------------------");



		//shortCircuit operations
		List<Employee> shortCircuit = employees.stream()
				.skip(1)	//Skip 1st element
				.limit(2)	//Take only 2 elements
				.collect(Collectors.toList());
		System.out.println(shortCircuit);

		System.out.println("---------------------------------------------------------------------------");





		//sort (based on names)
		List<Employee> sortedNames = employees.stream()
				.sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName())).collect(Collectors.toList());
		System.out.println("Sorted names: " + sortedNames);

		System.out.println("---------------------------------------------------------------------------");






		//max or min (salary)
		employees.stream()
				.max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()))
				.stream()
				.forEach(employee -> System.out.println("Max salaried employee: " + employee));

		System.out.println("---------------------------------------------------------------------------");




		//reduce (sum of salaries of all employees)
		Double totalSalary = employees.stream()
				.map(employee -> employee.getSalary())
				.reduce(0.0, Double::sum);
		System.out.println("Total salary: " + totalSalary);
		System.out.println("---------------------------------------------------------------------------");





	}

}
