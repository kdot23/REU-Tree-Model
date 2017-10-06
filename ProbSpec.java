public final class ProbSpec 
{

	public static final int YEARS = 100;
	
	public static final int SITESIZE = 2; //sites are 2x2 meter squares
	public static final int DISTCLASS = 8; //distance class sizes are 8 meters
	
	public static final int UNTREATED = 0;
	public static final int TREATED = 1;
	public static final int CUTDOWN = 2;
	
	public static final int HEIGHT = 50;
	public static final int WIDTH = 50;
	
	//tree health ratings
	public static final int V = 1; //all virulent cankers
	public static final int HV = 2; //mix of virulent and hypovirulent cankers
	public static final int HEALTHY = 3; //healthy tree
	
	//public static final int MAX_ACTIONS = 400; //maximum number of actions
	
	//DBH Stages
	public static final int DEAD = 0;
	public static final int DBHSTAGE1 = 1; //DBH <= 1 cm
	public static final int DBHSTAGE2 = 2; //1 < DBH <= 10 cm
	public static final int DBHSTAGE3 = 3; //10 < DBH <= 20 cm
	public static final int DBHSTAGE4 = 4; //DBH > 20 cm
	
	public static final double TREEDENSITY = 0.01071632*SITESIZE*SITESIZE;
	//^ from rough density analysis on West Salem plot, likelihood of a tree being in a site
	public static final double[] BEGINRATING = {0.2356828, 0.4405286, 1}; 
	//^ CDF of percentages from tree ratings in 2002 (original values {0.2356828, 0.2048458, 0.5594714})
	public static final double[] BEGINSTAGE = {0.1059603, 0.5342164, 0.7373069, 1};
	// ^CDF of percentages from tree stages in 2002 (orig. values {0.1059603, 0.4282561, 0.2030905, 0.2626932})
	
	public static final double PROBOFSPOREVIRU = 1- Math.exp(-1.5);
	public static final double PROBOFSPOREHYPO = 1- Math.exp(-.75);

	//public static final double[] V2V = {2.065, 0.3438};
	//public static final double[] HV2V = {5.817, 3.998};
	//public static final double[] HV2HV = {2.0317, 0.4236};

	//equation is y= 0.1782ln(x) + 0.6476
	public static final double[] numInfCDF = {5.6117, 3.6341};
		
	//percentage of infections from hypovirulent trees that are hypovirulent
	public static final double perHV2HV = 0.65;
	
	public static final double[] VDIST = {4.695, 1.777};
	public static final double[] HVDIST = {7.153, 4.049};
	
	//Reproduction
	public static final double[][] REPRODUCTION = {{0, 0.02, 0.32, 3.69},
													{0, 0.016, 0.78, 2.43},
													{0, 0.001, 0.03, 7.74}};
	
	//Population dynamics of West Salem in 2002.
	//rows are virulent, hypovirulent and healthy. Each entry corresponds to stage
	public static final double[] pop2002cdf = {0.03311258, 0.09050773, 0.15011038, 0.23399558, 0.23399558, 0.27373068, 
												0.31567329, 0.43929360, 0.51214128, 0.84326711, 0.94481236, 1.00000000};
	
	//Tree population
	//public static final int HEALTHYPOP = 2; //healthy tree population
	//public static final int RECOVERING = 1; //recovering tree pop.
	//public static final int EPIDEMIC = 0; //epidemic tree pop.
	
	public static double[][] newRatingCDF = {{0.69714286, 1},
												{0.20588235, 1},
												{0.52651515, 1},
												{0.09884467, 1}};
	//each array is p(death), p(s0) in n+1,...p(s4) in n+1. Arrays are clustered 5 in a row correspond to V,HV & H
	public static double[][] newStageCDF =  {{0.066, 0.962, 0.999, 0.999, 1}, 
												{0.006, 0.09, 0.99, 1, 1},
												{0.05, 0.05,0.3,	0.96,1},
												{0.021, 0.021, 0.101, 0.111, 1},
												{0.159,	0.996,	1,	1,	1},
												{0,	0.09,	0.98,	1,	1},
												{0.01,	0.01,	0.05,	0.96,	1},
												{0.001,	0.001,	0.006,	0.056,	1},
												{0.167,	0.994,	1,	1,	1},
												{0,	0.07,	0.99,	1,	1},
												{0,	0,	0,	0.95,	1},
												{0.013,	0.013,	0.013,	0.013,	1}};
}