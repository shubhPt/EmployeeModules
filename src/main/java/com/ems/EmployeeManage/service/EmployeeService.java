package com.ems.EmployeeManage.service;

import com.ems.EmployeeManage.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    //save more than 1 employee data on server
    List<Employee> uploadEmployee(MultipartFile file);
}
