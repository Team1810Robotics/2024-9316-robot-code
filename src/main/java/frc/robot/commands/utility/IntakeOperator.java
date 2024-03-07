package frc.robot.commands.utility;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeLift;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;

public class IntakeOperator extends SequentialCommandGroup {
    
    public IntakeOperator(IntakeSubsystem intakeSubsystem, LiftSubsystem liftSubsystem) {

        addCommands(
            new Intake(intakeSubsystem, false, true),
            //TODO: add timeout
            new IntakeLift(liftSubsystem, true)
        );
    }
}
