package com.voverc.provisioning.entity;

import com.googlecode.jmapper.annotations.JMap;
import java.io.Serializable;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Device implements Serializable, Mappedeble{
    
    private static final long serialVersionUID = 8668024361189632366L;
    
    @Id
    @Column(name = "mac_address")
    private String macAddress;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceModel model;
    
    @Column(name = "override_fragment")
    private String overrideFragment;
    
    @JMap("username")
    private String username;
    
    @JMap("password")
    private String password;
    
}
