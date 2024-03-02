package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class LiftSubsystem extends SubsystemBase {

    private Victor liftMotor;

    public LiftSubsystem(){
        liftMotor = new Victor(IntakeConstants.PIVOT_MOTOR);

    }

    public void pivotUp(){
        liftMotor.set(.5);
    }

    public void pivotDown(){
        liftMotor.set(-.5);
    }
    
    public void pivotStop(){
        liftMotor.stopMotor();
    }
}