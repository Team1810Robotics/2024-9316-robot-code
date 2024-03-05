package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftSubsystem;


public class IntakeLift extends Command {
    
    private LiftSubsystem liftSubsystem;

    private boolean desiredPosition;
    private boolean previousPosition;

    public IntakeLift(LiftSubsystem liftSubsystem, boolean desiredPosition) {
        this.desiredPosition = desiredPosition;
        this.liftSubsystem = liftSubsystem;        

    }

    /*We store the previous position variable in lift subsystem so it stays constant and 
    doesn't get refreshed upon initialozing the command, 
    then set a new (local) previousPosition variable equal to whatever it was */ 
    @Override
    public void initialize() {
        previousPosition = liftSubsystem.previousPosition;
    }

    @Override
    public void execute() {
        if (desiredPosition) {
            liftSubsystem.liftUp();
            liftSubsystem.previousPosition = true;
        } else {
            liftSubsystem.liftDown();
            liftSubsystem.previousPosition = false;
        }
    }

    @Override
    public boolean isFinished() {
        if (previousPosition && desiredPosition) {
            return true;
        } else if (!previousPosition && !desiredPosition) {
            return true;
        } else if (previousPosition && !desiredPosition) {
            return false;
        } else {
            return false;
        } 
    }
    @Override
    public void end(boolean interrupted) {
        liftSubsystem.liftStop();
    }
}
