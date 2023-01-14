package com.leehowon.task.address;

import com.leehowon.task.address.application.AddressHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.format.DateTimeFormatter.ofPattern;

@SpringBootApplication
public class Application {
    
    private static final DateTimeFormatter LOG_DATETIME_PATTERN = ofPattern( "HH:mm:ss.SSS" );
    
    public static final String RESET = "\033[0m";
    
    public static final String RED = "\033[0;31m";
    
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";
    
    
    public static void main( String[] args ) {
        AddressHandler handler = SpringApplication.run( Application.class, args )
                                                  .getBean( AddressHandler.class );
        
        start( handler );
    }
    
    private static void start( AddressHandler handler ){
        do {
            print( "고객의 도로명 주소를 입력하세요. ( 종료하시려면 'Q' 를 입력하세요. )" );
            System.out.print( "> " );
            
            String command = getCommand();
            
            if( isQuitSignal(command) ){
                System.out.println( "프로그램을 종료합니다..." );
                System.exit( 0 );
            }
            else if( "".equals(command) ){
                continue;
            }
            
            String address = handler.findAddress( command );
            
            if( address.isEmpty() ){
                error( "주소를 찾을 수 없습니다. 입력한 주소가 실제 주소가 맞습니까?" );
            }
            else {
                print( WHITE_BOLD_BRIGHT, "주소: %s".formatted(address) );
            }
        }
        while( true );
    }
    
    private static void print( String message ){
        print( RESET, message );
    }
    
    private static void error( String message ){
        print( RED, message );
    }
    
    private static void print( String color, String message ){
        System.out.printf( "%s[%s] %s%n%s", color, LocalTime.now().format(LOG_DATETIME_PATTERN), message, RESET );
    }
    
    private static String getCommand(){
        return new Scanner( System.in ).nextLine().trim();
    }
    
    private static boolean isQuitSignal( String command ){
        return "Q".equalsIgnoreCase( command );
    }
}