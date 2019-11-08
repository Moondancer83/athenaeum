package com.kalee.athenaeum.data;

import java.util.Date;

public class Document {

    private Long id;
    private String name;
    private long size;
    private String owner;
    private Date modifiedAt;
    private byte[] data;

    public Document() {
    }

    public Document(final String name, final byte[] data, final String owner) {
        this.name = name;
        this.size = data.length;
        this.owner = owner;
        this.data = data;
    }

    public Long getId() {
        return id;
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
}
