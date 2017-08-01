import java.util.ArrayList;
import java.util.Random;

public class State {
	//location class
		private class Location {
			int x, y;
			public Location(int x_, int y_){
				x = x_;
				y = y_;
			}
			public int getx(){
				return x;
			}
			public int gety(){
				return y;
			}
		}

		private int height = ProbSpec.HEIGHT;
		private int width = ProbSpec.WIDTH;
		
		private Tree[][] plot = new Tree[height][width];
		/*private int totalValue = -1; */
		
		public State(Tree[][] plot, int dimension)
		{
			this.plot = plot;
			height = dimension;
			width = dimension;
		}
		
		public int getHeight(){
			return height;
		}//end getHeight
		
		public int getWidth(){
			return width;
		}//end getWidth
		
		public void setTree(Tree t, int r, int c){
			plot[r][c] = t;
		}
		
		/*public int getValue(){ //returns the total value of the plot
			if(totalValue < 0){
				int sum = 0;
				for(int i=0; i<height; i++){
					for(int j=0; j<width; j++){
						sum += plot[i][j].getRating();
					}
				}
				totalValue = sum;
			}
			return totalValue;
		}//end getValue */
		
		public Tree[][] getPlot(){
			return plot;
		}
		
		public boolean equals(Object o){
			if(!(o instanceof State)){
				return false;
			}
			if(this==o){
				return true;
			}
			State os = (State) o;
			for (int row=0; row<plot.length; row++){
				for(int col=0; col<plot[row].length; col++){
					if(!plot[row][col].equals(os.plot[row][col])){
						return false;
					}
				}	
			}
			return true;
		}
		
		public int hashCode(){
			int code = 0;
			for (int row=0; row<plot.length; row++){
				for(int col=0; col<plot[row].length; col++){
					code += plot[row][col].hashCode();
					code *= 31;
				}
			}
			return code;
		}
		
		public static void shuffle(ArrayList<Location> a) {
	        int n = a.size();
	        for (int i = 0; i < n; i++) {
	            // choose index uniformly in [0, i]
	            int r = (int) (Math.random() * (i + 1));
	            Location swap = a.get(r);
	            a.set(r, a.get(i));
	            a.set(i, swap);
	        }
	    }
		
		
		/*This function takes the probability of a tree sporing, the coefficients of 
		the number of trees it could infect and the coefficients of the distance distribution,
		and the location of the tree. It then infects the trees around it based on random generated 
		numbers */
		public void infect(int blightType, double SporeProb, double[] infectCoef, double[] distCoef, int x, int y,
							Tree[][] p){
			double randSpore = Math.random();
			if(randSpore < SporeProb){ 
				double randInfections = Math.random();
				int numInfections = (int) Math.round(Math.exp(infectCoef[0]*randInfections - infectCoef[1]));
				for(int v=0; v <= numInfections; v++){
					double randDistance = Math.random();
					//Multiplied by 8 because a distance class is 8m 
					double distance = Math.round(Math.exp(distCoef[0]*randDistance - distCoef[1])*ProbSpec.DISTCLASS);
					//Divide by ProbSpec.SITESIZE because the plots are ProbSpec.SITESIZE meters
					int d = (int) Math.floor(distance / ProbSpec.SITESIZE);

					ArrayList<Location> possibleInfections = new ArrayList<Location>();
	 
					for(int i = x-d; i <= x+d; i++){
						if(Math.abs(i-x) ==  d){ //first or last row
							for(int j = y-d; j <= y+d; j++){
								try{
									if(p[i][j].getRating() == ProbSpec.HEALTHY || 
										(blightType == ProbSpec.HV && p[i][j].getRating() == ProbSpec.V)){
										
										possibleInfections.add(new Location(i,j));
									}
								}
								catch(ArrayIndexOutOfBoundsException e){}
							}
						}
						else{
							try{
								if(p[i][y-d].getRating() == ProbSpec.HEALTHY || 
										(blightType == ProbSpec.HV && p[i][y-d].getRating() == ProbSpec.V)){
									possibleInfections.add(new Location(i,y-d));
								}
								if(p[i][y+d].getRating() == ProbSpec.HEALTHY || 
										(blightType == ProbSpec.HV && p[i][y+d].getRating() == ProbSpec.V)){
									possibleInfections.add(new Location(i,y+d));
								}
							}
							catch(ArrayIndexOutOfBoundsException e){}
						}
					}

					if(possibleInfections.size() != 0){
						int luckyIndex = (int) Math.floor(Math.random()*possibleInfections.size());
						int a = (possibleInfections.get(luckyIndex)).getx();
						int b = (possibleInfections.get(luckyIndex)).gety();
						p[a][b].setRating(blightType);
					}
				}
			} 
		}
		
		
		public Tree[][] getNextYear(){
			Tree[][] nextYear = new Tree[height][width];
			nextYear = getPlot();
			
			ArrayList<Location> list = new ArrayList<Location>();
			for(int i=0; i<height; i++){
				for(int j=0; j<width; j++){
					Location l = new Location(i,j);
					list.add(l);
				}
			}
			shuffle(list);
			
			
			//iterate over the random ordering, generating the year's events
			for(int i=0; i<list.size(); i++){
				int a,b;
				a = (list.get(i)).getx();
				b = (list.get(i)).gety();
				
				if(nextYear[a][b].getStage()!=ProbSpec.DEAD){
					//Set new stage, simulating growth
					double randStage = Math.random();
				
					int rowNum = (nextYear[a][b].getRating()-1)*(ProbSpec.DBHSTAGE4 +1) 
									+ nextYear[a][b].getStage();
					
					for(int j=0; j<(ProbSpec.DBHSTAGE4+1); j++){
						if(randStage < ProbSpec.newStageCDF[rowNum][j]){
							nextYear[a][b].setStage(j);
							break;
						}
					}
					
				
				
					//Set new rating, simulating changes in tree health
					if(nextYear[a][b].getStage()==ProbSpec.DEAD){//if tree is dead, set rating and treatment to 0 also
						nextYear[a][b].setRating(ProbSpec.DEAD);
						nextYear[a][b].setTreatment(ProbSpec.UNTREATED);
					}
					else{
						double randRating = Math.random();
					
						int rowNum2 = (nextYear[a][b].getTreatment())*(ProbSpec.HEALTHY -1)
										+ (nextYear[a][b].getRating()-1);
						for(int j=0; j<(ProbSpec.HEALTHY-1); j++){
							if(randRating < ProbSpec.newStageCDF[rowNum2][j]){
								nextYear[a][b].setRating(j+1);
								break;
							}
						}
					}
				
					//Spread of fungus, possibly infecting new trees with hypovirulent/virulent blight
					if (nextYear[a][b].getRating() == ProbSpec.V)
						infect(ProbSpec.V, ProbSpec.PROBOFSPOREVIRU, ProbSpec.V2V, ProbSpec.VDIST, a, b, nextYear);
					if (nextYear[a][b].getRating() == ProbSpec.HV){
						infect(ProbSpec.V, ProbSpec.PROBOFSPOREHYPO, ProbSpec.HV2V, ProbSpec.VDIST, a, b, nextYear);
						infect(ProbSpec.HV, ProbSpec.PROBOFSPOREHYPO, ProbSpec.HV2HV, ProbSpec.HVDIST, a, b, nextYear);
					}
				
					//Reproduction of trees into Stage 1 trees
					double reproduction = ProbSpec.REPRODUCTION[nextYear[a][b].getRating()-1][nextYear[a][b].getStage()-1];
				
					Random r = new Random();
					double L = Math.exp(-reproduction);
					int k = 0;
					double p = 1.0;
					do {
				    	p = p * r.nextDouble();
				    	k++;
					} while (p > L);
					k = k - 1; //k is the random Poisson
				
					ArrayList<Location> possibleSites = new ArrayList<Location>();
					possibleSites = list; //instantiate with all possible sites
					shuffle(possibleSites); //randomize locations
					while(k > 0 && possibleSites.size() > 0){
						int c,d;
						c = (possibleSites.get(0)).getx();
						d = (possibleSites.get(0)).gety();
					
						if(nextYear[c][d].getStage() == ProbSpec.DEAD){ //if site is empty/dead
							nextYear[c][d].setStage(ProbSpec.DBHSTAGE1); //Creates a healthy, stage 1 seedling
							nextYear[c][d].setRating(ProbSpec.HEALTHY); 
							k--;
						}
						possibleSites.remove(0);
					}
				}
			}
			return nextYear;
		}
		
		public void printPlot()
		{
			for (int i=0; i < height; i++)
			{
				String s = "";
				for (int j=0; j < width; j++)
				{
					s += plot[i][j].toString() + "   ";
				}
				System.out.println(s);
			}			
		}

}