package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    
    private IntakeSubsystem intakeSubsystem;

    private boolean isReversed;
    private boolean ignoreNote;

    public Intake(IntakeSubsystem intakeSubsystem, boolean isReversed, boolean ignoreNote) {
        this.intakeSubsystem = intakeSubsystem;
        this.ignoreNote = ignoreNote;
        this.isReversed = isReversed;

        addRequirements(intakeSubsystem); // Add requirements checks other commands to see if they're using the same subsystem
    }

    @Override
    public void execute() {
        
        if (isReversed) {
            intakeSubsystem.reverseIntake();
        } else if (ignoreNote) {
            intakeSubsystem.runHorizontalIntake();
        }
    }

    @Override
    public boolean isFinished() {
        boolean isNote = intakeSubsystem.getInternalNoteDetector();
        if (!ignoreNote && !isNote) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }
}
