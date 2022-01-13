import java.awt.Insets;
import java.awt.Image;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.text.NumberFormat;
import javax.servlet.http.HttpSession;
import org.jfree.data.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.renderer.VerticalBarRenderer;
import org.jfree.chart.renderer.StandardXYItemRenderer;
import org.jfree.chart.plot.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.tooltips.*;
import org.jfree.chart.urls.*;
import org.jfree.chart.servlet.*;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Font;

/**
 　* ＜p＞Title: ＜/p＞
 　* ＜p＞Description: ＜/p＞
 　* ＜p＞Copyright: Copyright (c) 2003＜/p＞
 　* ＜p＞Company: ＜/p＞
 　* @author jagie
 　* @version 1.0
 */

public class GSPieChart
{
　public static void main(String[] args) {
　　Long[] testData = { new Long(10), new Long(20), new Long(30), new Long(40) } ;
　generatePieChart(testData, "", null, new PrintWriter(System.out), 580, 250);
}

    /**
     　* 生成图像文件
     　* @param datas Long[]数组
     　* @param targetUrl 点饼形图上的扇形，定向到的url
     　* @param session httpsession
     　* @param pw PrintWriter
     　* @param w 生成的图的宽度
     　* @param h 生成的图的高度
     　* @return 生成的图像文件的路径
     */

    public static String generatePieChart (Long[] datas, String targetUrl, HttpSession session, PrintWriter pw, int w, int h) {
　　String filename = null;
　　try
　　{
　　　DefaultPieDataset data = new DefaultPieDataset();
　　　data.setValue("正常纳税人", datas[0]);
　　　data.setValue("异常纳税人", datas[1]);
　　　data.setValue("数据不全纳税人", datas[2]);
　　　data.setValue("未处理纳税人", datas[3]);
　　　// Create the chart object
　　　Pie3DPlot plot = new Pie3DPlot(data);
　　　plot.setInsets(new Insets(0, 5, 5, 5));
　　　plot.setForegroundAlpha(0.6f);
　　　plot.setSectionLabelType(plot.NO_LABELS);
　　　plot.setURLGenerator(new StandardPieURLGenerator(targetUrl, "type"));
　　　plot.setToolTipGenerator(new StandardPieToolTipGenerator());
　　　JFreeChart chart = new JFreeChart("数据处理结果统计图", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
　　　chart.setTitle(new TextTitle("数据处理结果统计图", new Font("黑体", Font.BOLD, 15)));
　　　StandardLegend sl = (StandardLegend) chart.getLegend();
　　　sl.setItemFont(new Font("黑体", Font.TRUETYPE_FONT, 12));
　　　chart.setBackgroundPaint(new java.awt.Color(254, 254, 141));
　　　// 因为jfreechart把生成的png文件保存在系统的临时文件夹中，你需要在适当的
　　　//时候调用session.removeAttribute("JFreeChart_Deleter")，这样可以保证png文
　　　//件被删除掉
　　　ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
　　　filename = ServletUtilities.saveChartAsPNG(chart, w, h, info, session);
　　　// Write the image map to the PrintWriter
　　　ChartUtilities.writeImageMap(pw, filename, info); pw.flush();
　　} catch (Exception e)
　　　{
　　　　System.out.println("Exception - " + e.toString());
　　　　e.printStackTrace(System.out);
　　　　filename = "public_error_500x300.png";
　　　}
　 return filename;
　}
}
