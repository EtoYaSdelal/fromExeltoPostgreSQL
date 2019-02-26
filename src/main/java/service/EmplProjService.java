package service;

import bizlogic.Util;
import dao.DAO;
import entity.EmplProj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmplProjService extends Util implements DAO<EmplProj> {
    Connection connection = getConnection();

    @Override
    public void add(EmplProj emplProj) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("add");
        }
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO empl_proj(employee_id, project_id)" +
                        "VALUES (?,?)")) {
            ps.setLong(1, emplProj.getEmpId());
            ps.setLong(2, emplProj.getProjId());
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<EmplProj> getAll() throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get all");
        }
        List<EmplProj> emplProjs = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = null;
            try {
                rs = st.executeQuery("SELECT * FROM empl_proj");
                while (rs.next()) {
                    emplProjs.add(createEmplProj(rs));
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
        return emplProjs;
    }


    @Override
    public EmplProj getById(Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get by id");
        }
        EmplProj emplProj = new EmplProj();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM empl_proj WHERE employee_id=? OR project_id=?")) {
            ps.setLong(1, id);
            ps.setLong(2, id);
            ResultSet rs = null;
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    emplProj = createEmplProj(rs);
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
        return emplProj;
    }

    @Override
    public void update(EmplProj emplProj, Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("update");
        }
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE empl_proj SET employee_id=?,project_id=?")) {
            ps.setLong(1, id);
            ps.setLong(2, id);
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void delete(EmplProj emplProj) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("delete");
        }
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM empl_proj where employee_id=? and project_id = ?")) {
            ps.setLong(1, emplProj.getEmpId());
            ps.setLong(2, emplProj.getProjId());
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    private EmplProj createEmplProj(ResultSet rs) throws SQLException {
        EmplProj emplProj = new EmplProj();
        emplProj.setEmpId(rs.getLong(1));
        emplProj.setProjId(rs.getLong(2));
        return emplProj;
    }
}
