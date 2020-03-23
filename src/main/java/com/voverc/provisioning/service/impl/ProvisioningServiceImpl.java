package com.voverc.provisioning.service.impl;

import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.repository.DeviceRepository;
import com.voverc.provisioning.service.PropertiesLoader;
import com.voverc.provisioning.service.ProvisioningService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

@lombok.extern.log4j.Log4j2
public abstract class ProvisioningServiceImpl implements ProvisioningService {
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    protected PropertiesLoader propertiesLoader;
    
    @Override
    public String getProvisioningFile(String macAddress) {
        return deviceRepository.findById(macAddress)
                .map(device -> {
                    try {
                        return getProvisioningFile(device);
                    } catch (IOException ex) {
                        log.error("Error processing data : " + ex);
                        return null;
                    }
                })
                .orElse(null);
    }
    
    //implementation of design pattern 'Template method'
    protected abstract String getProvisioningFile(Device device) throws IOException;
    
}
