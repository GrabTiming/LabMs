package com.Lnn.util;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.entity.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInputUtil {

    public static void print(String filePath)
    {
        String excelFilePath = filePath;
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
            for (Row row : sheet) { // 迭代每一行
                System.out.print(row.getRowNum()+" ");
                if(isRowEmpty(row)) continue;
                for (Cell cell : row) { // 迭代每一列
                    // 根据不同数据类型，以字符串形式输出数据
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default: break;
                    }
                }
                System.out.println(); // 换行，以便于区分不同的行
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List readStudentFile(String filePath)
    {
        String excelFilePath = filePath;
        List<User> list = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
            int rowCount = sheet.getLastRowNum()+1;
            int columnCount = sheet.getRow(0).getLastCellNum();//列数
            for (int i=1;i<rowCount;i++) { // 迭代每一行
                Row row = sheet.getRow(i);
                if(isRowEmpty(row)) continue;
                User user = new User();
                for (int j=0;j<columnCount;j++) { // 迭代每一列
                    Cell cell = row.getCell(j);
                    System.out.println(cell.getCellType());
                    // 根据不同数据类型，以字符串形式输出数据
                    //学号、密码、姓名、班级、专业都是字符串
                    if(j==0) user.setAccount(cell.getStringCellValue());
                    else if(j==1) user.setPassword(cell.getStringCellValue());
                    else if(j==2) user.setUsername(cell.getStringCellValue());
                    else if(j==3) user.setClassName(cell.getStringCellValue());
                    else if(j==4) user.setMajor(cell.getStringCellValue());
                }
                user.setRole(SystemConstants.STUDENT_ROLE);
                list.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<User> readTeacherFile(String filePath,Integer role)
    {
        List<User> list = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
            int rowCount = sheet.getLastRowNum()+1;
            int columnCount = sheet.getRow(0).getLastCellNum();//列数
            for (int i=1;i<rowCount;i++) { // 迭代每一行
                Row row = sheet.getRow(i);
                if(isRowEmpty(row)) continue;
                User user = new User();
                for (int j=0;j<columnCount;j++) { // 迭代每一列
                    Cell cell = row.getCell(j);
                    // 根据不同数据类型，以字符串形式输出数据
                    //工号、密码、姓名、职称 都是字符串
                    if(j==0) user.setAccount(cell.getStringCellValue());
                    else if(j==1) user.setPassword(cell.getStringCellValue());
                    else if(j==2) user.setUsername(cell.getStringCellValue());
                    else if(j==3) user.setProRank(cell.getStringCellValue());
                }
                user.setRole(role);
                list.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row. getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell !=null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String filePath = "E:/ExcelTest/teacher.xlsx";
        print(filePath);

        //List<User> list= readTeacherFile(filePath,2);
//        for(User user:list)
//        {
//            System.out.println(user.toString());
//        }
    }


}
