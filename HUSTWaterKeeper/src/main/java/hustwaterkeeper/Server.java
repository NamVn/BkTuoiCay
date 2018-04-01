/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hustwaterkeeper;

import java.util.*;

/**
 *
 * @author Welcome
 */

// Bài này mô phỏng hoạt động của một máy chủ.
public class Server {
    
    //Hàng đợi các yêu cầu gửi đến từ Client
    ArrayList<Request> waitingQueue = new ArrayList<Request>() {};

    //Đưa yêu cầu vào trong hàng đợi
    public void addRequest(Request newrequest){
        waitingQueue.add(newrequest);
    }
    
    //Lấy yêu cầu ra và trả về cho Client cũng theo mô thức yêu cầu
    public Request process(){
        Request returnRequest = new Request();
        returnRequest.clientid = waitingQueue.get(waitingQueue.size()-1).clientid;
        if (waitingQueue.get(waitingQueue.size()-1).task.contains("Copy Data")) {
            returnRequest.data =""+ waitingQueue.get(waitingQueue.size()-1).data;
        }
        else if(waitingQueue.get(waitingQueue.size()-1).task.contains("Reset to 0")){
            returnRequest.data = "0";
        }
        returnRequest.task = "Return";
        waitingQueue.remove(waitingQueue.size()-1);
        return returnRequest;
    }
    
    // Hiển thị hàng đợi yêu cầu.
    public void display(){
        for (int i = 0; i < waitingQueue.size(); i++) {
            System.out.println(waitingQueue.get(i).toString());
        }
    }
    
}
