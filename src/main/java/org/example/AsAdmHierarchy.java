package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AsAdmHierarchy {

    @JacksonXmlProperty(localName = "ID")
    @JsonProperty("ID")
    private long Id;
    @JacksonXmlProperty(localName = "OBJECTID")
    @JsonProperty("OBJECTID")
    private int objectId;
    @JacksonXmlProperty(localName = "PARENTOBJID")
    @JsonProperty("PARENTOBJID")
    private int parentObjectId;

    @JacksonXmlProperty(localName = "CHANGEID")
    @JsonProperty("CHANGEID")
    private long changeId;

    @JacksonXmlProperty(localName = "PREVID")
    @JsonProperty("PREVID")
    private long prevId;

    @JacksonXmlProperty(localName = "NEXTID")
    @JsonProperty("NEXTID")
    private long nextId;

    @JacksonXmlProperty(localName = "UPDATEDATE")
    @JsonProperty("UPDATEDATE")
    private String updateDate;

    @JacksonXmlProperty(localName = "STARTDATE")
    @JsonProperty("STARTDATE")
    private String startDate;
    @JacksonXmlProperty(localName = "ENDDATE")
    @JsonProperty("ENDDATE")
    private String endDate;

    @JacksonXmlProperty(localName = "ISACTIVE")
    @JsonProperty("ISACTIVE")
    private String isActive;
    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(int parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    @JsonIgnore
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonIgnore
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    @JsonIgnore
    public long getChangeId() {
        return changeId;
    }

    public void setChangeId(long changeId) {
        this.changeId = changeId;
    }

    @JsonIgnore
    public long getPrevId() {
        return prevId;
    }

    public void setPrevId(int prevId) {
        this.prevId = prevId;
    }

    @JsonIgnore
    public long getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    @JsonIgnore
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    public boolean isActive() {
        return "1".equals(isActive);
    }

    public void setActive(boolean active) {
        this.isActive = active ? "1" : "0";
    }


}
