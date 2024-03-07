package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;


public class ShooterSubsystem  extends SubsystemBase {
    private VictorSPX leftShooter;
    private VictorSPX rightShooter;

    private double ampShoot = 1;



    public ShooterSubsystem(){
        leftShooter = new VictorSPX(ShooterConstants.LEFT_SHOOTER_MOTOR);
        rightShooter = new VictorSPX(ShooterConstants.RIGHT_SHOOTER_MOTOR);

        Shuffleboard.getTab("shooter").addNumber("leftShooter", () -> leftShooter.getMotorOutputPercent());
        Shuffleboard.getTab("shooter").addNumber("rightShooter", () -> rightShooter.getMotorOutputPercent());
    }

    public void shootSpeaker(){
        leftShooter.set(VictorSPXControlMode.PercentOutput, -1);
        rightShooter.set(VictorSPXControlMode.PercentOutput, 1);

    }

    public void shootAmp() {
        leftShooter.set(VictorSPXControlMode.PercentOutput, -ampShoot);
        rightShooter.set(VictorSPXControlMode.PercentOutput, ampShoot);

    }


    public void stop(){
        leftShooter.set(VictorSPXControlMode.Disabled, 0);
        rightShooter.set(VictorSPXControlMode.Disabled, 0);
    }


}
