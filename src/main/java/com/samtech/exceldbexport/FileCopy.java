package com.samtech.exceldbexport;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author Sammy
 */
public class FileCopy {

    public static void main(String[] args) throws IOException {

        Date sysDate = new Date();
        String eod = "EOD";
        String tnf = "TnF";
        String od  = "OD";

        DateFormat myDateFormat = new SimpleDateFormat("ddMMyyyy");
        String currFileDate = myDateFormat.format(sysDate);
        System.out.println("New Date format2 is: " + currFileDate);
        
        String newEODName = eod + "_" + currFileDate;
        String newTnFName = tnf + "_" + currFileDate;
        String newODName  = od  + "_" + currFileDate;

        File file1 = new File("C:\\Projects\\ExcelDBExport\\source\\"+ newEODName);
        File file2 = new File("C:\\Projects\\ExcelDBExport\\source\\"+ newTnFName);
        File file3 = new File("C:\\Projects\\ExcelDBExport\\source\\"+ newODName);
        
        //File file1 = new File(newEODName);
        //File file2 = new File(newTnFName);
        //File file3 = new File(newODName);


        File file = new File("user_details.xls");
        File dest = new File("C:\\Projects\\ExcelDBExport\\destination");
        File newSrc = new File("C:\\Projects\\ExcelDBExport\\source");
        //File newDest = new File(newDir);

        //FileUtils.copyFileToDirectory(file, dest);
        //FileUtils.copyDirectoryToDirectory(dest, newSrc);
        //FileUtils.deleteDirectory(dest);
        
        FileUtils.forceMkdir(file1);
        FileUtils.forceMkdir(file2);
        FileUtils.forceMkdir(file3);
    }
}
