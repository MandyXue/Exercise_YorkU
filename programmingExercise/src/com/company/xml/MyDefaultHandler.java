package com.company.xml;

import com.company.javabean.Committer;
import com.company.javabean.Report;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by mandyxue on 2017/3/4.
 */
public class MyDefaultHandler extends DefaultHandler {

    // XML file type
    private String fileType;

    // list of committers
    private ArrayList<Committer> committers;
    private Committer committer;

    // list of reports
    private Report report;
    private String reportCreatedTime;
    private String reportResolvedTime;

    // tag name after searching
    private String tagName;

    // getters and setters

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public ArrayList<Committer> getCommitters() {
        return committers;
    }

    public void setCommitters(ArrayList<Committer> committers) {
        this.committers = committers;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    // list initializer
    @Override
    public void startDocument() throws SAXException {
        if (fileType.equals("committer")) {
            this.committers = new ArrayList<>();
        } else if (fileType.equals("report")){
            this.report = new Report();
        } else {
            System.out.println("Error: Invalid input file type.");
        }
    }

    // start parsing
    @Override
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (fileType.equals("committer")) {
            if (qName.equals("logentry")) {
                this.committer = new Committer();
            }
        } else {
            // report
            if (qName.equals("item")) {
                this.reportCreatedTime = "";
                this.reportResolvedTime = "";
            }
        }
        this.tagName = qName;
    }

    @Override
    public void endElement(String s, String s1, String qName) throws SAXException {
        if (fileType.equals("committer")) {
            if (qName.equals("logentry")) {
                Integer flag = -1;
                for (int i = 0; i < this.committers.size(); i++) {
                    if (committers.get(i).getName().equals(this.committer.getName())) {
                        flag = i;
                        break;
                    }
                }
                if (flag == -1) {
                    this.committers.add(this.committer);
                } else {
//                System.out.println("Before: "+this.committers.get(flag).getName() +","+this.committers.get(flag).getNumberOfRevisions());
                    Committer newCommitter = this.committers.get(flag);
                    newCommitter.addRevision();
                    this.committers.set(flag, newCommitter);
//                System.out.println("After: "+this.committers.get(flag).getName() +","+this.committers.get(flag).getNumberOfRevisions());
                }
            }
        } else {
            // report
            if (qName.equals("item")) {

                if (this.reportResolvedTime != null && !this.reportResolvedTime.equals("")) {
//                    System.out.println(this.reportCreatedTime +","+this.reportResolvedTime);
                    try {
                        Report newReport = new Report(this.reportCreatedTime, this.reportResolvedTime);
//                        this.reports.add(newReport);
                        this.report = newReport;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        this.tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("--------------finish reading XML document--------------");
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (fileType.equals("committer")) {
            if(this.tagName!=null){
                String content = new String(ch,start,length);
                if (this.tagName.equals("author")) {
                    this.committer.setName(content);
                }
            }
        } else {
            // report
            if(this.tagName!=null){
                String content = new String(ch,start,length);
                if (this.tagName.equals("created")) {
                    this.reportCreatedTime = content;
                } else if (this.tagName.equals("resolved")) {
                    this.reportResolvedTime = content;
                }
            }
        }

    }
}
