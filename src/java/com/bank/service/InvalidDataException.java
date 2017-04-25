
package com.bank.service;

import javax.xml.ws.WebFault;

@WebFault
public class InvalidDataException extends Exception {

    public InvalidDataException() {
        super("Invalid data");
    }
}
