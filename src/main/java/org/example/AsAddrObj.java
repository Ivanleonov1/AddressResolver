package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AsAddrObj {

    @JacksonXmlProperty(localName = "ID")
    @JsonProperty("ID")
    private long Id;
    @JacksonXmlProperty(localName = "OBJECTID")
    private int objectId;

    @JacksonXmlProperty(localName ="OBJECTGUID")
    private String objectGUID;

    @JacksonXmlProperty(localName ="CHANGEID")
    private long changeId;

    @JacksonXmlProperty(localName ="NAME")
    private String name;

    @JacksonXmlProperty(localName ="TYPENAME")
    private String typeName;

    @JacksonXmlProperty(localName ="LEVEL")
    private long level;

    @JacksonXmlProperty(localName ="OPERTYPEID")
    private long operTypeId;

    @JacksonXmlProperty(localName ="PREVID")
    private long prevId;

    @JacksonXmlProperty(localName ="NEXTID")
    private long nextId;

    @JacksonXmlProperty(localName ="UPDATEDATE")
    private String updateDate;

    @JacksonXmlProperty(localName ="STARTDATE")
    private String startDate;

    @JacksonXmlProperty(localName ="ENDDATE")
    private String endDate;

    @JacksonXmlProperty(localName ="ISACTUAL")
    @JsonProperty("ISACTUAL")
    private String isActual;

    @JacksonXmlProperty(localName ="ISACTIVE")
    @JsonProperty("ISACTIVE")
    private String isActive;

    @JsonIgnore
    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @JsonIgnore
    public String getObjectGUID() {
        return objectGUID;
    }

    public void setObjectGUID(String objectGUID) {
        this.objectGUID = objectGUID;
    }
    @JsonIgnore
    public long getChangeId() {
        return changeId;
    }

    public void setChangeId(long changeId) {
        this.changeId = changeId;
    }
    @JsonIgnore
    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
    @JsonIgnore
    public long getOperTypeId() {
        return operTypeId;
    }

    public void setOperTypeId(long operTypeId) {
        this.operTypeId = operTypeId;
    }
    @JsonIgnore
    public long getPrevId() {
        return prevId;
    }

    public void setPrevId(long prevId) {
        this.prevId = prevId;
    }
    @JsonIgnore
    public long getNextId() {
        return nextId;
    }

    public void setNextId(long nextId) {
        this.nextId = nextId;
    }
    @JsonIgnore
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isActual() {
        return "1".equals(isActual);
    }

    public void setActual(boolean isActual) {
        this.isActual = isActual ? "1" : "0";
    }

    public boolean isActive() {
        return "1".equals(isActive);
    }

    public void setActive(boolean active) {
        this.isActive = active ? "1" : "0";
    }


}
