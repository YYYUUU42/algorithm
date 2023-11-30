package HashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<String, Integer>();

        map.put("test1",1);
        map.put("test2",2);

        Integer integer = map.get("test1");

        System.out.println(integer);
    }
}
