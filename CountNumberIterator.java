// fourth question in http://www.1point3acres.com/bbs/thread-217004-1-1.html
// In this question, we check and change index and count in hasNext() method.
// Need to check more when go home
public class CountNumberIterator {

    int[] nums;
    int count;
    int index;

    public CountNumberIterator(int[] nums) {
        this.nums = nums;
        count = 0;
        index = 0;
    }

    public int next() {
        count++;
        return nums[index + 1];
    }

    public boolean hasNext() {
        while (index < nums.length && count == nums[index] ) {
            count = 0;
            index += 2;
        }
        if (index >= nums.length) {
            return false;
        } else {
            return true;
        }
    }
    
    public static void main(String[] args) {
        CountNumberIterator c = new CountNumberIterator(new int[]{2, 3, 0, 4});
        while (c.hasNext()) {
            System.out.println(c.next());
        }
    }
}

