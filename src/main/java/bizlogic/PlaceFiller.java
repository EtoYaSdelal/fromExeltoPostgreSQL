package bizlogic;

import entity.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PlaceFiller {

    //        Workbook testBook = new HSSFWorkbook(); //создание таблицы
//        Sheet newSheet = testBook.createSheet(); // создание листа
//        Row newRow = newSheet.createRow(0); // создание строки
//        Cell name = newRow.createCell(0); // создание ячейки
//        name.setCellValue("FRY"); // запись в ячейку
//        Cell birthday = newRow.createCell(1); // новая ячейка должна иметь другую колонку в ряду иначе произайдет перезапись
//        DataFormat format = testBook.createDataFormat(); //создания формата даты
//        CellStyle dateStyle = testBook.createCellStyle(); //стиль даты
//        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
//        birthday.setCellStyle(dateStyle);
//        birthday.setCellValue(new Date((System.currentTimeMillis())));
//        newSheet.autoSizeColumn(1);
//
    private List<Address> addressList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();

    public PlaceFiller() {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\Fry\\Desktop\\some data\\Randmemployees.xls")) {
            Workbook readBook = new HSSFWorkbook(fis);
            Sheet sheet = readBook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();

            EntityBuilder entityBuilder = new EntityBuilder();

            for (int i = 1; i < rowNum; i++) {
                addressList.add(entityBuilder.buildAddress(
                        readBook.getSheetAt(0).getRow(i).getCell(0).toString()
                        , readBook.getSheetAt(0).getRow(i).getCell(1).toString()
                        , readBook.getSheetAt(0).getRow(i).getCell(2).toString()
                        , readBook.getSheetAt(0).getRow(i).getCell(3).toString()));

                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
                format.applyPattern("dd.MM.yy");
                java.sql.Date sqlDate = null;
                try {
                    Date date = format.parse((readBook.getSheetAt(0).getRow(i).getCell(6).toString()));
                    sqlDate = new java.sql.Date(date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                employeeList.add(entityBuilder.buildEmployee(
                        readBook.getSheetAt(0).getRow(i).getCell(4).toString()
                        , readBook.getSheetAt(0).getRow(i).getCell(5).toString()
                        , sqlDate
                        , addressList.get(i - 1)));


                projectList.add(entityBuilder.buildProject(readBook.getSheetAt(0).getRow(i).getCell(7).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

}
