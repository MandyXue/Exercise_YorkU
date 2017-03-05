package com.company.xml;

import com.company.javabean.Committer;
import com.company.javabean.Report;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by mandyxue on 2017/3/4.
 */
public class Parse {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // committer
            MyDefaultHandler handler1 = new MyDefaultHandler();
            handler1.setFileType("committer");

            File xmlFile = new File("/Users/mandyxue/Desktop/Exercise_YorkU/rawMaterials/hbaseSVNLog/hbase_svn_log.xml");

            InputStream stream = new FileInputStream(xmlFile);
            parser.parse(stream, handler1);

            ArrayList<Committer> committers = handler1.getCommitters();
            findLargestCommitter(committers);


            // report
            ArrayList<Report> reports = new ArrayList<>();
            MyDefaultHandler handler2 = new MyDefaultHandler();
            handler2.setFileType("report");

            String jiraDirectory = "/Users/mandyxue/Desktop/Exercise_YorkU/rawMaterials/hbaseBugReport";
            File xmlDirectory = new File(jiraDirectory);

            String[] fileList = xmlDirectory.list();
            System.out.println("--------------------------------------------");
            System.out.println("Reading files...");
            for (int i = 0; i < fileList.length; i++) {
                if (!fileList[i].endsWith(".xml")) {
                    continue;
                }
                File jiraFile = new File(jiraDirectory + "/" + fileList[i]);

                InputStream stream1 = new FileInputStream(jiraFile);
                parser.parse(stream1, handler2);

                Report newReport = handler2.getReport();
                reports.add(newReport);
            }

            findLargestResolutionTime(reports);

            // revisions
            HashMap<Integer,Integer> revisions = handler1.getRevisions();
            System.out.println("--------------------------------------------");
            reportNumberOfRevisions(revisions);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findLargestCommitter(ArrayList<Committer> committers) {
        Iterator<Committer> it = committers.iterator();
        Integer largest = 0;
        String name = "";
        while (it.hasNext()) {
            Committer temp = it.next();
            if (temp.getNumberOfRevisions() > largest) {
                largest = temp.getNumberOfRevisions();
                name = temp.getName();
            }
//            System.out.println(temp.getName() + ", " + temp.getNumberOfRevisions());
        }
        System.out.println("The committer who submitted the largest number of code revisions is: " + name + ", and his number of revisions is: " + largest);
    }

    public static void findLargestResolutionTime(ArrayList<Report> reports) {
        Iterator<Report> it = reports.iterator();
        long largest = 0;
        while (it.hasNext()) {
            Report temp = it.next();
            if (temp.getResolutionTime() > largest) {
                largest = temp.getResolutionTime();
            }
//            System.out.println(temp.getResolutionTime());
        }
        System.out.println("The maximum resolution time is: " + largest/(365*24*60*60) + " years " + largest%(365*24*60*60)/24/3600 + " days " + (largest%(24*60*60))/3600 + " hours " + (largest%3600)/60 + " minutes " + largest%60 + " seconds.");
    }

    public static void reportNumberOfRevisions(HashMap<Integer,Integer> revisions) {
        Iterator it = revisions.values().iterator();
        Integer max = 0;
        Integer min = 1;
        Double sum = 0.0;
        while (it.hasNext()) {
            Integer temp = (Integer) it.next();
            if (temp > max) {
                max = temp;
            }
            if (temp < min) {
                min = temp;
            }
            sum = sum + temp;
        }
        DecimalFormat df = new DecimalFormat("#.####");
        float f = Float.valueOf(df.format(sum/17326));
        System.out.println("The minimum number of code revisions is: " + min + " times.");
        System.out.println("The average number of code revisions is: " + f + " times.");
        System.out.println("The maximum number of code revisions is: " + max + " times.");
    }
}
