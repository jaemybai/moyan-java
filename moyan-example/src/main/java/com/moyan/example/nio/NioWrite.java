package com.moyan.example.nio;
import java.io.*;  
import java.nio.*;  
import java.nio.channels.*;  
  
public class NioWrite {  
    static private  byte message[] = { 83, 111, 109, 101, 32,  
        98, 121, 116, 101, 115, 46 };  
  
    static public void main( String args[] ) throws Exception {  
    	
    	String str = "hello world \n next test...";
    	message = str.getBytes();
        FileOutputStream fout = new FileOutputStream( "test.txt" );  
          
        FileChannel fc = fout.getChannel();  
          
        ByteBuffer buffer = ByteBuffer.allocate( 1024 );  
          
        for (int i=0; i<message.length; ++i) {  
            buffer.put( message[i] );  
        }  
          
        buffer.flip();  
          
        fc.write( buffer );  
          
        fout.close();  
    }  
}  