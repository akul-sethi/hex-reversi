package cs3500.reversi.view;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class AddHint implements BoardShape {

  private final BoardShape delegate;
  private final int num;
  public AddHint(BoardShape delegate, int num) {
    this.delegate = delegate;
    this.num = num;
  }
  @Override
  public void draw(Graphics2D g2) {
    this.delegate.draw(g2);
    Color old = g2.getColor();
    g2.setColor(Color.BLACK);
    double x = delegate.getBounds2D().getCenterX();
    double y = delegate.getBounds2D().getCenterY();
    g2.setFont(new Font("SansSerif", Font.BOLD, 15));
    g2.drawString("" + num, (int)x, (int)y);
    g2.setColor(old);
  }

  @Override
  public Rectangle getBounds() {
    return delegate.getBounds();
  }

  @Override
  public Rectangle2D getBounds2D() {
    return delegate.getBounds2D();
  }

  @Override
  public boolean contains(double x, double y) {
    return delegate.contains(x, y);
  }

  @Override
  public boolean contains(Point2D p) {
    return delegate.contains(p);
  }

  @Override
  public boolean intersects(double x, double y, double w, double h) {
    return delegate.intersects(x, y, w, h);
  }

  @Override
  public boolean intersects(Rectangle2D r) {
    return delegate.intersects(r);
  }

  @Override
  public boolean contains(double x, double y, double w, double h) {
    return delegate.contains(x, y, w, h);
  }

  @Override
  public boolean contains(Rectangle2D r) {
    return delegate.contains(r);
  }

  @Override
  public PathIterator getPathIterator(AffineTransform at) {
    return delegate.getPathIterator(at);
  }

  @Override
  public PathIterator getPathIterator(AffineTransform at, double flatness) {
    return delegate.getPathIterator(at, flatness);
  }
}
