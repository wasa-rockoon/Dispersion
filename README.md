An OpenRocket extension for calculating dispersion
==============================================
This extension automaticary iterates simulation parameter and outputs some values into csv. Currently it supports iterating the wind speed and direction. 

Usage
-------------------

1. [Download Dispersion.jar from here](https://github.com/wasa-rockoon/Dispersion/releases) (or build by running `ant`).
2. Copy this to the OpenRocket plugin directory (`~/.openrocket/Plugins/` on Linux, `~/Library/Application Support/OpenRocket/Plugins/` on Mac, `...\Application Data\OpenRocket\ThrustCurves\Plugins\` on Windows).
3. Restart OpenRocket.

You can add and configure it in the simulation edit dialog under Simulation options -> Add extension -> Flight -> Dispersion.

## How to calculate dispersion

1. Configure step value (0 for no iteration).
2. Press "Simulate&Plot" button and run simulation.
3. (The extension writes simulated values into ~/Downloads/simulation-xxx.csv.)
4. Press "<< Edit" button.
5. (Simulation parameters are automatically iterated).
6. Go to 2. and repeat such steps (need to click your mouse twice per one simulation) until you collect enough data.
