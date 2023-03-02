package com.bca.simpleapi.entities;

import com.bca.simpleapi.utils.Constants;

public class DeviceResponse {

    private String status = Constants.FAIL;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
