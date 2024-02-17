// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.TankDrive;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;



public class RobotContainer {
  private DriveSubsystem driveSubsystem = new DriveSubsystem();

  private Joystick leftJoystick = new Joystick(OperatorConstants.LEFT_JOYSTICK_PORT);
  private Joystick rightJoystick = new Joystick(OperatorConstants.RIGHT_JOYSTICK_PORT);

  


  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {
    driveSubsystem.setDefaultCommand(
      new TankDrive(
        () -> -leftJoystick.getY(),
        () -> -rightJoystick.getY(),
        driveSubsystem)
    );
    setShuffleboard();
    configureBindings();
  }


  private void configureBindings() {}
   
  public void setShuffleboard() {
   // Shuffleboard.getTab("Gen").addBoolean("Note Detector", () -> intakeSubsystem.getNoteDetector());
    
    autoChooser.setDefaultOption("No Auto", new InstantCommand());
   // autoChooser.addOption("offline", new Offline(driveSubsystem));
   // autoChooser.addOption("scoreOffline", new ScoreOffline(driveSubsystem, intakeSubsystem, shooterSubsystem));
   // autoChooser.addOption("scoreOfflineScore", new ScoreOfflineScore(shooterSubsystem, intakeSubsystem, driveSubsystem));
  //  autoChooser.addOption("score", new Score(shooterSubsystem, intakeSubsystem));
    Shuffleboard.getTab("Auto").add("Auto Chooser", autoChooser);
    
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
