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
    System.out.println(brLines);
    ArrayList<Integer> firstLine = bronzeBreakStr(brLines.get(0));
    int[][] map = new int[firstLine.get(0)][firstLine.get(1)];
    for (int r = 1; r < brLines.size() - firstLine.get(3); r++){ //create the map by getting the row number and just add each int from brLines to the map
      ArrayList<Integer> currentLine = bronzeBreakStr(brLines.get(r));
      System.out.println(currentLine);
      for (int c = 0; c < currentLine.size(); c++){
        map[r-1][c] = currentLine.get(c);
      }
    }
    //testing purpose
    for (int row = 0; row < map.length; row++){
      for (int col = 0; col < map[row].length; col++){
        System.out.print(map[row][col] + " ");
      }
      System.out.println();
    }
    System.out.println();

    //start trimming the land:
    //check every number of command/changes
    //from the end of the map info to the end of the
    for (int ch = map.length+1; ch < brLines.size(); ch++){
      ArrayList<Integer> thisLine = bronzeBreakStr(brLines.get(ch));
      System.out.println("ThisLine testing : " + thisLine);
      map = bronzeH(map, thisLine.get(0), thisLine.get(1), thisLine.get(2));
      for (int row = 0; row < map.length; row++){
        for (int col = 0; col < map[row].length; col++){
          System.out.print(map[row][col] + " ");
        }
        System.out.println();
      }
    }
    for (int row = 0; row < map.length; row++){
      for (int col = 0; col < map[row].length; col++){
        System.out.print(map[row][col] + " ");
      }
      System.out.println();
    }
    return 1;
  }

  public static int[][] bronzeH(int[][] land, int r, int c, int size){
//help to shrink/trim the land
    //find the largest land size first
    ArrayList<Integer> height = new ArrayList<Integer>();
    int[] moveR = {-1,-1,-1, 0,0,0, 1,1,1};
    int[] moveC = {-1, 0, 1,-1,0,1,-1,0,1};
    for (int i = 0; i<moveR.length; i++){ //collects the height of the 3x3 grid
      if ((r+moveR[i] >= 0 && r+moveR[i] < land.length) && (c+moveC[i] >= 0 && c+moveC[i] < land[0].length)){
        height.add(land[r+moveR[i]][c+moveC[i]]);
      }
    }
    Collections.sort(height); //sort the height
    System.out.println("Height : " + height);
    int after = 0;
    for (int z = 0; z<moveR.length; z++){ //modify the largest height
      if (land[r+moveR[z]][r+moveC[z]] == height.get(height.size()-1)){
        land[r+moveR[z]][r+moveC[z]] -= size;
        after = land[r+moveR[z]][r+moveC[z]];
      }
    }

    for (int z = 0; z<moveR.length; z++){ //modify the other heights to be the same as the largest after trimming
      if (land[r+moveR[z]][r+moveC[z]] >= after){
        land[r+moveR[z]][r+moveC[z]] = after;
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
