package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.utility.IntakeShooter;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSpeaker extends ParallelCommandGroup {

    public ShooterSpeaker(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {

        addCommands(
            new ShooterSpeaker(intakeSubsystem, shooterSubsystem),
            new IntakeShooter(intakeSubsystem, shooterSubsystem)
        );
    }

    
}
