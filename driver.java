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
        System.out.println(USACO.silver("testCases/ctravel.2.in"));
      }
    catch(FileNotFoundException e){
      System.out.println("Invalid filename: ");
    }
  }
}
/*import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//DRIVER BORROWED FROM GOOGLE GROUP, CREDITS TO: Bryant Stringham and Derek Lao
public class driver {
    public static void main(String[] args) {
        try {
            System.out.println("Bronze:");
            for (int i = 1; i < 6; i++) {
                File correct = new File("testCases/makelake." + i + ".out");
                Scanner yes = new Scanner(correct);
                int yourAnswer = USACO.bronze("testCases/makelake." + i + ".in");
                int correctAnswer = yes.nextInt();
                System.out.println(i + " Yours: " + yourAnswer + " Key: " + correctAnswer + " " + (yourAnswer == correctAnswer));
                yes.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        /*try {
            //System.out.println(USACO.silver("testCases/ctravel.2.in"));
            System.out.println("Silver:");
            for (int i = 1; i < 6; i++) {
                File checkPlus = new File("testCases/ctravel." + i + ".out");
                Scanner A = new Scanner(checkPlus);
                int yourAnswer = USACO.silver("testCases/makelake." + i + ".in");
                int correctAnswer = A.nextInt();
                System.out.println(i + " Yours: " + yourAnswer + " Key: " + correctAnswer + " " + (yourAnswer == correctAnswer));
                A.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }*/
  //  }
//}*/
