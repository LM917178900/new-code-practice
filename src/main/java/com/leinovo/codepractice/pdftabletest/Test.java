package com.leinovo.codepractice.pdftabletest;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Test {
    public static String File_Path = "D:/";

    public static void main(String[] args) throws Exception {
        Test.createPDF();

    }

    /**
     * 生成PDF
     *
     * @throws Exception
     */
    public static void createPDF() throws Exception {
        // 设置字体(由于自己缺少这个包)
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        // 第一步，创建document对象
        Rectangle rectPageSize = new Rectangle(PageSize.A4);

        //下面代码设置页面横置
        //rectPageSize = rectPageSize.rotate();

        //创建document对象并指定边距
        Document document = new Document(rectPageSize, 50, 50, 50, 50);
//        Document document = new Document();
//        try {
        // 第二步,将Document实例和文件输出流用PdfWriter类绑定在一起
        //从而完成向Document写，即写入PDF文档
        PdfWriter.getInstance(document, new FileOutputStream(File_Path + "PDF.pdf"));
        //第3步,打开文档
        document.open();
        //第3步,向文档添加文字. 文档由段组成
        document.add(new Paragraph("Hello World"));

        document.add(new Paragraph("世界你好"));

        PdfPTable table = new PdfPTable(3);
        // 生成表格   TODO 其他操作请自己百度
        for (int i = 0; i < 12; i++) {
            if (i == 0) {
                PdfPCell cell = new PdfPCell();
                cell.setColspan(3);
                //cell.setBackgroundColor(new Color(180,180,180));
                cell.addElement(new Paragraph("表格头"));
                table.addCell(cell);
            } else {
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Paragraph("表格内容"));
                table.addCell(cell);
            }
        }
        document.add(table);
//        } catch (DocumentException de) {
//            System.err.println(de.getMessage());
//        } catch (IOException ioe) {
//            System.err.println(ioe.getMessage());
//        }
        //关闭document
        document.close();

        System.out.println("PDF生成成功!");
    }

}
