package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftSubsystem;


public class IntakeLift extends Command {
    
    private LiftSubsystem pivotSubsystem;

    private boolean isUp;

    private boolean previous;
    private boolean wasDown;

    public IntakeLift(LiftSubsystem pivotSubsystem, boolean isUp) {
        this.pivotSubsystem = pivotSubsystem;
        this.isUp = isUp;

        wasDown = previous;
        
    }

    @Override
    public void execute() {
        if (isUp) {
            pivotSubsystem.pivotUp();
            previous = false;
        } else {
            pivotSubsystem.pivotDown();
            previous = true;
        }
    }

    @Override
    public boolean isFinished() {
        if (isUp && wasDown) {
            return false;
        } else if (isUp && !wasDown) {
            return true;
        } else if (!isUp && wasDown) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void end(boolean interrupted) {
        pivotSubsystem.pivotStop();
    }
}
