package jfxutils.images;

import javafx.scene.image.Image;

import java.lang.System.Logger;
import java.util.Optional;

/**
 * An implementation of the {@code ImageStorage} interface that associates PNG
 * images with a consecutive sequence of ints starting from 0. The images
 * representing the integers are loaded via the classloader of the class
 * specified.
 * 
 * <p>Consider, for example, the following code:
 * {@snippet :
 * ImageStorage<Integer> imageStorage = new OrdinalImageStorage(chess.game.PuzzleState.class,
 *     "none.png",
 *     "white_knight.png",
 *     "black_knight.png"
 * );
 * }
 * The code will search for {@code none.png}, {@code white_knight.png}, and
 * {@code black_knight.png} in the {@code chess.game} package, respectively.
 * Thus, for an Apache Maven project, the images should be placed under the
 * {@code src/main/resources/chess/game} directory.
 */
public class OrdinalImageStorage implements ImageStorage<Integer> {

    private static final Logger logger = System.getLogger(OrdinalImageStorage.class.getName());

    private final Image[] images;

    /**
     * Creates a {@code OrdinalImageStorage} instance to associate the image
     * resources specified with the integers 0, 1, 2, &hellip;.
     *
     * @param c the class whose classloader is used to load the images
     * @param resourceNames the resource names of the images
     */
    public OrdinalImageStorage(Class<?> c, String... resourceNames) {
        images = new Image[resourceNames.length];
        for (var i = 0; i < resourceNames.length; i++) {
            var url = String.format("%s/%s", c.getPackageName().replace('.', '/'), resourceNames[i]);
            try {
                images[i] = new Image(url);
                logger.log(Logger.Level.INFO, "Loaded image from {0}", url);
            } catch (Exception e) {
                // Failed to load image
                logger.log(Logger.Level.WARNING, "Failed to load image from {0}", url);
            }
        }
    }

    @Override
    public Optional<Image> get(Integer index) {
        return 0 <= index && index < images.length ? Optional.ofNullable(images[index]) : Optional.empty();
    }

}
