package com.marauder.util;

import java.math.BigDecimal;

public class BigDecimalUtil
{
    public static BigDecimal getFomatbBigDecimal(double num) {
        if (num > 100)
        {
            BigDecimal latitudeB = new BigDecimal(num).setScale(
                    12, BigDecimal.ROUND_DOWN);
            return latitudeB;
        }
        else if(num>10)
        {
            BigDecimal latitudeB = new BigDecimal(num).setScale(
                    11, BigDecimal.ROUND_DOWN);
            return latitudeB;
        }
        else { 
            BigDecimal latitudeB = new BigDecimal(num).setScale(
                    10, BigDecimal.ROUND_DOWN);
            return latitudeB;
        }
        
    }
}
