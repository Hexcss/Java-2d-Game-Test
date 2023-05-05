package Utils.TileManager.JoiseNoiseGenerator;

import Utils.Types.Interfaces.INoiseGenerator;
import com.sudoplay.joise.module.ModuleFractal;

/**
 * This class is a concrete implementation of the INoiseGenerator interface.
 * It uses the Joise library's ModuleFractal class to generate noise.
 */
public class JoiseNoiseGenerator implements INoiseGenerator {
    private final ModuleFractal noiseGenerator;

    /**
     * The constructor initializes a ModuleFractal instance with predefined parameters.
     * The fractal type is set to MULTI, the number of octaves is set to 3, and the frequency is set to 0.1.
     */
    public JoiseNoiseGenerator() {
        noiseGenerator = new ModuleFractal();
        noiseGenerator.setType(ModuleFractal.FractalType.MULTI);
        noiseGenerator.setNumOctaves(3);
        noiseGenerator.setFrequency(0.1);
    }

    /**
     * Returns the noise value at the specified coordinates.
     * @param x The x-coordinate in the noise space.
     * @param y The y-coordinate in the noise space.
     * @param z The z-coordinate in the noise space.
     * @return The noise value at the specified coordinates.
     */
    @Override
    public double getNoise(double x, double y, double z) {
        return noiseGenerator.get(x, y, z);
    }
}
