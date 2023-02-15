package com.ems.EmployeeManage.respository;

import com.ems.EmployeeManage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
