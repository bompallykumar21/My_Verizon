package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import testBase.TestBase;

public class Utilities extends TestBase{

    protected Utilities() throws IOException {
    }

    static Workbook book;
    public File file;
    static Sheet sheet;

    public static Object[][] getDatafromExcel(String testDataPath, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        FileInputStream file = null;
        file = new FileInputStream(testDataPath);
        book = WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName); //get sheet name

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int r = 0; r < sheet.getLastRowNum(); r++) {
            for (int c = 0; c < sheet.getRow(0).getLastCellNum(); c++) {
                data[r][c] = sheet.getRow(r + 1).getCell(c).toString();
            }
        }
        return data;
    }
}
