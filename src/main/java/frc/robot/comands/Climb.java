package frc.robot.comands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;
public class Climb extends Command {
  
  
    private ClimbSubsystem climbSubsystem;
    private String upDown;

    
    public Climb(ClimbSubsystem climbSubsystem, String upDown) {
        this.climbSubsystem = climbSubsystem;
        this.upDown = upDown;

        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        if(this.upDown == "Up"){
            climbSubsystem.climbUp();
        }
        else{
            climbSubsystem.climbDown();
    
        }
    }


    @Override
    public void end(boolean interrupted) {
        climbSubsystem.stop();
    }
}
