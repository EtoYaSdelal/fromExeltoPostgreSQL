//import bizlogic.PlaceFiller;
//import bizlogic.Util;
//import entity.Address;
//import entity.EmplProj;
//import entity.Employee;
//import entity.Project;
//import service.AddressService;
//import service.EmplProjService;
//import service.EmployeeService;
//import service.ProjectService;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Domain {
//    public static void main(String[] args) throws SQLException {
//        PlaceFiller placeFiller = new PlaceFiller();
//
//        AddressService adSer = new AddressService();
//        EmployeeService empSer = new EmployeeService();
//        ProjectService projSer = new ProjectService();
//        EmplProjService empPrSer = new EmplProjService();
//
////        List<Address> addressList = adSer.getAll();
////        for (Address a : addressList) {
////            System.out.println(a);
////        }
////
////        List<Employee> employeeList = empSer.getAll();
////        for (Employee e : employeeList) {
////            System.out.println(e);
////        }
////
////        List<Project> projectList = projSer.getAll();
////        for (Project p : projectList) {
////            System.out.println(p);
////        }
////
////        List<EmplProj> emplProjList = empPrSer.getAll();
////        for (EmplProj ep: emplProjList){
////            System.out.println(ep);
////        }
//
////        for (int i = 0; i < placeFiller.getAddressList().size(); i++) {
////            adSer.add(placeFiller.getAddressList().get(i));
////            empSer.add(placeFiller.getEmployeeList().get(i));
////            projSer.add(placeFiller.getProjectList().get(i));
////            empPrSer.add(placeFiller.getEmplProjList().get(i));
////        }
//    }
//}
