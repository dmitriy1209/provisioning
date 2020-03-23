package com.voverc.provisioning.entity;

public enum DeviceModel {
    
    CONFERENCE ("provisioningConferenceService"),
    DESK ("provisioningDeskService");
    
    @lombok.Getter
    private final String beanName;
        
    private DeviceModel(final String beanName){
        this.beanName = beanName;
    }
    
}
