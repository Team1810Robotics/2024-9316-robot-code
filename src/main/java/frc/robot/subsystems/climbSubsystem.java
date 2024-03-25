package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;


public class ClimbSubsystem extends SubsystemBase{

    //Not sure on the whole state of the motors, the wiring sheet and design disagree
    private VictorSPX climbLeftMotor;
    private VictorSPX climbRightMotor;
    private VictorSPX climbTensionMotor;

    private Encoder climbEncoder;

    public ClimbSubsystem() {
        climbLeftMotor = new VictorSPX(ClimbConstants.CLIMB_LEFT_MOTOR);
        climbRightMotor = new VictorSPX(ClimbConstants.CLIMB_RIGHT_MOTOR);
        climbTensionMotor = new VictorSPX(ClimbConstants.CLIMB_TENSION_MOTOR);

        climbEncoder = new Encoder(ClimbConstants.CLIMB_ENCODER_A, ClimbConstants.CLIMB_ENCODER_B);    
        
    }


    public void climbUp() {
        climbLeftMotor.set(VictorSPXControlMode.PercentOutput, 1);
        climbRightMotor.set(VictorSPXControlMode.PercentOutput, -1);
    }

    public void climbDown() {
        climbLeftMotor.set(VictorSPXControlMode.PercentOutput, -1);
        climbRightMotor.set(VictorSPXControlMode.PercentOutput, 1);
    }

    public double getRotations() {
        //don't know if this will work how I think it will, I think what this will do (based off the docs) this will return a distance of 1 per 2048 pules (i think one rotation??????)
        climbEncoder.setDistancePerPulse(1/2048);

        SmartDashboard.putNumber("Rotations", climbEncoder.getDistance());

        return climbEncoder.getDistance();
    }



    public void stop() {
        climbLeftMotor.set(VictorSPXControlMode.Disabled, 0);;
        climbRightMotor.set(VictorSPXControlMode.Disabled, 0);;
    }

    
    

}
