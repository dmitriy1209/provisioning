package com.voverc.provisioning.service.impl;

import com.googlecode.jmapper.annotations.JMap;
import com.voverc.provisioning.service.PropertiesLoader;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "provisioning")
@lombok.Data
public class PropertiesLoaderImpl implements PropertiesLoader{
    
    @JMap("domain")
    private String domain;
    
    @JMap("port")
    private String port;
    
    @JMap("codecs")
    private String codecs;
    
}
