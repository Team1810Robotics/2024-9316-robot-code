package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.utility.IntakeShooter;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterAmp extends ParallelCommandGroup {

    public ShooterAmp(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {

        addCommands(
            new Shooter(shooterSubsystem, true),
            new IntakeShooter(intakeSubsystem, shooterSubsystem)
        );
    }

    
}
