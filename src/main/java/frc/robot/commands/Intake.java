package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    
    private IntakeSubsystem intakeSubsystem;

    private boolean isReversed;
    private boolean ignoreNote;

    /**
     * 
     * @param isReversed sets whether or not the intake is reversed
     * @param ignoreNote allows the intake to run regardless of what the sensors read
     */
    public Intake(IntakeSubsystem intakeSubsystem, boolean isReversed, boolean ignoreNote) {
        this.intakeSubsystem = intakeSubsystem;
        this.ignoreNote = ignoreNote;
        this.isReversed = isReversed;

        addRequirements(intakeSubsystem); 
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
