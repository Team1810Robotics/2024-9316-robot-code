// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.Intake;
import frc.robot.commands.TankDrive;
import frc.robot.commands.auto.ScoreOffline;
import frc.robot.commands.auto.TwoCenter;


import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.Churro;
import frc.robot.commands.Shooter;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LightingSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.GearShiftSubsystem;

public class RobotContainer {
 
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  @SuppressWarnings("unused")
  private final GearShiftSubsystem gearShiftSubsystem = new GearShiftSubsystem();
  private final ChurroSubsystem churroSubsystem = new ChurroSubsystem();
  public final LightingSubsystem lightingSubsystem = new LightingSubsystem();
  
  private CommandJoystick leftJoystick = new CommandJoystick(OperatorConstants.LEFT_JOYSTICK_PORT);
  private CommandJoystick rightJoystick = new CommandJoystick(OperatorConstants.RIGHT_JOYSTICK_PORT);

  private CommandXboxController xboxController = new CommandXboxController(OperatorConstants.XBOX_CONTROLLER_PORT);


  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  public ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleoperated");
  public ShuffleboardTab autoTab = Shuffleboard.getTab("Autonomous");



  public RobotContainer() {
    driveSubsystem.setDefaultCommand(
      new TankDrive(
        () -> -leftJoystick.getY(), 
        () -> -rightJoystick.getY(), 
        driveSubsystem
        )
    );

    intakeSubsystem.setDefaultCommand(new Intake(intakeSubsystem, false, false, false));

    configureBindings();
    setElastic();

  }

  private void configureBindings() {
    
    xboxController.rightBumper().onTrue(shoot().withTimeout(2.4));
    

    xboxController.x()
      .whileTrue(new Intake(intakeSubsystem, true, true, true))
      .whileTrue(new Shooter(-1.0, shooterSubsystem));
    xboxController.b().whileTrue(new Intake(intakeSubsystem, false, false, false));

    xboxController.rightTrigger().whileTrue(new Churro(churroSubsystem, true));
    xboxController.leftTrigger().whileTrue(new Churro(churroSubsystem, false));



    // leftJoystick.button(11).whileTrue(new GearShift(gearShiftSubsystem, true))
    //                       .whileFalse(new GearShift(gearShiftSubsystem, false));


  }


  public void setElastic() {
    teleopTab.addBoolean("External Sensor", () -> !intakeSubsystem.getExternalNoteDetector());
    teleopTab.addBoolean("Internal Sensor", () -> !intakeSubsystem.getInternalNoteDetector());
    teleopTab.addBoolean("Left IR", () -> !intakeSubsystem.getLeftVerticalIntakeSensor());
    teleopTab.addBoolean("Right IR", () -> !intakeSubsystem.getRightVerticalIntakeSensor());


    teleopTab.addDouble("Match Time", () -> DriverStation.getMatchTime());
    teleopTab.add(CommandScheduler.getInstance());

    autoChooser.setDefaultOption("No Auto", new InstantCommand());
    autoChooser.addOption("One Note, no offline", shoot());
    autoChooser.addOption("Offline", offline());
    autoChooser.addOption("Two Note", new TwoCenter(driveSubsystem, shooterSubsystem, intakeSubsystem));
    autoChooser.addOption("One Note, Offline", new ScoreOffline(driveSubsystem, intakeSubsystem, shooterSubsystem));
    autoTab.add("Auto Chooser", autoChooser);
  }


  private Command shoot() {
    return (new Shooter(1, shooterSubsystem)).withTimeout(2)
              .alongWith(new WaitCommand(1.5)
                .andThen(new Intake(intakeSubsystem, false, true, true)));
  }

  private Command offline() {
    return driveSubsystem.drive(-.5, -.5).withTimeout(2);
  }

  @SuppressWarnings("unused")
  private Command delayedOffline() {
    return new WaitCommand(10).andThen(driveSubsystem.drive(-.5, -.5).withTimeout(3));
  }

  @SuppressWarnings("unused")
  private Command scoreDelayedOffline() {
    return shoot().andThen(new WaitCommand(7)).andThen(driveSubsystem.drive(-.5, -.5).withTimeout(2));
  }

  // public void setNamedCommands() {
  //   NamedCommands.registerCommand("Shoot", shoot());
  //   NamedCommands.registerCommand("Intake", new Intake(intakeSubsystem, false, false, false));
  // }

 
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
