package simulation;
import java.io.*;


public class Simulator { //all trees untreated
	
	public static void main(String[] args) throws IOException{
		State grid;
		grid = generateStartPlot();
		
		for(int i=0; i < ProbSpec.YEARS; i++){			
			Tree[][] list = grid.getNextYear();
			grid = new State(list);
		}

		
		/*int testCase = 1;//new Integer(args[0]);
		File file = new File("test_" + testCase + ".txt");
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(out);    
        Writer w = new BufferedWriter(osw);
		
		
			
		//iterate 100 times, getting next state
		
		grid.setNumbers();
		w.write(grid.printVector()+"\n"); */
			
		
			/*
			if((i+1)%10 == 0){
				w.write(grid.printVector()+ "\n");
			}
		}
		
		w.write(""+ grid.getNumbers());
		w.close(); */
	}
	
	public static State generateStartPlot(){
		Tree[][] trees = new Tree[ProbSpec.HEIGHT][ProbSpec.WIDTH];
		State grid = new State(trees);
		
			for(int i=0; i<ProbSpec.HEIGHT; i++){
				for(int j=0; j<ProbSpec.WIDTH; j++){
					double rand = Math.random();
					Tree t;
					int st = 0;
					int ra = 0;
					if(rand < ProbSpec.TREEDENSITY){
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
					grid.setTree(t, i, j);
				}
			}
			return grid;
	}
}
