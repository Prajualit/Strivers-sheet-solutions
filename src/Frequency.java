import java.util.*;

public class Frequency {

    public static List<List<Integer>> countFrequencies(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<List<Integer>> list = new ArrayList<>();


//        for (int i = 0; i < map.size(); i++) {
//            List<Integer> innerlist = new ArrayList<>();
//
//            innerlist.add((Integer) map.keySet().toArray()[i]);
//            innerlist.add((Integer) map.values().toArray()[i]);
//
//            list.add(innerlist);
//        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            List<Integer> innerList = new ArrayList<>();
            innerList.add(entry.getKey());
            innerList.add(entry.getValue());
            list.add(innerList);
        }

        return list;
    }

    public static int maxFrequency(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[i] += 1;
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }


        int[] result = new int[map.size()];

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[index] = entry.getValue();
            index++;
        }

        return getLargestNumber(result);
    }

    public static int getLargestNumber(int[] nums) {
        int largest = 0;
        for (int num : nums) {
            if (num > largest) {
                largest = num;
            }
        }
        System.out.println(largest);
        return largest;
    }

    public static void main(String[] main) {

        int[] nums = {5, 5, 5, 5, 4, 4, 3, 5};
        int[] nums1 = {1, 2, 4};

//        System.out.println(countFrequencies(nums));
        System.out.println(maxFrequency(nums1, 5));
//        getLargestNumber(nums1);
    }
}
