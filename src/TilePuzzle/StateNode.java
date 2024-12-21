package TilePuzzle;

import java.util.Collection;

public interface StateNode<T extends StateNode> {
	public void setHVal();
	public void setDVal();
	public int getValue(); 
	//This will always return hval + dval, it will just be easier in an interface.
	public void insertChildren(Collection<T> target);
}
