package com.lukasz.mgr.performance.control.requeststrike;

public class UsersResult {

    protected String algorithmName;
    protected Long dbTime;

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public Long getDbTime() {
        return dbTime;
    }

    public void setDbTime(Long dbTime) {
        this.dbTime = dbTime;
    }
}
