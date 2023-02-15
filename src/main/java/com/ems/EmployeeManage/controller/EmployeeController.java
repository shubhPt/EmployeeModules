package com.ems.EmployeeManage.controller;

import com.ems.EmployeeManage.entity.Employee;
import com.ems.EmployeeManage.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // handle list emp and return mode and view
    @GetMapping("/employees")
    public String listEmployees(Model model)
    {
        logger.info("Showing all data");
        model.addAttribute("employees",employeeService.getAllEmployees());
        // this will be return to employees.html file to show all employees
        //show all tuple
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeFrom(Model model){
        Employee employee = new Employee();
        logger.info(
                "New data been adding"
        );
        model.addAttribute("employee",employee);
        // add new tuple
        return "new_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        employeeService.saveEmployee(employee);
        logger.info(
                "Save data"
        );
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id,Model model)
    {
        model.addAttribute("employee",employeeService.getEmployeeById(id));
        //edit existing tuple
        logger.info(
                "data been search"
        );
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id ,@ModelAttribute("employee") Employee employee,Model model){
        Employee existEmployee = employeeService.getEmployeeById(id);
        existEmployee.setId(employee.getId());
        existEmployee.setFirstName(employee.getFirstName());
        existEmployee.setLastName(employee.getLastName());
        existEmployee.setEmail(employee.getEmail());
        existEmployee.setPhone(employee.getPhone());
        existEmployee.setEmpCode(employee.getEmpCode());
        logger.info(
                "data been updating"
        );
        //update tuple
        employeeService.updateEmployee(existEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        //delete a tuple
        logger.info(
                "data been deleting"
        );
        return "redirect:/employees";
    }
}
