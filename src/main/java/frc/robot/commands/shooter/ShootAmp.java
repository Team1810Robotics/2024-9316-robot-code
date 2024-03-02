package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Churro;
import frc.robot.commands.IntakeLift;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAmp extends SequentialCommandGroup {
    
    public ShootAmp(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, ChurroSubsystem churroSubsystem, LiftSubsystem liftSubsystem) {

        addCommands(
            //TODO: Add timeouts
            new Churro(churroSubsystem, true),
            new IntakeLift(liftSubsystem, false),
            new Shoot(intakeSubsystem, shooterSubsystem)
        );
    }
}
