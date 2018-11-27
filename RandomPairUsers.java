package Yelp;
/*给一堆list of users, 让你randomly pair任意的两个user*/

import java.util.Random;

public class RandomPairUsers {

    public static void getRandomPair(int[] users){
        Random r = new Random();
        int n = users.length;
        int idx1 = r.nextInt(n); //[0,n)
        int idx2 = r.nextInt(n);
        while(idx2 == idx1){
            idx2 = r.nextInt(n);
        }
        System.out.println(users[idx1] + "," + users[idx2]);
    }

    public static void main(String[] args){
        int[] nums = {10, 4, 2, 6, 13};
        getRandomPair(nums);
    }
}
