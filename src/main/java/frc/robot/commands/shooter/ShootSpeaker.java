package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Churro;
import frc.robot.commands.IntakeLift;
import frc.robot.subsystems.ChurroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootSpeaker extends SequentialCommandGroup{
    public ShootSpeaker(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, ChurroSubsystem churroSubsystem, LiftSubsystem liftSubsystem) {

        addCommands(
            // new Churro(churroSubsystem, false),
            // new IntakeLift(liftSubsystem, false),
            new Shoot(intakeSubsystem, shooterSubsystem)
        );
    }
}   
