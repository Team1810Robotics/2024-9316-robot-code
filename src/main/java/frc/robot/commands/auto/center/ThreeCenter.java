package frc.robot.commands.auto.center;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Intake;
import frc.robot.commands.Shooter;
import frc.robot.commands.auto.Drive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ThreeCenter extends SequentialCommandGroup{

    private ShooterSubsystem shooterSubsystem;
    private IntakeSubsystem intakeSubsystem;
    
    public ThreeCenter(DriveSubsystem driveSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        addCommands(
            shoot().withTimeout(3),
            new Drive(driveSubsystem, -.52, -.5).withTimeout(2.5).alongWith(new Intake(intakeSubsystem, false, false)),
            
            new Drive(driveSubsystem, .51, 0.5).withTimeout(2.9),
            shoot().withTimeout(3),

            new Drive(driveSubsystem, -.25, -.5).withTimeout(2.5),
            new Drive(driveSubsystem, .25, .5).withTimeout(2.7),
            shoot().withTimeout(3)
        );
    }

    private Command shoot() {
        return (new Shooter(1, shooterSubsystem)).withTimeout(1.5)
                  .alongWith(new WaitCommand(1)
                    .andThen(new Intake(intakeSubsystem, false, true)));
      }
}
