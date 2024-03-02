package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.utility.Shoot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PivotShoot extends SequentialCommandGroup {
    
    public PivotShoot(LiftSubsystem pivotSubsystem, IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem){
        addCommands(
            //TODO: Add a timeout for pivot
            new IntakeLift(pivotSubsystem, false),
            new Shoot(intakeSubsystem, shooterSubsystem)
        );
    }
}
