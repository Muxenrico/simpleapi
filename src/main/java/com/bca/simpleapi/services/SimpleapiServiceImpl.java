package com.bca.simpleapi.services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bca.simpleapi.entities.Device;
import com.bca.simpleapi.entities.DeviceResponse;
import com.bca.simpleapi.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class SimpleapiServiceImpl implements SimpleapiService {

    @Override
    public DeviceResponse addDevice(Device device) {
        DeviceResponse deviceResponse = new DeviceResponse();

        List<Device> deviceData = readJsonData();
        Device devicePresent = null;

        if (deviceData != null) {
            devicePresent = deviceData.parallelStream().filter(dev -> dev.getSignature().equals(device.getSignature()))
                    .findAny().orElse(null);
        } else {
            deviceData = new ArrayList<>();
        }

        if (devicePresent == null) {
            deviceData.add(device);

            boolean status = writeJsonData(deviceData);

            if (status) {
                deviceResponse.setStatus(Constants.SUCCESS);
                deviceResponse.setData(Constants.DEVICE_ADDED_SUCCESSFULLY);
            }
        } else {
            deviceResponse.setData(Constants.DEVICE_ALREADY_PRESENT);
        }
        return deviceResponse;
    }

    public List<Device> readJsonData() {
        List<Device> devices = new ArrayList<>();
        try {
            String json = Files.readString(Path.of("devices.json"));
            devices = new Gson().fromJson(json, new TypeToken<List<Device>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devices;
    }

    public boolean writeJsonData(List<Device> deviceData) {
        boolean status = false;
        try (FileWriter file = new FileWriter("devices.json")) {
            file.write(new Gson().toJson(deviceData));
            file.flush();

            status = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public DeviceResponse fetchAllDevice() {
        DeviceResponse deviceResponse = new DeviceResponse();

        List<Device> deviceData = readJsonData();

        deviceResponse.setStatus(Constants.SUCCESS);
        deviceResponse.setData(deviceData);
        return deviceResponse;
    }

    @Override
    public DeviceResponse fetchDeviceDetails(String signature) {
        DeviceResponse deviceResponse = new DeviceResponse();

        List<Device> deviceData = readJsonData();
        Device device = deviceData.parallelStream().filter(dev -> dev.getSignature().equals(signature)).findAny().orElse(null);

        if(device != null) {
            deviceResponse.setStatus(Constants.SUCCESS);
            deviceResponse.setData(device);
        } else {
            deviceResponse.setStatus(Constants.FAIL);
            deviceResponse.setData(Constants.DEVICE_NOT_FOUND);
        }
        return deviceResponse;
    }

    @Override
    public DeviceResponse updateDevice(Device device) {
        DeviceResponse deviceResponse = new DeviceResponse();

        List<Device> deviceData = readJsonData();
        deviceData.removeIf(dev -> dev.getSignature().equals(device.getSignature()));
        deviceData.add(device);

        boolean status = writeJsonData(deviceData);

        if(status) {
            deviceResponse.setStatus(Constants.SUCCESS);
            deviceResponse.setData(Constants.DEVICE_DETAILS_UPDATED_SUCCESSFULLY);
        }
        return deviceResponse;
    }

    @Override
    public DeviceResponse deleteDevice(String signature) {
        DeviceResponse deviceResponse = new DeviceResponse();

        List<Device> deviceData = readJsonData();
        deviceData.removeIf(dev -> dev.getSignature().equals(signature));

        boolean status = writeJsonData(deviceData);

        if(status) {
            deviceResponse.setStatus(Constants.SUCCESS);
            deviceResponse.setData(Constants.DEVICE_DELETED_SUCCESSFULLY);
        }
        return deviceResponse;
    }
}
