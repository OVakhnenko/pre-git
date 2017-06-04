package com.vakhnenko.entity;

import java.util.Date;

public class Changes {
    private int id;
    private String unit;
    private int type;
    private String author;
    private Date date;
    private String reason;

    public Changes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void clear() {
        setType(0);
        setUnit(null);
        setDate(null);
        setReason(null);
        setAuthor(null);
    }

    public boolean filled() {
        if (date != null && reason != null && author != null)
            return true;
        else return false;
    }

    @Override
    public String toString() {
        return unit + " " + author + " " + date + " " + reason;
    }
}
