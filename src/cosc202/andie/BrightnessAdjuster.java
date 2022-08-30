package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to change the level of brightness of an image
 * </p>
 * 
 * <p>
 * The images produced by this operation have a set amount added to all of their red, green, and blue values.
 * This causes either all of the pixels to be brighter or all to be dimmer
 * </p>
 * 
 *
 * 
 * @author Paddy Borthwick
 * @version 1.0
 */

public class BrightnessAdjuster implements ImageOperation, java.io.Serializable {

    private float brightnessFactor;

    /**
     * <p>
     * Create a new BrightnessAdjuster operation with the user inputed brighness.
     * </p>
     */
    BrightnessAdjuster(float brightnessFactor) {
        this.brightnessFactor = brightnessFactor;
    }

    /**
     * <p>
     * Create a new BrightnessAdjuster operation with a default value.
     * </p>
     */
    BrightnessAdjuster() {
        this(1.0f);
    }

    /**
     * <p>
     * Apply the brighness change to an image
     * <p>
     * 
     * This method uses the Java RescaleOp class to add this set amount with no regard for what the current pixel values are.
     * It has built in limitations where the R, G, and B values cannot go over 255
     * 
     * @ param input The BufferedImage we want to change
     * @ retiurn The Image with a brighness change.
     */ 
    public BufferedImage apply(BufferedImage input) {

        RescaleOp rescale = new RescaleOp(1.0f, brightnessFactor, null);
        input = rescale.filter(input, null);
        return input;

    }

}
