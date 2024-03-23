package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.utility.Drive;
import frc.robot.subsystems.DriveSubsystem;

public class Offline extends SequentialCommandGroup {

    public Offline(DriveSubsystem driveSubsystem) {

        addCommands(
            //drive back
            new Drive(driveSubsystem, -.5, -.5, false).withTimeout(3)
        );
    }
    
}
