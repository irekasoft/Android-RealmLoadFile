package com.irekasoft.realmreadfile;


import io.realm.RealmObject;

public class RealmTestClass0 extends RealmObject {
    private int integerValue;
    private String stringValue;
    private byte[] dataValue;

    public int getIntegerValue() { return integerValue; }

    public void setIntegerValue(int integerValue) { this.integerValue = integerValue; }

    public String getStringValue() { return stringValue; }

    public void setStringValue(String stringValue) { this.stringValue = stringValue; }

    public byte[] getDataValue() { return dataValue; }

    public void setDataValue(byte[] dataValue) { this.dataValue = dataValue; }

}
