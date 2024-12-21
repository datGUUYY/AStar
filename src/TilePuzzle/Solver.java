package TilePuzzle;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solver {
	public PriorityQueue<EightPuzzleStateNode> open;
	public HashSet<EightPuzzleStateNode> closed;
	protected boolean solved;
	public Solver()
	{
		int [][]start = {{1,5,2},{6,3,8},{0,7,4}};
		int [][]end = {{1,2,3},{4,5,6},{7,8,0}};
		EightPuzzleStateNode origin = new EightPuzzleStateNode(start, end);
		open = new PriorityQueue(11,
				new Comparator<EightPuzzleStateNode>() {

					@Override
					public int compare(EightPuzzleStateNode o1, EightPuzzleStateNode o2) {
						return o1.getValue() - o2.getValue();
					}});
		closed = new HashSet();
		open.add(origin);
		solved = false;
	}
	public void solve()
	{
//		if(isSolvable(new int[][]{{7,2,9},{1,8,5},{3,6,4}}))
		while(!open.isEmpty())
		{
			process();
			if(solved) break;
		}
//		else System.out.println("NOT DOABLE");
	}
	public void process()
	{
		EightPuzzleStateNode current = open.poll();
		ArrayList<EightPuzzleStateNode> temp = new ArrayList();
		current.insertChildren(temp);
		for(EightPuzzleStateNode child : temp)
		{
			if(Arrays.deepEquals(child.body,child.target))
			{
				printback(child);
				solved = true;
				break;
			}
			checkClosed(child);
		}
	}
	protected void checkClosed(EightPuzzleStateNode checknode)
	{
		for(EightPuzzleStateNode against : closed)
		{
			
			if(Arrays.deepEquals(against.body, checknode.body))
			{
				if(checknode.dval > against.dval)
				{
					closed.remove(against);
					open.add(checknode);
				}
				return;
			}
		}
		open.add(checknode);
	}
	protected void printback(EightPuzzleStateNode printee)
	{
		Stack<EightPuzzleStateNode> outputStack = new Stack();
		EightPuzzleStateNode current = printee;
		while(current != null)
		{
			outputStack.add(current);
			current = current.parent;
		}
		while(outputStack.size() > 5)
		{
			EightPuzzleStateNode []line = new EightPuzzleStateNode[5];
			for(int i = 0; i < 5; i++)
				line[i]=outputStack.pop();
			for(int i = 0; i < line[0].body.length; i++)
			{
				for(int j = 0; j < 5; j++)
				{
					for(int x : line[j].body[i])
					{
						System.out.print(x + " ");
					}
					System.out.print('\t');
				}
				System.out.println();
			}
			System.out.println();
		}
		
		while(!outputStack.isEmpty())
		{
			int [][]value = outputStack.pop().body;
			for(int []a : value)
			{
				for(int b : a)
					System.out.print(b + " ");
				
				System.out.println();
			}
			System.out.println();
		}
	}
	int getInvCount(int[] arr)
	{
	    int inv_count = 0;
	    for (int i = 0; i < 9; i++)
	        for (int j = i + 1; j < 9; j++)
	         
	            // Value 0 is used for empty space
	            if (arr[i] > 0 &&
	                            arr[j] > 0 && arr[i] > arr[j])
	                inv_count++;
	    return inv_count;
	}
	boolean isSolvable(int[][] puzzle)
	{
	    int linearPuzzle[];
	    linearPuzzle = new int[9];
	    int k = 0;
	     
	  // Converting 2-D puzzle to linear form
	    for(int i=0; i<3; i++)
	        for(int j=0; j<3; j++)
	            linearPuzzle[k++] = puzzle[i][j];
	     
	    // Count inversions in given 8 puzzle
	    int invCount = getInvCount(linearPuzzle);
	 
	    // return true if inversion count is even.
	    return (invCount % 2 == 0);
	}
		
	public static void main(String []args)
	{
		Solver solver = new Solver();
		solver.solve();
	}
	
}
