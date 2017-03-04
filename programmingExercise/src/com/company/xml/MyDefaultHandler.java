package com.company.xml;

import com.company.javabean.Committer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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

    // tag name after searching
    private String tagName;

    // getters and setters

    public ArrayList<Committer> getCommitters() {
        return committers;
    }

    public void setCommitters(ArrayList<Committer> committers) {
        this.committers = committers;
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
        this.committers = new ArrayList<>();
    }

    // start parsing
    @Override
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("logentry")) {
            this.committer = new Committer();
        }
        this.tagName = qName;
    }

    @Override
    public void endElement(String s, String s1, String qName) throws SAXException {
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
        this.tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("--------------finish reading XML document--------------");
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(this.tagName!=null){
            String content = new String(ch,start,length);
            if (this.tagName.equals("author")) {
                this.committer.setName(content);
            }
        }
    }
}
