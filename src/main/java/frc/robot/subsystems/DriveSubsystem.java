package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


import com.ctre.phoenix.sensors.PigeonIMU;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    


    private CANSparkMax backLeftMotor;
    private CANSparkMax frontLeftMotor;
    private CANSparkMax backRightMotor;
    private CANSparkMax frontRightMotor;

    private PigeonIMU pigeon;
    private DifferentialDriveOdometry odometry;

    private DifferentialDriveKinematics kinematics;
    private DifferentialDrive drive;

    public DriveSubsystem() {
        backLeftMotor = new CANSparkMax(DriveConstants.BACK_LEFT_MOTOR, MotorType.kBrushless);
        frontLeftMotor = new CANSparkMax(DriveConstants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(DriveConstants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
        frontRightMotor = new CANSparkMax(DriveConstants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
    
        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

        frontLeftMotor.setInverted(DriveConstants.LEFT_INVERTED);
        frontLeftMotor.getEncoder().setPositionConversionFactor(8 * Math.PI);

        frontRightMotor.setInverted(DriveConstants.RIGHT_INVERTED);
        frontRightMotor.getEncoder().setPositionConversionFactor(8 * Math.PI);

        pigeon = new PigeonIMU(DriveConstants.PIGEON);

        odometry = new DifferentialDriveOdometry(getRoations(), getLeftDistance(), getRightDistance());

        kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(18.5));

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

        drive.tankDrive(speeds.left, speeds.right);
    }

    public void arcadeDrive(ChassisSpeeds chassisSpeeds) {
        double forwardSpeed = MathUtil.applyDeadband(chassisSpeeds.vxMetersPerSecond, DriveConstants.DEADBAND);
        double roationSpeed = MathUtil.applyDeadband(chassisSpeeds.omegaRadiansPerSecond, DriveConstants.DEADBAND);

        var speeds = DifferentialDrive.arcadeDriveIK(forwardSpeed, roationSpeed, true);

        drive.arcadeDrive(speeds.left, speeds.right);

    }

    private Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    private DifferentialDriveWheelPositions getWheelPositions() {
        DifferentialDriveWheelPositions positions = new DifferentialDriveWheelPositions(getLeftDistance(), getRightDistance());

        return positions;
    }

    private void resetPose(Pose2d pose) {
        odometry.resetPosition(getRoations(), getWheelPositions(), pose);
    }

    private Rotation2d getRoations() {
        return Rotation2d.fromDegrees(pigeon.getYaw());
    }

    public ChassisSpeeds getSpeeds() {
        var speeds = new DifferentialDriveWheelSpeeds(getRightDistance(), getLeftDistance());
        return kinematics.toChassisSpeeds(speeds);
    }

    public double getLeftDistance() {
        return frontLeftMotor.getEncoder().getPosition();
    }

    public double getRightDistance() {
        return frontRightMotor.getEncoder().getPosition();
    }


    public void stop() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
    }

    @Override
    public void periodic() {
        odometry.update(getRoations(), getWheelPositions());
    }
}
