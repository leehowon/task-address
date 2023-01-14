package com.leehowon.task.address.infra.jpa;

import com.leehowon.task.address.domain.AddressRepository;
import com.leehowon.task.address.domain.QRoadAddress;
import com.leehowon.task.address.domain.RoadAddress;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;

@Repository
public class DefaultAddressRepository extends QuerydslRepositorySupport implements AddressRepository {
    
    private final QRoadAddress address = QRoadAddress.roadAddress;
    
    public DefaultAddressRepository() {
        super( RoadAddress.class );
    }
    
    @Override
    public Collection<String> findCities() {
        return from( address ).select( address.city ).distinct()
                              .fetch();
    }
    
    @Override
    public Collection<String> findCountiesByCity( String city ) {
        JPQLQuery< String > listQuery = from( address ).select( address.county ).distinct();
        
        equalCity( listQuery, city );
        
        return listQuery.fetch();
    }
    
    @Override
    public Collection<String> findRoadsByCityAndCounty( String city, String county ) {
        JPQLQuery< String > listQuery = from( address ).select( address.road ).distinct();
    
        equalCity( listQuery, city );
        equalCounty( listQuery, county );
        
        return listQuery.fetch();
    }
    
    private < T > void equalCity( JPQLQuery<T> query, String city ){
        if( city != null && !city.isEmpty() ){
            query.where( address.city.eq(city) );
        }
    }
    
    private < T > void equalCounty( JPQLQuery<T> query, String county ){
        if( county != null && !county.isEmpty() ){
            query.where( address.county.like("%" + county + "%") );
        }
    }
    
    @Override
    public void save( RoadAddress address ) {
        Objects.requireNonNull( getEntityManager() )
               .persist( address );
    }
}