import bizlogic.PlaceFiller;
import bizlogic.SessionUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;
import service.AddressService;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Domain {
    public static void main(String[] args) throws SQLException {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

        PlaceFiller pf = new PlaceFiller(10);
        List<Address> addressList = pf.getAddressList();
        List<Employee> employeeList = pf.getEmployeeList();
        List<Project> projectList = pf.getProjectList();

        Set<Employee> employees2 = new LinkedHashSet<>(employeeList);
        projectList.forEach(x -> x.setEmployees(employees2));

        Set<Project> projects2 = new LinkedHashSet<>(projectList);
        employeeList.forEach(x -> x.setProjects(projects2));

        AddressService addressService = new AddressService();
//        List<Address> showAddresses = addressService.getAll();
//        showAddresses.forEach(System.out::println);
//
//        EmployeeService employeeService = new EmployeeService();
//        employeeService.delete(1L);
//        List<Employee> showEmployees = employeeService.getAll();
//        showEmployees.forEach(System.out::println);
//
//        ProjectService projectService = new ProjectService();
//        projectService.delete(1L);
//        List<Project> projectList = projectService.getAll();
//        projectList.forEach(System.out::println);

        for (int i = 0; i < addressList.size(); i++) {
            addressService.add(addressList.get(i));
            session.save(employeeList.get(i));
            session.save(projectList.get(i));
        }


        sessionUtil.closeTransactionSession();


    }
}
