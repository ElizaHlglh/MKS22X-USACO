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
    while(info.hasNextLine()){
      brLines.add(info.nextLine());
    }
    return 1;
  }

  //public int[][] bronzeH(int[][] land, int r, int c, int size){
//help to shrink/trim the land
  //}

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
