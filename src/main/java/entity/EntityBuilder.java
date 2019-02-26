package entity;

import java.sql.Date;

public class EntityBuilder {

    public Address buildAddress(Long id, String c, String ci, String s, String pc) {
        Address address = new Address();
        address.setId(id);
        address.setCountry(c);
        address.setCity(ci);
        address.setStreet(s);
        address.setPostCode(pc);
        return address;
    }

    public Employee buildEmployee(Long id, String fn, String sn, Date bday, Long adId) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(fn);
        employee.setLastName(sn);
        employee.setBirthday(bday);
        employee.setAddressId(adId);
        return employee;
    }

    public Project buildProject(Long id, String title) {
        Project project = new Project();
        project.setId(id);
        project.setTitle(title);
        return project;
    }

    public EmplProj buildEmplProj(Long empId, Long projId) {
        EmplProj emplProj = new EmplProj();
        emplProj.setEmpId(empId);
        emplProj.setProjId(projId);
        return emplProj;
    }

}
