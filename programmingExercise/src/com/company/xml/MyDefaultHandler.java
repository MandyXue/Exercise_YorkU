package com.company.xml;

import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Created by mandyxue on 2017/3/4.
 */
public class MyDefaultHandler extends DefaultHandler {

    // XML file type
    private String fileType;

    // list of committers
    private HashMap<String,Integer> committers;
    private String committer;

    // tag name after searching
    private String tagName;

    // getters and setters

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    // list initializer
    @Override
    public void startDocument() throws SAXException {
//        if (fileType.equals("commit")) {
            this.committers = new HashMap<>();
//        } else {
//            reports = new ArrayList<Report>();
//        }
    }

    // start parsing
    @Override
    public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("logentry")) {
            this.committer = String.valueOf(attributes.getValue(0));
        }
        this.tagName = qName;
    }

    @Override
    public void endElement(String s, String s1, String qName) throws SAXException {
        if (qName.equals("logentry")) {
            if (!committers.containsKey(this.committer)) {
                committers.put(this.committer, 1);
            } else {
                Integer value = committers.get(this.committer);
                committers.put(this.committer, value+1);
            }
        }
        this.tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {

    }
}
