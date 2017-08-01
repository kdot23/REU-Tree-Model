
public class Simulator {

	public static void main(String[] args) {
		/*
		Tree tree1 = new Tree(ProbSpec.DBHSTAGE2,ProbSpec.HEALTHY,ProbSpec.UNTREATED);
		Tree tree2 = new Tree(ProbSpec.DBHSTAGE1,ProbSpec.V, ProbSpec.UNTREATED);
		Tree tree3 = new Tree(ProbSpec.DBHSTAGE3,ProbSpec.HEALTHY, ProbSpec.TREATED);
		
		Tree[][] treeList = {{tree1, new Tree(), new Tree()}, {new Tree(), new Tree(), tree2},
				{new Tree(), tree3, new Tree()}}; */
		
		
		Tree[][] treeList = new Tree[ProbSpec.HEIGHT][ProbSpec.WIDTH];
		
		State s = new State(treeList, ProbSpec.HEIGHT);
		
		
		for (int i=0; i < ProbSpec.HEIGHT; i++)
		{
			for (int j=0; j < ProbSpec.WIDTH; j++)
			{
				double rand = Math.random();
				Tree t;
				if (rand < ProbSpec.TREEDENSITY)
				{
					int ra = (int) Math.ceil(Math.random()*3);
					int st = (int) Math.ceil(Math.random()*4);
					t = new Tree(st, ra, ProbSpec.UNTREATED);	
				}
				else
				{
					t = new Tree();
				}
				s.setTree(t, i, j);
						
			}
		}
		
		for (int i=0; i <100; i++)
		{			
			Tree[][] list = s.getNextYear();
			s = new State(list,50);
		}
		
		s.printPlot(); 	

	}

}
