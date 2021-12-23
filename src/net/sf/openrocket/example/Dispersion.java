package net.sf.openrocket.example;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

import net.sf.openrocket.simulation.SimulationConditions;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.extension.AbstractSimulationExtension;
import net.sf.openrocket.simulation.extension.SimulationExtension;
import net.sf.openrocket.unit.DegreeUnit;
import net.sf.openrocket.unit.UnitGroup;
import net.sf.openrocket.util.BugException;
import net.sf.openrocket.simulation.listeners.example.CSVSaveListener;

/**
 * The actual simulation extension.  A new instance is created for
 * each simulation it is attached to.
 *
 * This class contains the configuration and is called before the
 * simulation is run.  It can do changes to the simulation, such
 * as add simulation listeners.
 *
 * All configuration should be stored in the config variable, so that
 * file storage will work automatically.
 */
public class Dispersion extends AbstractSimulationExtension {

    @Override
    public String getName() {
        return "Dispersion";
    }

    @Override
    public String getDescription() {
        // This description is shown when the user clicks the info-button on the extension
        return "The extension for dispersion";
    }


    @Override
    public void initialize(SimulationConditions conditions) throws SimulationException {
        int iteration = (int)getIteration();
        double windSpeedStep = getWindSpeedStep();
        double windDirStep = getWindDirStep();
        double windSpeedMax = getWindSpeedMax();


        double windSpeed =
            conditions.getSimulation().getOptions().getWindSpeedAverage();
        double windDir =
            conditions.getSimulation().getOptions().getWindDirection();
        double launchDir =
            conditions.getSimulation().getOptions().getLaunchRodDirection();


        String home = System.getProperty("user.home");
        String path = Paths.get(home, "Downloads").toString();

        conditions.getSimulationListenerList().add(new DispersionSimulationListener(path, windSpeed, windDir, launchDir));


        int windDirIteration =
            windDirStep == 0 ? 0 : (int)Math.round(2 * Math.PI / windDirStep);

        windDir += windDirStep;
        if (windDirIteration == 0 ||
            (iteration != 0 && iteration % windDirIteration == 0))
            windSpeed += windSpeedStep;

        conditions.getSimulation().getOptions().setWindSpeedAverage(windSpeed);
        conditions.getSimulation().getOptions().setWindDirection(windDir);

        setIteration((double)(iteration + 1));
    }

    public double getWindSpeedStep() {
        return config.getDouble("wind_speed_step", 0.0);
    }
    public double getWindSpeedMax() {
        return config.getDouble("wind_speed_max", 10.0);
    }
    public double getWindDirStep() {
        return config.getDouble("wind_dir_step", 0.0);
    }
    public double getIteration() {
        return config.getDouble("iteration", 0.0);
    }


    public void setWindSpeedStep(double step) {
        config.put("wind_speed_step", step);
        fireChangeEvent();
    }
    public void setWindSpeedMax(double max) {
        config.put("wind_speed_max", max);
        fireChangeEvent();
    }
    public void setWindDirStep(double step) {
        config.put("wind_dir_step", step);
        fireChangeEvent();
    }
    public void setIteration(double iteration) {
        config.put("iteration", iteration);
        fireChangeEvent();
    }
}
