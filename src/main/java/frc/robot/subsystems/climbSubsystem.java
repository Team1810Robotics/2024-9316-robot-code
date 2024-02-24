package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;


public class ClimbSubsystem extends SubsystemBase{

    private Relay climbMotor;
    
    public ClimbSubsystem() {
        climbMotor = new Relay(CLIMB_MOTOR_PORT);

    }

    public void climbUp() {
        climbMotor.set(Relay.Value.kForward);
    }

    public void climbDown() {
        climbMotor.set(Relay.Value.kReverse);
    }

    public void stop() {
        climbMotor.set(Relay.Value.kOff);
    }


}
