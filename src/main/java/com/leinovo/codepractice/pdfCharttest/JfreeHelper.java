package com.leinovo.codepractice.pdfCharttest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;

import java.awt.*;

/**
 * @description: JfreeHelper
 * @author: leiming5
 * @date: 2022/1/3 14:26
 */
public class JfreeHelper {

    public static  CategoryDataset getDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1310, "药品采购金额", "崔庙镇");
        dataset.addValue(720.68, "药品采购金额", "泗水镇");
        dataset.addValue(675.3, "药品采购金额", "高三镇");
        dataset.addValue(560, "药品采购金额", "程关镇");
        dataset.addValue(680.88, "药品采购金额", "六合镇");
        dataset.addValue(780, "药品采购金额", "环翠镇");

        return dataset;
    }

    public static JFreeChart getChart(CategoryDataset dataset){
        JFreeChart chart = ChartFactory.createBarChart3D(
                "药品采购金额汇总",// 图形名称
                "",// 分类名称，为横坐标名称
                "采购金额(元)",// 值名称，为纵坐标名称
                dataset,// 数据集合
                PlotOrientation.VERTICAL,// 垂直显示
                false,// 是否显示图例
                false,// 是否使用工具提示
                false);// 是否使用url
        return chart;
    }

    public static void chartFix(JFreeChart chart){
        // 配置字体
        Font xfont = new Font("宋体", Font.PLAIN, 12);// X轴
        Font yfont = new Font("宋体", Font.PLAIN, 12);// Y轴
        Font kfont = new Font("宋体", Font.PLAIN, 12);// 底部
        Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题
        // 图形的绘制结构对象,对于饼图不适用
        CategoryPlot plot = chart.getCategoryPlot();

        // 图片标题
        chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));

        // 底部
        LegendTitle legendTitle = chart.getLegend();
        if (legendTitle != null) {
            legendTitle.setItemFont(kfont);
        }

        // X 轴
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(xfont);// 轴标题
        domainAxis.setTickLabelFont(xfont);// 轴数值
        domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色
         domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Y 轴
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(yfont);
        rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
        rangeAxis.setTickLabelFont(yfont);
    }
}
