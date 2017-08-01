
public class Tree {
	private int rating;
	private int treatment; //0=no treatment, 1=treatment
	private int stage;
	
	public Tree(){
		rating = ProbSpec.DEAD;
	}
	
	public Tree(int stage, int rating, int treatment){ 
		this.rating = rating;
		this.stage = stage;
		this.treatment = treatment;
	}
		
	public void setRating(int r){
		rating = r;
	}
	public int getRating(){
		return rating;
	}
	public void setTreatment(int t){
		treatment = t;
	}
	public int getTreatment(){
		return treatment;
	}
	
	public void setStage(int s){
		stage = s;
	}
	
	public int getStage(){
		return stage;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Tree)){
			return false;
		}
		if(this==o){
			return true;
		}
		Tree ot = (Tree) o;
		return (this.rating == ot.rating && this.treatment == ot.treatment 
				&& this.stage == ot.stage);
	}
	
	public int hashCode(){
		return 31*rating + treatment; //need to update!!!!
	}
	
	public String toString(){
		String s;
		String t;
		String r;
		
		if (rating == ProbSpec.DEAD)
		{
			s = "    ";
		}
		else
		{
			
		if (treatment == ProbSpec.UNTREATED)
			t = "U";
		else
			t = "T";
		
		if (rating == ProbSpec.HEALTHY)
			r = " H";
		else {
			if (rating == ProbSpec.HV)
				r = "HV";
			else
				r = " V";
		}
		s =  r + stage + t;
		}
		return (s);
	}

}