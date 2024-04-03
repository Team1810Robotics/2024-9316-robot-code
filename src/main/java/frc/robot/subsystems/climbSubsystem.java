package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;


public class ClimbSubsystem extends SubsystemBase{

    private VictorSPX climbMotor;
    private DigitalInput climbSensorA;
    private DigitalInput climbSensorB;

    public enum ClimbModes {
        UP,
        DOWN,
        HOLD
    }


    public ClimbSubsystem() {
        climbMotor = new VictorSPX(ClimbConstants.CLIMB_MOTOR);
        climbSensorA = new DigitalInput(ClimbConstants.CLIMB_SENSOR_A);
        climbSensorB = new DigitalInput(ClimbConstants.CLIMB_SENSOR_B);
        
        Shuffleboard.getTab("climb").addBoolean("sensor A", () -> getclimbSensorA());
        Shuffleboard.getTab("climb").addBoolean("sensor B", () -> getclimbSensorB());
    }


    public void climbUp() {
        climbMotor.set(VictorSPXControlMode.PercentOutput, .3);
    }

    public void climbDown() {
        climbMotor.set(VictorSPXControlMode.PercentOutput, -.3);
    }

    //needs a bit of tuning
    public void climbHold() {
        climbMotor.set(VictorSPXControlMode.PercentOutput, -.25);
    }

    public boolean getclimbSensorA() {
        return climbSensorA.get();
    }
     public boolean getclimbSensorB() {
        return climbSensorB.get();
    }


    public void stop() {
        climbMotor.set(VictorSPXControlMode.Disabled, 0);
    }

    
    



}
