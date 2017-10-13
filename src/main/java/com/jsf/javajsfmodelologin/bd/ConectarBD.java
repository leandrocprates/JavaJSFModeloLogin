/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.javajsfmodelologin.bd;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author lprates
 */
public class ConectarBD {
    
    static final String url = "jdbc:mysql://localhost:3306/jsfTeste"; 
    static final String usuario = "root"; 
    static final String senha = "root"; 
    

    /*
     * Funcao reponsavel por conectar no banco de dados 
     */
    public static Connection conectarBD(){
        
        Connection con = null ; 
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection( url ,usuario , senha);
        } catch (Exception ex) {
        }
        return con; 
    }
    
    
    
}
