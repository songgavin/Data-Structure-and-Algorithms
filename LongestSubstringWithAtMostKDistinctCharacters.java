class LongestSubstringWithAtMostKDistinctCharacters {
    class ListNode {
        int lastIndex = -1;
        char c;
        ListNode prev;
        ListNode next;
        ListNode(char c, int lastIndex) {
            this.c = c;
            this.lastIndex = lastIndex;
        }
    }

    class DoubleLinkedList {
        ListNode head = new ListNode('0', 0);
        ListNode tail = new ListNode('0', 0);
        int length = 0;
        DoubleLinkedList () {
            head.next = tail;
            tail.prev = head;
        }

        void add(ListNode node) {
            node.prev = tail.prev;
            tail.prev.next = node;
            node.next = tail;
            tail.prev = node;
            length++;
        }

        void delete(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
            length--;
        }

        ListNode pollLeft() {
            ListNode ret = head.next;
            head.next = ret.next;
            ret.next.prev = head;
            ret.prev = null;
            ret.next = null;
            return ret;
        }

        int size() {
            return length;
        }
    }

    public int lengthOfLongestSubstringKDistinctInStream(String s, int k) {
        int left = 0;
        Map<Character, ListNode> charToListNode = new HashMap<>();
        DoubleLinkedList deque = new DoubleLinkedList();

        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            ListNode nodeRight;
            if (charToListNode.containsKey(s.charAt(right))) {
                nodeRight = charToListNode.get(s.charAt(right));
                deque.delete(nodeRight);
                nodeRight.lastIndex = right;
            } else {
                nodeRight = new ListNode(s.charAt(right), right);
                charToListNode.put(s.charAt(right), nodeRight);
            }
            deque.add(nodeRight);

            while (charToListNode.size() > k) {
                ListNode node = deque.pollLeft();
                left = node.lastIndex + 1;
                charToListNode.remove(node.c);
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
    
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        Map<Character, Integer> charToCount = new HashMap<>();
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            if (charToCount.containsKey(s.charAt(right))) {
                charToCount.put(s.charAt(right), 0);
            }

            charToCount.put(s.charAt(right), charToCount.get(s.charAt(right)) + 1);

            while (charToCount.size() > k) {
                int count = charToCount.get(s.charAt(left));
                if (count == 1) {
                    charToCount.remove(s.charAt(left));
                } else {
                    charToCount.put(s.charAt(left), charToCount.get(s.charAt(left)) - 1);
                }
                left++;
            }

            maxLength = Math.max(right - left + 1, maxLength);
        }

        return maxLength;
    }
}
