/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.javajsfmodelologin.bean;



import com.jsf.javajsfmodelologin.dao.TB_UsuarioDAO;
import com.jsf.javajsfmodelologin.model.TB_Usuario;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
 
@ManagedBean
@SessionScoped
public class LoginBean {
     
    private static final Logger logger = LogManager.getLogger(LoginBean.class);    

    TB_UsuarioDAO usuarioDAO = new TB_UsuarioDAO();
    
    
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) throws IOException {
        
        logger.debug("Usuario {}" , username);
        logger.debug("Senha {}" , password);
         
        if(username != null && password != null ) {
            
            TB_Usuario tbUsuario = usuarioDAO.buscaUsuario(username, password) ; 
            
            if ( tbUsuario.getUsuario().equals(username) &&  tbUsuario.getSenha().equals(password) ){
                FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
            }else {
                logger.error("Usuario e Senha Invalidos");
            }
            
        } else {
            logger.error("Usuario e Senha Invalidos");
        }
       
    }
    
    /**
     * Usar com instrucao action 
     * 
     */
    public String login2() {
        return "welcome" ; 
    }
    
}