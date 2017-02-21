package org.usfirst.frc.team4828;

import edu.wpi.first.wpilibj.Victor;

public class Climber {

    private Victor climberMotor1;
    private Victor climberMotor2;
    private static final double RAISE_SPEED = 0.5;
    private static final double LOWER_SPEED = -0.5;

    /**
     * Create climber object encapsulating the climber motor.
     *
     * @param motorPort1 port of the first climber motor
     * @param motorPort2 port of the second climber motor
     */
    public Climber(int motorPort1, int motorPort2){
        climberMotor1 = new Victor(motorPort1);
        climberMotor2 = new Victor(motorPort2);
    }

    /**
     * Raise the robot at a given speed.
     *
     * @param speed double speed
     */
    public void raise(double speed){
        climberMotor1.set(speed);
        climberMotor2.set(speed);
    }

    /**
     * Raise the robot at the default speed.
     */
    public void raise(){
        climberMotor1.set(RAISE_SPEED);
        climberMotor2.set(RAISE_SPEED);
    }

    /**
     * Lower the robot at a given speed.
     *
     * @param speed double speed (positive)
     */
    public void lower(double speed){
        climberMotor1.set(-speed);
        climberMotor2.set(-speed);
    }

    /**
     * Lower the robot at the default speed.
     */
    public void lower(){
        climberMotor1.set(LOWER_SPEED);
        climberMotor2.set(LOWER_SPEED);
    }

    /**
     * Stop raising or lowering the robot.
     */
    public void stop(){
        climberMotor1.set(0);
        climberMotor2.set(0);
    }

}
