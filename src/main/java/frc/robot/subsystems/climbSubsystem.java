package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;


public class climbSubsystem extends SubsystemBase{

    private Victor climbLeftMotor;
    private Victor climbRightMotor;
    private Victor climbTensionMotor;

    public climbSubsystem() {
        climbLeftMotor = new Victor(ClimbConstants.CLIMB_LEFT_MOTOR_PORT);
        climbRightMotor = new Victor(ClimbConstants.CLIMB_RIGHT_MOTOR_PORT);
        climbTensionMotor = new Victor(ClimbConstants.CLIMB_TENSION_MOTOR_PORT);
    }

    public void climbUp() {
        climbLeftMotor.set(1);
        climbRightMotor.set(1);
    }

    public void climbDown() {
        climbLeftMotor.set(-1);
        climbRightMotor.set(-1);
    }

    public void stop() {
        climbLeftMotor.stopMotor();
        climbRightMotor.stopMotor();
    }


}
