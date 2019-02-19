import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 *
 * Description - the puzzle is about computing constant velocity of the car, to make it go through all crossroad lights without changing velocity or stopping, and no committing speeding.
 * The data given through the Scanner in is: maxVelocity allowed on the road, lights count, and for each lights distance to them (from the start) and duration of lights change (same for red and green)
 **/
class TrafficLightsPuzzle {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // km/h
        BigDecimal maxVel = new BigDecimal(in.nextInt());
        ///System.out.println(speed);
        int lightCount = in.nextInt();

        BigDecimal[] distances = new BigDecimal[lightCount];
        BigDecimal[] durations = new BigDecimal[lightCount];

        for (int i = 0; i < lightCount; i++) {
            distances[i] = new BigDecimal(in.nextInt());
            durations[i] = new BigDecimal(in.nextInt());
        }

        for (int i = 0; i < lightCount; i++) {
            // meters to km
            BigDecimal distance = distances[i];
            ///System.out.println(distance);
            // seconds to hours
            BigDecimal duration = durations[i];
            ///System.out.println(duration);
            //m and s to ms (need exactly or more, that's why ceiling
            BigDecimal velNeededToMakeItMS = distance.divide(duration, 10, RoundingMode.CEILING);
            //ms to kmh
            BigDecimal velNeededToMakeItKmH = MSToKmH(velNeededToMakeItMS);



            //check if max vel is ok
            if(maxVel.compareTo(velNeededToMakeItKmH) > 0){
                continue;
            }



            //divide to integral vel needed to make it and actual vel
            BigDecimal numbersOfCyclesBeforeReachCrossAtVel = velNeededToMakeItKmH.divideToIntegralValue(maxVel);
            //check if odd (divide by 2, get remainder, if not 0 number is odd)
            if(numbersOfCyclesBeforeReachCrossAtVel.divideAndRemainder(new BigDecimal(2))[1].compareTo(new BigDecimal(0)) != 0){
                //divide vel needed to make it by number of cycles plus one (this makes next cycle than the odd one (odd == red light) )
                maxVel = velNeededToMakeItKmH.divide(numbersOfCyclesBeforeReachCrossAtVel.add(new BigDecimal(1)), 0, RoundingMode.FLOOR);
                //check all lights once again...
                i = -1;
            }
        }
        maxVel = maxVel.setScale(0, RoundingMode.FLOOR);
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(maxVel);
    }
    private static BigDecimal kmHToMS(BigDecimal kmH){
        return kmH.divide(new BigDecimal(3.6),  10, RoundingMode.CEILING);
    }
    private static BigDecimal MSToKmH(BigDecimal mS){
        return mS.multiply(new BigDecimal(3.6)).setScale(10, RoundingMode.FLOOR);
    }


}