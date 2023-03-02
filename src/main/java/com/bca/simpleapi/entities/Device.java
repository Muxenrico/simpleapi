package com.bca.simpleapi.entities;

public class Device {

    public String signature;
    public String apkName;
    public int printTotal;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public int getPrintTotal() {
        return printTotal;
    }

    public void setPrintTotal(int printTotal) {
        this.printTotal = printTotal;
    }
}