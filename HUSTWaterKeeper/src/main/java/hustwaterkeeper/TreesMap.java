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
public class TreesMap {
    Object[][] object = new Object[8][5];
    public void setObject(){
        object[0][0] = new WaterPoint('A',1);
        object[0][1] = new Tree('B', 1, 2, 2);
        object[0][2] = new Tree('C', 1, 1, 2);
        object[0][3] = new Tree('D', 1, 1, 2);
        object[0][4] = new WaterPoint('E',1);
        
        object[1][0] = new Tree('A', 2, 1, 2);
        object[1][1] = new Tree('B', 2, 1, 2);
        object[1][2] = new Tree('C', 2, 1, 2);
        object[1][3] = new Tree('D', 2, 1, 2);
        object[1][4] = new Tree('E', 2, 1, 2);
        
        object[2][0] = new Tree('A',3, 1, 2);
        object[2][1] = new Tree('B',3, 1, 2);
        object[2][2] = new WaterPoint('C',3);
        object[2][3] = new Tree('D',3, 1, 2);
        object[2][4] = new Tree('E',3, 1, 2);
        
        object[3][0] = new Tree('A', 3, 1, 2);
        object[3][1] = new Tree('B', 3, 1, 2);
        object[3][2] = new Tree('C', 3, 1, 2);
        object[3][3] = new Tree('D', 3, 1, 2);
        object[3][4] = new Tree('E', 3, 1, 2);
        
        object[4][0] = new WaterPoint('A',5);
        object[4][1] = new Tree('B', 5, 1, 2);
        object[4][2] = new Tree('C', 5, 1, 2);
        object[4][3] = new Tree('D', 5, 1, 2);
        object[4][4] = new WaterPoint('E',5);
        
        object[5][0] = new Tree('A', 6, 1, 2);
        object[5][1] = new Tree('B', 6, 1, 2);
        object[5][2] = new Tree('C', 6, 1, 2);
        object[5][3] = new Tree('D', 6, 1, 2);
        object[5][4] = new Tree('E', 6, 1, 2);
        
        object[6][0] = new Tree('A', 7, 2, 4);
        object[6][1] = new Tree('B', 7, 2, 4);
        object[6][2] = new Tree('C', 7, 2, 4);
        object[6][3] = new Tree('D', 7, 2, 4);
        object[6][4] = new Tree('E', 7, 2, 4);
        
        object[7][0] = new WaterPoint('A',8);
        object[7][1] = new Tree('B', 8, 2, 4);
        object[7][2] = new Tree('C', 8, 2, 4);
        object[7][3] = new Tree('D', 8, 2, 4);
        object[7][4] = new WaterPoint('E',8);      
        
    }

    public int getDistance(char horizonalPositionA, int verticalPositionA, char horizonalPositionB, int verticalPositionB){
        int horizonaldistance =Math.abs(horizonalPositionA - (int)horizonalPositionB);
        int verticaldistance = Math.abs(verticalPositionA - verticalPositionB);
        if (verticaldistance < horizonaldistance) {
            return horizonaldistance;
        }
        else return verticaldistance;
    }
    public TreesMap() {
        this.setObject();
    }
    
    public static void main(String[] args) {
        TreesMap matrix =  new TreesMap();
        System.out.println(matrix.getDistance('A', 1, 'C', 2));
    }
}
