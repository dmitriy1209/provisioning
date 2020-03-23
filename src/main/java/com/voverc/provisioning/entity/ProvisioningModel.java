package com.voverc.provisioning.entity;

@lombok.Data
public class ProvisioningModel {
    private String username;
    private String password;
    private String domain;
    private String port;
    private String codecs;
    private String timeout;
}
