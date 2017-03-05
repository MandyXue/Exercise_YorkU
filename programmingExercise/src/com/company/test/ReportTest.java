package com.company.test;

import com.company.javabean.Report;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mandyxue on 2017/3/2.
 */
public class ReportTest {

    @Test
    public void testReport() throws Exception {
        Report testReport1 = new Report("Fri, 1 Feb 2008 21:23:06 +0000", "Sat, 2 Feb 2008 05:28:25 +0000");
        Assert.assertEquals(testReport1.getResolutionTime(), 29119);

        Report testReport2 = new Report("", "Sat, 2 Feb 2008 05:28:25 +0000");
        Assert.assertEquals(testReport2.getResolutionTime(), -1);

        Report testReport3 = new Report("Sat, 2 Feb 2008 05:28:25 +0000", "");
        Assert.assertEquals(testReport3.getResolutionTime(), -1);

        Report testReport4 = new Report(null, "");
        Assert.assertEquals(testReport4.getResolutionTime(), -1);

        Report testReport5 = new Report("Thu, 10 Sep 2015 13:01:46 +0000", "Mon, 20 Jun 2016 19:30:17 +0000");
        System.out.println(testReport5.getResolutionTime());
    }
}