import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Sidebar extends JPanel {

    public final DefaultListModel<String> names = new DefaultListModel<>();
    public final JList<String> list = new JList<>(names);
    public final Pill newButton = new Pill("+ New", Style.INK, Color.WHITE);
    public final Pill deleteButton = new Pill("Delete", Style.BUTTON_GREY, Style.DANGER);

    public Sidebar(NoteStorage storage) {
        super(new BorderLayout());
        storage.names().forEach(names::addElement);

        setBackground(Style.SIDEBAR);
        setBorder(new MatteBorder(0, 0, 0, 1, Style.LINE));   
        setPreferredSize(new Dimension(270, 0));

        JLabel title = new JLabel("Notes");
        title.setFont(Style.font(Font.BOLD, 26));
        title.setForeground(Style.INK);
        title.setBorder(new EmptyBorder(26, 24, 14, 24));

        list.setBackground(Style.SIDEBAR);
        list.setForeground(Style.INK);
        list.setCellRenderer(new NoteRow(storage));
        JScrollPane scroll = new JScrollPane(list);
        Style.slim(scroll, Style.SIDEBAR);

        JPanel buttons = new JPanel(new BorderLayout(8, 0));
        buttons.setBackground(Style.SIDEBAR);
        buttons.setBorder(new EmptyBorder(14, 16, 18, 16));
        buttons.add(newButton, BorderLayout.CENTER);
        buttons.add(deleteButton, BorderLayout.EAST);

        add(title, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }
}