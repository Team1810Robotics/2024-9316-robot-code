package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ShootSpeaker;
import frc.robot.commands.utility.ShootDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreOffline extends ParallelCommandGroup {

   
    public ScoreOffline(DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {
        addCommands(
                new ShootSpeaker(shooterSubsystem),
                new ShootDrive(intakeSubsystem, driveSubsystem)
        );
    } 
}
