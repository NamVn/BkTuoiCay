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
public class ServiceAssigner {

    public ServiceAssigner() {
        // Bài này mô phỏng API sẽ kiểm tra số yêu cầu chờ đợi với mỗi máy chủ, nếu máy nào ít yêu cầu hơn thì sẽ đưa yêu cầu cần xử lý vào máy đó.
        Request[] requests =  new Request[5];
        
        // Thiết lập yêu cầu
        for (int i = 0; i < requests.length; i++) {
            requests[i] =  new Request();
            requests[i].clientid = ""+i;
            requests[i].data = ""+(i*10);
        }
        requests[0].task = "Copy Data";
        requests[1].task = "Copy Data";
        requests[2].task = "Reset to 0";
        
        // Thiết thuộc tính máy chủ máy thợ
        servers[0] =  new Server();
        servers[0].addRequest(requests[0]);
        servers[1] =  new Server();
        servers[1].addRequest(requests[1]);
        servers[1].addRequest(requests[2]);
        this.display();
        System.out.println("----------");
        
        // API nhận được yêu cầu từ máy Client
        requests[3].task = "Copy Data";
        this.assignRequest(requests[3]);
        this.display();
        System.out.println("----------");
        
        // Máy chủ số 2 xử lý dữ liệu.
        System.out.println("Process from server 1: "+servers[1].process().toString());
        this.display();
        System.out.println("----------");
        
        //API nhận được yêu cầu từ máy Client
        requests[4].task = "Reset to 0";
        this.assignRequest(requests[4]);
        this.display();
        System.out.println("----------");
    }
    
    Server[] servers =  new Server[2];
    
    
    // Đây chính là thuật toán nhận dạng
    public void assignRequest(Request newrequest){
        if (servers[0].waitingQueue.size()<servers[1].waitingQueue.size()) {
            servers[0].addRequest(newrequest);
        }
        else{
            servers[1].addRequest(newrequest);
        }
    }
    
    public void display(){
        for (int i = 0; i < servers.length; i++) {
            System.out.println("Server " + (i+1));
            servers[i].display();
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        ServiceAssigner server = new ServiceAssigner();
    }
    
}
