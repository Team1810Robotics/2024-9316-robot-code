package frc.robot.subsystems;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    
    private PWMSparkMax backLeftMotor;
    private PWMSparkMax frontLeftMotor;

    private PWMSparkMax backRightMotor;
    private PWMSparkMax frontRightMotor;




    public DriveSubsystem() {
        backLeftMotor = new PWMSparkMax(DriveConstants.BACK_LEFT_MOTOR);
        frontLeftMotor = new PWMSparkMax(DriveConstants.FRONT_LEFT_MOTOR);


        backRightMotor = new PWMSparkMax(DriveConstants.BACK_RIGHT_MOTOR);
        frontRightMotor = new PWMSparkMax(DriveConstants.FRONT_RIGHT_MOTOR);

        backLeftMotor.addFollower(frontLeftMotor);
        backRightMotor.addFollower(frontRightMotor);

        backLeftMotor.setInverted(DriveConstants.LEFT_INVERTED);
        backRightMotor.setInverted(DriveConstants.RIGHT_INVERTED);



    }

    public void drive(double leftSpeed, double rightSpeed) {
        leftSpeed = MathUtil.applyDeadband(leftSpeed, .02);
        rightSpeed = MathUtil.applyDeadband(rightSpeed, .02);

        var speeds = DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, true);

        backLeftMotor.set(speeds.left);
        backRightMotor.set(speeds.right);


    }

    public void stop() {
        backLeftMotor.stopMotor();
        backRightMotor.stopMotor();
    }

}
