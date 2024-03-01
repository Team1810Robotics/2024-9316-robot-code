package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.utility.Shoot;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAmp extends SequentialCommandGroup {
    
    public ShootAmp(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, ChurroSubsystem churroSubsystem) {

        addCommands(
            new Churro(churroSubsystem, true),
            new Shoot(intakeSubsystem, shooterSubsystem, churroSubsystem)
        );
    }
}
