import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;


public class MyButtonUI extends ButtonUI implements Serializable,
		MouseListener, KeyListener {

	private final static MyButtonUI m_buttonUI=new MyButtonUI();
	
	 private Font f=new Font("Sefir",Font.PLAIN,14);
	 protected Border m_borderRaised = UIManager.getBorder("Button.border");

	  protected Border m_borderLowered = UIManager
	      .getBorder("List.noFocusBorder");

	  protected Color m_backgroundNormal = UIManager
	      .getColor(Color.cyan);

	  protected Color m_backgroundPressed = UIManager
	      .getColor("Button.shadow");

	  protected Color m_foregroundNormal = UIManager
	      .getColor("Button.light");

	  protected Color m_foregroundActive = UIManager
	      .getColor("Button.select");
	  
	  protected Color m_focusBorder = UIManager.getColor("Button.focusBorder");

	  public static ComponentUI createUI(JComponent c) {
	    return m_buttonUI;
	  }
	  public void installUI(JComponent c) {
		    super.installUI(c);

		    c.addMouseListener(this);
		    c.addKeyListener(this);
		  }
	  public void uninstallUI(JComponent c) {
		    super.uninstallUI(c);
		    c.removeMouseListener(this);
		    c.removeKeyListener(this);
		  }
	@Override
	 public void keyPressed(KeyEvent e) {
	    int code = e.getKeyCode();
	    if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
	      JComponent c = (JComponent) e.getComponent();
	     // c.setBorder(m_borderLowered);
	      c.setBackground(m_backgroundPressed);
	    }
	  }

	@Override
	  public void keyReleased(KeyEvent e) {
	    int code = e.getKeyCode();
	    if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
	      JComponent c = (JComponent) e.getComponent();
	      //c.setBorder(m_borderRaised);
	      c.setBackground(m_backgroundNormal);
	    }
	  }

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	    JComponent c = (JComponent) e.getComponent();
	    c.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	   // c.repaint();
	  }

	@Override
	  public void mouseExited(MouseEvent e) {
	    JComponent c = (JComponent) e.getComponent();
	    c.setForeground(Color.BLACK);
	    c.setBorder(BorderFactory.createEmptyBorder());
	    System.out.println(m_foregroundNormal);
	    c.repaint();
	  }
	

	@Override
	  public void mousePressed(MouseEvent e) {
	    JComponent c = (JComponent) e.getComponent();
	   
	    c.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	    c.setBackground(m_backgroundPressed);
	    c.setForeground(Color.red);
	    c.repaint();
	   
	  }

	@Override
	  public void mouseReleased(MouseEvent e) {
	    JComponent c = (JComponent) e.getComponent();
	    c.setBackground(m_backgroundNormal);
	    c.setForeground(Color.BLACK);
	  }
	public void paint(Graphics g, JComponent comp)
	{
		
		AbstractButton but=(AbstractButton)comp;
		Dimension d=but.getSize();
		String text=but.getText();
		Graphics2D g2=(Graphics2D)g;
		g2.setFont(f);
		FontRenderContext context=g2.getFontRenderContext();
		Rectangle2D bounds=f.getStringBounds(text, context);
		int x=(int)(d.getWidth()-bounds.getWidth())/2;
		int y=(int)(d.getHeight()-bounds.getHeight())/2;
		int ascent=(int)-bounds.getY();
		int baseY=y+ascent;
		 g.setColor(but.getForeground());
		g2.drawString(text, x, baseY);
		
	}
	  public Dimension getPreferredSize(JComponent c) {
		  System.out.println(c);
		  AbstractButton but=(AbstractButton)c;
		  FontRenderContext context=c.getFontMetrics(f).getFontRenderContext();
		  Rectangle2D bounds=f.getStringBounds(but.getText(),context);
		  double width=bounds.getWidth();
		  double height=bounds.getHeight();
		    Dimension d =new Dimension((int)width,(int)height) ;
		    if (m_borderRaised != null) {
		      Insets ins = m_borderRaised.getBorderInsets(c);
		      d.setSize(d.width + ins.left + ins.right, d.height + ins.top
		          + ins.bottom);
		    }
		    return d;
		  }

}
