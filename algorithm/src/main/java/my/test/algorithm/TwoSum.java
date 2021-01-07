package my.test.algorithm;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6 输出：[1,2]
 *
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6 输出：[0,1]  
 *
 * 提示：
 *
 * 2 <= nums.length <= 103
 *
 * -109 <= nums[i] <= 109
 *
 * -109 <= target <= 109
 *
 * 只会存在一个有效答案
 *
 * 来源：力扣（LeetCode）
 *
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author lin
 * @date 2021/1/7 17:28
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum3(nums, target);
        for (int i : result) {
            System.out.println(i);
        }

        int[] nums2 = {3, 2, 4};
        target = 6;
        result = twoSum3(nums2, target);
        for (int i : result) {
            System.out.println(i);
        }

        int[] nums3 = {3, 3};
        target = 6;
        result = twoSum3(nums3, target);
        for (int i : result) {
            System.out.println(i);
        }
    }

    /**
     * 思路：先取第一个数，然后第二个数取第一个数的后一位，不符合要求则第二个数后移一位，再计算
     *
     * 都不符合要求则第一个数后移一位，再继续上述循环，直到找到结果结束
     *
     * 这是我的代码，在跳出循环、构造返回值部分做的很差 o(╥﹏╥)o
     */
    public static int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = 1;
        boolean over = false;
        for (; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    over = true;
                    break;
                }
            }
            if (over) {
                break;
            }
        }
        int[] result = new int[2];
        result[0] = i;
        result[1] = j;
        return result;
    }

    /**
     * 下面是标准的暴力枚举法，可以看到和上面思路是一样的，但是要精简很多
     */
    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 下面这种哈希表法很巧妙，我之前完全没有想到 -_-||
     *
     * 思路：获取到一个数后，判断其另一半是否在 map 中，如果不在则将自身和自身的下标存入map；如果在，则返回自身和另一半的下标
     *
     * 必须先进行 containsKey 操作，后进行 put 操作，否则会出现和自身匹配的问题
     */
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


}
