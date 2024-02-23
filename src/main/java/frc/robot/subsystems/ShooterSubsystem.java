package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem  extends SubsystemBase {
    private Victor leftShooter;
    private Victor rightShooter;



public ShooterSubsystem(){
    leftShooter = new Victor(ShooterConstants.LEFT_SHOOTER_MOTOR);
    rightShooter = new Victor(ShooterConstants.RIGHT_SHOOTER_MOTOR);

}

public void shoot(){
    leftShooter.set(1);
    rightShooter.set(-1);

}

public void stop(){
    leftShooter.stopMotor();
    rightShooter.stopMotor();
}


}
