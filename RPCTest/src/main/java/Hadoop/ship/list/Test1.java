package Hadoop.ship.list;
/*
 * -oo <-> +oo
------
-oo <-> ABC <-> +oo
-oo <-> ABC <-> +oo
------
-oo <-> ABC <---------> +oo
-oo <-> ABC <-> DEF <-> +oo
------
-oo <-> ABC <-----------------> +oo
-oo <-> ABC <-> DEF <-> KLM <-> +oo
------
-oo <-----------------> HIJ <---------> +oo
-oo <-> ABC <---------> HIJ <---------> +oo
-oo <-> ABC <-> DEF <-> HIJ <-> KLM <-> +oo
------
-oo <-------------------------> HIJ <---------> +oo
-oo <-> ABC <---------> GHJ <-> HIJ <---------> +oo
-oo <-> ABC <-> DEF <-> GHJ <-> HIJ <-> KLM <-> +oo
------
-oo <-> AAA <-----------------------------------------> +oo
-oo <-> AAA <-------------------------> HIJ <---------> +oo
-oo <-> AAA <-> ABC <---------> GHJ <-> HIJ <---------> +oo
-oo <-> AAA <-> ABC <-> DEF <-> GHJ <-> HIJ <-> KLM <-> +oo
------
 */
public class Test1 {
	 public static void main(String[] args) {
		         SkipList S = new SkipList();
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		         // System.out.println("======");
		         S.put("ABC", 123);
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		          //System.out.println("======");
		         S.put("DEF", 123);
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		         // System.out.println("======");
		         S.put("KLM", 123);
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		         // System.out.println("======");
		         S.put("HIJ", 123);
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		         // System.out.println("======");
		         S.put("GHJ", 123);
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		         // System.out.println("======");
		         S.put("AAA", 123);
		         S.printHorizontal();
		         System.out.println("------");
		         // S.printVertical();
		         // System.out.println("======");
		     }

}

