package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;
public class Climb extends Command {
  
  
    private ClimbSubsystem climbSubsystem;
    private Boolean isUp;

    /**
     * 
     * @param isUp if up, climber goes up
     */
    public Climb(ClimbSubsystem climbSubsystem, Boolean isUp) {
        this.climbSubsystem = climbSubsystem;
        this.isUp = isUp;

        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        if (isUp){
            climbSubsystem.climbUp();
        } else {
            climbSubsystem.climbDown();
        }
    }


    @Override
    public boolean isFinished() {
        if (climbSubsystem.getRotations() >= Math.abs(7) /*not sure if the absolute value is necessary but I could only find a reddit post with the answer I need so I can't tell */) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void end(boolean interrupted) {
        climbSubsystem.stop();
    }
}
