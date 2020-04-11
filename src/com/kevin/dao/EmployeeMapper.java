package com.kevin.dao;

import com.kevin.mybatis.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);
}
