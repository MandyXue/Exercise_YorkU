package com.company.javabean;

/**
 * Created by mandyxue on 2017/3/2.
 */
public class Committer {

    // constructor
    public Committer() {
        name = "";
        numberOfRevisions = 1;
    }

    // parameters
    private String name;
    private Integer numberOfRevisions;

    // methods
    public void addRevision() {
        ++this.numberOfRevisions;
    }

    // setter and getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfRevisions() {
        return numberOfRevisions;
    }

    public void setNumberOfRevisions(Integer numberOfRevisions) {
        this.numberOfRevisions = numberOfRevisions;
    }
}
