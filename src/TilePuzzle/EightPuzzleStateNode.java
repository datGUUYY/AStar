package TilePuzzle;

import java.util.ArrayList;
import java.util.Collection;

public class EightPuzzleStateNode 
	extends EightPuzzleCloneable<EightPuzzleStateNode>
	implements StateNode<EightPuzzleStateNode> 
{
	public EightPuzzleStateNode(EightPuzzleStateNode parent) {
		super(parent);
	}
	
	public EightPuzzleStateNode(int[][] body, int[][] target)
	{
		super(body,target);
	}
	
	public int hval, dval;
	@Override
	public void setHVal() {
		//MANHATTAN
	hval = manhattan();
	}

	@Override
	public void setDVal() {
		dval = (parent != null) ? parent.dval + 1 : 0;
	}

	@Override
	public int getValue() {
		return hval + dval;
	}

	@Override
	public void insertChildren(Collection<EightPuzzleStateNode> targetCollection) {
		ArrayList<Integer> moves = new ArrayList();
		insertMoves(moves);
		for(int move : moves)
		{
			EightPuzzleStateNode newState = new EightPuzzleStateNode(this);
			newState.takeMove(move);
			newState.setDVal();
			newState.setHVal();
			targetCollection.add(newState);
		}
	}

}
