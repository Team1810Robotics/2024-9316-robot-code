package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class Shooter extends Command {
    private ShooterSubsystem shooterSubsystem;

    private boolean amp;

    public Shooter(ShooterSubsystem shooterSubsystem, boolean amp) {
        this.shooterSubsystem = shooterSubsystem;
        this.amp = amp;

        addRequirements(shooterSubsystem);
    }

    @Override 
    public void execute() {
        if (amp) {
            shooterSubsystem.shootAmp();
        } else {
            shooterSubsystem.shootSpeaker();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
    }
}
