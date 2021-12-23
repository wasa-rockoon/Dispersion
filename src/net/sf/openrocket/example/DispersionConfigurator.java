package net.sf.openrocket.example;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.gui.SpinnerEditor;
import net.sf.openrocket.gui.adaptors.DoubleModel;
import net.sf.openrocket.gui.components.BasicSlider;
import net.sf.openrocket.gui.components.UnitSelector;
import net.sf.openrocket.plugin.Plugin;
import net.sf.openrocket.simulation.extension.AbstractSwingSimulationExtensionConfigurator;
import net.sf.openrocket.unit.UnitGroup;

/**
 * The Swing configuration dialog for the extension.
 *
 * The abstract implementation provides a ready JPanel using MigLayout
 * to which you can build the dialog.
 */
@Plugin
public class DispersionConfigurator
    extends AbstractSwingSimulationExtensionConfigurator<Dispersion> {

    public DispersionConfigurator() {
        super(Dispersion.class);
    }

    @Override
    protected JComponent getConfigurationComponent(Dispersion extension, Simulation simulation, JPanel panel) {

        // Reference altitude

        panel.add(new JLabel("Wind speed step:"));

        DoubleModel windSpeedModel =
            new DoubleModel(extension, "WindSpeedStep",
                            UnitGroup.UNITS_VELOCITY, 0.0);

        JSpinner windSpeedSpin = new JSpinner(windSpeedModel.getSpinnerModel());
        windSpeedSpin.setEditor(new SpinnerEditor(windSpeedSpin));
        panel.add(windSpeedSpin, "w 75lp!");

        UnitSelector windSpeedUnit = new UnitSelector(windSpeedModel);
        panel.add(windSpeedUnit, "w 25");

        BasicSlider windSpeedSlider =
            new BasicSlider(windSpeedModel.getSliderModel(0.0, 10.0));
        panel.add(windSpeedSlider, "w 75lp, wrap");


        panel.add(new JLabel("Wind direction step:"));

        DoubleModel windDirModel =
            new DoubleModel(extension, "WindDirStep",
                            UnitGroup.UNITS_ANGLE, 0.0);

        JSpinner windDirSpin = new JSpinner(windDirModel.getSpinnerModel());
        windDirSpin.setEditor(new SpinnerEditor(windDirSpin));
        panel.add(windDirSpin, "w 75lp!");

        UnitSelector windDirUnit = new UnitSelector(windDirModel);
        panel.add(windDirUnit, "w 25");

        BasicSlider windDirSlider =
            new BasicSlider(windDirModel.getSliderModel(0.0, 2 * Math.PI));
        panel.add(windDirSlider, "w 75lp, wrap");



        panel.add(new JLabel("Iteration count:"));

        DoubleModel iterationModel =
            new DoubleModel(extension, "Iteration",
                            UnitGroup.UNITS_NONE, 0);

        JSpinner iterationSpin = new JSpinner(iterationModel.getSpinnerModel());
        iterationSpin.setEditor(new SpinnerEditor(iterationSpin));
        panel.add(iterationSpin, "w 75lp!");

        UnitSelector iterationUnit = new UnitSelector(iterationModel);
        panel.add(iterationUnit, "w 25");

        return panel;
    }
}
