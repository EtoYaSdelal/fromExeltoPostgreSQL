package repository;

import entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee WHERE first_name=?1 and last_name=?2", nativeQuery = true)
    Employee findByFirstNameAndLastName(String firstName, String lastName);

    Employee findByLastName(String lastName);
}
