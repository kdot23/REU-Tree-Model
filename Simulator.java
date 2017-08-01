
public class Simulator {

	public static void main(String[] args) {

		Tree[][] treeList = new Tree[ProbSpec.HEIGHT][ProbSpec.WIDTH];
		
		State s = new State(treeList);
		
		
		for (int i=0; i < ProbSpec.HEIGHT; i++)
		{
			for (int j=0; j < ProbSpec.WIDTH; j++)
			{
				double rand = Math.random();
				Tree t;
				int st = 0;
				int ra = 0;
				if (rand < ProbSpec.TREEDENSITY)
				{
					double randTreeType = Math.random();
					
					for (int k=0; k<ProbSpec.pop2002cdf.length;k++){
							if (randTreeType < ProbSpec.pop2002cdf[k]){
								ra = k / ProbSpec.DBHSTAGE4 + 1;
								st = k % ProbSpec.DBHSTAGE4 + 1;
								break;
							}
						}	
				}
				t = new Tree(st, ra, ProbSpec.UNTREATED); 
				s.setTree(t, i, j);						
			}
		}
		
		s.setNumbers();
		s.getVector();
		
		for (int i=0; i < ProbSpec.YEARS; i++)
		{			
			Tree[][] list = s.getNextYear();
			s = new State(list);
			if (i % 100 == 0){
				s.getVector();
			}
		}
		
	s.getNumbers(); 
	s.getVector();

		}
	}
