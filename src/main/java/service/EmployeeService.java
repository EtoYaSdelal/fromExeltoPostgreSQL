package service;

import bizlogic.Util;
import dao.DAO;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends Util implements DAO<Employee> {
    Connection connection = getConnection();

    @Override
    public void add(Employee employee) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("add");
        }
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO employee (id, first_name, last_name, birthday, address_id)" +
                        "VALUES ( ?,?,?,?,?)")) {
            ps.setLong(1, employee.getId());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getLastName());
            ps.setDate(4, employee.getBirthday());
            ps.setLong(5, employee.getAddressId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get all");
        }
        List<Employee> employees = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = null;
            try {
                rs = st.executeQuery("SELECT * FROM employee");
                while (rs.next()) {
                    employees.add(createEmployee(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get by id");
        }
        Employee employee = new Employee();
        try (PreparedStatement pr = connection.prepareStatement("SELECT * FROM employee WHERE id=?")) {
            pr.setLong(1, id);
            ResultSet rs = null;
            try {
                rs = pr.executeQuery();
                while (rs.next()) {
                    employee = createEmployee(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return employee;
    }


    @Override
    public void update(Employee employee, Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("update");
        }
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE employee SET first_name =?, last_name=?,birthday=?,address_id=? WHERE id=?")) {
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDate(3, employee.getBirthday());
            ps.setLong(4, employee.getAddressId());
            ps.setLong(5, id);
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Employee employee) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("delete");
        }
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE id =?")) {
            ps.setLong(1, employee.getId());
            ps.executeUpdate();
        }
    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setBirthday(rs.getDate("birthday"));
        employee.setAddressId(rs.getLong("address_id"));
        return employee;
    }
}
