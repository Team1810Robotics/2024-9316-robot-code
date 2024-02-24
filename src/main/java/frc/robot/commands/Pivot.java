package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSubsystem;


public class Pivot extends Command {
    
    private PivotSubsystem pivotSubsystem;

    private boolean isUp;

    public Pivot(PivotSubsystem pivotSubsystem, boolean isUp) {
        this.pivotSubsystem = pivotSubsystem;
        this.isUp = isUp;
        
    }

    @Override
    public void execute() {
        if (isUp) {
            pivotSubsystem.pivotUp();
        } else {
            pivotSubsystem.pivotDown();
        }
    }
    @Override
    public void end(boolean interrupted) {
        pivotSubsystem.pivotStop();
    }
}
