
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
		//s.printPlot();
		
		 for (int i=0; i < ProbSpec.YEARS; i++)
		{			
			Tree[][] list = s.getNextYear();
			//RUN TREAT FUNCTION
			//treatAll(list);
			s = new State(list);
			
			if (i % 10 == 0){
				//s.getVector();
				//s.printPlot();
				
			}
		}
		
	s.getNumbers(); 
	s.getVector();
	//s.printPlot();

		}
	
	//Treat all infected trees
	public static void treatAll(Tree[][] list){
		for (int k=0; k < ProbSpec.HEIGHT; k++){
			for (int l=0; l < ProbSpec.WIDTH; l++){
				if (list[k][l].getRating() == ProbSpec.V || list[k][l].getRating() == ProbSpec.HV){
					list[k][l].setTreatment(ProbSpec.TREATED);
					}
		  	}
		  }
	}
	
	//Treat all stage 3 or 4 infected trees
	public static void treatBig (Tree[][] list){
		for (int k=0; k < ProbSpec.HEIGHT; k++){
			for (int l=0; l < ProbSpec.WIDTH; l++){
				if ((list[k][l].getStage() == ProbSpec.DBHSTAGE3 || list[k][l].getStage() == ProbSpec.DBHSTAGE4) &&
						(list[k][l].getRating() == ProbSpec.V || list[k][l].getRating() == ProbSpec.HV)){
					list[k][l].setTreatment(ProbSpec.TREATED);
			}
		}
		} 
	}
	
	//Treat 50% of infected trees
	public static void treat50Per (Tree[][] list){
		for (int k=0; k < ProbSpec.HEIGHT; k++){
			for (int l=0; l < ProbSpec.WIDTH; l++){
				if (list[k][l].getRating() == ProbSpec.V || list[k][l].getRating() == ProbSpec.HV){
					if (Math.random() >= 0.5){
						list[k][l].setTreatment(ProbSpec.TREATED);
					}
					}
		  	}
		  }
		
	}
	
	
	}
