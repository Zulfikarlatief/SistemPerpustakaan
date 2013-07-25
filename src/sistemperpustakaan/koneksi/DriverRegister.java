/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemperpustakaan.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zword
 */
public class DriverRegister {
    private static Connection koneksi;
    
    private static String user="root";
    private static String pass="root" ;
    private static String db="sistemperpustakkan" ;
    
    
    public static Connection getConnection(){
        if(koneksi==null){
            try{
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,user,pass);
            }catch(SQLException e){
                Logger.getLogger(DriverRegister.class.getName()).log(Level.SEVERE,null,e);
            }
            
        }
        return koneksi;
    }
}
