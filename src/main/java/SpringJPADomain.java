import entity.Address;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AddressRepository;

public class SpringJPADomain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressRepository addressRepository = context.getBean(AddressRepository.class);
//        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
//        ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

//        PlaceFiller pf = new PlaceFiller(20);
//
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
//            addressRepository.save(addressList.get(i));
//            employeeRepository.save(employeeList.get(i));
//            projectRepository.save(projectList.get(i));
//        }

        Address address = new Address();
        address.setId(1L);
        address.setCountry("sss");
        address.setCity("fff");
        address.setStreet("rrr");
        address.setPostCode("ppp");
        addressRepository.save(address);
    }
}
