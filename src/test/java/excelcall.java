import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

//class to call the excel sheet and use the data in the sheet
public class excelcall
{

    @Test
    public JSONObject excel()throws FileNotFoundException, IOException
    {
        String xlpath = "src\\main\\resources\\userdata.xlsx";
        FileInputStream inputStream = new FileInputStream(xlpath);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet =workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int columns = sheet.getRow(1).getLastCellNum();

        JSONArray arr = new JSONArray();

        for(int r=0;r<=rows;r++)
        {
            JSONObject input = new JSONObject();

            XSSFRow row = sheet.getRow(r);
            for(int c=0; c<columns; c++) {
                XSSFCell cell = row.getCell(c);

                switch (c) {
                    case 0:
                        input.put("name", cell.getStringCellValue());
                        break;

                    case 1:
                        input.put("gender", cell.getStringCellValue());
                        break;

                    case 2:
                        input.put("email", cell.getStringCellValue());
                        break;

                    case 3:
                        input.put("status", cell.getStringCellValue());
                        break;
                }

            }
            arr.put(input);
        }

        int index = (int) (Math.random()*10);
        System.out.println(index);
        return arr.getJSONObject(index);
    }
}
