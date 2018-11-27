package Yelp;

import java.util.*;

public class BusinessChain {

    static class Business{
        String name;
        String location;
        String id;
        public Business(String name, String location, String id) {
            this.name = name;
            this.location = location;
            this.id = id;
        }
    }



    static class Chain{
        String name;
        int frequency;
        public Chain(String name, int fre){
           this.name = name;
           this.frequency = fre;
        }
    }
    // output chainname和出现次数
    public List<Chain> getMostFreqBus(String location, List<Business> businessList){
        List<Chain> chainList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(Business business : businessList){
            if(business.location.equals(location)){
                map.put(business.name, map.getOrDefault(business.name, new ArrayList<>()));
                if(!map.get(business.name).contains(business.id)){
                    map.get(business.name).add(business.id);
                }
            }
        }
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            Chain chain = new Chain(entry.getKey(), entry.getValue().size());
            chainList.add(chain);
        }
        Collections.sort(chainList, new Comparator<Chain>(){
            public int compare(Chain c1, Chain c2){
                if(c1.frequency == c2.frequency){
                    return c1.name.hashCode() - c2.name.hashCode();
                }else{
                    return c2.frequency - c1.frequency;
                }
            }
        });
        return chainList;

    }

    public static void main(String[] args){
        BusinessChain bc = new BusinessChain();
        BusinessChain.Business b1 = new Business("aa", "austin", "101");
        BusinessChain.Business b2 = new Business("wal", "austin", "102");
        BusinessChain.Business b3 = new Business("wal", "austin", "109");
        BusinessChain.Business b4 = new Business("aa", "austin", "101");
        BusinessChain.Business b6 = new Business("aa", "austin", "103");
        BusinessChain.Business b5 = new Business("qfc", "austin", "105");
        BusinessChain.Business b7 = new Business("qfc", "dallas", "111");
        List<Business> businessList = new ArrayList<>();
        businessList.add(b1);
        businessList.add(b2);
        businessList.add(b3);
        businessList.add(b4);
        businessList.add(b5);
        businessList.add(b6);
        businessList.add(b7);

        int size = bc.getMostFreqBus("austin", businessList).size();
        System.out.println(size);
        for(int i = 0;i < size; i++){
            System.out.print(bc.getMostFreqBus("austin", businessList).get(i).name + " ");
            System.out.print(bc.getMostFreqBus("austin", businessList).get(i).frequency);
            System.out.println();
        }



    }
}
