package app;

import java.util.ArrayList;
import java.util.List;

public class SimpleTreeNodesLevelDeterminator {
	
	public static <T> void determinateLevels (SimpleTree<T> simpleTree) {
		
		if (simpleTree.Root == null) return;
		
		simpleTree.Root.Level = 0; 
		
		determinateLevels(simpleTree.Root.Children, 0, 1);
		
	}
	
	private static <T> void determinateLevels (List<SimpleTreeNode<T>> simpleTreeNodes, int index, int level) {

		
		if (simpleTreeNodes == null) return;
		
		if(index < simpleTreeNodes.size()) {
			
			simpleTreeNodes.get(index).Level = level;
			
			determinateLevels(simpleTreeNodes.get(index).Children, 0, level + 1);
			determinateLevels(simpleTreeNodes, index + 1, level);
				
		}
		
	}
	
}
