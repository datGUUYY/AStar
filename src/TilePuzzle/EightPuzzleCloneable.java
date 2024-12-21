package TilePuzzle;

import java.util.ArrayList;
import java.util.Collection;

public class EightPuzzleCloneable<T extends EightPuzzleCloneable> extends EightPuzzle {
	/*
	 * This class includes all of the 
	 */
	int[][] target = {{1,2,3},{4,5,6},{7,8,0}};
	T parent = null;
	public EightPuzzleCloneable(int[][] body, int[][] target)
	{
		super(body);
		target = target;
	}
	public EightPuzzleCloneable(T parent)
	{
		this.body = new int[parent.body.length][];
		for(int i = 0; i < parent.body.length; i++)
			this.body[i] = parent.body[i].clone();
		
		this.target = new int[parent.target.length][];
		for(int i = 0; i < parent.target.length; i++)
			this.target[i] = parent.target[i].clone();
		
		this.parent = parent;
	}
	
	protected void generateGameStates(Collection target) {
		//TODO: fill out
	}
	protected int manhattan()
	{
		int value = 0;
		for(int i = 0; i < target.length; i++)
		{
			for(int j = 0; j < target[0].length; j++)
			{
				int targetValue = target[i][j];
				for(int x = 0, y = 0; y < target[0].length; x++)
				{
					y = (x >= target.length) ? y + 1: y;
					x = (x >= target.length) ? x % target.length : x;
					if(body[x][y] == targetValue)
					{
						value += Math.abs(x - i) + Math.abs(y - j);
						break;
					}
				}
					
			}
		}
		return value;
	}
	
}
