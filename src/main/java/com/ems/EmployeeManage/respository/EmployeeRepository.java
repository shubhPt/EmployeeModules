package com.ems.EmployeeManage.respository;

import com.ems.EmployeeManage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    /*
    * we have created repository to interact with employee from database
    * we can use jpaRepository method without implementing them methods such as findOne(),findById(),findAll()
    */
}
