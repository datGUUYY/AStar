package TilePuzzle;

import java.lang.reflect.Array;
import java.util.Collection;

public class EightPuzzle {
	//TODO: get moves IN INTERFACE
	protected int[][] body = new int[3][3];
		//TODO: input validation for each subarray having the same length
	public EightPuzzle()
	{
		for(int i = 1; i < 9; i++)
		{
			body[i/3][i%3] = i;
		}
		body[2][2]= 0;
	}
	public EightPuzzle(int [][]body)
	{
		this.body = new int[body.length][body[0].length];
		for(int i = 0; i < body.length; i++)
		{
			this.body[i] = body[i].clone();
		}
	}
	public int getEmpty()
	{
		for(int i = 0; i < body.length * body[0].length; i++)
			if(body[i/3][i % 3] == 0)
				return i;
		throw new Error(); //TODO: replace with more specific error
	}
	public void insertMoves(Collection target) //TODO test if Collection is a valid type
	{
		//TODO: refactor into generic //didnt need to do?
		int empty = getEmpty();
		if(empty > 2) target.add(empty - 3);
		if(empty % 3 > 0) target.add(empty - 1);
		if(empty < 6) target.add(empty + 3);
		if(empty % 3 < 2) target.add(empty + 1);
	}
	public void takeMove(int i)
	{
		int empty = getEmpty();
		int inner = body[0].length;
		body[empty / inner][empty%inner] = body[i/inner][i%inner];
		body[i/inner][i%inner] = 0;
	}
}
