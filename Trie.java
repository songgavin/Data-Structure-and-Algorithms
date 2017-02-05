// Fourth quesiton on http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=224520&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311

import java.util.*;

class TrieNode {
	public boolean isWord = false;
	public Map<Character, TrieNode> children = new HashMap<>();
}

public class Trie {

	public TrieNode root;
	public Trie() {
		root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {

			if (!cur.children.containsKey(c)) {
				cur.children.put(c, new TrieNode());
			}

			cur = cur.children.get(c);
		}

		cur.isWord = true;
	}

	public boolean search(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {

			if (!cur.children.containsKey(c)) {
				return false;
			}

			cur = cur.children.get(c);
		}
		return cur.isWord;
	}

	public List<String> get(String prefix) {
		TrieNode cur = root;

		StringBuilder sb = new StringBuilder();
		for (char c : prefix.toCharArray()) {
			if (!cur.children.containsKey(c)) {
				return Collections.emptyList();
			}
			sb.append(c);
			cur = cur.children.get(c);
		}

		List<String> result = new ArrayList<>();
		dfs(cur, sb, result);
		return result;
	}

	public void dfs(TrieNode cur, StringBuilder sb, List<String> result) {
		if (cur.isWord) {
			result.add(sb.toString());
		}

		for (Map.Entry<Character, TrieNode> entry : cur.children.entrySet()) {
			sb.append(entry.getKey());
			dfs(entry.getValue(), sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		String[] words = {"abc", "a", "bc", "b", "c", "abcd"};
		for (String word : words) {
			trie.insert(word);
		}
		System.out.println(trie.get(args[0]));
	}
}
