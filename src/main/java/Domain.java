import bizlogic.SessionUtil;
import entity.Employee;
import org.hibernate.Session;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class Domain {
    public static void main(String[] args) throws SQLException {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();

//        PlaceFiller pf = new PlaceFiller();
//        List<Address> addressList = pf.getAddressList();
//        List<Employee> employeeList = pf.getEmployeeList();
//        List<Project> projectList = pf.getProjectList();
//
//        Set<Employee> employees2 = new LinkedHashSet<>(employeeList);
//        projectList.forEach(x -> x.setEmployees(employees2));
//
//        Set<Project> projects2 = new LinkedHashSet<>(projectList);
//        employeeList.forEach(x -> x.setProjects(projects2));

//        AddressService addressService = new AddressService();
//        List<Address> showAddresses = addressService.getAll();
//        showAddresses.forEach(System.out::println);

        EmployeeService employeeService = new EmployeeService();
        employeeService.delete(21L);
        List<Employee> showEmployees = employeeService.getAll();
        showEmployees.forEach(System.out::println);

//        ProjectService projectService = new ProjectService();
//        projectService.delete(1L);
//        List<Project> projectList = projectService.getAll();
//        projectList.forEach(System.out::println);

//        for (int i = 0; i < addressList.size(); i++) {
//            addressService.add(addressList.get(i));
//            session.save(employeeList.get(i));
//            session.save(projectList.get(i));
//        }


        sessionUtil.closeTransactionSession();


    }
}
