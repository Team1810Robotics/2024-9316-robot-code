package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChurroSubsystem;

public class Churro extends Command {
    private ChurroSubsystem churroSubsystem;

    private boolean isUp;

    private boolean wasDown;

    public Churro(ChurroSubsystem churroSubsystem, boolean isUp) {
        this.churroSubsystem = churroSubsystem;
        this.isUp = isUp;

        addRequirements(churroSubsystem);
    }

    @Override
    public void execute() {
        if (isUp) {
            churroSubsystem.churroUp();
            wasDown = false;
        } else {
            churroSubsystem.churroDown();
            wasDown = true;
        }

    }

    @Override
    public boolean isFinished() {
        if (isUp && wasDown) {
            return false;
        } else if (isUp && !wasDown) {
            return true;
        } else if (!isUp && wasDown) {
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
