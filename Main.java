package fungus;

public class Main
{
    private GUI gui;
    private simulation.State plot;
    
    public static void main( String[] args )
    {
        Main m = new Main();
        m.makeSimulator();
    }
    
    private void makeSimulator()
    {
        plot = simulation.Simulator.generateStartPlot();
        gui = new GUI( plot );
    }
}
