package com.bca.simpleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bca.simpleapi.entities.Device;
import com.bca.simpleapi.entities.DeviceResponse;
import com.bca.simpleapi.services.SimpleapiService;

@RestController
public class SimpleapiController {

    @Autowired
    SimpleapiService simpleapiService;

    @PostMapping("/device")
    public DeviceResponse addDevice(@RequestBody Device device) {
        return simpleapiService.addDevice(device);
    }

    @GetMapping("/device/all")
    public DeviceResponse fetchAllDevice() {
        return simpleapiService.fetchAllDevice();
    }

    @GetMapping("/device/{signature}")
    public DeviceResponse fetchDeviceDetails(@PathVariable String signature) {
        return simpleapiService.fetchDeviceDetails(signature);
    }

    @PutMapping("/device")
    public DeviceResponse updateDevice(@RequestBody Device device) {
        return simpleapiService.updateDevice(device);
    }

    @DeleteMapping("/device/{signature}")
    public DeviceResponse deleteDevice(@PathVariable String signature){
        return simpleapiService.deleteDevice(signature);
    }
}
