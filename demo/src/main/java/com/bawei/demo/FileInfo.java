package com.bawei.demo;

import java.io.Serializable;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/4
 * Time: 10:00
 */
public class FileInfo implements Serializable {
    private int id;
    private String url;
    private String filename;
    private int length;
    private int finished;

    public FileInfo(int id, String url, String filename, int length, int finished) {
        this.id = id;
        this.url = url;
        this.filename = filename;
        this.length = length;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", filename='" + filename + '\'' +
                ", length=" + length +
                ", finished=" + finished +
                '}';
    }
}
