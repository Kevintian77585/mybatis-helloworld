package com.kevin.dao;

import com.kevin.mybatis.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);
    public void addEmp(Employee employee);
    public void updateEmp(Employee employee);
    public void deleteById(Integer id);


}
