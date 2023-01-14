package com.leehowon.task.address.application;

import com.leehowon.task.address.domain.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 주소처리기
 */
@Component
public class AddressHandler {
    
    private static final Pattern PATTERN = Pattern.compile( "[^\\d가-힣]" ); //숫자 및 완성형 한글만..
    
    private final AddressRepository addressRepository;
    
    public AddressHandler( AddressRepository addressRepository ) {
        this.addressRepository = addressRepository;
    }
    
    /**
     * 도로명 주소를 찾기
     * @param query 입력 주소
     * @return 매칭된 도로명 주소
     */
    public String findAddress( String query ){
        String address = PATTERN.matcher( query )
                                .replaceAll( "" );
        String city = findCity( address );
        String county = findCounty( address, city );
        String road = findRoad( address, city, county );
        
        if( road.isEmpty() ){ //도로를 찾을 수 없을
            return "";
        }
        
        return String.join( " ", city, county, road )
                     .trim();
    }
    
    private String findCity( String address ){
        return addressRepository.findCities().stream()
                                .filter( address::contains )
                                .findFirst()
                                .orElse( "" );
    }
    
    private String findCounty( String address, String city ){
        return addressRepository.findCountiesByCity(city).stream()
                                .map( it -> parse(address, it) )
                                .filter( it -> !it.isEmpty() )
                                .max( Comparator.comparing(String::length) )
                                .orElse( "" );
    }
    
    private String parse( String address, String county ){
        List< String > temp = new ArrayList<>();
        
        for( String splitCounty : county.split(" ") ){ //시군구가 함께 있을 때
            if( address.contains(splitCounty) )
                temp.add( splitCounty );
        }
        
        return String.join( " ", temp );
    }
    
    private String findRoad( String address, String city, String county ){
        return addressRepository.findRoadsByCityAndCounty(city, county).stream()
                                .filter( address::contains )
                                .max( Comparator.comparing(String::length) )
                                .orElse( "" );
    }
}