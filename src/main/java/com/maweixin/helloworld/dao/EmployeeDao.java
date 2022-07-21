package com.maweixin.helloworld.dao;

import com.maweixin.helloworld.pojo.Department;
import com.maweixin.helloworld.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工dao
@Repository
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    //员工所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>(); //创建一个部门表

        employees.put(1001, new Employee(1001,"AA","282259956@qq.com",1,new Department(101,"教学部")));
        employees.put(1002, new Employee(1002,"BB","282259956@qq.com",1,new Department(102,"市场部")));
        employees.put(1003, new Employee(1003,"CC","282259956@qq.com",1,new Department(103,"教研部")));
        employees.put(1004, new Employee(1004,"DD","282259956@qq.com",1,new Department(104,"运营部")));
        employees.put(1005, new Employee(1005,"EE","282259956@qq.com",1,new Department(105,"后勤部")));
    }

    //主键自增
    private static Integer initId = 1006;

    //新增一个员工
    public void save(Employee employee) {
        if(employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    //获得所有员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过id得到员工
    public Employee getEmploeeById(Integer id) {
        return employees.get(id);
    }

    //删除员工通过id
    public void delEmploee(Integer id) {
        employees.remove(id);
    }
}
