// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.GearShift;
import frc.robot.commands.Intake;
import frc.robot.commands.Shoot;
import frc.robot.commands.TankDrive;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GearShiftSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

  private DriveSubsystem driveSubsystem = new DriveSubsystem();
  private GearShiftSubsystem gearShiftSubsystem = new GearShiftSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  private Joystick leftJoystick = new Joystick(OperatorConstants.LEFT_JOYSTICK_PORT);
  private Joystick rightJoystick = new Joystick(OperatorConstants.RIGHT_JOYSTICK_PORT);

  private JoystickButton leftJoystickButton_11 = new JoystickButton(leftJoystick, 11);

  private CommandXboxController xboxController = new CommandXboxController(OperatorConstants.XBOX_CONTROLLER_PORT);


  private SendableChooser<Command> autoChooser = new SendableChooser<>();


  public RobotContainer() {
    driveSubsystem.setDefaultCommand(
      new TankDrive(
        () -> -leftJoystick.getY(),
        () -> -rightJoystick.getY(),
        driveSubsystem)
    );
    configureBindings();
    setShuffleboard();
  }

  private void configureBindings() {
    xboxController.rightBumper().onTrue(new Shoot(intakeSubsystem, shooterSubsystem).withTimeout(5));
    
    xboxController.x().whileTrue(new Intake(intakeSubsystem, true, true));
    xboxController.b().onTrue(new Intake(intakeSubsystem, false, false));

    leftJoystickButton_11.whileTrue(new GearShift(gearShiftSubsystem, true)).whileFalse(new GearShift(gearShiftSubsystem, false));
    
  }


  public void setShuffleboard() {
    Shuffleboard.getTab("Teleoperated").addBoolean("External Sensor", () -> !intakeSubsystem.getExternalNoteDetector());
    Shuffleboard.getTab("Teleoperated").addBoolean("Internal Sensor", () -> !intakeSubsystem.getInternalNoteDetector());

   autoChooser.setDefaultOption("No Auto", new InstantCommand());

    Shuffleboard.getTab("Auto").add("Auto Chooser", autoChooser);
  }
 


    public Command getAutonomousCommand() {
      return Commands.print("No Auto");
    }


  
}
