package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChurroSubsystem;

public class Churro extends Command {
    private ChurroSubsystem churroSubsystem;

    private boolean isUp;

    private boolean isDown;

    private boolean previous;

    public Churro(ChurroSubsystem churroSubsystem, boolean isUp) {
        this.churroSubsystem = churroSubsystem;
        this.isUp = isUp;

        previous = isDown;

        addRequirements(churroSubsystem);
    }

    @Override
    public void execute() {
        if (isUp) {
            churroSubsystem.churroUp();
            isDown = false;
        } else {
            churroSubsystem.churroDown();
            isDown = true;
        }

    }

    @Override
    public boolean isFinished() {
        if (previous && !isUp) {
            return true;
        } else if (!previous && !isUp){
            return false;
        } else if (previous && isUp) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        churroSubsystem.churroStop();
    }

}
