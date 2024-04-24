package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** 정적 필터링
 * JsonIgnoreProperties : 변수 이름 변경 시 여기서도 변경 필요
 * JsonIgnore : 변수 이름 변경 여부와 관계 없음
 * 따라서 JsonIgnoreProperties 보단 JsonIgnore 추천 */
//@JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;
//    @JsonIgnore // response json에서 ignore됨
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
            "field1='" + field1 + '\'' +
            ", field2='" + field2 + '\'' +
            ", field3='" + field3 + '\'' +
            '}';
    }
}
