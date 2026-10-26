import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NoteRow extends DefaultListCellRenderer {

    private final NoteStorage storage;
    private boolean selected;

    public NoteRow(NoteStorage storage) {
        this.storage = storage;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object name, int index,
                                                  boolean isSelected, boolean hasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, name, index, isSelected, hasFocus);
        selected = isSelected;
        label.setOpaque(false);
        label.setForeground(Style.INK);
        label.setFont(Style.font(Font.PLAIN, 14));
        label.setBorder(new EmptyBorder(11, 24, 11, 24));
        label.setText("<html><b>" + name + "</b><br><span style='color:#8A8A8E;font-size:11px'>"
                + preview((String) name) + "</span></html>");
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (selected) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(10, 2, getWidth() - 20, getHeight() - 4, 16, 16);
            g2.setColor(Style.LINE);
            g2.drawRoundRect(10, 2, getWidth() - 21, getHeight() - 5, 16, 16);
            g2.dispose();
        }
        super.paintComponent(g);
    }

    private String preview(String name) {
        for (String line : storage.read(name).split("\n")) {
            if (line.isBlank()) continue;
            if (line.length() > 26) line = line.substring(0, 26) + "\u2026";
            return line.replace("&", "&amp;").replace("<", "&lt;");
        }
        return "Empty note";
    }
}