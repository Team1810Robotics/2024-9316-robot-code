package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    
    private IntakeSubsystem intakeSubsystem;

    private boolean isReversed;
    private boolean ignoreNote;
    private BooleanSupplier externalSensor;

    public Intake(IntakeSubsystem intakeSubsystem, boolean isReversed, boolean ignoreNote, BooleanSupplier externalSensor) {
        this.intakeSubsystem = intakeSubsystem;
        this.ignoreNote = ignoreNote;
        this.isReversed = isReversed;
        this.externalSensor = externalSensor;

        addRequirements(intakeSubsystem); // Add requirements checks other commands to see if they're using the same subsystem
    }

    @Override
    public void execute() {
        
        if (isReversed) {
            intakeSubsystem.reverseIntake();
        } else if (!isReversed && externalSensor.getAsBoolean()){
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
