package com.lukasz.mgr.performance.control.algorithm.result;

public class AlgorithmResult {

    private String algorithmName;
    private Long alhorithmTime;
    private Long dbTime;

    public Long getAlhorithmTime() {
        return alhorithmTime;
    }

    public void setAlhorithmTime(Long alhorithmTime) {
        this.alhorithmTime = alhorithmTime;
    }

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
