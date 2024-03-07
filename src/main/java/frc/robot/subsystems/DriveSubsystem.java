package frc.robot.subsystems;



import com.ctre.phoenix.sensors.PigeonIMU;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.ReplanningConfig;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    
    private PWMSparkMax backLeftMotor;
    private PWMSparkMax frontLeftMotor;

    private PWMSparkMax backRightMotor;
    private PWMSparkMax frontRightMotor;

    private PigeonIMU pigeon;

    private DifferentialDriveOdometry odometry;




    public DriveSubsystem() {
        backLeftMotor = new PWMSparkMax(DriveConstants.BACK_LEFT_MOTOR);
        frontLeftMotor = new PWMSparkMax(DriveConstants.FRONT_LEFT_MOTOR);


        backRightMotor = new PWMSparkMax(DriveConstants.BACK_RIGHT_MOTOR);
        frontRightMotor = new PWMSparkMax(DriveConstants.FRONT_RIGHT_MOTOR);

        backLeftMotor.addFollower(frontLeftMotor);
        backRightMotor.addFollower(frontRightMotor);

        backLeftMotor.setInverted(DriveConstants.LEFT_INVERTED);
        backRightMotor.setInverted(DriveConstants.RIGHT_INVERTED);

        pigeon = new PigeonIMU(DriveConstants.PIGEON);

        AutoBuilder.configureRamsete(
            this::getPose, 
            this::resetPose, 
            this::getSpeeds, 
            this::arcadeDrive, 
            new ReplanningConfig(), 
            () -> {
                var alliance = DriverStation.getAlliance();
                if (alliance.isPresent()) {
                    return alliance.get() == DriverStation.Alliance.Red;
                }
                return false;
            }, 
            this);


    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftSpeed = MathUtil.applyDeadband(leftSpeed, .02);
        rightSpeed = MathUtil.applyDeadband(rightSpeed, .02);

        var speeds = DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, true);

        backLeftMotor.set(speeds.left);
        backRightMotor.set(speeds.right);


    }

    public void arcadeDrive(ChassisSpeeds speeds) {
        double forwardSpeed = MathUtil.applyDeadband(speeds.vxMetersPerSecond, DriveConstants.DEADBAND);
        double rotationSpeed = MathUtil.applyDeadband(speeds.omegaRadiansPerSecond, DriveConstants.DEADBAND);

        DifferentialDrive.arcadeDriveIK(forwardSpeed, rotationSpeed, true);
    }

    private Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    private DifferentialDriveWheelPositions getWheelPositions() {
        DifferentialDriveWheelPositions positions = new DifferentialDriveWheelPositions(null, null);

        return positions;
    }

    private void resetPose(Pose2d pose) {
        odometry.resetPosition(getRoations(), getWheelPositions(), pose);
    }

    private Rotation2d getRoations() {
        var rotations = new Rotation2d(Math.toRadians(pigeon.getYaw()));

       return rotations;
    }

    public ChassisSpeeds getSpeeds() {
        ChassisSpeeds speeds = new ChassisSpeeds();

        return speeds;
    }

    public void stop() {
        backLeftMotor.stopMotor();
        backRightMotor.stopMotor();
    }

}
