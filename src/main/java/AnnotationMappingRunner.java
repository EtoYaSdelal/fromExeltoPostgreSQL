import bizlogic.HibernateUtil;
import bizlogic.PlaceFiller;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;

import java.sql.Date;
import java.util.*;

public class AnnotationMappingRunner {
    public static void main(String[] args) {
        PlaceFiller pf = new PlaceFiller();

        List<Address> addressList = pf.getAddressList();
        List<Employee> employeeList = pf.getEmployeeList();
        List<Project> projectList = pf.getProjectList();

        Set<Employee> employees2 = new LinkedHashSet<>(employeeList);
        projectList.forEach(x -> x.setEmployees(employees2));

        Set<Project> projects2 = new LinkedHashSet<>(projectList);
        employeeList.forEach(x->x.setProjects(projects2));

        Session session = HibernateUtil.getSs().openSession();
        session.beginTransaction();

//        Address address = new Address();
//        address.setCountry("country");
//        address.setCity("city");
//        address.setStreet("street");
//        address.setPostCode("666");
//
//        Project project = new Project();
//        project.setTitle("new PRoj");
//
//        Employee employee = new Employee();
//        employee.setFirstName("fry");
//        employee.setLastName("ok");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(1989, Calendar.OCTOBER,5);
//        Date date= new Date(calendar.getTime().getTime());
//        employee.setBirthday(date);
//        employee.setAddress(address);
//
//        Set<Employee> employees = new HashSet<>();
//        employees.add(employee);
//        project.setEmployees(employees);
//
//        Set<Project> projects = new HashSet<>();
//        projects.add(project);
//        employee.setProjects(projects);
//
//        session.save(address);
//        session.save(employee);
//        session.save(project);
        for(int i=0;i<addressList.size();i++){
            session.save(addressList.get(i));
            session.save(employeeList.get(i));
            session.save(projectList.get(i));
        }

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
