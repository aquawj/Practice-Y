package Yelp;
import java.util.*;

/**给一个String数组代表用户有很多bookmark，每个String可能是一个或者多个单词，
需要在用户搜索输入一个字符串的时候返回所有包含这个字符串的bookmark
一开始是只返回字符串为prefix的bookmark，注意bookmark里如果有多个单词，第二个单词prefix符合也要返回这个bookmark
followup是如果不只是prefix, bookmark中任意地方符合就返回，注意一下大小写
followup2是问如果能储存一个让这种搜索加速的结构，用什么，我说了prefix tree，不过只剩三分钟就没实现
*/
public class Bookmark {

    public List<String> getBookMarks(List<String> bookmarks, String input){
        List<String> res = new ArrayList<>();
        for(String bookmark : bookmarks){
            String[] words = bookmark.split("\\s+");
            for(String word : words){
                if(word.contains(input)){
                    res.add(bookmark);
                    break;
                }
            }
        }

        return res;
    }
}
