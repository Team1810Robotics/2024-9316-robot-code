package frc.robot.comands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;
public class Climb extends Command {
  
  
    private ClimbSubsystem climbSubsystem;
    private Boolean isUp;

    
    public Climb(ClimbSubsystem climbSubsystem, Boolean isUp) {
        this.climbSubsystem = climbSubsystem;
        this.isUp = isUp;

        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        if(isUp == true){
            climbSubsystem.climbUp();
        } else {
            climbSubsystem.climbDown();
        }
    }


    @Override
    public void end(boolean interrupted) {
        climbSubsystem.stop();
    }
}