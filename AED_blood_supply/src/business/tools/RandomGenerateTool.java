package business.tools;

import business.blood.BloodType;
import java.util.Random;

/**
 * Created by Lysergids on 2016/10/19 0019.
 */
public class RandomGenerateTool {


    //use for generate name
    public String generateString(int length) {
        Random rng = new Random();
        String characters = "qwertyuiopasdfghjklzxcvbnm";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        text[0] = (char)(text[0]-32);
        return new String(text);
    }

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    public String randBloodType(){
        Random rand = new Random();
        int i = rand.nextInt(10000);
        if (i < 9900){
            Random rands = new Random();
            int j = rand.nextInt(4);
            switch (j) {
                case 0:
                    return BloodType.TYPEA.getValue();
                case 1:
                    return BloodType.TYPEB.getValue();
                case 2:
                    return BloodType.TYPEO.getValue();
                default:
                    return BloodType.TYPEAB.getValue();
            }
        }else{
            int j = randInt(4,8);
            switch (j) {
                case 4:
                    return BloodType.TYPERHA.getValue();
                case 5:
                    return BloodType.TYPERHB.getValue();
                case 6:
                    return BloodType.TYPERHO.getValue();
                default:
                    return BloodType.TYPERHAB.getValue();
            }
        }
    }
    public boolean randHemo(){
        Random rand = new Random();
        int i = rand.nextInt(10000);
        if (i < 9000){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean randill(){
        Random rand = new Random();
        int i = rand.nextInt(100);
        if (i < 95){
            return true;
        }else{
            return false;
        }
    }

    public double randDouble(double min, double max) {
        Random rand = new Random();
        double randomDoubleRate = rand.nextDouble();
        double randomDouble = randomDoubleRate*max + (1 - randomDoubleRate)*min;
        return randomDouble;
    }


    public boolean randBool(){
        Random rand = new Random();
        boolean randBool = rand.nextBoolean();
        return randBool;
    }

    public boolean randSmoker(){
        Random rand = new Random();
        int i = rand.nextInt(10);
        if (i < 7)
            return false;
        else
            return true;
    }

    public boolean randDiabetes(){
        Random rand = new Random();
        int i = rand.nextInt(100);
        if (i < 90)
            return false;
        else
            return true;
    }

}
