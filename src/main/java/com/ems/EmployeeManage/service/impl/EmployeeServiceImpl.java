package com.ems.EmployeeManage.service.impl;

import com.ems.EmployeeManage.controller.EmployeeController;
import com.ems.EmployeeManage.entity.Employee;
import com.ems.EmployeeManage.helper.ExcelHelper;
import com.ems.EmployeeManage.respository.EmployeeRepository;
import com.ems.EmployeeManage.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public List<Employee> uploadEmployee(MultipartFile file) {
        List<Employee> employees = null;
        try {
            employees = ExcelHelper.excelToEmployees(file.getInputStream());
            employeeRepository.saveAll(employees);
            if(employees.size()==0)
            {
                logger.info("Having error file is not upload so the size is zero");
                return employees;
            }
            for(int i=0;i<10;i++)
            {
                System.out.println(employees.get(i).getId());
                System.out.println(employees.get(i).getEmail());
                System.out.println(employees.get(i).getEmpCode());
                System.out.println(employees.get(i).getFirstName());
                System.out.println(employees.get(i).getLastName());
                System.out.println(employees.get(i).getPhone());
            }
        } catch (IOException e) {
            logger.info("Having error file is not upload ~|~");
            throw new RuntimeException(e);
        }
        return employees;
    }
}
