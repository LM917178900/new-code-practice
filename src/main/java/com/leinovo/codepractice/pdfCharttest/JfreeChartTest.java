package com.leinovo.codepractice.pdfCharttest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @description: JfreeChartTest
 * @author: leiming5
 * @date: 2022/1/3 14:02
 */
public class JfreeChartTest {

    public static void saveCharToJPG(JFreeChart chart, String fileName) throws IOException {
        OutputStream fos=null;
        try{
//fos=new FileOutputStream("D:\\fruit.jpg");
            fos=new BufferedOutputStream(new FileOutputStream(fileName));
            ChartUtilities.writeChartAsJPEG(fos,1.0f,chart,1024,768,null);
        }catch(Exception e){
        }finally{
            fos.close();
        }
    }

    public static void main(String[] args) throws IOException{
//saveCharToJPG(createBarChart(createDataset()),"D:\\fruit.jpg");
        JFreeChart chart2 = createChart2();
        JfreeHelper.chartFix(chart2);
        saveCharToJPG(chart2,"D:\\fruit2.jpg");
    }
    ///这两个不用我说了吧.就是设置数据什么的.
//    createChart2(createDataset() ;
    private static JFreeChart createChart2(){
        return JfreeHelper.getChart(JfreeHelper.getDataset());
    };
//    内容你自己已经会了,不说.看返回值也知道.
////////////////这是生成本地文件的.
//            也可以通过JAVABEAN.在JSP里调用:
//    来一个柱状图的:
    //数据...//////////////
    private static CategoryDataset getDataSet2() {
        return null;
    };
    public static String generateBarChart(HttpSession session, PrintWriter pw, int w, int h){
        String filename=null;
        CategoryDataset dataset=getDataSet2();
        JFreeChart chart= ChartFactory.createBarChart3D(
                "水果产量图", // 图表标题
                "水果", // 目录轴的显示标签
                "产量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
        );
        try{
            /*------得到chart的保存路径----*/
            ChartRenderingInfo info=new ChartRenderingInfo(new StandardEntityCollection());
            filename= ServletUtilities.saveChartAsPNG(chart,w,h,info,session);
            /*------使用printWriter将文件写出----*/
            ChartUtilities.writeImageMap(pw,filename,info,true);
            pw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        return filename;
    }
}
