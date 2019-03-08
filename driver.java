import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driver{
    public static void main(String[]args){
      try{
        String test1 = "4 6 22 2";
        System.out.println(test1);
        System.out.println("Testing int extraction: ");
        System.out.println(USACO.bronzeBreakStr(test1));
        System.out.println(USACO.bronze("testCases/makelake.1.in"));
      }
    catch(FileNotFoundException e){
      System.out.println("Invalid filename: ");
    }
  }
}
