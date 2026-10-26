import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditorPanel extends JPanel {

    private final JLabel heading = new JLabel(" ");
    private final JTextArea editor = new JTextArea();
    private boolean loading = false;   

    public EditorPanel(Runnable onEdit) {
        super(new BorderLayout());
        setBackground(Style.PAPER);

        heading.setFont(Style.font(Font.BOLD, 30));
        heading.setForeground(Style.INK);
        heading.setBorder(new EmptyBorder(34, 52, 6, 52));

        editor.setBackground(Style.PAPER);
        editor.setForeground(Style.INK);
        editor.setCaretColor(Style.ACCENT);
        editor.setSelectionColor(Style.SELECTION);
        editor.setSelectedTextColor(Style.INK);
        editor.setFont(Style.font(Font.PLAIN, 17));
        editor.setMargin(new Insets(10, 52, 30, 52));
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);
        editor.getDocument().addDocumentListener((OnChange) () -> {
            if (!loading) onEdit.run();
        });

        JScrollPane scroll = new JScrollPane(editor);
        Style.slim(scroll, Style.PAPER);

        add(heading, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void show(String name, String text) {
        loading = true;
        editor.setText(text);
        loading = false;
        editor.setEnabled(name != null);
        heading.setText(name == null ? "Create a note to get started" : name);
    }

    public String getText() {
        return editor.getText();
    }

    public void focusEditor() {
        editor.requestFocus();
    }
}