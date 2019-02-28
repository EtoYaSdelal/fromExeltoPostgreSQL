import bizlogic.SessionUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;
import service.AddressService;
import service.EmployeeService;
import service.ProjectService;

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
//
//        for (int i = 0; i < addressList.size(); i++) {
//            addressService.add(addressList.get(i));
//            session.save(employeeList.get(i));
//            session.save(projectList.get(i));
//        }

        Address newAddress = new Address();
        newAddress.setCountry("rrr");
        newAddress.setCity("fff");
        newAddress.setStreet("ggg");
        newAddress.setPostCode("555");

        AddressService addressService = new AddressService();
        List<Address> showAddresses = addressService.getAll();
        showAddresses.forEach(System.out::println);

        EmployeeService employeeService = new EmployeeService();
        List<Employee> showEmployees = employeeService.getAll();
        showEmployees.forEach(System.out::println);

        ProjectService projectService = new ProjectService();
        List<Project> projectList = projectService.getAll();
        projectList.forEach(System.out::println);


        sessionUtil.closeTransactionSession();


    }
}
