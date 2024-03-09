package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Intake;
import frc.robot.commands.Shooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeakerOffline extends SequentialCommandGroup{

    private ShooterSubsystem shooterSubsystem;
    private IntakeSubsystem intakeSubsystem;
    
    public SpeakerOffline(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        addCommands(
            shootSpeaker().withTimeout(3),
            new Drive(driveSubsystem, -1, -1).withTimeout(1)
        );
    }

    private Command shootSpeaker() {
        return (new Shooter(1, shooterSubsystem).alongWith(new WaitCommand(1).andThen(new Intake(intakeSubsystem, false, true))));
  }
}
