package com.lukasz.mgr.performance.control.algorithm.result;

public class AlgorithmResult {

    protected String algorithmName;
    protected Long alhorithmTime;
    protected Long dbTime;
    protected long iterations;

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

    public long getIterations() {
        return iterations;
    }

    public void setIterations(long iterations) {
        this.iterations = iterations;
    }
}
