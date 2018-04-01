/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hustwaterkeeper;

import java.util.ArrayList;

/**
 *
 * @author Welcome
 */
public class Sprayer {
    int verticalPosition; //Lấy tung  độ vị trí là các số từ 1 - ...
    char horizonalPosition; //Lấy hoành  độ vị trí là các chữ cái A - Z
    int currentWater; //Lượng nước hiện tại
    int limitedWater; //Lượng nước tối đa mà người tưới cây có thể mang được.
    ArrayList plannedTreeArrayList = new ArrayList(); //Danh sách vị trí các ô cây cần tưới, sau khi tưới xong sẽ bỏ một cây
    ArrayList completedTreeArrayList = new ArrayList(); //Danh sách các cây đã tưới

     public Sprayer() {
    }
        
    public Sprayer(char horizonalPosition, int verticalPosition,  int currentWater, int limitedWater) {
        this.verticalPosition = verticalPosition;
        this.horizonalPosition = horizonalPosition;
        this.currentWater = currentWater;
        this.limitedWater = limitedWater;
    }

    public ArrayList getPlannedTreeArrayList() {
        return plannedTreeArrayList;
    }

    public void setPlannedTreeArrayList(ArrayList plannedTreeArrayList) {
        this.plannedTreeArrayList = plannedTreeArrayList;
    }

    public ArrayList getCompletedTreeArrayList() {
        return completedTreeArrayList;
    }

    public void setCompletedTreeArrayList(ArrayList completedTreeArrayList) {
        this.completedTreeArrayList = completedTreeArrayList;
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
   
    // Lấy giá trị tất cả các điểm mà người tưới cây phải đi khi sau khi đã biết các cây cần tưới.
    public String getRoute(){
        String route = "";
        String beginpoints[];
        String endpoints[];
        for (int i = 0; i < plannedTreeArrayList.size()-1; i++) {
            beginpoints = this.setposition((String) plannedTreeArrayList.get(i));
            endpoints = this.setposition((String) plannedTreeArrayList.get(i+1));
            route += this.getRoute(beginpoints[0].charAt(0),Integer.valueOf(beginpoints[1]),endpoints[0].charAt(0),Integer.valueOf(endpoints[1]));
        }
        return route;
    }
    
    // Lấy giá trị tất cả các điểm mà người tưới cây phải đi khi sau khi đã biết biết điểm đầu và điểm cuối.
    public String getRoute(char horizonalPositionA, int verticalPositionA, char horizonalPositionB, int verticalPositionB){
        String route = "";
        int horizonaldistance =(int)horizonalPositionB - (int)horizonalPositionA;
        int verticaldistance = verticalPositionB - verticalPositionA;
        int verticalvertor = (verticaldistance>=0)?1:-1;
        int horizonalvertor = (horizonaldistance>=0)?1:-1;
//        System.out.println(horizonalvertor);
//        System.out.println(verticalvertor);
        int verticalstep = verticalPositionA;
        char horizonalstep = horizonalPositionA;
        if (Math.abs(verticaldistance)<Math.abs(horizonaldistance)) {
            for (int i = 0; i < Math.abs(verticaldistance); i++) {
                horizonalstep = (char) (horizonalstep + horizonalvertor);
                verticalstep = verticalstep + verticalvertor;
                route += " " + horizonalstep + ":" + verticalstep;
            }
            for (int i = 0; i < Math.abs(horizonaldistance)-Math.abs(verticaldistance); i++) {
                horizonalstep = (char) (horizonalstep + horizonalvertor);
                route += " " + horizonalstep + ":" + verticalstep;
            }
        }
        else if(Math.abs(verticaldistance)>Math.abs(horizonaldistance)) {
            for (int i = 0; i < Math.abs(horizonaldistance); i++) {
                horizonalstep = (char) (horizonalstep + horizonalvertor);
                verticalstep = verticalstep + verticalvertor;
                route += " " + horizonalstep + ":" + verticalstep;
            }
            for (int i = 0; i < Math.abs(verticaldistance)-Math.abs(horizonaldistance); i++) {
                verticalstep =  verticalstep + verticalvertor;
                route += " " + horizonalstep + ":" + verticalstep;
            }
        }
        else{
                for (int i = 0; i < Math.abs(horizonaldistance); i++) {
                
                    horizonalstep = (char) (horizonalstep + horizonalvertor);
                    verticalstep = verticalstep + verticalvertor;
                    route += " " + horizonalstep + ":" + verticalstep;
            }
                   }
        return route;
    }

    public Sprayer(int verticalPosition, char horizonalPosition, int currentWater, int limitedWater) {
        this.verticalPosition = verticalPosition;
        this.horizonalPosition = horizonalPosition;
        this.currentWater = currentWater;
        this.limitedWater = limitedWater;
    }
    public static void main(String[] args) {
        Sprayer sprayer = new Sprayer();
        System.out.println(sprayer.getRoute('C', 2, 'B', 3));
    }
}
