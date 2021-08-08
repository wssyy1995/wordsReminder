package com.wordreminder.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ListReq {


    @Min(1)
    private int pageNo;

    private int pageSize;


}
