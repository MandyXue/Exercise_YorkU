package com.company.JavaBean;

/**
 * Created by mandyxue on 2017/3/2.
 */
public class Committer {

    public Committer() {
        name = "";
        numberOfRevisions = 0;
    }

    // parameters
    private String name;
    private int numberOfRevisions;

    // methods
    public int addRevision() {
        numberOfRevisions++;
        return numberOfRevisions;
    }

    // setter and getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfRevisions() {
        return numberOfRevisions;
    }

    public void setNumberOfRevisions(int numberOfRevisions) {
        this.numberOfRevisions = numberOfRevisions;
    }
}
