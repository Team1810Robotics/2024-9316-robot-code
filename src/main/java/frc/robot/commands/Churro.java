package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ChurroSubsystem;

public class Churro extends Command {
    private ChurroSubsystem churroSubsystem;


    //up is true, down is false
    private boolean desiredPosition;

    public Churro(ChurroSubsystem churroSubsystem, boolean desiredPosition) {
        this.churroSubsystem = churroSubsystem;
        this.desiredPosition = desiredPosition;


        addRequirements(churroSubsystem);
    }


    @Override
    public void execute() {
        if (desiredPosition) {
            churroSubsystem.churroUp();
        } else {
            churroSubsystem.churroDown();
        }

    }


    @Override
    public void end(boolean interrupted) {
        churroSubsystem.churroStop();
    }

}
