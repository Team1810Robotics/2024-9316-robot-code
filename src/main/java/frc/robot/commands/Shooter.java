package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class Shooter extends Command {
   private ShooterSubsystem shooterSubsystem;
   
   public Shooter(ShooterSubsystem shooterSubsystem){
    this.shooterSubsystem = shooterSubsystem;

    addRequirements(shooterSubsystem);

   }
   @Override
   public void execute() {
       shooterSubsystem.shoot();
   }

   @Override
   public void end(boolean interrupted) {
      shooterSubsystem.shoot(.33);
   }

}
