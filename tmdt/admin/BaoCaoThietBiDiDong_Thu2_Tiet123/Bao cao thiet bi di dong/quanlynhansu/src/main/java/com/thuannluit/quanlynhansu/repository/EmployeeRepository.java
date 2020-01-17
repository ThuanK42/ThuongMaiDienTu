package com.thuannluit.quanlynhansu.repository;

import com.thuannluit.quanlynhansu.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Đánh dấu một Class Là tầng Repository, phục vụ truy xuất dữ liệu
// JpaRepository cung cấp các hàm có sẵn để xử lý các tác vụ
// co the coi thang nay nhu lop DAO
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
