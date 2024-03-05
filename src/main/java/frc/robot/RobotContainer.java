// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.Churro;
import frc.robot.commands.GearShift;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeLift;
import frc.robot.commands.TankDrive;
import frc.robot.commands.shooter.Shoot;
import frc.robot.commands.shooter.ShootAmp;
import frc.robot.commands.shooter.ShootSpeaker;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.GearShiftSubsystem;


public class RobotContainer {

  private DriveSubsystem driveSubsystem = new DriveSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private GearShiftSubsystem gearShiftSubsystem = new GearShiftSubsystem();
  private LiftSubsystem liftSubsystem = new LiftSubsystem();
  private ChurroSubsystem churroSubsystem = new ChurroSubsystem();
  
  private Joystick leftJoystick = new Joystick(OperatorConstants.LEFT_JOYSTICK_PORT);
  private Joystick rightJoystick = new Joystick(OperatorConstants.RIGHT_JOYSTICK_PORT);

  private JoystickButton leftJoystickButton_11 = new JoystickButton(leftJoystick, 11);
  private CommandXboxController xboxController = new CommandXboxController(OperatorConstants.XBOX_CONTROLLER_PORT);

  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  private ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleoperated");
  private ShuffleboardTab autoTab = Shuffleboard.getTab("Autonomous");

  private GenericEntry liftUp = teleopTab.add("Manual Lift", false).getEntry();
  private GenericEntry churroUp = teleopTab.add("Manual Churro", false).getEntry();



  public RobotContainer() {
    driveSubsystem.setDefaultCommand(
      new TankDrive(
        () -> -leftJoystick.getY(),
        () -> -rightJoystick.getY(),
        driveSubsystem)
    );
    // intakeSubsystem.setDefaultCommand(new IntakeOperator(intakeSubsystem, liftSubsystem));
    configureBindings();

    setShuffleboard();
  }

  private void configureBindings() {

    xboxController.rightBumper().onTrue(new Shoot(intakeSubsystem, shooterSubsystem).withTimeout(5));
    xboxController.leftBumper().onTrue(new Shoot(intakeSubsystem, shooterSubsystem).withTimeout(5));
    
    xboxController.x().whileTrue(new Intake(intakeSubsystem, true, true));
    xboxController.b().onTrue(new Intake(intakeSubsystem, false, false));


    leftJoystickButton_11.whileTrue(new GearShift(gearShiftSubsystem, true))
                          .whileFalse(new GearShift(gearShiftSubsystem, false));

    if (liftUp.getBoolean(false)) {
      xboxController.a().whileTrue(new IntakeLift(liftSubsystem, false));
      xboxController.y().whileTrue(new IntakeLift(liftSubsystem, true));
    }

    if (churroUp.getBoolean(false)) {
      xboxController.a().whileTrue(new Churro(churroSubsystem, false));
      xboxController.y().whileTrue(new Churro(churroSubsystem, true));
    }
  }


  public void setShuffleboard() {

    teleopTab.addBoolean("External Sensor", () -> !intakeSubsystem.getExternalNoteDetector());
    teleopTab.addBoolean("Internal Sensor", () -> !intakeSubsystem.getInternalNoteDetector());



    autoChooser.setDefaultOption("No Auto", new InstantCommand());
    autoTab.add("Auto Chooser", autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
 


}
