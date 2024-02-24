package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class PivotSubsystem extends SubsystemBase {

    private Victor pivotMotor;

    public PivotSubsystem(){
        pivotMotor = new Victor(IntakeConstants.PIVOT_MOTOR);

    }

    public void pivotUp(){
        pivotMotor.set(.5);
    }

    public void pivotDown(){
        pivotMotor.set(-.5);
    }
    
    public void pivotStop(){
        pivotMotor.stopMotor();
    }
}