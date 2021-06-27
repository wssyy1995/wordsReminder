package com.wordreminder.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class idReq {


    @Min(0)
    private int id;


}
