package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    
    private IntakeSubsystem intakeSubsystem;

    private boolean isReversed;
    private boolean ignoreNoteExt;
    private boolean ignoreNoteInt;

    /**
     * 
     * @param isReversed sets whether or not the intake is reversed
     * @param ignoreNoteInt allows the intake to run regardless of what the intenral sensor reads
     * @param ignoreNoteExt runs intake regardless of what external sensor reads
     */
    public Intake(IntakeSubsystem intakeSubsystem, boolean isReversed, boolean ignoreNoteExt, boolean ignoreNoteInt) {
        this.intakeSubsystem = intakeSubsystem;
        this.ignoreNoteExt = ignoreNoteExt;
        this.ignoreNoteInt = ignoreNoteInt;
        this.isReversed = isReversed;

        addRequirements(intakeSubsystem); 
    }

    @Override
    public void execute() {
        
        if (isReversed) {
            intakeSubsystem.reverseIntake();
        } else if (ignoreNoteExt) {
            intakeSubsystem.runHorizontalIntake();
        }
    }

    @Override
    public boolean isFinished() {
        boolean isNote = intakeSubsystem.getInternalNoteDetector();
        if (!ignoreNoteInt && !isNote) {
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
