package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    
    private CANSparkMax backLeftMotor;
    private CANSparkMax frontLeftMotor;
    private CANSparkMax backRightMotor;
    private CANSparkMax frontRightMotor;

    public DriveSubsystem() {
        backLeftMotor = new CANSparkMax(DriveConstants.BACK_LEFT_MOTOR, MotorType.kBrushless);
        frontLeftMotor = new CANSparkMax(DriveConstants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(DriveConstants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
        frontRightMotor = new CANSparkMax(DriveConstants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);



        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        frontLeftMotor.setInverted(DriveConstants.LEFT_INVERTED);
        frontRightMotor.setInverted(DriveConstants.RIGHT_INVERTED);

        backLeftMotor.setInverted(DriveConstants.LEFT_INVERTED);
        backRightMotor.setInverted(DriveConstants.RIGHT_INVERTED);

    }

    public void drive(double leftSpeed, double rightSpeed) {
        leftSpeed = MathUtil.applyDeadband(leftSpeed, DriveConstants.DEADBAND);
        rightSpeed  =MathUtil.applyDeadband(rightSpeed, DriveConstants.DEADBAND);

        var speeds = DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, true);

        frontRightMotor.set(speeds.right);
        frontLeftMotor.set(speeds.left);
    }

    public void stop() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
    }

}
