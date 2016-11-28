package utils;

import java.util.Scanner;
import exeptions.EmptyLineExeption;

/**
 *
 * @author Dmitry
 */
public class StringWrite {

    public String inputString(){
        try{
            String str = new Scanner(System.in).nextLine();
            if ("".equals(str))
                throw new EmptyLineExeption();
            return str;
        }catch(EmptyLineExeption ele){
            System.out.println("Incorrect string");
        }
        return null;
    }
    
}
