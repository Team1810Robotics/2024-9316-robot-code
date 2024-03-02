package frc.robot.commands.utility;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeLift;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;

public class LiftIntake extends SequentialCommandGroup {
    
    public LiftIntake(IntakeSubsystem intakeSubsystem, LiftSubsystem pivotSubsystem) {

        addCommands(
            new Intake(intakeSubsystem, false, true),
            //TODO: add timeout
            new IntakeLift(pivotSubsystem, true)
        );
    }
}
