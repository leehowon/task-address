package com.leehowon.task.address;

import com.leehowon.task.address.application.AddressHandler;
import com.leehowon.task.address.domain.AddressRepository;
import com.leehowon.task.address.domain.RoadAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class AddressHandlerTests {
    
    @Autowired
    private AddressHandler handler;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @BeforeEach
    void init(){
    
    }
    
    @Test
    @DisplayName( "주소테스트1" )
    void test1() {
        addressRepository.save( RoadAddress.of("경기도", "성남시 분당구", "백현로") );
        addressRepository.save( RoadAddress.of("경기도", "성남시 분당구", "백현로101번길") );
        addressRepository.save( RoadAddress.of("경기도", "성남시 분당구", "백현로144번길") );
        
        String address = "성남, 분당 백 현 로 265, 푸른마을 아파트로 보내주세요!!";
        String expectAddress = "백현로";
        
        assertEquals( expectAddress, handler.findAddress(address) );
    }
    
    @Test
    @DisplayName( "주소테스트2" )
    void test2() {
        addressRepository.save( RoadAddress.of("서울특별시", "마포구", "도화길") );
        addressRepository.save( RoadAddress.of("서울특별시", "마포구", "도화2길") );
        addressRepository.save( RoadAddress.of("서울특별시", "마포구", "도화2안길") );
        addressRepository.save( RoadAddress.of("서울특별시", "마포구", "도화4길") );
        
        String address = "마포구 도화-2길 코끼리분식";
        String expectAddress = "마포구 도화2길";
        
        assertEquals( expectAddress, handler.findAddress(address) );
    }
    
    @Test
    @DisplayName( "주소테스트3" )
    void test3() {
        addressRepository.save( RoadAddress.of("경상남도", "창녕군", "종로") );
        addressRepository.save( RoadAddress.of("대구광역시", "중구", "종로") );
        addressRepository.save( RoadAddress.of("서울특별시", "종로구", "종로") );
        addressRepository.save( RoadAddress.of("서울특별시", "종로구", "종로11길") );
        
        String address = "서울시 종로구 종로";
        String expectAddress = "종로구 종로";
        
        assertEquals( expectAddress, handler.findAddress(address) );
    }
    
    @Test
    @DisplayName( "주소테스트4" )
    void test4() {
        addressRepository.save( RoadAddress.of("경상남도", "창녕군", "종로") );
        addressRepository.save( RoadAddress.of("대구광역시", "중구", "종로") );
        addressRepository.save( RoadAddress.of("서울특별시", "종로구", "종로") );
        addressRepository.save( RoadAddress.of("서울특별시", "종로구", "종로11길") );
        
        String address = "종로";
        String expectAddress = "종로";
        
        assertEquals( expectAddress, handler.findAddress(address) );
    }
    
    @Test
    @DisplayName( "주소테스트5" )
    void test5() {
        addressRepository.save( RoadAddress.of("경기도", "성남시 분당구", "백현로") );
        addressRepository.save( RoadAddress.of("경기도", "성남시 분당구", "백현로101번길") );
        addressRepository.save( RoadAddress.of("경기도", "성남시 분당구", "백현로144번길") );
        addressRepository.save( RoadAddress.of("서울특별시", "마포구", "도화길") );
        addressRepository.save( RoadAddress.of("서울특별시", "마포구", "도화2길") );
        
        String address = "경기 마포구 종로";
        String expectAddress = "";
        
        assertEquals( expectAddress, handler.findAddress(address) );
    }
}