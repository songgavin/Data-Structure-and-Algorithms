// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=224520&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311

public class TwoSumClosest {
    public static int[] twoSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int minDis = Integer.MAX_VALUE;
        int[] result = new int[2];
        
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] > target - nums[i]) {
                break;
            }
            
            int index = binarySearch(nums, i + 1, nums.length - 1, target - nums[i]);
            if (index < nums.length) {
                if (Math.abs(nums[i] + nums[index]) < minDis) {
                    minDis = Math.abs(nums[i] + nums[index]);
                    result[0] = nums[i];
                    result[1] = nums[index];
                }
            }
            
            if (index > i + 1) {
                if (Math.abs(nums[i] + nums[index - 1]) < minDis) {
                    minDis = Math.abs(nums[i] + nums[index - 1]);
                    result[0] = nums[i];
                    result[1] = nums[index - 1];
                }
            }
            
            i++;
        }
        
        return result;
    }
    
    public static int twoSumSmaller(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        
        return count;
    }
    
    public static int binarySearch(int[] nums, int start, int end, int target) {
        int left = start;
        int right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return right;
    }
    public static void main(String[] args) {
        int[] nums = {1, 5, 6, 8, 10};
        System.out.println(Test.twoSumSmaller(nums, 2));
    }
}
