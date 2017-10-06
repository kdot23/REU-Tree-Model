
public class Main {

	    private GUI gui;
	    private State plot;
	    
	    public static void main( String[] args )
	    {
	        Main m = new Main();
	        m.makeSimulator();
	    }
	    
	    private void makeSimulator()
	    {
	        plot = Simulator.generateStartPlot();
	        gui = new GUI( plot );
	    }
}


