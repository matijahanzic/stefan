/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import stefan.business.objects.BillItem;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import stefan.business.objects.Material;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Robert
 */
public class ExcelManager {

    private Font arialCE30Bold;
    private Font arialDoubleBorder;
    private Font arialDoubleBorder12Bold;
    private Font arialBold;
    private CellStyle styleArialCE30Bold;
    private CellStyle styleArialDoubleBorder12Bold;
    private Font arialCE10;
    private Font arial36Bold;
    private Font arial;
    private CellStyle stylearial36Bold;
    private CellStyle styleArialCE10;
    private CellStyle styleArial;
    private CellStyle styleArialBold;
    private CellStyle styleArialBorderBottom;
    private CellStyle styleArialCE10BorderBottom;
    private CellStyle styleArialCE10AlignRight;
    private CellStyle styleArialCE10BorderBottomAlignRight;
    private CellStyle styleArialDoubleBorder;
    private CellStyle styleTopAndBottomBorder;
    private Font arialCE10Bold;
    private CellStyle styleArialCE10Bold;

    public ExcelManager(Workbook workbook) {

        arialCE30Bold = workbook.createFont();
        arialCE30Bold.setFontHeightInPoints((short) 30);
        arialCE30Bold.setFontName("Arial CE");
        arialCE30Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);

        arial36Bold = workbook.createFont();
        arial36Bold.setFontHeightInPoints((short) 36);
        arial36Bold.setFontName("Arial");
        arial36Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);

        stylearial36Bold = workbook.createCellStyle();
        stylearial36Bold.setFont(arial36Bold);
        stylearial36Bold.setAlignment(CellStyle.ALIGN_CENTER);
        stylearial36Bold.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        arialDoubleBorder12Bold = workbook.createFont();
        arialDoubleBorder12Bold.setFontHeightInPoints((short) 12);
        arialDoubleBorder12Bold.setFontName("Arial");
        arialDoubleBorder12Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);

        styleArialDoubleBorder12Bold = workbook.createCellStyle();
        styleArialDoubleBorder12Bold.setFont(arialDoubleBorder12Bold);
        styleArialDoubleBorder12Bold.setBorderBottom(CellStyle.BORDER_DOUBLE);

        styleArialCE30Bold = workbook.createCellStyle();
        styleArialCE30Bold.setFont(arialCE30Bold);

        arialDoubleBorder = workbook.createFont();
        arialDoubleBorder.setFontHeightInPoints((short) 10);
        arialDoubleBorder.setFontName("Arial");

        styleArialDoubleBorder = workbook.createCellStyle();
        styleArialDoubleBorder.setFont(arialDoubleBorder);
        styleArialDoubleBorder.setBorderBottom(CellStyle.BORDER_DOUBLE);


        styleTopAndBottomBorder = workbook.createCellStyle();
        styleTopAndBottomBorder.setBorderBottom(CellStyle.BORDER_THIN);
        styleTopAndBottomBorder.setBorderTop(CellStyle.BORDER_THIN);

        arialCE10 = workbook.createFont();
        arialCE10.setFontHeightInPoints((short) 10);
        arialCE10.setFontName("Arial CE");

        arial = workbook.createFont();
        arial.setFontHeightInPoints((short) 10);
        arial.setFontName("Arial");

        styleArial = workbook.createCellStyle();
        styleArial.setFont(arial);

        arialBold = workbook.createFont();
        arialBold.setFontHeightInPoints((short) 10);
        arialBold.setFontName("Arial");
        arialBold.setBoldweight(Font.BOLDWEIGHT_BOLD);

        styleArialBold = workbook.createCellStyle();
        styleArialBold.setFont(arialBold);

        styleArialBorderBottom = workbook.createCellStyle();
        styleArialBorderBottom.setFont(arial);
        styleArialBorderBottom.setBorderBottom(CellStyle.BORDER_THIN);

        styleArialCE10 = workbook.createCellStyle();
        styleArialCE10.setFont(arialCE10);

        styleArialCE10BorderBottom = workbook.createCellStyle();
        styleArialCE10BorderBottom.setFont(arialCE10);
        styleArialCE10BorderBottom.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        styleArialCE10AlignRight = workbook.createCellStyle();
        styleArialCE10AlignRight.setFont(arialCE10);
        styleArialCE10AlignRight.setAlignment(CellStyle.ALIGN_RIGHT);

        styleArialCE10BorderBottomAlignRight = workbook.createCellStyle();
        styleArialCE10BorderBottomAlignRight.setFont(arialCE10);
        styleArialCE10BorderBottomAlignRight.setAlignment(CellStyle.ALIGN_RIGHT);
        styleArialCE10BorderBottomAlignRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        arialCE10Bold = workbook.createFont();
        arialCE10Bold.setFontHeightInPoints((short) 10);
        arialCE10Bold.setFontName("Arial CE");
        arialCE10Bold.setBoldweight(Font.BOLDWEIGHT_BOLD);

        styleArialCE10Bold = workbook.createCellStyle();
        styleArialCE10Bold.setFont(arialCE10Bold);
    }

    public FileOutputStream CreateNewFile(String fileName, String filePath) throws FileNotFoundException {

        String file = filePath + "\\" + fileName + ".xls";
        fileName = null;
        return new FileOutputStream(file);
    }

    public Sheet CreateNewBillSheet(Workbook workbook, int i, String date, String number, boolean isEmpty) {
        Sheet sheet = workbook.createSheet();
        sheet.setFitToPage(true);
        sheet.setDisplayGridlines(false);
        workbook.setSheetName(i, "sheet" + i);

        sheet.setMargin(Sheet.TopMargin, 0.3);
        sheet.setMargin(Sheet.LeftMargin, 0.2);
        sheet.setMargin(Sheet.BottomMargin, 0);
        sheet.setMargin(Sheet.RightMargin, 0);
        sheet.setColumnWidth(1, 256 * 12);
        sheet.setColumnWidth(10, 256 * 12);
        sheet.setHorizontallyCenter(true);
        PrintSetup ps = sheet.getPrintSetup();
        sheet.setAutobreaks(true);
        ps.setFitHeight((short) 1);
        ps.setFitWidth((short) 1);
        ps.setHeaderMargin(0.5);
        ps.setFooterMargin(0.5);

        ps.setPaperSize(PrintSetup.A4_PAPERSIZE);
        workbook.setPrintArea(i, 1, 10, 2, 64);
        WriteHeader(sheet, date);
        if (!isEmpty) {
            WriteBillHeader(sheet, i, number);
        }
        return sheet;
    }

    public Sheet CreateNewOtpremnicaSheet(Workbook workbook, int i, String date, String number) {
        Sheet sheet = workbook.createSheet();
        sheet.setFitToPage(true);
        sheet.setDisplayGridlines(false);
        workbook.setSheetName(i, "sheet" + i);

        sheet.setMargin(Sheet.TopMargin, 0);
        sheet.setMargin(Sheet.LeftMargin, 0.6);
        sheet.setMargin(Sheet.BottomMargin, 0);
        sheet.setMargin(Sheet.RightMargin, 0);
        sheet.setColumnWidth(1, 256 * 12);
        sheet.setColumnWidth(10, 256 * 12);
        sheet.setHorizontallyCenter(true);
        PrintSetup ps = sheet.getPrintSetup();
        sheet.setAutobreaks(true);
        ps.setFitHeight((short) 1);
        ps.setFitWidth((short) 1);
        ps.setHeaderMargin(0.5);
        ps.setFooterMargin(0.5);

        ps.setPaperSize(PrintSetup.A4_PAPERSIZE);
        workbook.setPrintArea(i, 1, 10, 2, 64);
        WriteHeader(sheet, date);
        WriteOtpremnicaHeader(sheet, i, number);
        return sheet;
    }

    public void WriteOtpremnicaHeader(Sheet sheet, int page, String number) {
        if (page == 0) {
            Row row17 = sheet.createRow(17);
            Cell cell17_1 = row17.createCell(1);
            cell17_1.setCellValue("LIEFERSCHEIN   Nr.   " + number);
            cell17_1.setCellStyle(styleArialDoubleBorder12Bold);
            Cell cell17_2 = row17.createCell(2);
            cell17_2.setCellStyle(styleArialDoubleBorder12Bold);
            Cell cell17_3 = row17.createCell(3);
            cell17_3.setCellStyle(styleArialDoubleBorder12Bold);
            Cell cell17_4 = row17.createCell(4);
            cell17_4.setCellStyle(styleArialDoubleBorder12Bold);

            Row row18 = sheet.createRow(18);
            Cell cell18_1 = row18.createCell(1);
            cell18_1.setCellValue("Otpremnica br.");
            cell18_1.setCellStyle(styleArial);

            Row row20 = sheet.createRow(20);
            Cell cell20_1 = row20.createCell(1);
            cell20_1.setCellValue("Leistung / Lieferung");
            cell20_1.setCellStyle(styleArial);

            Row row21 = sheet.createRow(21);
            Cell cell21_1 = row21.createCell(1);
            cell21_1.setCellValue("Zeichnungsnummer");
            cell21_1.setCellStyle(styleArial);

            Cell cell21_4 = row21.createCell(4);
            cell21_4.setCellValue("STÜCK");
            cell21_4.setCellStyle(styleArialCE10AlignRight);

            Cell cell21_9 = row21.createCell(9);
            cell21_9.setCellValue("KARTON NUMMER");
            cell21_9.setCellStyle(styleArial);

            Row row22 = sheet.createRow(22);
            Cell cell22_1 = row22.createCell(1);
            cell22_1.setCellValue("Vrsta robe");
            cell22_1.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_2 = row22.createCell(2);
            cell22_2.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_3 = row22.createCell(3);
            cell22_3.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_4 = row22.createCell(4);
            cell22_4.setCellValue("Komada");
            cell22_4.setCellStyle(styleArialCE10BorderBottomAlignRight);

            Cell cell22_5 = row22.createCell(5);
            cell22_5.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_6 = row22.createCell(6);
            cell22_6.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_7 = row22.createCell(7);
            cell22_7.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_8 = row22.createCell(8);
            cell22_8.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_9 = row22.createCell(9);
            cell22_9.setCellValue("Karton broj");
            cell22_9.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_10 = row22.createCell(10);
            cell22_10.setCellStyle(styleArialCE10BorderBottom);

        } else {

            Row row17 = sheet.createRow(17);
            Cell cell17_1 = row17.createCell(1);
            cell17_1.setCellValue("Leistung / Lieferung");
            cell17_1.setCellStyle(styleArialCE10);

            Row row18 = sheet.createRow(18);
            Cell cell18_1 = row18.createCell(1);
            cell18_1.setCellValue("Zeichnungsnummer");
            cell18_1.setCellStyle(styleArialCE10);

            Cell cell18_4 = row18.createCell(4);
            cell18_4.setCellValue("STÜCK");
            cell18_4.setCellStyle(styleArialCE10AlignRight);

            Cell cell18_9 = row18.createCell(9);
            cell18_9.setCellValue("KARTON NUMMER");
            cell18_9.setCellStyle(styleArialCE10);

            Row row19 = sheet.createRow(19);
            Cell cell19_1 = row19.createCell(1);
            cell19_1.setCellValue("Vrsta robe");
            cell19_1.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_2 = row19.createCell(2);
            cell19_2.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_3 = row19.createCell(3);
            cell19_3.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_4 = row19.createCell(4);
            cell19_4.setCellValue("Komada");
            cell19_4.setCellStyle(styleArialCE10BorderBottomAlignRight);

            Cell cell19_5 = row19.createCell(5);
            cell19_5.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_6 = row19.createCell(6);
            cell19_6.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_7 = row19.createCell(7);
            cell19_7.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_8 = row19.createCell(8);
            cell19_8.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_9 = row19.createCell(9);
            cell19_9.setCellValue("Karton broj");
            cell19_9.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_10 = row19.createCell(10);
            cell19_10.setCellStyle(styleArialCE10BorderBottom);
        }
    }

    public void WriteBillHeader(Sheet sheet, int page, String number) {
        if (page == 0) {
            Row row15 = sheet.createRow(15);
            Cell cell15_1 = row15.createCell(1);
            cell15_1.setCellValue("EORI-Nr. : DE3456110");
            cell15_1.setCellStyle(styleArialCE10);

            Row row17 = sheet.createRow(17);
            Cell cell17_1 = row17.createCell(1);
            cell17_1.setCellValue("WARENRECHNUNG   Nr.   " + number);
            cell17_1.setCellStyle(styleArialDoubleBorder12Bold);
            Cell cell17_2 = row17.createCell(2);
            cell17_2.setCellStyle(styleArialDoubleBorder12Bold);
            Cell cell17_3 = row17.createCell(3);
            cell17_3.setCellStyle(styleArialDoubleBorder12Bold);
            Cell cell17_4 = row17.createCell(4);
            cell17_4.setCellStyle(styleArialDoubleBorder12Bold);

            Row row18 = sheet.createRow(18);
            Cell cell18_1 = row18.createCell(1);
            cell18_1.setCellValue("Račun br.");
            cell18_1.setCellStyle(styleArialCE10);

            Row row20 = sheet.createRow(19);
            Cell cell20_1 = row20.createCell(1);
            cell20_1.setCellValue("Vrsta robe");
            cell20_1.setCellStyle(styleArialCE10);
            
            Cell cell20_4 = row20.createCell(4);
            cell20_4.setCellValue("Komada");
            cell20_4.setCellStyle(styleArialCE10AlignRight);
            
            Cell cell20_6 = row20.createCell(6);
            cell20_6 .setCellValue("Cij.kn/kom:");
            cell20_6 .setCellStyle(styleArialCE10);
            
            Cell cell20_8 = row20.createCell(8);
            cell20_8 .setCellValue("Popust");
            cell20_8 .setCellStyle(styleArialCE10);
            
            Cell cell20_9 = row20.createCell(9);
            cell20_9 .setCellValue("Vrijednost ( kn )");
            cell20_9 .setCellStyle(styleArialCE10);

            Row row21 = sheet.createRow(20);
            Cell cell21_1 = row21.createCell(1);
            cell21_1.setCellValue("Leistung / Lieferung");
            cell21_1.setCellStyle(styleArialCE10);           

            Row row22 = sheet.createRow(21);
            Cell cell22_1 = row22.createCell(1);
            cell22_1.setCellValue("Zeichnungsnummer");
            cell22_1.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_2 = row22.createCell(2);
            cell22_2.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_3 = row22.createCell(3);
            cell22_3.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_4 = row22.createCell(4);
            cell22_4.setCellValue("STÜCK");
            cell22_4.setCellStyle(styleArialCE10BorderBottomAlignRight);

            Cell cell22_5 = row22.createCell(5);
            cell22_5.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_6 = row22.createCell(6);       
            cell22_6.setCellValue("EUR / STCK");
            cell22_6.setCellStyle(styleArialCE10BorderBottom);
            
            Cell cell22_7 = row22.createCell(7);
            cell22_7.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_8 = row22.createCell(8);
            cell22_8.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_9 = row22.createCell(9);
            cell22_9.setCellValue("WARENWERT ( EUR )");
            cell22_9.setCellStyle(styleArialCE10BorderBottom);

            Cell cell22_10 = row22.createCell(10);
            cell22_10.setCellStyle(styleArialCE10BorderBottom);


        } else {   
                   
            
            
            Row row16 = sheet.createRow(16);           
            
            Cell cell16_6 = row16.createCell(6);
            cell16_6.setCellValue("Zbroj sa stranice " + page + " :");
            cell16_6.setCellStyle(styleArialCE10);

            Cell cell16_10 = row16.createCell(10);
            cell16_10.setCellValue("kn");
            cell16_10.setCellStyle(styleArial);
             

            Row row17 = sheet.createRow(17);
            
            Cell cell17_6 = row17.createCell(6);
            cell17_6.setCellValue("Zwischensumme von Seite " + page + " :");
            cell17_6.setCellStyle(styleArialCE10);
            
            Cell cell17_10 = row17.createCell(10);
            cell17_10.setCellValue("EUR");
            cell17_10.setCellStyle(styleArial);
            
            Row row18 = sheet.createRow(18);            
            Cell cell17_1 = row18.createCell(1);
            cell17_1.setCellValue("Vrsta robe");
            cell17_1.setCellStyle(styleArialCE10);            
           
            Row row19 = sheet.createRow(19);  
            Cell cell18_1 = row19.createCell(1);
            cell18_1.setCellValue("Leistung / Lieferung");
            cell18_1.setCellStyle(styleArialCE10);

            Cell cell18_4 = row19.createCell(4);
            cell18_4.setCellValue("Komada");
            cell18_4.setCellStyle(styleArialCE10AlignRight);

            Cell cell18_6 = row19.createCell(6);
            cell18_6.setCellValue("Cij.kn/kom");
            cell18_6.setCellStyle(styleArialCE10);
            
            Cell cell18_8 = row19.createCell(8);
            cell18_8 .setCellValue("Popust");
            cell18_8 .setCellStyle(styleArialCE10);

            Cell cell18_9 = row19.createCell(9);
            cell18_9.setCellValue("Vrijednost ( kn )");
            cell18_9.setCellStyle(styleArialCE10);

            Row row20 = sheet.createRow(20);
            Cell cell19_1 = row20.createCell(1);
            cell19_1.setCellValue("Zeichnungsnummer");
            cell19_1.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_2 = row20.createCell(2);
            cell19_2.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_3 = row20.createCell(3);
            cell19_3.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_4 = row20.createCell(4);
            cell19_4.setCellValue("STÜCK");
            cell19_4.setCellStyle(styleArialCE10BorderBottomAlignRight);

            Cell cell19_5 = row20.createCell(5);
            cell19_5.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_6 = row20.createCell(6);
            cell19_6.setCellValue("EUR / STCK");
            cell19_6.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_7 = row20.createCell(7);            
            cell19_7.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_8 = row20.createCell(8);
            cell19_8.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_9 = row20.createCell(9);
            cell19_9.setCellValue("WARENWERT ( EUR )");
            cell19_9.setCellStyle(styleArialCE10BorderBottom);

            Cell cell19_10 = row20.createCell(10);
            cell19_10.setCellStyle(styleArialCE10BorderBottom);
        }
    }

    private void WriteHeader(Sheet sheet, String date) {

        Row row2 = sheet.createRow(2);
        Cell cell2_1 = row2.createCell(1);
        cell2_1.setCellValue("ŠTEFAN");
        cell2_1.setCellStyle(styleArialCE30Bold);
        Cell cell2_4 = row2.createCell(4);
        cell2_4.setCellValue("d.o.o. za preradu metala i trgovinu");
        cell2_4.setCellStyle(styleArialCE10);

        Row row3 = sheet.createRow(3);
        Cell cell3_4 = row3.createCell(4);
        cell3_4.setCellValue("Razvor 24D, 49 295 KUMROVEC, CROATIA");
        cell3_4.setCellStyle(styleArialCE10);

        Row row4 = sheet.createRow(4);
        Cell cell4_4 = row4.createCell(4);
        cell4_4.setCellValue("tel.: +385 49 500 197");
        cell4_4.setCellStyle(styleArialCE10);

        Row row5 = sheet.createRow(5);
        Cell cell5_4 = row5.createCell(4);
        cell5_4.setCellValue("fax: +385 49 500 198");
        cell5_4.setCellStyle(styleArialCE10);

        Row row6 = sheet.createRow(6);
        Cell cell6_4 = row6.createCell(4);
        cell6_4.setCellValue("e-mail : stefan@kr.t-com.hr");
        cell6_4.setCellStyle(styleArialCE10);

        Row row7 = sheet.createRow(7);
        Cell cell7_4 = row7.createCell(4);
        cell7_4.setCellValue("Zagrebačka Banka d.d., IBAN: HR6523600001101291901 BIC: ZABAHR2X");
        cell7_4.setCellStyle(styleArialCE10);

        Row row8 = sheet.createRow(8);
        Cell cell8_4 = row8.createCell(4);
        cell8_4.setCellValue("Privredna Banka Zagreb d.d., IBAN: HR1023400091110904189 BIC: PBZGHR2X");
        cell8_4.setCellStyle(styleArialCE10BorderBottom);

        //border
        Cell cell8_1 = row8.createCell(1);
        cell8_1.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_2 = row8.createCell(2);
        cell8_2.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_3 = row8.createCell(3);
        cell8_3.setCellStyle(styleArialCE10BorderBottom);        
        Cell cell8_5 = row8.createCell(5);
        cell8_5.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_6 = row8.createCell(6);
        cell8_6.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_7 = row8.createCell(7);
        cell8_7.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_8 = row8.createCell(8);
        cell8_8.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_9 = row8.createCell(9);
        cell8_9.setCellStyle(styleArialCE10BorderBottom);
        Cell cell8_10 = row8.createCell(10);
        cell8_10.setCellStyle(styleArialCE10BorderBottom);

        //end border

        Row row9 = sheet.createRow(9);
        Cell cell9_6 = row9.createCell(6);
        cell9_6.setCellValue("PDV ident.broj : HR68386551086");
        cell9_6.setCellStyle(styleArialCE10);

        Row row10 = sheet.createRow(10);
        Cell cell10_1 = row10.createCell(1);
        cell10_1.setCellValue("Firma");
        cell10_1.setCellStyle(styleArialCE10);
        Cell cell10_8 = row10.createCell(8);
        cell10_8.setCellValue("Vaš znak :");
        cell10_8.setCellStyle(styleArialCE10);

        Row row12 = sheet.createRow(12);
        Cell cell12_1 = row12.createCell(1);
        cell12_1.setCellValue("FOPAC Maschinenbau GmbH");
        cell12_1.setCellStyle(styleArialCE10Bold);
        Cell cell12_8 = row12.createCell(8);
        cell12_8.setCellValue("Naš znak :");
        cell12_8.setCellStyle(styleArialCE10);

        Row row13 = sheet.createRow(13);
        Cell cell13_1 = row13.createCell(1);
        cell13_1.setCellValue("Industriestr. 1");
        cell13_1.setCellStyle(styleArialCE10);

        Row row14 = sheet.createRow(14);
        Cell cell14_1 = row14.createCell(1);
        cell14_1.setCellValue("D-26671 Barßel");
        cell14_1.setCellStyle(styleArialCE10);
        Cell cell14_8 = row14.createCell(8);
        cell14_8.setCellValue("Datum     :");
        cell14_8.setCellStyle(styleArialCE10);
        Cell cell14_9 = row14.createCell(9);
        cell14_9.setCellValue(" " + date);
        cell14_9.setCellStyle(styleArialCE10);

    }

    public int AddOtpremnicaBillItems(int pageNum, int billItemsAdded, Sheet sheet, BillItem billItem) {
        int rowNum = 0, columnNum = 1;
        String upisNiklanje = "";
        if (pageNum == 1) {
            rowNum = 23;
        } else {
            rowNum = 20;
        }

        Row row = sheet.createRow(rowNum + (billItemsAdded * 3));
        Cell cell = row.createCell(columnNum);
        cell.setCellValue(billItem.getDesignIdentity() + " " + billItem.getDesignClass());
        cell.setCellStyle(styleArial);

        Cell celltest = row.createCell(9);
        if (billItem.getPackageNumber().toLowerCase().contains("p")) {
            celltest.setCellValue("Palette");
        } else {
            celltest.setCellValue(billItem.getPackageNumber());
        }
        celltest.setCellStyle(stylearial36Bold);

        Row row1 = sheet.createRow(rowNum + 1 + (billItemsAdded * 3));
        Cell cell1 = row1.createCell(columnNum);
        cell1.setCellValue(billItem.getDesignNumber() + ", ");
        cell1.setCellStyle(styleArial);

        Cell celltest2 = row1.createCell(9);
        celltest2.setCellStyle(stylearial36Bold);
       
        if (billItem.getNiklanje() == true) {
            upisNiklanje = "X02";
        } 
        else {
            upisNiklanje = "F";
        }
      
        Cell cell2 = row1.createCell(columnNum + 1);
        cell2.setCellValue(billItem.getDesignName().replace("*", "").replace("/", "") + ", " + upisNiklanje);
        cell2.setCellStyle(styleArial);

        Cell cell3 = row1.createCell(columnNum + 3);
        cell3.setCellValue(billItem.getParts());
        cell3.setCellStyle(styleArial);

        Cell cell4 = row1.createCell(columnNum + 8);
        // cell4.setCellValue(billItem.getPackageNumber());
        cell4.setCellStyle(stylearial36Bold);

        Row row2 = sheet.createRow(rowNum + 2 + (billItemsAdded * 3));
        Cell cell7 = row2.createCell(columnNum);
        cell7.setCellValue("Geliefert laut Pos. " + billItem.getPosition() + " Ihrer Bestellung");
        cell7.setCellStyle(styleArialBorderBottom);

        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(rowNum + (billItemsAdded * 3), rowNum + (billItemsAdded * 3) + 2, columnNum + 8, columnNum + 8));

        Cell cell8 = row2.createCell(columnNum + 1);
        cell8.setCellStyle(styleArialBorderBottom);

        Cell cell9 = row2.createCell(columnNum + 2);
        cell9.setCellStyle(styleArialBorderBottom);

        Cell cell10 = row2.createCell(columnNum + 3);
        cell10.setCellStyle(styleArialBorderBottom);

        Cell cell11 = row2.createCell(columnNum + 4);
        cell11.setCellValue(billItem.getOrderNumber());
        cell11.setCellStyle(styleArialBorderBottom);

        Cell cell12 = row2.createCell(columnNum + 5);
        cell12.setCellValue("vom " + billItem.getOrderDate().toLocaleString().substring(0, 11));
        cell12.setCellStyle(styleArialBorderBottom);

        for (int k = 0; k <= 3; k++) {
            row2.createCell(columnNum + 6 + k).setCellStyle(styleArialBorderBottom);
        }

        return (rowNum + 3 + (billItemsAdded * 3));
    }

    public List<Double> AddBillItems(int pageNum, int billItemsAdded, Sheet sheet, BillItem billItem, BigDecimal exchangeRate) {
        //current row, Bolzen komada, Bolzen ukCijena, Welle komada, Welle ukCijena
        List<Double> data = new ArrayList<Double>();
        String upisNiklanje = "";

        int rowNum = 0, columnNum = 1;
        if (pageNum == 1) {
            rowNum = 22;
        } else {
            rowNum = 21;
        }

        data.add((double) rowNum + 4 + (billItemsAdded * 4));

        String designName = billItem.getDesignName();
        if (designName.toLowerCase().contains("bolzen")) {
            data.add((double) billItem.getParts());
            data.add((double) billItem.getTotalPrice().doubleValue());
            data.add((double) 0);
            data.add((double) 0);
        } else if (designName.toLowerCase().contains("welle")) {
            data.add((double) 0);
            data.add((double) 0);
            data.add((double) billItem.getParts());
            data.add((double) billItem.getTotalPrice().doubleValue());
        } else {
            data.add((double) 0);
            data.add((double) 0);
            data.add((double) 0);
            data.add((double) 0);
        }


        Row row = sheet.createRow(rowNum + (billItemsAdded * 4));
        Cell cell = row.createCell(columnNum);
        cell.setCellValue(billItem.getDesignIdentity() + " " + billItem.getDesignClass());
        cell.setCellStyle(styleArial);

        Row row1 = sheet.createRow(rowNum + 1 + (billItemsAdded * 4));
        Cell cell1 = row1.createCell(columnNum);
        cell1.setCellValue(billItem.getDesignNumber() + ", ");
        cell1.setCellStyle(styleArial);

       
        if (billItem.getNiklanje() == true) {
            upisNiklanje = "X02";
        } else 
        {
            upisNiklanje = "F";
        }
      
        Cell cell2 = row1.createCell(columnNum + 1);

        cell2.setCellValue(billItem.getDesignName().replace("*", "").replace("/", "") + ", " + upisNiklanje);
        cell2.setCellStyle(styleArial);
        Cell cell3 = row1.createCell(columnNum + 3);

        cell3.setCellValue(billItem.getParts());
        cell3.setCellStyle(styleArial);       
                 
        BigDecimal pricePerPartKN = billItem.getPricePerPart().multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPriceKN = billItem.getTotalPrice().multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discount = pricePerPartKN.multiply(new BigDecimal(billItem.getParts())).setScale(2, RoundingMode.HALF_UP).subtract(totalPriceKN);
        
        Cell cel14 = row1.createCell(columnNum + 5);        
        cel14.setCellValue(pricePerPartKN.toString().replace(".", ","));
        cel14.setCellStyle(styleArial);
        
        
        Cell cell66 = row1.createCell(columnNum + 7); 
        if (discount.compareTo(new BigDecimal("0.00"))!=0)
        {
            cell66.setCellValue(discount.toString().replace(".", ","));
            cell66.setCellStyle(styleArial);
        }        
         
        Cell cell5 = row1.createCell(columnNum + 8);
        
        cell5.setCellValue(totalPriceKN.toString().replace(".", ","));
        cell5.setCellStyle(styleArial);
        
        Cell cell6 = row1.createCell(columnNum + 9);
        cell6.setCellValue("kn");
        cell.setCellStyle(styleArial);
        
        Row row2 = sheet.createRow(rowNum + 2 + (billItemsAdded * 4));
        Cell cel24 = row2.createCell(columnNum + 5);
        cel24.setCellValue(billItem.getPricePerPart().toString().replace(".", ","));
        cel24.setCellStyle(styleArial);
        
        Cell cel25 = row2.createCell(columnNum + 8);
        //totalString = String.format("%,2f", billItem.getTotalPrice());
        cel25.setCellValue(billItem.getTotalPrice().toString().replace(".", ","));
        cel25.setCellStyle(styleArial);
        
        Cell cel26 = row2.createCell(columnNum + 9);
        cel26.setCellValue("EUR");
        cel26.setCellStyle(styleArial);
        
        
        Row row3 = sheet.createRow(rowNum + 3 + (billItemsAdded * 4));
        Cell cell7 = row3.createCell(columnNum);
        

        cell7.setCellValue("Geliefert laut Pos. " + billItem.getPosition() + " Ihrer Bestellung");
        cell7.setCellStyle(styleArialBorderBottom);
        Cell cell8 = row3.createCell(columnNum + 1);

        cell8.setCellStyle(styleArialBorderBottom);
        Cell cell9 = row3.createCell(columnNum + 2);

        cell9.setCellStyle(styleArialBorderBottom);
        Cell cell10 = row3.createCell(columnNum + 3);

        cell10.setCellStyle(styleArialBorderBottom);
        Cell cell11 = row3.createCell(columnNum + 4);

        cell11.setCellValue(billItem.getOrderNumber());
        cell11.setCellStyle(styleArialBorderBottom);
        Cell cell12 = row3.createCell(columnNum + 5);

        cell12.setCellValue("vom " + billItem.getOrderDate().toLocaleString().substring(0, 11));
        cell12.setCellStyle(styleArialBorderBottom);

        for (int k = 0; k <= 3; k++) {
            row3.createCell(columnNum + 6 + k).setCellStyle(styleArialBorderBottom);
        }

        return data;
    }

    public void AddSum(Sheet sheet, BigDecimal sum, BigDecimal sumKN, int currentRow) {

        Row row2 = sheet.createRow(currentRow);
        Cell cell222 = row2.createCell(7);
        cell222.setCellValue("Zbroj stranice       :");
        cell222.setCellStyle(styleArialCE10);

        Cell cell12 = row2.createCell(9);
        cell12.setCellValue(sumKN.toString().replace(".", ","));
        cell12.setCellStyle(styleArialCE10);

        Cell cell22 = row2.createCell(10);
        cell22.setCellValue("kn");
        cell22.setCellStyle(styleArial);
        
        Row row = sheet.createRow(currentRow + 1);
        Cell cell = row.createCell(7);
        cell.setCellValue("Zwischensumme  :");
        cell.setCellStyle(styleArialCE10);

        Cell cell1 = row.createCell(9);
        cell1.setCellValue(sum.toString().replace(".", ","));
        cell1.setCellStyle(styleArialCE10);

        Cell cell2 = row.createCell(10);
        cell2.setCellValue("EUR");
        cell2.setCellStyle(styleArial);
    }

    public void AddTotalSum(Sheet sheet, BigDecimal totalSum, BigDecimal totalSumKn, int currentRow) {

        Row row2 = sheet.createRow(currentRow);
        Cell cell3 = row2.createCell(9);
        cell3.setCellValue(totalSumKn.toString().replace(".", ","));
        cell3.setCellStyle(styleArial);

        Cell cell14 = row2.createCell(10);
        cell14.setCellValue("kn");
        cell14.setCellStyle(styleArial);
        
        Row row = sheet.createRow(currentRow+1);
        Cell cell = row.createCell(9);
        cell.setCellValue(totalSum.toString().replace(".", ","));
        cell.setCellStyle(styleArialDoubleBorder);

        Cell cell1 = row.createCell(10);
        cell1.setCellValue("EUR");
        cell1.setCellStyle(styleArialDoubleBorder);
    }

    public void AddPageNumber(Sheet sheet, int i, int pageNum) {

        Row row = sheet.getRow(64);
        if (row == null)
        {           
                row = sheet.createRow(64);
        }
        Cell cell = row.createCell(10);
        cell.setCellValue(i + "/" + pageNum);
        cell.setCellStyle(styleArialCE10AlignRight);
    }

    public void AddMissingData(Sheet sheet, BigDecimal totalSum, BigDecimal totalSumKN) {
        
        Row row = sheet.getRow(16);
        Cell cell = row.createCell(9);
        cell.setCellValue(totalSumKN.toString().replace(".", ","));
        cell.setCellStyle(styleArial);
        
        row = sheet.getRow(17);
        cell = row.createCell(9);
        cell.setCellValue(totalSum.toString().replace(".", ","));
        cell.setCellStyle(styleArial);
    }

    public void AddAditionalData(Sheet sheet, int bolzenKom, double bolzenCijena, int welleKom, double welleCijena, int totalKom) {
        Row row = sheet.getRow(2);
        Cell cell = row.createCell(13);
        cell.setCellValue("BOLZEN");
        cell.setCellStyle(styleArialCE10AlignRight);
        Cell cell1 = row.createCell(14);
        cell1.setCellValue("WELLE");
        cell1.setCellStyle(styleArialCE10AlignRight);
        Row row1 = sheet.getRow(3);
        Cell cell2 = row1.createCell(12);
        cell2.setCellValue("Komada:");
        cell2.setCellStyle(styleArial);
        Cell cell3 = row1.createCell(13);
        cell3.setCellValue(bolzenKom);
        cell3.setCellStyle(styleArial);
        Cell cell4 = row1.createCell(14);
        cell4.setCellValue(welleKom);
        Row row2 = sheet.getRow(4);
        Cell cell7 = row2.createCell(12);
        cell7.setCellValue("Uk Cijena:");
        cell7.setCellStyle(styleArial);
        Cell cell5 = row2.createCell(13);
        cell5.setCellValue(String.valueOf(bolzenCijena).replace(".", ","));
        cell5.setCellStyle(styleArial);
        Cell cell6 = row2.createCell(14);
        cell6.setCellValue(String.valueOf(welleCijena).replace(".", ","));
        cell6.setCellStyle(styleArial);
        Row row3 = sheet.getRow(6);
        Cell cell9 = row3.createCell(12);
        cell9.setCellValue("Uk kom:");
        cell9.setCellStyle(styleArial);
        Cell cell10 = row3.createCell(13);
        cell10.setCellValue(totalKom);
        cell10.setCellStyle(styleArial);
    }

    public void WriteFooter(int i, Sheet sheet, BigDecimal totalSum) {
        
        Row row = sheet.getRow(i);        
       
        if (row==null)
        {
            row = sheet.createRow(i);
        }
        
        Cell cell = row.createCell(1);
        cell.setCellValue("Oslobođeno PDV-a prema čl.41,st.1 Zakona");
        cell.setCellStyle(styleArial);

        Row row1 = sheet.createRow(i + 2);
        Cell cell1 = row1.createCell(1);
        cell1.setCellValue("Lieferung frei Barssel");
        cell1.setCellStyle(styleArialBold);

        Row row2 = sheet.createRow(i + 4);
        Cell cell2 = row2.createCell(1);
        cell2.setCellValue("Kolli");
        cell2.setCellStyle(styleArial);
        Cell cell3 = row2.createCell(3);
        cell3.setCellValue(":");
        cell3.setCellStyle(styleArial);

        Row row3 = sheet.createRow(i + 5);
        Cell cell4 = row3.createCell(1);
        cell4.setCellValue("Brutto kg");
        cell4.setCellStyle(styleArial);
        Cell cell5 = row3.createCell(3);
        cell5.setCellValue(":");
        cell5.setCellStyle(styleArial);

        Row row4 = sheet.createRow(i + 6);
        Cell cell6 = row4.createCell(1);
        cell6.setCellValue("Netto  kg");
        cell6.setCellStyle(styleArial);

        Cell cell7 = row4.createCell(3);
        cell7.setCellValue(":");
        cell7.setCellStyle(styleArial);

        Row row5 = sheet.createRow(i + 8);
        Cell cell8 = row5.createCell(1);
        cell8.setCellValue("ZAKLJUČAK");
        cell8.setCellStyle(styleArial);

        Cell cell9 = row5.createCell(3);
        cell9.setCellValue(":");
        cell9.setCellStyle(styleArial);

        Cell cell10 = row5.createCell(4);
        cell10.setCellValue("911");
        cell10.setCellStyle(styleArialCE10AlignRight);

        Cell cell11 = row5.createCell(5);
        cell11.setCellValue(" /  10.10.11.");
        cell11.setCellStyle(styleArial);
        
        
        Row rowN1 = sheet.createRow(i + 10);
        Cell cellN12 = rowN1.createCell(1);
        cellN12.setCellValue("Oznaka operatera            :");
        cellN12.setCellStyle(styleArial);
        
        Cell cellN14 = rowN1.createCell(4);
        cellN14.setCellValue("1");
        cellN14.setCellStyle(styleArial);
        
        Row rowN2 = sheet.createRow(i + 11);
        Cell cellN2 = rowN2.createCell(1);
        cellN2.setCellValue("Način plaćanja                :");
        cellN2.setCellStyle(styleArial);

        Cell cellN4= rowN2.createCell(4);
        cellN4.setCellValue("transakcijski račun");
        cellN4.setCellStyle(styleArial);
        
        Row row6 = sheet.createRow(i + 12);
        Cell cell12 = row6.createCell(1);
        cell12.setCellValue("BROJ TRANSAKCIJSKOG RAČUNA / BANKVERBINDUNG  :       ");
        cell12.setCellStyle(styleArial);


        Row row7 = sheet.createRow(i + 13);
        Cell cell13 = row7.createCell(1);
        cell13.setCellValue(totalSum.toString().replace(".", ","));
        cell13.setCellStyle(styleArial);


        Cell cell14 = row7.createCell(2);
        cell14.setCellValue("EUR   an");
        cell14.setCellStyle(styleArial);
        Cell cell15 = row7.createCell(4);
        cell15.setCellValue("ZAGREBAČKA BANKA DD, Zagreb");
        cell15.setCellStyle(styleArial);

        Row row8 = sheet.createRow(i + 14);
        Cell cell16 = row8.createCell(4);
        cell16.setCellValue("IBAN            : HR6523600001101291901");
        cell16.setCellStyle(styleArial);

        Row row9 = sheet.createRow(i + 15);
        Cell cell17 = row9.createCell(4);
        cell17.setCellValue("SWIFT Code : ZABA HR 2X");
        cell17.setCellStyle(styleArial);
    }

    public void AddTopAndBottomBorder(Sheet sheet) {
        Row row = sheet.createRow(23);
        for (int i = 1; i < 11; i++) {
            ((Cell) (row.createCell(i))).setCellStyle(styleTopAndBottomBorder);
        }
    }

    public void WriteOtpremnicaFooter(int i, Sheet sheet) {
        Row row = sheet.createRow(i);
        Cell cell = row.createCell(1);
        cell.setCellValue("Der Unterzeichner erklert, dass die in diesem Dokument aufgefuhrten Waren ");
        cell.setCellStyle(styleArial);

        Row row1 = sheet.createRow(i + 1);
        Cell cell1 = row1.createCell(1);
        cell1.setCellValue("Ursprungserzeugnisse des Landes HR entsprechen.Eine Kumulierung wurde nicht ");
        cell1.setCellStyle(styleArial);

        Row row2 = sheet.createRow(i + 2);
        Cell cell2 = row2.createCell(1);
        cell2.setCellValue("angewendet.Der Unterzeichner verpflichtet sich, den Zollbehorden alle von ihnen");
        cell2.setCellStyle(styleArial);

        Row row3 = sheet.createRow(i + 3);
        Cell cell3 = row3.createCell(1);
        cell3.setCellValue("zusatzlich verlangten Belege zur Verfugung zu stellen.");
        cell3.setCellStyle(styleArial);

        Row row4 = sheet.createRow(i + 4);
        Cell cell4 = row4.createCell(5);
        cell4.setCellValue("Žig i potpis odgovorne osobe:Irena Štefan");
        cell4.setCellStyle(styleArial);
    }
}
