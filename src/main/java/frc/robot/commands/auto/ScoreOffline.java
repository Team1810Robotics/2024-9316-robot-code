package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Intake;
import frc.robot.commands.Shooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ScoreOffline extends SequentialCommandGroup {
    private ShooterSubsystem shooterSubsystem;
    private IntakeSubsystem intakeSubsystem;
    
    public ScoreOffline(DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.shooterSubsystem = shooterSubsystem;

        addCommands(
            shoot().withTimeout(3),
            driveSubsystem.drive( -.52, -.5).withTimeout(2.5).alongWith(new Intake(intakeSubsystem, false, false, false))
        );
    }

    private Command shoot() {
    return (new Shooter(1, shooterSubsystem)).withTimeout(1.5)
              .alongWith(new WaitCommand(1)
                .andThen(new Intake(intakeSubsystem, false, true, true)));
  }
}
