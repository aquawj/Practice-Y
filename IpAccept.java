package Yelp;
/**accept_request。输入ip和timestamp，return true/false。每个ip在1s内只能被accept 1次。例子：
ts=0.1s ip=1.2.3.4    return true
ts=0.2s ip=1.2.3.5    return true
ts=1.1s ip=1.2.3.4    return false-baidu 1point3acres
ts=1.2s ip=1.2.3.4    return true
解法朴素，拿个dict/hashmap存一下就行。之后会问你时间复杂度和空间复杂度。

follow up。多输入个rate，即每个ip在1s内只能被accept rate次。例子：
rate = 3
ts=0.1s ip=1.2.3.4    return true
ts=0.2s ip=1.2.3.4    return true
ts=0.3s ip=1.2.3.4    return true
ts=0.4s ip=1.2.3.4    return false
这个就要注意dict要用个list/其他数据结构存ts了，并且要把过期的ts给pop掉。
用list的话pop(0)是要O(n)时间的。我写的时候是用list写的，
最后和面试官提了一句pop(0)会慢，最好用deque来做，就能O(1) pop(0)了。*/
public class IpAccept {
}
