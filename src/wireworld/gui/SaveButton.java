package wireworld.gui;

import wireworld.system.FileManager;
import wireworld.system.WireWorld;

import java.awt.*;

public class SaveButton extends Button {

    public final static int width = 100;
    public final static int height = 100;

    SaveButton(GenerationWindow window, int x, int y) {
        super(window, x, y, width, height);
        super.addAction(ba);
        setText("Save", 36, 11, 64);
        defaultColor = new Color(255, 40, 40);
        hoverColor = new Color(255, 90, 90);
    }

    private final ButtonAction ba = new ButtonAction() {
        @Override
        public void clickAction() {
            String tmp_savedFilePath = FileManager.savedFilePath;
            String name_addition = "_generation" + WireWorld.generation.generationNumber + "snapshot.gen"; //np. blabla_generation23snapshot.gen

            if ( FileManager.savedFilePath != null ) {
                if ( FileManager.savedFilePath.endsWith(".gen") )
                    FileManager.savedFilePath = FileManager.savedFilePath.replaceFirst(".gen", name_addition);
                else
                    FileManager.savedFilePath = FileManager.savedFilePath.concat(name_addition);

                FileManager.saveGenerationToFile(WireWorld.generation);
                FileManager.savedFilePath = tmp_savedFilePath;
            } else {
                //przecież tutaj FileManager.savedFilePath jest null, więc nie można się do niego odnosić

                if ( FileManager.openedFilePath.endsWith(".gen") )
                    FileManager.savedFilePath = FileManager.openedFilePath.replaceFirst(".gen", name_addition);
                else
                    FileManager.savedFilePath = FileManager.openedFilePath.concat(name_addition);

                FileManager.saveGenerationToFile(WireWorld.generation);
                FileManager.savedFilePath = tmp_savedFilePath;

            }
        }
    };

}