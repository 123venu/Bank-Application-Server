
package com.bank.service;

import javax.xml.ws.WebFault;

@WebFault
public class UsernamePasswordException extends Exception{

    public UsernamePasswordException() {
        super("Incorrect username or password");
    }
    
}
