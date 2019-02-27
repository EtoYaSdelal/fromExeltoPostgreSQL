package bizlogic;

import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;

import java.sql.Date;

public class EntityBuilder {
    public Address buildAddress(String c, String ci, String s, String pc) {
        Address address = new Address();
        address.setCountry(c);
        address.setCity(ci);
        address.setStreet(s);
        address.setPostCode(pc);
        return address;
    }

    public Employee buildEmployee(String fn, String sn, Date bday, Address address) {
        Employee employee = new Employee();
        employee.setFirstName(fn);
        employee.setLastName(sn);
        employee.setBirthday(bday);
        employee.setAddress(address);
        return employee;
    }

    public Project buildProject(String title) {
        Project project = new Project();
        project.setTitle(title);
        return project;
    }

}
