package com.sk.jdp.common.sample.user.model;

import lombok.Data;

@Data
public class UserSearch {
	//검색 키워드
    private String searchText;
    //페이지 크기
    private int pageSize;
    //조회할 페이지 index
    private int pageNum;
    
}
