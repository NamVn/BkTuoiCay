/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hustwaterkeeper;

/**
 *
 * @author Welcome
 */
public class Request {
    String task;
    String data;
    String clientid;

    // Yêu cầu được hiểu là các thông điệp gửi từ Client tới API để 
    @Override
    public String toString() {
        return "Request from Client " + clientid +" have the task = "+ task+" and variable = "+data;
    }
    
}
