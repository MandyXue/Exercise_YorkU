package com.company.javabean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mandyxue on 2017/3/2.
 */
public class Report {

    // constructor
    public Report(){
        createdTime = null;
        resolvedTime = null;
        resolutionTime = -1;
    }

    public Report(String createdTimeStr, String resolvedTimeStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        sdf.setLenient(false);

        if (createdTimeStr != null && !createdTimeStr.equals("")) {
            this.createdTime = sdf.parse(createdTimeStr);
        } else {
            this.createdTime = null;
        }

        if (resolvedTimeStr != null && !resolvedTimeStr.equals("")) {
            this.resolvedTime = sdf.parse(resolvedTimeStr);
        } else {
            this.resolvedTime = null;
        }

        if (createdTime != null && resolvedTime != null) {
            resolutionTime = (resolvedTime.getTime() - createdTime.getTime())/1000;
        } else {
            this.resolutionTime = -1;  // -1 represent no resolved time or no created time
        }
    }

    // parameters

    private Date resolvedTime;
    private Date createdTime;

    // -1 represent no resolved time or no created time
    // 单位为秒
    private long resolutionTime;

    // getters and setters

    public Date getResolvedTime() {
        return resolvedTime;
    }

    public void setResolvedTime(Date resolvedTime) {
        this.resolvedTime = resolvedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public long getResolutionTime() {
        return resolutionTime;
    }

    public void setResolutionTime(long resolutionTime) {
        this.resolutionTime = resolutionTime;
    }
}
