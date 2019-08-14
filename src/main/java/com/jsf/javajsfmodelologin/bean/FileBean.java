/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.javajsfmodelologin.bean;


import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean
public class FileBean {

    private String texto ; 
    private String valor;
    private UploadedFile file;

    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public UploadedFile getFile() {
        return file;
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        
        System.out.println("Campo texto: "+ texto);
        
        Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	String action = params.get("action");        
        System.out.println("Valor: " + action );
        
        
        
        System.out.println("Arquivo carregado: " + event.getFile().getFileName());
        System.out.println( Thread.currentThread().getId() +"-"+ Thread.currentThread().getName());
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onChange(){
        System.out.println("Campo texto: "+ texto);
    }
    
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
