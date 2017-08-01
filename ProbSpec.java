
import java.io.*;
import java.util.*;

public class ProbSpec {
	
	public static final int YEARS = 100; //number of years to simulate for
	
	public static final int SITESIZE = 2; //sites are 2x2 meter squares
	public static final int DISTCLASS = 8; //distance class sizes are 8 meters
	
	public static final double TREEDENSITY = 0.0107*SITESIZE*SITESIZE;
	
	
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
	
	public static final double PROBOFSPOREVIRU = 1- Math.exp(-1.5);
	public static final double PROBOFSPOREHYPO = 1- Math.exp(-.75);

	public static final double[] V2V = {2.065, 0.3438};
	public static final double[] HV2V = {5.817, 3.998};
	public static final double[] HV2HV = {2.0317, 0.4236};

	public static final double[] VDIST = {4.695, 1.777};
	public static final double[] HVDIST = {7.153, 4.049};
	
	//Reproduction
	public static final double[][] REPRODUCTION = {{0, 0.02, 0.32, 3.69},
													{0, 0.016, 0.78, 2.43},
													{0, 0.001, 0.03, 7.74}};
	
	public static double[][] newRatingCDF = {{0.69714286,	1},
												{0.20588235,	1},
												{0.52651515,	1},
												{0.09884467,	1}};
	
	
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
			
			
	
	/*
	 * ; = new double[(TREATED+1)*(HEALTHY-1)][(HEALTHY-1)];public ProbSpec(){
	 * //new double[(HEALTHY)*(DBHSTAGE4)][DBHSTAGE4+1];
	 
	
		String fileNameDefined = "newRatingCDF.csv";
        File file1 = new File(fileNameDefined);
               
        try{
            Scanner inputStream = new Scanner(file1);
            // hashNext() loops line-by-line
            while(inputStream.hasNextDouble()){
            	System.out.println(inputStream.nextDouble());
                for(int i=0; i<TREATED+1; i++){
                	for(int j=0; j<(HEALTHY-1); j++){
                		for(int k=0; k<(HEALTHY-1); k++){
                			newRatingCDF[i*(HEALTHY-1)+j][k] = inputStream.nextDouble();
                   		}
                	}
                }
            }
            // after loop, close scanner
            inputStream.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
		
        fileNameDefined = "newStageCDF.csv";
        File file2 = new File(fileNameDefined);
            
        for(int i=0; i<HEALTHY; i++){
        	//a dead tree stays dead
        	newStageCDF[i*(DBHSTAGE4+1)+DEAD][DEAD] = 1;
        	newStageCDF[i*(DBHSTAGE4+1)+DEAD][DBHSTAGE1] = 1;
        	newStageCDF[i*(DBHSTAGE4+1)+DEAD][DBHSTAGE2] = 1;
        	newStageCDF[i*(DBHSTAGE4+1)+DEAD][DBHSTAGE3] = 1;
        	newStageCDF[i*(DBHSTAGE4+1)+DEAD][DBHSTAGE4] = 1;
        } 
        
        try{
            Scanner inputStream = new Scanner(file2);
            while(inputStream.hasNextDouble()){
                for(int i=0; i<HEALTHY; i++){
                	for(int j=0; j<DBHSTAGE4; j++){
                		for(int k=0; k<DBHSTAGE4+1; k++){
                			newStageCDF[i*(DBHSTAGE4)+j][k] = inputStream.nextDouble();
                		}
                	}
                }
            }
            // after loop, close scanner
            inputStream.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }       
	}//close constructor

public double[][] getStageCDF(){
	return newStageCDF;
}*/
}
