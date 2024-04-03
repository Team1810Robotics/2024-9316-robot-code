package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class Climb extends Command {
  
    private ClimbSubsystem climbSubsystem;
    private ClimbSubsystem.ClimbModes mode;



    /**
     * 
     * @param mode mode for the climber
     */
    public Climb(ClimbSubsystem climbSubsystem, ClimbSubsystem.ClimbModes mode) {
        this.climbSubsystem = climbSubsystem;
        this.mode = mode;

        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        switch (mode) {
            case UP:
               climbSubsystem.climbUp(); 
                break;
            case DOWN:
                climbSubsystem.climbDown();
                break;
            case HOLD:
                climbSubsystem.climbHold();
                break;
        }
    }

    // If the climber is moving down and sensor A gets tripped, it will stop running
    // Or, if the climber is moving up and sensor b gets tripped, it will stop running
    @Override
    public boolean isFinished() {
        if ((climbSubsystem.getclimbSensorA() && mode == ClimbSubsystem.ClimbModes.DOWN) || (climbSubsystem.getclimbSensorB() && mode ==  ClimbSubsystem.ClimbModes.UP)) {
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
