// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Climb;
import frc.robot.subsystems.ClimbSubsystem;

public class RobotContainer {

  private ClimbSubsystem climbSubsystem = new ClimbSubsystem();

  private CommandXboxController xboxController = new CommandXboxController(OperatorConstants.XBOX_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    xboxController.y().onTrue(new Climb(climbSubsystem, true));
    xboxController.a().onTrue(new Climb(climbSubsystem, false));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
