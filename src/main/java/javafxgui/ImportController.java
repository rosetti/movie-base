package javafxgui;

import processManagers.ImportMovies;

/**
 * Created by Vin on 29/11/2017.
 */
public class ImportController {

    ImportView view;
    ImportModel model;

    public ImportController(ImportView view, ImportModel model) {
        this.view = view;
        this.model = model;

        setImportAction();
    }

    public void show() {
        view.show();
    }

    private void setImportAction() {
        view.setImportAction(e -> {
            System.out.println("Starting Import Process");
            ImportMovies importProcess = new ImportMovies(view, view.getInputPath());
            Thread importThread = new Thread(importProcess);
            importThread.run();
        });
    }
}
