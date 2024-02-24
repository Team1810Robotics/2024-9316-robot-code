package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase{
    private Victor leftShooterMotor;
    private Victor rightShooterMotor;

    public ShooterSubsystem(){
        leftShooterMotor = new Victor(ShooterConstants.LEFT_SHOOTER_MOTOR);
        rightShooterMotor = new Victor(ShooterConstants.RIGHT_SHOOTER_MOTOR);

    
    }

    public void shoot() {
        leftShooterMotor.set(1);
        rightShooterMotor.set(-1);
    }
    

    public void stop(){
        leftShooterMotor.stopMotor();
        rightShooterMotor.stopMotor();
    }
}   