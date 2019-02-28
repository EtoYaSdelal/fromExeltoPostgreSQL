package service;

import bizlogic.SessionUtil;
import dao.DAO;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService extends SessionUtil implements DAO<Employee> {

    @Override
    public void add(Employee employee) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(employee);
        closeTransactionSession();
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();

        String sql = " SELECT * FROM EMPLOYEE";
        NativeQuery<Employee> query = session.createNativeQuery(sql).addEntity(Employee.class);
        List<Employee> employeeList = query.getResultList();

        closeTransactionSession();
        return employeeList;
    }

    @Override
    public Employee getById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Employee employee = session.get(Employee.class, id);
        closeTransactionSession();
        return employee;
    }

    @Override
    public void update(Employee employee, Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();

        Employee old = session.get(Employee.class, id);
        old.setFirstName(employee.getFirstName());
        old.setLastName(employee.getLastName());
        old.setBirthday(employee.getBirthday());

        session.save(old);
        closeTransactionSession();
    }

    @Override
    public void delete(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(session.get(Employee.class, id));
        closeTransactionSession();
    }
}
