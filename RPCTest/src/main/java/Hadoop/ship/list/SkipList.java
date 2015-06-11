package Hadoop.ship.list;

import java.util.Random;



/**
 * 
 public class SkipList { public SkipListEntry head; // First element of the
 * top level public SkipListEntry tail; // Last element of the top level
 * 
 * 
 * public int n; // number of entries in the Skip List
 * 
 * public int h; // Height public Random r; // Coin toss
 * 
 * ....
 * 
 * } 
 * basic method : get() put() remove()
 * 
 * @author jack 2015年5月9日
 *
 */
public class SkipList {
	public SkipListEntry head;
	public SkipListEntry tail;
	public int number;// number of entries in the Skip List
	public int height;
	public Random random;

	public SkipList() {
		/*
		 * ----------------------------------- Create an -oo and an +oo object
		 * -----------------------------------
		 */
		SkipListEntry p1 = new SkipListEntry(SkipListEntry.negInf, null);
		SkipListEntry p2 = new SkipListEntry(SkipListEntry.posInf, null);
		/*
		 * -------------------------------------- Link the -oo and +oo object
		 * together ---------------------------------------
		 */
		p1.right = p2;
		p2.left = p1;
		/*
		 * -------------------------------------- Initialize "head" and "tail"
		 * ---------------------------------------
		 */
		head = p1;
		this.tail = p2;
		/*
		 * -------------------------------------- Other initializations
		 * ---------------------------------------
		 */
		number = 0; // No entries in Skip List
		height = 0; // Height is 0
		random = new Random(); // Make random object to simulate coin toss
	}
	/*basic method : get() put() remove()
	 * Notice that each basic operation must first find (search) the appropriate entry 
	 * (using a key) before the operation can be completed.*/
	 /* ------------------------------------------------------
    findEntry(k): find the largest key x <= k
                  on the LOWEST level of the Skip List
    ------------------------------------------------------ */
	/**
	 * If the key k is found in the Skip List, findEntry(k) will return the reference to the entry containg the key k
If the key k is not found in the Skip List, findEntry(k) will return the reference to the floorEntry(k) entry containg a key that is smaller than k
	 * @param key
	 * @return
	 */
	public SkipListEntry findEntry(String key){
		SkipListEntry p=head;
		while (true) {
			 /* ------------------------------------------------
	           Search RIGHT until you find a LARGER entry

	           E.g.: k = 34

	                     10 ---> 20 ---> 30 ---> 40
	                                      ^
	                                      |
	                                      p must stop here
			p.right.key = 40
	           ------------------------------------------------ */  
			while (p.right.key!=SkipListEntry.posInf&&p.right.key.compareTo(key)<=0) {
				p=p.right;// move to right
			}
			if (p.down!=null) {
				p=p.down;
			} else {
                 break;// We reached the LOWEST level... Exit...
			}
		}
		return p;// Note: p.key <= key
	}
	/**
	 * Returns the value associated with a key.
	 * @param key
	 * @return
	 */
	public Integer get(String key) {
		SkipListEntry p=findEntry(key);
		if (key.equals(p.key)) {
			return p.value;
		}else {
			return null;
		}
	}
	/* *
	 *  put(k, v)
	    {
	       SkipListEntry p;

	       p = findEntry(k);

	       if ( k.equals( p.key ) )    // Found !
	       {
	          p.value = v;             // Update the value
		  return;                  // Done
	       }

	        ==================================================
	          Not found.

		  Then:   p == floorEntry(k) !!!
		  ==================================================        

	       (1) insert (k,v)  AFTER  p
	       (2) make a column of (k,v) of RANDOM height*/
	    
	public Integer put (String k, Integer v){
		
		SkipListEntry p=findEntry(k);
		if (k.equals(p.key)) {
			Integer oldInteger=p.value;
			p.value=v;
			return oldInteger;
		}

	     /* -------------------------------------------------------------
	        Key k is not found, then p = floorEntry(k) (See: click here)

	        The rest of the code will insert a new entry (k,v)
	        ------------------------------------------------------------- */

	   SkipListEntry q = new SkipListEntry(k,v);       // Create a new entry with k and v   

	     /* --------------------------------------------------------------
	        Insert q into the lowest level after SkipListEntry p:

	                         p   put q here           p        q
	                         |     |                  |        |
			         V     V                  V        V        V
	        Lower level:    [ ] <------> [ ]    ==>  [ ] <--> [ ] <--> [ ]
	        --------------------------------------------------------------- */
	       q.left=p;
	       q.right=p.right;
	       p.right.left=q;
	       p.right=q;

	     /* -----------------------------------------------------
	        Make a "tower" of the entry e or RANDOM height
		----------------------------------------------------- */

	   int  i = 0;                   // Current level = 0
	   while (random.nextDouble()<0.5) {
		// Coin toss success ! ---> build one more level !!!
	        /* -------------------------------------------------------------------
		   Check if we need to increase the height of the -oo and +oo "pillars
		   ------------------------------------------------------------------- */
		   if (i>=height) {// We reached the top level !!!,
			SkipListEntry p1=new SkipListEntry(SkipListEntry.negInf, null);
			SkipListEntry p2=new SkipListEntry(SkipListEntry.posInf, null);
			/** --------------------
		      Link them
		      -------------------- */
			p1.right=p2;
			p1.down=head;
			p2.left=p1;
			p2.down=tail;
			head.up=p1;
			tail.up=p2;
			 /* --------------------
		      Update head and tail
		      -------------------- */
			head=p1;
			tail=p2;
			height=height+1;
		}
		   /* ------------------------------------
           Find first element with an UP-link
           ------------------------------------ */
		   while (p.up==null) {
			p=p.left;
		}
		   /* --------------------------------
		   Make p point to this UP element
		   -------------------------------- */
	        p = p.up;
	        /* ---------------------------------------------------
	           Add one more (k,*) to the column

		   Schema for making the linkage:

	                p <--> e(k,*) <--> p.right
	                          ^
			          |
			          v
			          q
		   ---------------------------------------------------- */
	        SkipListEntry e=new SkipListEntry(k, null);
	        /* ---------------------------------------
	    	   Initialize links of e
	    	   --------------------------------------- */
	        e.left=p;
	        e.right=p.right;
	        e.down=q;
	    	/* ---------------------------------------
	    	   Change the neighboring links..
	    	   --------------------------------------- */
	        p.right.left=e;
	        p.right=e;
	        q.up=e;
	        q=e;         // Set q up for next iteration (if there is one)
	        i = i + 1;   // Current level increases by one
	     }
	   number=number+1;
	   return null;
	}
	 public void printHorizontal() {
		         String s = "";
		         int i;
		         SkipListEntry p;
		         p = head;
		         while (p.down != null) {
		             p = p.down;
		         }
		         i = 0;
		         while (p != null) {
		             p.pos = i++;
		             p = p.right;
		         }
		         p = head;
		         while (p != null) {
		             s = getOneRow(p);
		             System.out.println(s);
		             p = p.down;
		         }

		     }

		     //用了打印测试
	
		     public String getOneRow(SkipListEntry p) {
               String s;
		         int a, b, i;
		         a = 0;
		         s = "" + p.key;
		         p = p.right;
		         while (p != null) {

		             SkipListEntry q;
		             q = p;
		             while (q.down != null)
		                 q = q.down;
		             b = q.pos;  
		             s = s + " <-";
		             for (i = a + 1; i < b; i++)
		                 s = s + "--------";
		             s = s + "> " + p.key;
		             a = b;	
		             p = p.right;
		         }
		         return (s);
		     }
		     //用了打印测试
		     public void printVertical() {
		         String s = "";
		         SkipListEntry p;
		         p = head;
		         while (p.down != null)
		             p = p.down; 
		         while (p != null) {
		             s = getOneColumn(p);
		             System.out.println(s);	  
		             p = p.right;
		         }
		     }

		     //用了打印测
		 public String getOneColumn(SkipListEntry p) {
		         String s = "";
		         while (p != null) {
		             s = s + " " + p.key;
		             p = p.up;
		         }
		         return (s);
		     }

	
}
