package com.spring.Glassline.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    public ResourceNotFoundException(String resourceName){
        super(String.format("%s Not Found",resourceName));
        this.resourceName = resourceName;
    }
}
