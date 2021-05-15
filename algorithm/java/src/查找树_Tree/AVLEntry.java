package 查找树_Tree;
import java.util.Map;


public class AVLEntry<K, V> implements Map.Entry<K, V>{
	
	public K key;
	public V value;
	public int height = 1;
	
	public AVLEntry<K, V> left;
	public AVLEntry<K, V> right;
	public AVLEntry<K, V> parent;
	
	public AVLEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public V setValue(V value) {
		this.value = value;
		return this.value;
	}

}
