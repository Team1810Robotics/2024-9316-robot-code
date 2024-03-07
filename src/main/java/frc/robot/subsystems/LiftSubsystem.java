package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class LiftSubsystem extends SubsystemBase {

    private VictorSPX liftMotor;

    public boolean previousPosition;

    public LiftSubsystem(){
        liftMotor = new VictorSPX(IntakeConstants.PIVOT_MOTOR);

    }

    public void liftUp(){
        liftMotor.set(VictorSPXControlMode.PercentOutput, -.5);
    }

    public void liftDown(){
        liftMotor.set(VictorSPXControlMode.PercentOutput, .5);
    }
    
    public void liftStop(){
        liftMotor.set(VictorSPXControlMode.Disabled, 0);;
    }
}