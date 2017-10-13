/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.javajsfmodelologin.dao;

import com.jsf.javajsfmodelologin.bd.ConectarBD;
import com.jsf.javajsfmodelologin.model.TB_Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author lprates
 */
public class TB_UsuarioDAO {
    
    
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TB_UsuarioDAO.class);    
    
    
    
    final static String SELECT = " SELECT " +
                                "    id, " +
                                "    usuario, " +
                                "    senha " +
                                " FROM tb_usuario "; 
    

    public TB_Usuario buscaUsuario(String usuario, String senha){
        
        
        TB_Usuario tbUsuario = new TB_Usuario();
        Connection con = null ; 
        PreparedStatement ps = null ; 
        ResultSet rs = null ; 
        
        try {
            con = ConectarBD.conectarBD() ;
            
            String WHERE = " WHERE  usuario = ? AND senha = ?  " ;

            String SQL = SELECT + WHERE ; 
            
            logger.debug("SQL : [{}]" , SQL );

            
            ps =  con.prepareStatement(SQL ) ;
            ps.setString(1,usuario );
            ps.setString(2,senha );
            
            rs = ps.executeQuery() ; 
            
            while ( rs.next() ){
                tbUsuario = preecheUsuario(rs);
                break;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TB_UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                logger.error("Erro: {}" , ex);
            }
        }
        
        
        return tbUsuario ; 
        
    }
    
    public TB_Usuario preecheUsuario(ResultSet rs){
        
        TB_Usuario tbUsuario = new TB_Usuario();
        
        try {
            tbUsuario.setId(rs.getInt("id"));
            tbUsuario.setUsuario(rs.getString("usuario"));
            tbUsuario.setSenha(rs.getString("senha"));
        } catch (SQLException ex) {
                logger.error("Erro: {}" , ex);
        }
        return tbUsuario ;
    }
    
    
}
