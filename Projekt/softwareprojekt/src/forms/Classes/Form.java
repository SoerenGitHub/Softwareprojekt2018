package forms.Classes;

import figures.Figure;
import Math.Calculation;
import javafx.scene.control.TextField;

/**
 * Author: Sören Schuba
 * Diese Abstrakte Klasse erleichtert die Verarbeitung im Backend:
 * Backend.handleInput()
 */
public abstract class Form {
    //Textfelder clearen
    public abstract void clearTextField();
    //Plain erstellen
    public abstract Figure save(int id, String name);
    //Anzeige eines bestehenden Objekts
    public abstract void showFigure(Figure selectedfigure);
}
