package com.thuannluit.quanlynhansu.controller;

import com.thuannluit.quanlynhansu.model.Employee;
import com.thuannluit.quanlynhansu.service.EmployeeNotFoundException;
import com.thuannluit.quanlynhansu.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController được dùng trước các class, các method trong class này sẽ trả về text thay vì trả về view
//chú thích lớp này là RESTful Controller
public class EmployeeServiceController {
    private final EmployeeService employeeService;

    public EmployeeServiceController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //@GetMapping phuong thuc GET, link /employees, tra ve danh sach doi tuong
    @GetMapping(value = "/employees")
    public List<Employee> index() {
        return employeeService.getAll();
    }

    //@GetMapping phuong thuc GET, link /employees/find-by-name/{name}, tra ve danh sach doi tuong theo ten
    //@PathVariable lay bien name tu duong dan
    @GetMapping(value = "/employees/find-by-name/{name}")
    public List<Employee> findEmployeeByName(@PathVariable String name) {
        return employeeService.findNameEmployee(name);
    }

    // @PostMapping phuong thuc POST, link /employees, tao moi doi tuong
    //@RequestBody giúp chuyển đổi dữ liệu gửi lên thành đối tượng (khi gửi request sẽ gửi body gồm dữ liệu dạng JSON)
    @PostMapping(value = "/employees")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    //@DeleteMapping phuong thuc DELETE, link /employees/{id}, xoa doi tuong theo id
    //@PathVariable lay bien id tu duong dan
    @DeleteMapping(value = "/employees/{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    //@PutMapping phuong thuc PUT, link /employees/{id}, chinh sua doi tuong theo id
    @PutMapping(value = "/employees/{id}")
    //@PathVariable lay bien id tu duong dan
    //@RequestBody giúp chuyển đổi dữ liệu gửi lên thành đối tượng (khi gửi request sẽ gửi body gồm dữ liệu dạng JSON)
    public Employee update(@PathVariable(value = "id") Integer id, @RequestBody Employee employeeDetails) throws EmployeeNotFoundException {
        return employeeService.updateEmployee(id, employeeDetails);
    }

}
