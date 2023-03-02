package com.bca.simpleapi.services;

import org.springframework.stereotype.Service;

import com.bca.simpleapi.entities.Device;
import com.bca.simpleapi.entities.DeviceResponse;

@Service
public interface SimpleapiService {

    DeviceResponse addDevice(Device device);

    DeviceResponse fetchAllDevice();

    DeviceResponse fetchDeviceDetails(String signature);

    DeviceResponse updateDevice(Device device);

    DeviceResponse deleteDevice(String signature);
}
