package frc.robot.commands.utility;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Intake;
import frc.robot.commands.Pivot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class PivotIntake extends SequentialCommandGroup {
    
    public PivotIntake(IntakeSubsystem intakeSubsystem, PivotSubsystem pivotSubsystem) {

        addCommands(
            new Intake(intakeSubsystem, false, true),
            //TODO: add timeout
            new Pivot(pivotSubsystem, true)
        );
    }
}
