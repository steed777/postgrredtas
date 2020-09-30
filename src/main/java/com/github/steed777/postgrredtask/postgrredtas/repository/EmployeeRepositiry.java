package com.github.steed777.postgrredtask.postgrredtas.repository;



import com.github.steed777.postgrredtask.postgrredtas.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepositiry extends JpaRepository<Employee, Long> {

@Query("from Employee e where concat(e.lastname,' ')" +
        "like concat('%', :name, '%')")// для поиска.
    // Сравниваем с введеноми пользователем символами и ищем по совподению
// '%'-любое количество символов до и после
List<Employee>findByName(@Param("name")String name);
//ищем сотродников по имени

}

