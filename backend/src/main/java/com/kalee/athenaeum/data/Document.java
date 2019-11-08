package com.kalee.athenaeum.data;

import java.util.Date;

public class Document {

    private String name;
    private long size;
    private String owner;
    private Date modifiedAt;
    private byte[] data;
    private String url;

    public Document() {
    }

    public Document(final String name, final byte[] data) {
        this.name = name;
        this.size = data.length;
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(final long size) {
        this.size = size;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(final Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

}
