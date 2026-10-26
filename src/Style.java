import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public final class Style {

    public static final Color SIDEBAR = new Color(0xF2EFEA);
    public static final Color PAPER = new Color(0xFBFAF8);
    public static final Color LINE = new Color(0xE3DFD8);
    public static final Color INK = new Color(0x1D1D1F);
    public static final Color ACCENT = new Color(0xE8590C);
    public static final Color SELECTION = new Color(0xFFD9C2);
    public static final Color BUTTON_GREY = new Color(0xE8E4DC);
    public static final Color DANGER = new Color(0xC2362B);

    private Style() { }

    public static Font font(int style, float size) {
        for (String f : new String[]{"SF Pro Text", "Helvetica Neue", "Segoe UI", "Inter"})
            if (new Font(f, Font.PLAIN, 1).getFamily().equals(f)) return new Font(f, style, 1).deriveFont(size);
        return new Font(Font.SANS_SERIF, style, 1).deriveFont(size);
    }

    public static void slim(JScrollPane sp, Color bg) {
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.getViewport().setBackground(bg);
        sp.getVerticalScrollBar().setBackground(bg);
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            JButton none() { JButton b = new JButton(); b.setPreferredSize(new Dimension(0, 0)); return b; }
            protected JButton createDecreaseButton(int o) { return none(); }
            protected JButton createIncreaseButton(int o) { return none(); }
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) { }
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 45));
                g2.fillRoundRect(r.x + 1, r.y, r.width - 2, r.height, 8, 8);
            }
        });
    }
}