/**
 * Author: David Riley & M. Allen
 *
 * Draws a simple graphical rectangle.
 */
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings( "serial" )
public class Rectangle extends JComponent
{
    /**
     * post: getX() == x and getY() == y and getWidth() == w and getHeight() ==
     * h and getBackground() == Color.black
     */
    public Rectangle( int x, int y, int w, int h )
    {
        super();
        setBounds( x, y, w, h );
        setBackground( Color.black );
    }
    
    /**
     * @param comp
     *            : JComponent to test for intersection
     * @return : true iff this intersects comp
     */
    public boolean intersects( JComponent comp )
    {
        java.awt.Rectangle r1, r2;
        r1 = this.getBounds( null );
        r2 = comp.getBounds( null );
        return r1.intersects( r2 );
    }
    
    /**
     * post: Draws a filled Rectangle and the upper left corner is ( getX(),
     * getY() ) and the rectangle's dimensions are getWidth() and getHeight()
     * and the rectangle's color is getBackground()
     */
    public void paint( Graphics g )
    {
        g.setColor( getBackground() );
        g.fillRect( 0, 0, getWidth() - 1, getHeight() - 1 );
        paintChildren( g );
    }
    
    
}
