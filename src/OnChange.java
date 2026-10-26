import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface OnChange extends DocumentListener {
    void changed();

    default void insertUpdate(DocumentEvent e) { changed(); }
    default void removeUpdate(DocumentEvent e) { changed(); }
    default void changedUpdate(DocumentEvent e) { changed(); }
}