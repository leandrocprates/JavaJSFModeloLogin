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

