package jfxutils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Provides various utility methods.
 */
public class JFXUtils {

    // Private constructor to prevent instantiation
    private JFXUtils() {}

    /**
     * {@return the stage of the scene to which the specified node belongs}
     *
     * @param node the node whose stage is to be returned
     * @throws NullPointerException if the node is not currently attached to a
     *                              scene
     */
    public static Stage getWindow(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    /**
     * Loads the FXML document specified using the class loader of the provided
     * class and displays it on the provided stage. The method accepts a
     * {@link Consumer} object to perform an action on the controller of the
     * FXML document to be loaded. For example, it can be used to pass data to
     * the controller.
     *
     * @param stage the stage on which the scene loaded is shown
     * @param context the class whose class loader is used to locate the FXML
     *                document
     * @param name the resource name of the FXML document
     * @param controllerConfigurator represents an action to be performed on the
     *                               controller of the FXML document to be
     *                               loaded, may be {@code null}
     * @param <T> the type of the controller class of the FXML document to be
     *           loaded
     * @throws IOException if any I/O error occurs
     */
    public static <T> void loadFXML(Stage stage, Class<?> context, String name, Consumer<T> controllerConfigurator) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(context.getResource(name));
        Parent root = fxmlLoader.load();
        if (controllerConfigurator != null) {
            T controller = fxmlLoader.getController();
            controllerConfigurator.accept(controller);
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

}
