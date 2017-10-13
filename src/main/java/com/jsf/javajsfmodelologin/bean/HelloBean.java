/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.javajsfmodelologin.bean;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author lprates
 */
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

        private static final Logger logger = LogManager.getLogger(HelloBean.class);    
    
    
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
                logger.debug("Debug");
                logger.error("Error");
                logger.warn("Warn");
                logger.info("Info");
	}
}
