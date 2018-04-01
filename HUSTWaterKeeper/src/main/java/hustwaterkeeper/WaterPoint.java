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
public class WaterPoint {
    int verticalPosition; //Lấy tung  độ vị trí là các số từ 1 - ...
    char horizonalPosition; //Lấy hoành  độ vị trí là các chữ cái A - Z
    
    WaterPoint(){
        
    }

    public WaterPoint(char horizonalPosition, int verticalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizonalPosition = horizonalPosition;
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
}
