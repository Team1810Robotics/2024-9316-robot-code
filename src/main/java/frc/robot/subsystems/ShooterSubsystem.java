package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;


public class ShooterSubsystem  extends SubsystemBase {
    private VictorSPX leftShooter;
    private VictorSPX rightShooter;

    // private final SlewRateLimiter limiter;


    public ShooterSubsystem(){
        leftShooter = new VictorSPX(ShooterConstants.LEFT_SHOOTER_MOTOR);
        rightShooter = new VictorSPX(ShooterConstants.RIGHT_SHOOTER_MOTOR);

        // limiter = new SlewRateLimiter(0.5);


    }


    public void shoot(double speed){
        leftShooter.set(VictorSPXControlMode.PercentOutput, -speed);
        rightShooter.set(VictorSPXControlMode.PercentOutput, speed);

    }


    public void stop(){
        leftShooter.set(VictorSPXControlMode.Disabled, 0);
        rightShooter.set(VictorSPXControlMode.Disabled, 0);
    }


}
