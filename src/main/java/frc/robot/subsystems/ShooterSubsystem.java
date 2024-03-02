package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;


public class ShooterSubsystem  extends SubsystemBase {
    private VictorSPX leftShooter;
    private VictorSPX rightShooter;



public ShooterSubsystem(){
    leftShooter = new VictorSPX(ShooterConstants.LEFT_SHOOTER_MOTOR);
    rightShooter = new VictorSPX(ShooterConstants.RIGHT_SHOOTER_MOTOR);

}

public void shoot(){
    leftShooter.set(VictorSPXControlMode.PercentOutput, 1);
    rightShooter.set(VictorSPXControlMode.PercentOutput, -1);

}

public void stop(){
    leftShooter.set(VictorSPXControlMode.Disabled, 0);
    rightShooter.set(VictorSPXControlMode.Disabled, 0);
}


}
