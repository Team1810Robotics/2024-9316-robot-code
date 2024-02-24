package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.utility.Shoot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PivotShoot extends SequentialCommandGroup {
    
    public PivotShoot(PivotSubsystem pivotSubsystem, IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem){
        addCommands(
            //TODO: Add a timeout for pivot
            new Pivot(pivotSubsystem, false),
            new Shoot(intakeSubsystem, shooterSubsystem)
        );
    }
}
