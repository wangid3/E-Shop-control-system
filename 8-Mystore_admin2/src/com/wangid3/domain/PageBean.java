package com.wangid3.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class PageBean {
    //当前是哪一页
    private Integer currentPage;

    private Integer totalPage;

    private Integer totalCount;

    private List<Goods> goodsList=new ArrayList<>();

}
