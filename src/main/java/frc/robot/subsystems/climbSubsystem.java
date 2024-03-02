package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;


public class ClimbSubsystem extends SubsystemBase{

    private Victor climbMotor;

    public ClimbSubsystem() {
        climbMotor = new Victor(ClimbConstants.CLIMB_MOTOR);

    }

    public void climbUp() {
        climbMotor.set(1);
    }

    public void climbDown() {
        climbMotor.set(-1);
    }

    public void stop() {
        climbMotor.stopMotor();
    }


}
