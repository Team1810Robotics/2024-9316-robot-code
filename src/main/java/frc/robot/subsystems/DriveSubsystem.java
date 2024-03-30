package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
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
    

        drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

        setSparkMAXSettings();





        
        pigeon = new PigeonIMU(DriveConstants.PIGEON);

        odometry = new DifferentialDriveOdometry(getRoations(), getLeftDistance(), getRightDistance());
        kinematics = new DifferentialDriveKinematics(DriveConstants.TRACKWIDTH);
        
        Shuffleboard.getTab("Autonomous").addDouble("Gyro Yaw", () -> pigeon.getYaw());
        Shuffleboard.getTab("Autonomous").addDouble("leftEncoder", () -> frontLeftMotor.getEncoder().getPosition());
        Shuffleboard.getTab("Autonomous").addDouble("rightEncoder", () -> frontRightMotor.getEncoder().getPosition());

        AutoBuilder.configureRamsete(
            this::getPose, 
            this::resetPose, 
            this::getSpeeds, 
            this::autoDrive, 
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

    public Command drive(double leftSpeed, double rightSpeed) {
        return run(() -> drive.tankDrive(leftSpeed, rightSpeed));
    }


    public void teleopDrive(double leftSpeed, double rightSpeed) {
        leftSpeed = MathUtil.applyDeadband(leftSpeed, DriveConstants.DEADBAND);
        rightSpeed = MathUtil.applyDeadband(rightSpeed, DriveConstants.DEADBAND);

        var speeds = DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, true);

        drive.tankDrive(speeds.left, speeds.right);

    }

    public void autoDrive(ChassisSpeeds chassisSpeeds) {
        double forwardSpeed = chassisSpeeds.vxMetersPerSecond;
        double roationSpeed = chassisSpeeds.omegaRadiansPerSecond;

        var speeds = DifferentialDrive.arcadeDriveIK(forwardSpeed, roationSpeed, true);

        drive.arcadeDrive(speeds.left, speeds.right);
    }

    private Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    private DifferentialDriveWheelPositions getWheelPositions() {
        return new DifferentialDriveWheelPositions(getLeftDistance(), getRightDistance());
    }

    private void resetPose(Pose2d pose) {
        odometry.resetPosition(getRoations(), getWheelPositions(), pose);
    }

    //I Know.
    private Rotation2d getRoations() {
        return Rotation2d.fromDegrees(pigeon.getYaw());
    }

    public ChassisSpeeds getSpeeds() {
        var speeds = new DifferentialDriveWheelSpeeds(getLeftDistance(), getRightDistance());

        return kinematics.toChassisSpeeds(speeds);
    }

    public double getLeftDistance() {
        return frontLeftMotor.getEncoder().getPosition();
    }

    public double getRightDistance() {
        return frontRightMotor.getEncoder().getPosition();
    }

    public void zeroGyro() {
        pigeon.setYaw(0);
    }


    public void stop() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
    }

    public void setSparkMAXSettings() {
        backLeftMotor.restoreFactoryDefaults();
        frontLeftMotor.restoreFactoryDefaults();
        backRightMotor.restoreFactoryDefaults();
        frontRightMotor.restoreFactoryDefaults();

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        backLeftMotor.setIdleMode(IdleMode.kCoast);
        frontLeftMotor.setIdleMode(IdleMode.kCoast);
        backRightMotor.setIdleMode(IdleMode.kCoast);
        frontRightMotor.setIdleMode(IdleMode.kCoast);
        
        backLeftMotor.setSmartCurrentLimit(80);
        frontRightMotor.setSmartCurrentLimit(80);
        backRightMotor.setSmartCurrentLimit(80);
        frontRightMotor.setSmartCurrentLimit(80);
        
        frontLeftMotor.setInverted(DriveConstants.LEFT_INVERTED);
        frontRightMotor.setInverted(DriveConstants.RIGHT_INVERTED);
        
        //we do this so that when we call getPosition() it takes the rotations and multiplies it by 8π,
        //which is the circumfrence of our wheels, so that getPosition() returns total distance traveled
        frontLeftMotor.getEncoder().setPositionConversionFactor(8 * Math.PI);
        frontRightMotor.getEncoder().setPositionConversionFactor(8 * Math.PI);

        backLeftMotor.burnFlash();
        frontLeftMotor.burnFlash();
        backRightMotor.burnFlash();
        frontRightMotor.burnFlash();
        try {
            Thread.sleep(750);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void periodic() {
        odometry.update(getRoations(), getWheelPositions());
    }
}
