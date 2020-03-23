package com.voverc.provisioning.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.entity.ProvisioningModel;
import java.io.IOException;
import org.springframework.stereotype.Service;

//this class processing desk conference model only
@lombok.extern.log4j.Log4j2
@Service("provisioningConferenceService")
public class ProvisioningConferenceServiceImpl extends ProvisioningServiceImpl{
    
    //maping data from applecation properties and database to 'ProvisioningModel' object and after that to string
    //mapping is carried out using 'fasterxml' (for Json) and a jMapper (for copying data from one object to another)
    @Override
    public String getProvisioningFile(final Device device) throws IOException{        
            final ObjectMapper objectMapper = new ObjectMapper();
            final ProvisioningModel provisioningModel = device.getOverrideFragment()==null ? 
                    new ProvisioningModel() : objectMapper.readValue(device.getOverrideFragment(), ProvisioningModel.class);
            return objectMapper.writeValueAsString(propertiesLoader.map(device.map(provisioningModel)));
    }
    
}
