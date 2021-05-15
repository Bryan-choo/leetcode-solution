package 查找树_Tree;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AVLMap<K, V> implements Map<K, V> {

	private int size = 0;
	public AVLEntry<K, V> root;
	
	
	public AVLMap() {
		System.out.println("init...");
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (null == key) {
			throw new NullPointerException("key value can not be null");
		} else {
			AVLEntry<K, V> entry = new AVLEntry<K, V>(key, value);
			entry = this.addEntry(entry);
			this.updateHeight(entry);
			root = this.checkAVL(entry);
			return value;
		}
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	private AVLEntry<K, V> addEntry(AVLEntry<K, V> entry) {
		AVLEntry<K, V> p = root;
		if (null == root) {
			root = entry;
			p = root;
			size++;
		} else {
			while (null != p) {
				int result = this.compare(entry.key, p.key);
				if (result == 0) {
					p.value = entry.value;
					return p;
				} else {
					if (result < 0) {
						if (null != p.left) {
							p = p.left;
						} else  {
							p.left = entry;
							entry.parent = p;
							size++;
							p = p.left;
							break;
						}
					} else {
						if (null != p.right) {
							p = p.right;
						} else {
							p.right = entry;
							entry.parent = p;
							size++;
							p = p.right;
							break;
						}
					}
				}
				
			}
		}
		return p;
	}
	
	private AVLEntry<K, V> checkAVL(AVLEntry<K, V> entry) {
		while (null != entry) {
			int leftTreeLength;
			int rightTreeLength;
			leftTreeLength = ((null == entry.left)?0:entry.left.height)+1;
			rightTreeLength = ((null == entry.right)?0:entry.right.height)+1;
			
			if(Math.abs(leftTreeLength - rightTreeLength) >= 2) {
				if (leftTreeLength > rightTreeLength) {
					
//					LL
					if (getHeight(entry.left.left) > getHeight(entry.left.right)) {
						entry = this.rotateRight(entry);
					} else {
//					LR
						entry = this.firstLeftThenRight(entry);
					}
					
				} else {
//					RR
					if (getHeight(entry.right.right) > getHeight(entry.right.left)) {
						entry = this.rotateLeft(entry);
					} else {
//					RL	
						entry = this.firstRightTheLeft(entry);
					}
				}
			}
			if (null != entry.parent)
				entry = entry.parent;
			else
				break;
		}
		return entry;
	}
	private int getHeight(AVLEntry<K, V> entry) {
		return (null == entry)?0:entry.height;
	}
	
	private void updateHeight(AVLEntry<K, V> entry) {
		while (null != entry.parent && Math.abs(entry.height - entry.parent.height) == 0) {
			entry.parent.height++;
			entry = entry.parent;
		}
	}
	private AVLEntry<K, V> rotateRight(AVLEntry<K, V> entry) {
		AVLEntry<K, V> left = entry.left;
		entry.left = left.right;
		if (null != entry.left) {
			entry.left.parent = entry;
		}
		left.right = entry;
		if (null != entry.parent) {
			if (entry.parent.left == entry)
				entry.parent.left = left;
			else
				entry.parent.right = left;
		}
		left.parent = entry.parent;
		entry.parent = left;
		entry.height = Math.max(getHeight(entry.left), getHeight(entry.right)) + 1;
		left.height = Math.max(getHeight(entry.left), getHeight(entry.right)) + 1;
		entry = left;
		return entry;
	}
	
	private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> entry) {
		AVLEntry<K, V> right = entry.right;
		entry.right = right.left;
		if (null != entry.right) {
			entry.right.parent = entry;
		}
		right.left = entry;
		if (null != entry.parent) {
			if (entry.parent.left == entry)
				entry.parent.left = right;
			else
				entry.parent.right = right;
		}
		right.parent = entry.parent;
		entry.parent = right;
		entry.height = Math.max(getHeight(entry.left), getHeight(entry.right));
		right.height = Math.max(getHeight(right.left), getHeight(right.right));
		entry = right;
		return entry;
	}
	
	private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> entry) {
		AVLEntry<K, V> left = entry.left;
		left = this.rotateLeft(left);
		entry = this.rotateRight(entry);
		return entry;
	}
	
	private AVLEntry<K, V> firstRightTheLeft(AVLEntry<K, V> entry) {
		AVLEntry<K, V> right = entry.right;
		right = this.rotateRight(right);
		entry = this.rotateLeft(right);
		return entry;
	}
	
	public int compare(K key1, K key2) {
		Comparable<K> a = (Comparable<K>) key1;
		return a.compareTo(key2);
	}
	
	public static void main(String[] args) {
//		TreeNode t1 = new TreeNode(1);
//		TreeNode t2 = new TreeNode(2);
//		TreeNode t3 = new TreeNode(3);
//		TreeNode t4 = new TreeNode(15);
//		TreeNode t5 = new TreeNode(7);
//		TreeNode t6 = new TreeNode(7);
//		TreeNode t7 = new TreeNode(7);
		AVLMap<Integer, String> map = new AVLMap<Integer, String>();
		map.put(1, "1");
		map.put(3, "3");
		map.put(5, "5");
		map.put(2, "2");
		map.put(7, "7");
		map.put(8, "8");
		map.put(9, "9");
		map.put(12, "12");
		map.put(18, "18");
		System.out.println(map.toString());
	}
}
