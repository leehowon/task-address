package com.leehowon.task.address.domain;

import java.util.Collection;

public interface AddressRepository {
    
    Collection< String > findCities();
    
    Collection< String > findCountiesByCity( String city );
    
    Collection< String > findRoadsByCityAndCounty( String city, String county );
    
    void save( RoadAddress address );
}