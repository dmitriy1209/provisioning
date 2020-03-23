package com.voverc.provisioning.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voverc.provisioning.entity.Device;
import com.voverc.provisioning.entity.ProvisioningModel;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

//this class processing desk device model only
@lombok.extern.log4j.Log4j2
@Service("provisioningDeskService")
public class ProvisioningDeskServiceImpl extends ProvisioningServiceImpl{
    
    //maping data from applecation properties and database to 'ProvisioningModel' object and after that to string
    //mapping is carried out using 'fasterxml' (for Json) and a jMapper (for copying data from one object to another)
    @Override
    protected String getProvisioningFile(final Device device) throws IOException {
        //,apping from application properties (propertiesLoader) and database (device) to dto (provisioningModel)
        final ProvisioningModel provisioningModel = propertiesLoader.map(device.map(new ProvisioningModel()));
        final Map<String, String> resultMap = new ObjectMapper().convertValue(provisioningModel, Map.class);
        return addPropertiesToMap(device.getOverrideFragment(), resultMap).entrySet().stream()
                .map(entry->String.valueOf(entry))
                .collect(Collectors.joining("\n"));
    }
    
    private Map<String, String> addPropertiesToMap(String overrideFragment, Map resultMap) throws IOException {
        if (overrideFragment!=null) {
            final Properties prop = new Properties();
            prop.load(new StringReader(overrideFragment));
            resultMap.putAll((Map)prop);
        }
        return resultMap;
    }
    
}
