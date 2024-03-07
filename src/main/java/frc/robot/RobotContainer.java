// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import frc.robot.commands.TankDrive;


import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.commands.Churro;
import frc.robot.commands.GearShift;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeLift;
import frc.robot.commands.shooter.Shoot;
import frc.robot.commands.shooter.ShootSpeaker;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.GearShiftSubsystem;


import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class RobotContainer {

  private DriveSubsystem driveSubsystem = new DriveSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private GearShiftSubsystem gearShiftSubsystem = new GearShiftSubsystem();
  private LiftSubsystem liftSubsystem = new LiftSubsystem();
  private ChurroSubsystem churroSubsystem = new ChurroSubsystem();
  
  private CommandJoystick leftJoystick = new CommandJoystick(OperatorConstants.LEFT_JOYSTICK_PORT);
  private CommandJoystick rightJoystick = new CommandJoystick(OperatorConstants.RIGHT_JOYSTICK_PORT);

  private CommandXboxController xboxController = new CommandXboxController(OperatorConstants.XBOX_CONTROLLER_PORT);

  private SendableChooser<Command> autoChooser;

  private ShuffleboardTab teleopTab = Shuffleboard.getTab("Teleoperated");
  private ShuffleboardTab autoTab = Shuffleboard.getTab("Autonomous");


  public RobotContainer() {
    driveSubsystem.setDefaultCommand(
      new TankDrive(
        () -> leftJoystick.getY(), 
        () -> rightJoystick.getY(), 
        driveSubsystem
        )
    );


    // intakeSubsystem.setDefaultCommand(new Intake(intakeSubsystem, false, false));
    configureBindings();
    setElastic();

    autoChooser = AutoBuilder.buildAutoChooser();
  }

  private void configureBindings() {
    // xboxController.rightBumper().onTrue(new ShootSpeaker(shooterSubsystem, intakeSubsystem, churroSubsystem).withTimeout(7));
    // xboxController.leftBumper().onTrue(new ShootAmp(shooterSubsystem, intakeSubsystem, churroSubsystem).withTimeout(7));
    
    // xboxController.x().whileTrue(new Intake(intakeSubsystem, true, true));
    // xboxController.b().onTrue(new Intake(intakeSubsystem, false, false));

    // leftJoystickButton_11.whileTrue(new GearShift(gearShiftSubsystem, true))
    //                       .whileFalse(new GearShift(gearShiftSubsystem, false));
  }


  public void setElastic() {
    teleopTab.addBoolean("External Sensor", () -> !intakeSubsystem.getExternalNoteDetector());
    teleopTab.addBoolean("Internal Sensor", () -> !intakeSubsystem.getInternalNoteDetector());

    autoTab.add("Auto Chooser", autoChooser);
  }

  public void setNamedCommands() {
    NamedCommands.registerCommand("Shoot Speaker", new ShootSpeaker(shooterSubsystem, intakeSubsystem, churroSubsystem, liftSubsystem));
    NamedCommands.registerCommand("Intake", new Intake(intakeSubsystem, false, false));
  }
 
  public Command getAutonomousCommand() {
    return Commands.print("nothin");
  }







 


}
