package com.samtech.exceldbexport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Exporter {

    final static Logger logger = Logger.getLogger(Exporter.class);

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String selectSql = "SELECT BRANCH, NAME, USERNAME FROM mystock.app_users;";

        try {
            ResultSet resSet = dbConn.getPreparedStatement(selectSql).executeQuery();
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet = workBook.createSheet("USERS");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("BRANCH_CODE");
            header.createCell(1).setCellValue("NAME");
            header.createCell(2).setCellValue("USERNAME");

            sheet.autoSizeColumn(0);
            //sheet.autoSizeColumn(1);
            sheet.setColumnWidth(1, 256 * 25);
            sheet.setColumnWidth(2, 256 * 25);
            
            sheet.setZoom(150);

            int index = 1;
            while (resSet.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(resSet.getString("BRANCH"));
                row.createCell(1).setCellValue(resSet.getString("NAME"));
                row.createCell(2).setCellValue(resSet.getString("USERNAME"));
                index++;
            }
            String file = "/home/samuel/Projects/ExcelDBExport/app_user_details.xls";
            FileOutputStream fileOutputStrm = new FileOutputStream(file);
            workBook.write(fileOutputStrm);
            fileOutputStrm.flush();

//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Info Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("User Details Exported Successfully");
//            alert.showAndWait();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error in getAuthorisedRecordsInEIVW: " + ex.getMessage());
            logger.debug("Error in getAuthorisedRecordsInEIVW: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
