/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hustwaterkeeper;
import hustwaterkeeper.Tree;
import java.util.ArrayList;
/**
 *
 * @author Welcome
 */
public class SprayingController {
    
    Sprayer[] availableSprayers; // Những người tưới cây đã xác nhận làm việc, có lưu trữ tọa độ và lượng nước còn
    TreesMap matrixMap =  new TreesMap();
    ArrayList treePosition = new ArrayList();
    ArrayList waterPointPosition = new ArrayList();

    public SprayingController() {
        availableSprayers =  new Sprayer[5];
        availableSprayers[0] = new Sprayer('B', 8, 5, 5);
        availableSprayers[1] = new Sprayer('E', 4, 5, 5);
        availableSprayers[2] = new Sprayer('A', 2, 5, 5);
        availableSprayers[3] = new Sprayer('D', 1, 5, 5);
        availableSprayers[4] = new Sprayer('C', 6, 5, 5);
        this.scanTreesMap();
        
        System.out.println("Lập lịch tưới cây");
        this.planSchedule();
        for (int i = 0; i < this.availableSprayers.length; i++) {
            System.out.println("Người tưới cây thứ: "+i + " phải tưới những cây "+this.availableSprayers[i].plannedTreeArrayList.size()+" trees");
            for (int j = 0; j < this.availableSprayers[i].plannedTreeArrayList.size(); j++) {
                System.out.println(availableSprayers[i].plannedTreeArrayList.get(j));
            }
            System.out.println(availableSprayers[i].getRoute());
        }
        
        System.out.println("Giả sử những người tưới cây bị hết nước. Họ sẽ phải tìm nước");
        for (int i = 0; i < this.availableSprayers.length; i++) {
            String route = this.findWaterPoint(this.availableSprayers[i]);
            System.out.println(route);
        }
    }
    
    public void scanTreesMap(){
        /* Nhiệm vụ đoạn code này là lấy vi trí các điêm tiếp nước và các cây cần tưới */
                
        Tree temperaturetree;
        WaterPoint temperaturewaterpoint;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                if((""+matrixMap.object[i][j].getClass()).contains("hustwaterkeeper.Tree")){
                    temperaturetree = (Tree) matrixMap.object[i][j];
                        if (temperaturetree.currentWater<temperaturetree.limitedWater) {
                             treePosition.add(temperaturetree.getposition());
                    }
                }
                else{
                    temperaturewaterpoint = (WaterPoint) matrixMap.object[i][j];
                    waterPointPosition.add(temperaturewaterpoint.getposition());
                }
            }
        }
        
    }
    
    public String findWaterPoint(Sprayer emptysprayer){
        /* Nhiệm vụ đoạn code này là lấy vi trí các điêm tiếp nước và các cây cần tưới */
        String[] waterpoints;
        String route = "";
        int temperaturedistance = 0;
        int verticalnearestwaterpoint = 0;
        char horizonalnearestwaterpoint = 'A';
        int mindistance = matrixMap.object.length;
        
        for (int i = 0; i < waterPointPosition.size(); i++) {
            waterpoints = ((String) waterPointPosition.get(i)).split(":");
                    temperaturedistance = matrixMap.getDistance(emptysprayer.getHorizonalPosition(), emptysprayer.getVerticalPosition(),waterpoints[0].charAt(0), Integer.valueOf(waterpoints[1]));
                    if (mindistance > temperaturedistance) {
                        mindistance = temperaturedistance;
                        horizonalnearestwaterpoint = waterpoints[0].charAt(0);
                        verticalnearestwaterpoint = Integer.valueOf(waterpoints[1]);
                    }
        }
        System.out.println("Điểm lấy nước của họ là: "+horizonalnearestwaterpoint+":"+verticalnearestwaterpoint);
        System.out.println("Hành trình họ phải đi là: ");
        return emptysprayer.getRoute(emptysprayer.getHorizonalPosition(), emptysprayer.getVerticalPosition(), horizonalnearestwaterpoint, verticalnearestwaterpoint);
        
    }
    
    
    public void planSchedule(){
             
        /* Nhiệm vụ đoạn code này là lên lịch tưới cây */
        String treepoints[];
        int mindistance;
        int temperaturedistance = 0;
//        System.out.println(treePosition.size());
//        System.out.println(availableSprayers.length);
        int limitedtree = (treePosition.size()/availableSprayers.length) + 1;
        Sprayer sprayer = null;
        for (int i = 0; i < treePosition.size(); i++) {
            mindistance = matrixMap.object.length;
            for (int j = 0; j < availableSprayers.length; j++) {
                 if (availableSprayers[j].plannedTreeArrayList.size()<limitedtree) {
//                     System.out.println("Limited: "+ limitedtree);
//                      System.out.println("Sprayer "+j+": "+availableSprayers[j].plannedTreeArrayList.size());
                    treepoints = ((String) treePosition.get(i)).split(":");
                    temperaturedistance = matrixMap.getDistance(availableSprayers[j].getHorizonalPosition(), availableSprayers[j].getVerticalPosition(),treepoints[0].charAt(0), Integer.valueOf(treepoints[1]));
                    if (mindistance > temperaturedistance) {
                        mindistance = temperaturedistance;
                        sprayer = availableSprayers[j];
                    }
                     
                }
            }
            sprayer.plannedTreeArrayList.add((String) treePosition.get(i));
        }
    }

    
    
    
    
    public static void main(String[] args) {
        SprayingController sprayingplanner = new SprayingController();
        
    }
    
}
