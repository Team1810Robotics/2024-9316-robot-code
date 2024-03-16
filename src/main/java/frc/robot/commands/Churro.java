package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChurroSubsystem;

public class Churro extends Command {
    private ChurroSubsystem churroSubsystem;


    //up is true, down is false
    private boolean desiredPosition;
    private boolean previousPosition;

    public Churro(ChurroSubsystem churroSubsystem, boolean desiredPosition) {
        this.churroSubsystem = churroSubsystem;
        this.desiredPosition = desiredPosition;


        addRequirements(churroSubsystem);
    }

    @Override
    public void initialize() {
        previousPosition = churroSubsystem.previousPosition;
    }
    @Override
    public void execute() {
        if (desiredPosition) {
            churroSubsystem.churroUp();
            previousPosition = false;
        } else {
            churroSubsystem.churroDown();
            previousPosition = true;
        }

    }

    @Override
    public boolean isFinished() {
        if (desiredPosition == previousPosition) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        churroSubsystem.churroStop();
    }

}
