package com.voverc.provisioning.service.impl;

import com.voverc.provisioning.entity.Device;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//This class will be by default injected into all variables of type 'ProvisioningService' (becouse of Primary annotaion)
//its possible to add a "Proxy" to method 'getProvisioningFile' 
@Service
@Primary
public class ProvisioningServiceFactory extends ProvisioningServiceImpl{
    
    @Autowired
    private ApplicationContext context;
    
    @Override
    protected String getProvisioningFile(Device device) throws IOException{
        return createService(device).getProvisioningFile(device);
    }
    
    //implementation of design pattern 'Factory method'. Creates one of impleventation of abstract class 'ProvisioningServiceImpl'
    private ProvisioningServiceImpl createService(Device device) {
        return context.getBean(device.getModel().getBeanName(), ProvisioningServiceImpl.class);
    }
    
}
