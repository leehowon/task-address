package com.leehowon.task.address.domain;

import javax.persistence.*;

/**
 * 도로명주소정보
 */
@Table
@Entity
public class RoadAddress {
    /** 식별자 */
    @Id @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private Long id;
    /** 시,도 */
    private String city;
    /** 시,군,구 */
    private String county;
    /** 로,길 */
    private String road;
    
    protected RoadAddress(){}
    
    protected RoadAddress( Long id, String city, String county, String road ) {
        this.id = id;
        this.city = city;
        this.county = county;
        this.road = road;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getCounty() {
        return county;
    }
    
    public String getRoad() {
        return road;
    }
    
    public static RoadAddress of( String city, String county, String road ){
        return new RoadAddress( null, city, county, road );
    }
}