import java.util.*;
import java.io.*;
public class USACO{

  private int[][] bronzeInfo;
  private ArrayList<String> brLines;

  //bronze: take the file and read into it.
  //1. has info on the size of land, water size, and number of trimming
  //2. 6x6 ft is size of area
  public static int bronze(String filename) throws FileNotFoundException{
    ArrayList<String> brLines = new ArrayList<String>();
    File file = new File(filename);
    Scanner info = new Scanner(file);
    while(info.hasNextLine()){ //store info of every line
      brLines.add(info.nextLine());
    }
    //System.out.println(brLines);
    ArrayList<Integer> firstLine = bronzeBreakStr(brLines.get(0));//get the first line with the info of the map size, repeatation, and elevation
    int[][] map = new int[firstLine.get(0)][firstLine.get(1)]; //create map with the size from line 1
    for (int r = 1; r < brLines.size() - firstLine.get(3); r++){ //create the map by getting the row number and just add each int from brLines to the map
      ArrayList<Integer> currentLine = bronzeBreakStr(brLines.get(r));
      //System.out.println(currentLine);
      for (int c = 0; c < currentLine.size(); c++){
        map[r-1][c] = currentLine.get(c);
      }
    }
    //testing purpose
    /*for (int row = 0; row < map.length; row++){
      for (int col = 0; col < map[row].length; col++){
        System.out.print(map[row][col] + " ");
      }
      System.out.println();
    }
    System.out.println();*/

    //start trimming the land:
    //check every number of command/changes
    //from the end of the map info to the end of the
    //System.out.println("map.length : " + map.length);
    for (int ch = map.length+1; ch < brLines.size(); ch++){
      ArrayList<Integer> thisLine = bronzeBreakStr(brLines.get(ch));
      //System.out.println("ThisLine testing : " + thisLine);
      map = bronzeH(map, thisLine.get(0), thisLine.get(1), thisLine.get(2));
    }
    /*for (int row = 0; row < map.length; row++){
      for (int col = 0; col < map[row].length; col++){
        System.out.print(map[row][col] + " ");
      }
      System.out.println();
    }*/
    int lakeSize = 0;
    //compare the water level with the map
    //find area with less than water level and then use elevation number to subtract the lower land size to find lake size
    for (int row = 0; row < map.length; row++){
      for (int col = 0; col < map[row].length; col++){
        if (map[row][col] < firstLine.get(2)){
          map[row][col] = firstLine.get(2) - map[row][col];
          lakeSize += map[row][col];
        }
        else{
          map[row][col] = -1;
        }
      }
    }
    /*System.out.println("lakeSize: " + lakeSize); //print map
    for (int row = 0; row < map.length; row++){
      for (int col = 0; col < map[row].length; col++){
        System.out.print(map[row][col] + " ");
      }
      System.out.println();
    }*/
    return lakeSize * 72 * 72;//for volume, multiple the height by the width and length(6x6 ft/ 72x72 inch)
  }

  public static int[][] bronzeH(int[][] land, int r, int c, int size){
//help to shrink/trim the land
    //find the largest land size first
    //System.out.println("r : " + r + "       c : " + c + "        size : " + size);
    ArrayList<Integer> height = new ArrayList<Integer>();
    int[] moveR = {-1,-1,-1, 0,0,0, 1,1,1};
    int[] moveC = {-1, 0, 1,-1,0,1,-1,0,1};
    for (int i = 0; i<moveR.length; i++){ //collects the height of the 3x3 grid
      if ((r+moveR[i] >= 0 && r+moveR[i] < land.length) && (c+moveC[i] >= 0 && c+moveC[i] < land[0].length)){
        height.add(land[r+moveR[i]][c+moveC[i]]);
        //System.out.println("Coordinates: " + r+moveR[i] + ", " + c+moveC[i]);
      }
    }
  //  System.out.println("height is: " + height);
    Collections.sort(height); //sort the height
    //System.out.println("heightA is: " + height);
    int after = 0;
    for (int z = 0; z<moveR.length; z++){ //modify the largest height
      if (land[r+moveR[z]][c+moveC[z]] == height.get(height.size()-1)){
        //System.out.println("land to trim: " + land[r+moveR[z]][c+moveC[z]]);
        land[r+moveR[z]][c+moveC[z]] =  land[r+moveR[z]][c+moveC[z]] - size;
        //System.out.println("Trimming : " + land[r+moveR[z]][r+moveC[z]]);
        after = land[r+moveR[z]][c+moveC[z]];
      }
    }
    //System.out.println("After : " + after);

    for (int j = 0; j<moveR.length; j++){ //modify the other heights to be the same as the largest after trimming
      if (land[r+moveR[j]][c+moveC[j]] >= after){
        land[r+moveR[j]][c+moveC[j]] = after;
      }
    }
    return land;
  }

  public static ArrayList<Integer> bronzeBreakStr(String info){ //given a string, return an array of int in it
    int intcount = 0;
    for (int i = 0; i < info.length(); i++){
      if (info.charAt(i) == ' '){
        intcount++;
      }
    }
    ArrayList<Integer> ans = new ArrayList<Integer>();
    int previousSpace = 0;
    for (int i = 0; i < info.length(); i++){
      if (info.charAt(i) == ' '){
        if (previousSpace == 0){
          ans.add(Integer.parseInt(info.substring(previousSpace, i)));
          previousSpace = i;
        }
        else{
          ans.add(Integer.parseInt(info.substring(previousSpace+1, i)));
          previousSpace = i;
        }
      }
    }
    ans.add(Integer.parseInt(info.substring(previousSpace+1,info.length())));
    return ans;
  }

  /*public static int silver(String filename){
//how many ways in 6 steps/seconds can reach final spot
//can't return to previous spots
//planning: create a second board to show possible location and place (-) on previous spot
    //try catch Scanner
    return 1;
  }*/
}
