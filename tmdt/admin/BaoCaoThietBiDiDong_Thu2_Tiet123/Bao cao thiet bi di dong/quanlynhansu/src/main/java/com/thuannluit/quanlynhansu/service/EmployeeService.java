package com.thuannluit.quanlynhansu.service;

import com.thuannluit.quanlynhansu.model.Employee;
import com.thuannluit.quanlynhansu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
//@Service biểu thị đây là một  Service component trong tầng business
public class EmployeeService {
    @Autowired
    //Annotation này trong spring giúp tự động liên kết các Bean lại với nhau
    private EmployeeRepository employeeRepository;

    // lay het danh sach nhan vien
    //findAll() ham co san,tra ve toan bo danh sach co trong database
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // Tim kiem nhan vien thong qua ten
    public List<Employee> findNameEmployee(String name) {
        List<Employee> list = employeeRepository.findAll(); // lay toan bo nhan vien tu database -> list
        List<Employee> listEmpFind = new ArrayList<>(); // list chua cac nhan vien tim duoc
        for (Employee employee : list) { // chay tung phan tu
            if (compareName(employee.getName(), name)) { //so sanh 2 ten
                listEmpFind.add(employee); // neu trung thi add vao list nhan vien tim duoc
            }
        }
        return listEmpFind; // tra ve list tim dc
    }

    // So sanh ten
    private boolean compareName(String nameEmp, String nameEmp2) {
        boolean result = false;
        nameEmp = nameEmp.trim(); // cat khoang trang dau cuoi
        nameEmp2 = nameEmp2.trim(); // cat khoang trang dau cuoi
        String[] name = nameEmp.split(" "); // mang chua cac phan tu duoc cat tu ten 1
        String[] name2 = nameEmp2.split(" "); // mang chua cac phan tu duoc cat tu ten 2
        //lay chuoi cuoi trong ten cua 2 dua ra chuyen ve chu thuong sau do kiem tra name 1 co chua nam 2 hay khong
        if (name[name.length - 1].toLowerCase().equals(name2[name2.length - 1].toLowerCase())) return result = true;
        return result;
    }


    // Tao moi 1 nhan vien
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // xoa 1 nhan vien
    public void deleteEmployee(Integer id) {
        //deleteById ham co san luon, xoa doi tuong theo id
        employeeRepository.deleteById(id);
    }

    // Cap nhat thong tin nhan vien
    public Employee updateEmployee(Integer id, Employee employeeDetails) throws EmployeeNotFoundException {
        // findById tra ve doi tuong theo id o day tra ve employee2
        Employee employee2 = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        // neu tim ra doi tuong
        // set lai tung thuoc tinh cho doi tuong
        employee2.setNo(employeeDetails.getNo());
        employee2.setName(employeeDetails.getName());
        employee2.setBirthday(employeeDetails.getBirthday());
        employee2.setGender(employeeDetails.getGender());
        employee2.setPhone(employeeDetails.getPhone());
        employee2.setEmail(employeeDetails.getEmail());
        employee2.setAddress(employeeDetails.getAddress());
        // save luu doi tuong
        return employeeRepository.save(employee2);
    }

}
