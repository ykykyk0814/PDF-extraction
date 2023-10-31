import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem2 {

    public static int[] findIndex(int[] numbers, int target) {
        int[] result = new int[]{-1, -1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < numbers.length; index += 1) {
            if (map.containsKey(target - numbers[index])) {
                result = new int[]{map.get(target - numbers[index]), index};
                break;
            } else {
                map.put(numbers[index], index);
            }
        }
        return result;
    }
    //TODO test
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 5, 7, 9, 2};
        int target = 3;
        System.out.println(Arrays.toString(findIndex(numbers, target)));
    }
}

