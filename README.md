# JavaJSFModeloLogin


Este projeto tem por objectivo a configuração do ambiente para utilizar o JSF e criar uma tela de Login do sistema. 

- Mysql - Banco de Dados 
- Lombok - Para geração de Getter e Setter automaticos 
- JDBC Puro 


Pacote **com.jsf.javajsfmodelologin.bd** contem a classe **ConectarBD** de conexão no banco de dados 

```
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


```

O pacote **com.jsf.javajsfmodelologin.bean** ira conter todos os beans que se relacionam com as view . No exemplo abaixo a classe  **LoginBean** 


```
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
```

O pacote **com.jsf.javajsfmodelologin.dao** conterá todos os DAO que farão as querys no banco de dados. 

```

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

```

O pacote **com.jsf.javajsfmodelologin.model** conterão os modelos que representarão as tabelas do banco de dados . 


```
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.javajsfmodelologin.model;

import lombok.Data;

/**
 *
 * @author lprates
 */
@Data
public class TB_Usuario {
    private int id ; 
    private String usuario;
    private String senha;
    
    public TB_Usuario(){
        this.id =0 ; 
        this.usuario="" ; 
        this.senha="";
    }
    
    
}



```
