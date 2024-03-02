package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftSubsystem;


public class IntakeLift extends Command {
    
    private LiftSubsystem liftSubsystem;

    private boolean isUp;

    private boolean previous;
    private boolean wasDown;

    public IntakeLift(LiftSubsystem liftSubsystem, boolean isUp) {
        this.liftSubsystem = liftSubsystem;
        this.isUp = isUp;

        wasDown = previous;
        
    }

    @Override
    public void execute() {
        if (isUp) {
            liftSubsystem.pivotUp();
            previous = false;
        } else {
            liftSubsystem.pivotDown();
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
        liftSubsystem.pivotStop();
    }
}
