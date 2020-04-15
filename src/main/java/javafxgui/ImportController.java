package javafxgui;

import javafx.application.Platform;
import org.apache.log4j.helpers.SyslogQuietWriter;
import processManagers.ImportMovies;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            //if (view.)
            ImportMovies importProcess = new ImportMovies(view, view.getInputPath());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(importProcess);
            executorService.shutdown();
            //Platform.runLater(importProcess);
            //Thread importThread = new Thread(importProcess);
            //importThread.run();
            System.out.println("Helloooo!");
        });
    }
}
