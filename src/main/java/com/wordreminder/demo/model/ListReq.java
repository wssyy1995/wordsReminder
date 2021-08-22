package com.wordreminder.demo.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ListReq {


    @Min(1)
    private int pageNo;

    private int pageSize;

    //1.data：默认按日期 2.letter：按字母（暂不做）
    private String sort;


}
