import javax.swing.*;
import java.awt.BorderLayout;

public class SimpleNotes extends JFrame {

    private final NoteStorage storage = new NoteStorage();
    private final Sidebar sidebar = new Sidebar(storage);
    private final EditorPanel editor = new EditorPanel(this::saveSelected);

    public SimpleNotes() {
        super("Notes");

        sidebar.list.addListSelectionListener(e -> openSelected());
        sidebar.newButton.addActionListener(e -> newNote());
        sidebar.deleteButton.addActionListener(e -> deleteNote());

        add(sidebar, BorderLayout.WEST);
        add(editor, BorderLayout.CENTER);

        if (!sidebar.names.isEmpty()) sidebar.list.setSelectedIndex(0); else openSelected();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private void openSelected() {
        String name = sidebar.list.getSelectedValue();
        editor.show(name, name == null ? "" : storage.read(name));
    }

    private void saveSelected() {
        String name = sidebar.list.getSelectedValue();
        if (name == null) return;
        storage.write(name, editor.getText());
        sidebar.list.repaint();   
    }

    private void newNote() {
        String name = JOptionPane.showInputDialog(this, "Note name:");
        if (name == null) return;
        name = name.replaceAll("[^\\w \\-]", "").trim();   
        if (name.isEmpty() || storage.exists(name)) return;
        storage.write(name, "");
        sidebar.names.addElement(name);
        sidebar.list.setSelectedValue(name, true);
        editor.focusEditor();
    }

    private void deleteNote() {
        String name = sidebar.list.getSelectedValue();
        if (name == null) return;
        storage.delete(name);
        sidebar.names.removeElement(name);
        openSelected();
    }

    public static void main(String[] args) throws Exception {
//        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> new SimpleNotes().setVisible(true));
    }
}