package Yelp;
/**题目是设计一个餐厅排队叫号的API。有一些不同size的Party，每个Party有已经等待的时间time。
 * 找到等待最久的能fit空出来的table的party。(等待时间优先，能坐下就行)
 Party class给好了，其他的都自己设计。
 开始说priorityqueue，面试官问了各个操作的复杂度，然后说复杂度有点高，让换一个数据结构。
 最后还是在提醒下想到了deque，有问了相应的复杂度。
 在改进PQ的时候，面试官说想象一下真实的业务场景，就是每个party的waiting time已经按降序排好了，并且要可以支持后续的取号操作。
 我当时的想法是每次取号addLast，叫号pollFirst，如果size不满足就存起来，找到之后再把之前存的addFirst进去。
 那比如table size = 3, queue 里按等待时间从小到大的 group size 分别是 5, 6, 3, 2, 4，你的意思是先从头部取出5, 6，
 直到遇到3，返回，然后再把5，6塞回去，变成5,6,2,4？那这样的worse case叫号是O(n)
 */
import java.util.*;

class Party{
    int groupSize;
    int waittime;
    boolean isServed;
}

class Table{
    int tableSize;
    boolean isAvailable;
}

public class TableFit {
    List<Table> tables = new ArrayList<>();
    //1. pq + queue
    PriorityQueue<Party> waitQueue = new PriorityQueue<>((p1, p2) -> (p2.waittime - p1.waittime));
    Queue<Party> temp = new LinkedList<>();
    //2. deque
    Deque<Party> deque = new LinkedList<>();
    Stack<Party> tempStack = new Stack<>();

    public void serveNewComer(Party p){
        waitQueue.offer(p);
        deque.addLast(p); // add to last
    }
    public void fitTable(){
        if(waitQueue.isEmpty()){
            return;
        }
        for(Table table : tables){
            if(table.isAvailable){ //考虑的都是一张桌子空出来的情况。未考虑继续遍历桌子。题目应该就是assume现在有一张空桌子。
                //1. pq：O(nlogn + n + logn + nlogn) ~~ O(nlogn)
                while(!waitQueue.isEmpty() && waitQueue.peek().groupSize > table.tableSize){
                    Party head = waitQueue.poll();
                    temp.add(head);
                }
                if(waitQueue.isEmpty()){
                    return;
                }
                //waitQueue不空：安排坐下
                Party sit = waitQueue.poll();
                sit.isServed = true;
                table.isAvailable = false;
                //加回priority queue
                if(!temp.isEmpty()){
                    for(Party p : temp){
                        waitQueue.offer(p);
                    }
                }

                //2. deque：O(n) 比queue的好处：不符合size的暂时移出去，但是加回来的时候还能在头（相当于顺序还在）
                while(!deque.isEmpty() && deque.peekFirst().groupSize > table.tableSize){
                    Party head = deque.pollFirst();
                    tempStack.push(head);
                }
                if(deque.isEmpty()){
                    return;
                }
                //deque不空：安排坐下
                Party sitParty = deque.pollFirst();
                sitParty.isServed = true;
                table.isAvailable = false;
                //加回deque
                if(!tempStack.isEmpty()){
                    Party p = tempStack.pop();
                    deque.offerFirst(p); //重点：重新加，加在头部
                }
            }
        }

    }
}
