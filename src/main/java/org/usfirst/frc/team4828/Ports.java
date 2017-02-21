package org.usfirst.frc.team4828;

public class Ports {
    // PWM
    public static final int INTAKE = 9;
    public static final int CLIMBER_1 = 8;
    public static final int CLIMBER_2 = 7;
    public static final int AGITATOR = 6;

    // Drivetrain
    public static final int DT_FRONT_LEFT = 1;
    public static final int DT_BACK_LEFT = 2;
    public static final int DT_FRONT_RIGHT = 3;
    public static final int DT_BACK_RIGHT = 4;

    // Shooter
    public static final int SERVO_LEFT_MASTER = 3;
    public static final int SERVO_LEFT_SLAVE = 2;
    public static final int SERVO_RIGHT_MASTER = 0;
    public static final int SERVO_RIGHT_SLAVE = 1;
    public static final int MOTOR_LEFT = 5;
    public static final int MOTOR_RIGHT = 6;
    public static final int INDEXER_RIGHT = 10; // use port 0 on the navx as port 10
    public static final int INDEXER_LEFT = 11;  // use port 1 on the navx as port 11

    // Sensors
    public static final int IR_CHANNEL = 2;
    public static final int US_CHANNEL = 0; // Analog

    // Switch
    public static final int DIPSWITCH_1 = 6;
    public static final int DIPSWITCH_2 = 4;
    public static final int DIPSWITCH_3 = 5;
    public static final int DIPSWITCH_4 = 3;

    public static final int ACTIVE_GEAR_LEFT = 12;
    public static final int ACTIVE_GEAR_RIGHT = 13;

    private Ports(){
        throw new AssertionError(
                "A Summoner has Disconnected.\n"
                        + "(Assertion Error 'cause you instantiated class Ports.\n"
                        + "What part of private don't you understand, Nikhil...\n"
                        + "Sigh.)\n"
        );
    }
}
