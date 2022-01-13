package com.leinovo.codepractice.pdfCharttest;

import java.io.PrintWriter;

/**
 * @description: PieChartTest
 * @author: leiming5
 * @date: 2022/1/4 9:50
 */
public class PieChartTest {
    public static void main(String[] args) {
        Long[] testData = {new Long(10), new Long(20), new Long(30), new Long(40)};
        generatePieChart(testData,"",null,new PrintWriter((System.out)),580,250);
    }

    private static void generatePieChart(Long[] testData, String s, Object o, PrintWriter printWriter, int i, int i1) {
    }

}
