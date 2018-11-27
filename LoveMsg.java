package Yelp;

import java.util.*;
/**
 * 给个List of Love, Love这个class里有receiver，message 和 sender三个信息，返回降序排列收到最多message的receiver的list。我用Hashmap 和 PriorityQueue，面试官问为啥不直接在答案的list里面排序。
 * Followup是如果有人作弊，两个人之间发很多message咋办，加了set记录sender。面试小哥叫Will。
 *
 * 题目是有一个love class有三个element sender receiver message，给一个list的love class，
 * 返回receive的message最多的top K个receiver。
 * //hashmap + priority queue 各种pq复杂度
 //楼主是先hashmap，再转list， sort然后输出接受message 前三的sender。
 follow up就是优化code
 follow up 1，时间复杂度从nlogn 到nlogk；follow up 2，跟题目给的数据结构有关，我记得就加个set，很简单。

 2. meeting room看一遍*/

class Love{
    String sender;
    String receiver;
    String message;
}

public class LoveMsg {

    //nlogk
    public List<String> getMostMsgReceiver(List<Love> loveList, int k){
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Set<String> senderSet = new HashSet<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, (e1, e2) -> (e1.getValue() - e2.getValue()));

        for(Love love : loveList){
            map.put(love.receiver, map.getOrDefault(love.receiver, 0) + 1);
        }
        //follow: +set
        for(Love love : loveList){
            if (map2.containsKey(love.receiver)) {
                if(!senderSet.contains(love.sender)){
                    map2.put(love.receiver, map.get(love.receiver) + 1);
                }
            }else{
                map2.put(love.receiver, 0);
            }
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            pq.offer(entry);
            if(pq.size() > k){
                pq.poll();
            }
        }
        for(Map.Entry<String, Integer> entry : pq){
            res.add(entry.getKey());
        }
        return res;
    }
}
