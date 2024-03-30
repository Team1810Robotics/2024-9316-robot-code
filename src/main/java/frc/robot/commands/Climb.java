package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class Climb extends Command {
  
    private ClimbSubsystem climbSubsystem;
    private boolean isUp;

    /**
     * 
     * @param isUp if up, climber goes up
     */
    public Climb(ClimbSubsystem climbSubsystem, boolean isUp) {
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

    // If the climber is moving down and sensor A gets tripped, it will stop running
    // Or, if the climber is moving up and sensor b gets tripped, it will stop running
    @Override
    public boolean isFinished() {
        if ((climbSubsystem.getclimbSensorA() && !isUp) || (climbSubsystem.getclimbSensorB() && isUp)) {
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
