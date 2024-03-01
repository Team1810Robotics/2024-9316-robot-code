package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChurroConstants;

public class ChurroSubsystem extends SubsystemBase {
    
    private Victor ampLinearActuator;

    public ChurroSubsystem() {
       ampLinearActuator = new  Victor(ChurroConstants.AMP_BAR);
    }

    public void churroUp() {
        ampLinearActuator.set(.5);
    }

    public void churroDown() {
        ampLinearActuator.set(-.5);
    }

    public void churroStop() {
        ampLinearActuator.stopMotor();
    }

}

