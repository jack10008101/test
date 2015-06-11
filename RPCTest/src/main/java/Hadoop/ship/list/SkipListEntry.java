package Hadoop.ship.list;

/**
 * public class SkipListEntry { public String key; public Integer value;
 * 
 * public SkipListEntry up; // up link public SkipListEntry down; // down link
 * public SkipListEntry left; // left link public SkipListEntry right; // right
 * link
 * 
 * ... (methods) }
 * 
 * @author jack 2015年5月9日
 *
 */
public class SkipListEntry {
	public String key;
	public Integer value;
	public SkipListEntry up;
	public SkipListEntry down;
	public SkipListEntry left;
	public SkipListEntry right;
	public static String negInf = "-oo"; // -inf key value
	public static String posInf = "+oo"; // +inf key value

	public SkipListEntry(String key, Integer value) {
		this.key = key;
		this.value = value;
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
	}
	 public boolean equals(Object o) {
		         SkipListEntry ent;
		         try {
		             ent = (SkipListEntry) o; // 检测类型
		         } catch (ClassCastException ex) {
		             return false;
		         }
		         return (ent.key == key) && (ent.value == value);
		     }
		     public String toString() {
		         return "(" + key + "," + value + ")";
		     }

		     public int pos; //主要为了打印 链表用

}
