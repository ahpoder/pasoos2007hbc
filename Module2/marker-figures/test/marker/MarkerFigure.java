package marker;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import javax.swing.*;

/** Marker Figure exercise. 
 * Move the AU logo using the mouse, and use the
 * FigureChange Observer pattern mechanisms of MiniDraw
 * to make the two arrows always point to the logo.
 * 
 * @author Henrik Bærbak Christensen (c) 2008
 */
public class MarkerFigure {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Move the logo with the mouse",
                               new MarkerFactory() );
    editor.open();
    
    ArrowImageFigure rightArrow = new ArrowImageFigure( "arrow-right", new Point(0, 200));
    ArrowImageFigure downArrow = new ArrowImageFigure( "arrow-down", new Point(200, 0));
    Figure logo = new ImageFigure( "au-logo", new Point(200, 200));
	logo.addFigureChangeListener(new LogoFigureChangeListener(rightArrow, downArrow));
	
    editor.setTool( new SelectionTool(editor) );

    editor.drawing().add(rightArrow);
    editor.drawing().add(downArrow);
    editor.drawing().add(logo);
	
	
  }
}

// decorator pattern to override the standard move
class ArrowImageFigure extends ImageFigure
{
	public ArrowImageFigure(java.lang.String name, java.awt.Point origin)
	{
		super(name, origin);
	}
	public void moveBy(int x, int y) { }
	public void myMoveBy(int x, int y) { basicMoveBy(x,y); invalidate(); }
}

// Observer pattern to receive notification when logo move
class LogoFigureChangeListener implements FigureChangeListener
{
	public LogoFigureChangeListener(ArrowImageFigure right, ArrowImageFigure down)
	{
		this.right = right;
		this.down = down;
	}
	public void figureChanged(FigureChangeEvent e)
	{
		Figure f = e.getFigure();
		java.awt.Rectangle r = f.displayBox();
		if (oldRectangle != null)
		{
			int movedX = r.x - down.displayBox().x;
			int movedY = r.y - right.displayBox().y;
			right.myMoveBy(0, movedY);
			down.myMoveBy(movedX, 0);
		}
		oldRectangle = r;
	}

	public void figureInvalidated(FigureChangeEvent e)
	{
	}

	public void figureRemoved(FigureChangeEvent e)
	{
	}

	public void figureRequestRemove(FigureChangeEvent e)
	{
	}

	public void figureRequestUpdate(FigureChangeEvent e) 
	{
	}
	private java.awt.Rectangle oldRectangle = null;
	private ArrowImageFigure right;
	private ArrowImageFigure down;
}

class MarkerFactory implements Factory {

  public DrawingView createDrawingView( DrawingEditor editor ) {
    return new StandardDrawingView( editor );
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}
