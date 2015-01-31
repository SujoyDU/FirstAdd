/**
 * Created by Sujoy on 1/29/2015.
 */
public class TestClass {
    public static void main(String args[]) {
        System.out.println("Hello Github!!!");
        int testVar = 3;
        for(int i=0 ; i<3; i++) {
            testVar++;
        }
        System.out.println(testVar);
        boolean doesItWork=false;
        if(testVar==6){
            doesItWork = true;
        }
        if(doesItWork) System.out.println("git integration successfull!!");

    }
}
