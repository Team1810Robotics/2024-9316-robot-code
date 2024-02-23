package frc.robot;

public class Constants {
    public class OperatorConstants {
        public static final int LEFT_JOYSTICK_PORT = 0;
        public static final int RIGHT_JOYSTICK_PORT = 1;
        public static final int XBOX_CONTROLLER_PORT = 2;
    }
    public class DriveConstants {
        public static final int BACK_LEFT_MOTOR = 0;
        public static final int FRONT_LEFT_MOTOR = 1;
        public static final boolean LEFT_INVERTED = true;


        public static final int BACK_RIGHT_MOTOR = 2;
        public static final int FRONT_RIGHT_MOTOR = 3;
        public static final boolean RIGHT_INVERTED = false;

        public static final double DEADBAND = .05;

        
    }

    public class ShooterConstants {
       public static final int LEFT_SHOOTER_MOTOR = 8;
       public static final int RIGHT_SHOOTER_MOTOR = 7;
    }

    public class IntakeConstants {
       public static final int LEFT_INTAKE_MOTORS = 0;
       public static final int RIGHT_INTAKE_MOTORS = 1;
       public static final int VERTICAL_INTAKE_MOTORS = 2;

       public static final int INTERNAL_SENSOR = 2;
       public static final int EXTERNAL_SENSOR = 3;
       public static final int LEFT_VERTICAL_SENSOR = 0;
       public static final int RIGHT_VERTICAL_SENSOR = 1;

        
    }
}