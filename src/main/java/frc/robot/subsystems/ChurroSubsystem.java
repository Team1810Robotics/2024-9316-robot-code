package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChurroConstants;

public class ChurroSubsystem extends SubsystemBase {
    
    private VictorSPX rightChurroMotor;
    private VictorSPX leftChurroMotor;


    public ChurroSubsystem() {
       leftChurroMotor = new VictorSPX(ChurroConstants.LEFT_CHURRO_MOTOR);
       rightChurroMotor = new VictorSPX(ChurroConstants.RIGHT_CHURRO_MOTOR);
    }

    public void churroUp() {
        leftChurroMotor.set(VictorSPXControlMode.PercentOutput, .75);
        rightChurroMotor.set(VictorSPXControlMode.PercentOutput, .75);
    }

    public void churroDown() {
        leftChurroMotor.set(VictorSPXControlMode.PercentOutput, -.75);
        rightChurroMotor.set(VictorSPXControlMode.PercentOutput, -.75);
    }

    public void churroStop() {
        leftChurroMotor.set(VictorSPXControlMode.Disabled, 0);
        rightChurroMotor.set(VictorSPXControlMode.Disabled, 0);
    }

}

