/*
 * test.java
 *
 * Created on May 5, 2008, 5:10 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gpsextraction_03;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author murtuza
 */
public class test {
    
    /** Creates a new instance of test */
    public test() {
    }
    
    public static void main(String[] args){
        FileInputStream fis;
        DataInputStream dis;
        BufferedInputStream b;
        byte[] x = new byte[4];
        try{
            fis = new FileInputStream("file0340.txt");
            dis = new DataInputStream(fis);
            b = new BufferedInputStream(dis,1024);
            int a;
            while((a=b.read(x))!=-1){
              System.out.println(x[0]);
              System.out.println(x[1]);
              System.out.println(x[2]);
              System.out.println(x[3]);
              
            }
            
        }catch(IOException e){
            
        }
    }
    
}
