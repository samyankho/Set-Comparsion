# Set-Comparsion

a. When using Java's HashSet, the initial capacity of the data structure must smaller than total amount of entries / Load Factor .
   Otherwise we have no reason to hashing.  (Load Factor = Count of elements in Table / Total Size of Hash Table)
   And performance will better if capacity gets reduced.



b. In part1, we use balance tree(AVL TREE) to implement a set, it only requires O(logn) time to accomplish functions like search, insertion or deletion.
   But in HeapSort it requires O(n) time to accomplish functions like search or deletion and O(logn) time to do a insertion.
   So balance tree is a better data structure to implement a set, however HeapSort is good for priority queue because high priority element is close to root.



c. First, the data in both TreeSet and TreeMap are in a sorted order.
   Second, the interface of TreeMap is implemented when set interface is implemented in TreeSet.
   And we can say that TreeSet is implemented by a TreeMap instance.
   
   
   testrun.txt is the result of the sets comparsion between treeset, hashset and set implemented by balance tree(AVL TREE).
