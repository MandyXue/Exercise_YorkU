package com.company.xml;

import com.company.javabean.Committer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mandyxue on 2017/3/4.
 */
public class CommitterParse {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MyDefaultHandler handler = new MyDefaultHandler();

            File xmlFile = new File("/Users/mandyxue/Desktop/Exercise_YorkU/rawMaterials/hbaseSVNLog/hbase_svn_log.xml");

            InputStream stream = new FileInputStream(xmlFile);
            parser.parse(stream, handler);

            ArrayList<Committer> committers = handler.getCommitters();
            findLargestCommitter(committers);
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
}
