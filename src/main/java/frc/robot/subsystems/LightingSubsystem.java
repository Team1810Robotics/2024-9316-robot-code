package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LightingConstants;


public class LightingSubsystem extends SubsystemBase {
	
	private Spark blinkin;

	

	 public LightingSubsystem() {
	 	blinkin = new Spark(LightingConstants.LED_LIGHTING);
	 }
		
	/**
	 * if the robot is not in hatMode and in normal drive, the LED turns solid white (0.93)
	 */
	public void lightsGreen(){
		blinkin.set(LightingConstants.LED_GREEN);
	}

    public void lightsOff() {
		blinkin.set(0.99); 
	}

	public void lightsRainbow(){
		blinkin.set(-0.99);
	}

	public void lightsRed() {
		blinkin.set(.61);
	}

	public void lightsBlue() {
		blinkin.set(0.87);
	}

	public void lightsPurple() {
		blinkin.set(LightingConstants.LED_PURPLE);
	}

	public void allianceLights() {
		DriverStation.Alliance redAlliance = DriverStation.Alliance.Red;
		DriverStation.Alliance blueAlliance = DriverStation.Alliance.Blue;

		DriverStation.Alliance ourAlliance = DriverStation.getAlliance().get();

		if (ourAlliance == redAlliance) {
			lightsRed();
		} else if (ourAlliance == blueAlliance) {
			lightsBlue();
		} else {
			lightsRainbow();
		}
	}
}

