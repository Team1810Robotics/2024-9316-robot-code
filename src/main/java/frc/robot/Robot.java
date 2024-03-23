// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  // PhotonCamera camera = new PhotonCamera("photonvision");

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_robotContainer.lightingSubsystem.lightsOff();

    // CameraServer.startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
     m_robotContainer.lightingSubsystem.lightsOff();
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    m_robotContainer.lightingSubsystem.lightsRainbow();
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  @Override
  public void teleopPeriodic() {
    if (!m_robotContainer.intakeSubsystem.getInternalNoteDetector()) {
      m_robotContainer.lightingSubsystem.lightsGreen();
    } else if (!m_robotContainer.intakeSubsystem.getExternalNoteDetector() || !m_robotContainer.intakeSubsystem.getLeftVerticalIntakeSensor() || !m_robotContainer.intakeSubsystem.getRightVerticalIntakeSensor()){
      m_robotContainer.lightingSubsystem.lightsPurple();
    } else {
      m_robotContainer.lightingSubsystem.allianceLights();
    }

  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
