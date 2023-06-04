package com.deyvidsantos.shoppingclient.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    
    private Integer status;
    private String message;
    private Date timestamp;
}
