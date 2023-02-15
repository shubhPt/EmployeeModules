package com.ems.EmployeeManage;

import com.ems.EmployeeManage.entity.Employee;
import com.ems.EmployeeManage.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication

public class EmployeeManagementApplication implements CommandLineRunner {

	public static void main(String[] args)
	{
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@GetMapping("/")
	public GreetResponse greet()
	{
		return new GreetResponse("Hello : Change URL to localhost:8080/employees",
				List.of("Java","C++"),
				new Person("Spring boot",20,987654321,500000.0));
	}

	record Person(String name,int age,long phone,double salary){

	}

	record GreetResponse(
			String greet,
			List<String> favLanguage,
			Person person
	){}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) {

		Employee employee_1 = new Employee("me","back","sh.dh@gmail.com","83XXXX6500","12345");
		Employee employee_2 = new Employee("jat","","jt@gmail.com","8XXXXXX500","T0002");
		Employee employee_3 = new Employee("why","tel","wy.te@gmail.com","8306000500","T1234");
		try{
			employeeRepository.save(employee_1);
			employeeRepository.save(employee_2);
			employeeRepository.save(employee_3);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
