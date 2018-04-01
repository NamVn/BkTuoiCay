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
public class Tree {
    int verticalPosition; //Lấy tung  độ vị trí là các số từ 1 - ...
    char horizonalPosition; //Lấy hoành  độ vị trí là các chữ cái A - Z
    int currentWater; //Lượng nước hiện tại
    int limitedWater; //Lượng nước tối đa cây có thể tải được

    public Tree() {
    }

    public Tree(char horizonalPosition,int verticalPosition,  int currentWater, int limitedWater) {
        this.verticalPosition = verticalPosition;
        this.horizonalPosition = horizonalPosition;
        this.currentWater = currentWater;
        this.limitedWater = limitedWater;
    }
    
    

    public int getLimitedWater() {
        return limitedWater;
    }

    public void setLimitedWater(int limitedWater) {
        this.limitedWater = limitedWater;
    }

    public int getVerticalPosition() {
        return verticalPosition; 
    }

    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    public char getHorizonalPosition() {
        return horizonalPosition;
    }
    
    public String getposition(){
        return "" + this.horizonalPosition + ":" + this.verticalPosition;
    }
    // Khi giao tiếp với các máy khác, vị trí được theo công thức A:5, B:7
    
    public String[] setposition(String position){
        return position.split(":");
    } //Lấy vị trí từ các máy khác dưới dạng String
    
    public void setHorizonalPosition(char horizonalPosition) {
        this.horizonalPosition = horizonalPosition;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public void setCurrentWater(int currentWater) {
        this.currentWater = currentWater;
    }
    
    public int getDemandWater(){
        return this.getLimitedWater() - this.getCurrentWater();
    }// Tính lượng nước cần bổ sung
    
    public int getLostWater(){
        if (this.limitedWater==2) {
            return 2;
        }
        else return 1;
        // Về hao hụt thì mình fix cứng luôn
        // Nếu lượng nước tối đa là 2 thì một ngày sẽ hao hụt 2 đơn vị nước (1 ngày tưới 1 lần) cây cỏ
        // Nếu lượng nước tối đa là 4 thì một ngày sẽ hao hụt 1 đơn vị nước (4 ngày tưới 1 lần) cây gỗ
    }
// Tính lượng nước hao hụt hàng ngày
    
}
