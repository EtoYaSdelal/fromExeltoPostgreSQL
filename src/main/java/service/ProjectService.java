package service;

import bizlogic.Util;
import dao.DAO;
import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectService extends Util implements DAO<Project> {
    Connection connection = getConnection();

    @Override
    public void add(Project project) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("add");
        }
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO project (id, title)" +
                        "VALUES ( ?,?)")) {
            ps.setLong(1, project.getId());
            ps.setString(2, project.getTitle());
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public List<Project> getAll() throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get all");
        }
        List<Project> projects = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = null;
            try {
                rs = st.executeQuery("SELECT * FROM project");
                while (rs.next()) {
                    projects.add(createProject(rs));
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
        return projects;
    }


    @Override
    public Project getById(Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("get by id");
        }
        Project project = new Project();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM project WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = null;
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    project = createProject(rs);
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
        return project;
    }

    @Override
    public void update(Project project, Long id) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("update");
        }
        try (PreparedStatement ps = connection.prepareStatement("UPDATE project SET title=? where id=?")) {
            ps.setString(1, project.getTitle());
            ps.setLong(2, id);
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void delete(Project project) throws SQLException {
        if (connection.isClosed()) {
            connection = getConnection("delete");
        }
        try (PreparedStatement ps = connection.prepareStatement("DELETE from progect WHERE id=?")) {
            ps.setLong(1, project.getId());
            ps.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Project createProject(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setId(rs.getLong(1));
        project.setTitle(rs.getString(2));
        return project;
    }
}
