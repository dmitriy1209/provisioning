package com.voverc.provisioning.controller;

import com.voverc.provisioning.service.ProvisioningService;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@lombok.AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProvisioningController {
    
    private final ProvisioningService provisioningService;
    
    @ApiOperation("Get provisioning file by MAC address")
    @GetMapping("/provisioning/{mac_address}")
    public ResponseEntity<String> getTaskByProjectId(@PathVariable ("mac_address") String macAddress) {
        final String provisioningFile = provisioningService.getProvisioningFile(macAddress);
        return provisioningFile==null ? ResponseEntity.status(BAD_REQUEST).build() : ResponseEntity.status(OK).body(provisioningFile);
    }
    
}