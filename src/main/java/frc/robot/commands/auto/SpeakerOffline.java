package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Churro;
import frc.robot.commands.Intake;
import frc.robot.commands.Shooter;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeakerOffline extends SequentialCommandGroup{

    private ShooterSubsystem shooterSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ChurroSubsystem churroSubsystem;
    
    public SpeakerOffline(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, ChurroSubsystem churroSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.churroSubsystem = churroSubsystem;

        addCommands(
            shootSpeaker().withTimeout(1.5),
            new Drive(driveSubsystem, -.5, -.5).withTimeout(2),
            
            new Drive(driveSubsystem, .5, 0.5).withTimeout(2.4),
            shootSpeaker().withTimeout(1.5)
        );
    }

    private Command shootSpeaker() {
        return new Churro(churroSubsystem, false)
                    .andThen(new Shooter(1, shooterSubsystem))
                    .alongWith(new WaitCommand(1)).andThen(new Intake(intakeSubsystem, false, true, () -> !intakeSubsystem.getInternalNoteDetector()));
      }
}
