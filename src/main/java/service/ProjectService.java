package service;

import bizlogic.SessionUtil;
import dao.DAO;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.List;

public class ProjectService extends SessionUtil implements DAO<Project> {

    @Override
    public void add(Project project) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(project);
        closeTransactionSession();
    }

    @Override
    public List<Project> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();

        String sql = "SELECT * FROM Project";
        NativeQuery<Project> query = session.createNativeQuery(sql).addEntity(Project.class);
        List<Project> projectList = query.getResultList();

        closeTransactionSession();
        return projectList;
    }

    @Override
    public Project getById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Project project = session.get(Project.class, id);
        closeTransactionSession();
        return project;
    }

    @Override
    public void update(Project project, Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Project old = session.get(Project.class, id);
        old.setTitle(project.getTitle());
        session.save(old);
        closeTransactionSession();
    }

    @Override
    public void delete(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Project project =session.get(Project.class, id);
       // project.setEmployees(null);
        session.remove(project);
        closeTransactionSession();
    }
}