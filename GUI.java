package fungus;

import simulation.Tree;
import simulation.State;
import simulation.ProbSpec;
import simulation.Simulator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener
{
    private final int POINT_SIZE = 10;
    private static final int HEIGHT = 40;
    private static final int WIDTH = 50;
    
    private int width, height;
    
    private JFrame window;
    private JButton start, reset;
    private Timer timer;
    private JPanel mainPanel;
    
    private State grove;
    private Rectangle[][] forest;
    
    public GUI( State st )
    {
    	grove = st;
        
        window = new JFrame( "Chestnut Grove Simulation" );
        window.setLocation( 20, 20 );
        window.getContentPane()
        .setLayout( new BoxLayout( window.getContentPane(),
                                  BoxLayout.Y_AXIS ) );
        window.getContentPane().setBackground( Color.white );
        // window.setResizable( false );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        width = WIDTH * POINT_SIZE;
        height = HEIGHT * POINT_SIZE;
        
        mainPanel = new JPanel();
        mainPanel.setLayout( null );
        mainPanel.setBackground( window.getContentPane().getBackground() );
        mainPanel.setPreferredSize( new Dimension( width, height ) );
        setStartGrid( grove.getPlot() );
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( window.getContentPane().getBackground() );
        buttonPanel.setPreferredSize( new Dimension( width, 40 ) );
        
        start = new JButton( "Start" );
        start.setSize( 70, 30 );
        start.addActionListener( this );
        start.setAlignmentX( Component.CENTER_ALIGNMENT );
        
        reset = new JButton( "Reset" );
        reset.setSize( 70, 30 );
        reset.addActionListener( this );
        reset.setAlignmentX( Component.CENTER_ALIGNMENT );
        
        buttonPanel.add( start );
        buttonPanel.add( reset );
        
        window.add( mainPanel );
        window.add( buttonPanel );
        window.pack();
        window.setResizable( false );
        window.setVisible( true );
        
        timer = new Timer( 100, this );
        
    }
    
    private void setStartGrid( Tree[][] grid )
    {
        forest = new Rectangle[grid.length][grid[0].length];
        for ( int row = 0; row < forest.length; row++ )
            for ( int col = 0; col < forest[0].length; col++ )
            {
                int xLoc = col * POINT_SIZE;
                int yLoc = row * POINT_SIZE;
                
                forest[row][col] = new Rectangle( xLoc, yLoc,
                                                 POINT_SIZE, POINT_SIZE );
                forest[row][col].setBackground( getCellColor( grid[row][col].getRating() ) );
                mainPanel.add( forest[row][col] );
            }
    }
    
    private Color getCellColor( int status )
    {
        Color c = Color.black;
        
        switch ( status )
        {
            case ProbSpec.V:
                c = Color.red;
                break;
            case ProbSpec.HEALTHY:
                c = Color.green;
                break;
            case ProbSpec.HV:
                c = Color.blue;
                break;
        }
        
        return c;
    }
    
    private void displayGrid( Tree[][] grid )
    {
        for ( int i = 0; i < grid.length; i++ )
            for ( int j = 0; j < grid[i].length; j++ )
            {
                forest[i][j].setBackground( getCellColor( grid[i][j].getRating() ) );
            }
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == start )
        {
            if ( start.getText().equals( "Start" ) )
            {
                start.setText( "Pause" );
                timer.start();
            }
            else
            {
                start.setText( "Start" );
                timer.stop();
            }
        }
        else if ( e.getSource() == reset )
        {
            timer.stop();
            start.setText( "Start" );
            grove = Simulator.generateStartPlot();
            displayGrid( grove.getPlot() );
        }
        else if ( e.getSource() == timer )
        {
            displayGrid( grove.getNextYear() );
        }
    }
    
}
